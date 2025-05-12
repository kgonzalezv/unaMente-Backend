package com.unamenteblog.user_service.controller;

import com.unamenteblog.user_service.domain.DataCreateUserGoogle;
import com.unamenteblog.user_service.domain.DataResponseUser;
import com.unamenteblog.user_service.domain.LoginRequest;
import com.unamenteblog.user_service.domain.UserMSJ;
import com.unamenteblog.user_service.model.User;
import com.unamenteblog.user_service.service.GoogleOAuthService;
import com.unamenteblog.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("${variable.ruta}/users")
public class UserController {

    private final UserService userService;
    private final GoogleOAuthService googleOAuthService;

    public UserController(UserService userService, GoogleOAuthService googleOAuthService) {
        this.userService = userService;
        this.googleOAuthService = googleOAuthService;
    }

    @PostMapping
    public ResponseEntity<DataCreateUserGoogle> createBasicUser(@RequestBody DataCreateUserGoogle dataCreateUser) {
        User user = userService.save(new User(dataCreateUser));
        return ResponseEntity.ok(null);
    }

    @PostMapping("/google-login")
    public ResponseEntity<?> googleLoginOrRegister(@RequestBody Map<String, String> body) {
        String token = body.get("token");

        Map<String, Object> userInfo = googleOAuthService.verifyAccessToken(token);
        User user = userService.findUserByGoogleId((String) userInfo.get("sub")).orElse(null);

        if (!userInfo.isEmpty()) {
            if (Objects.nonNull(user)) {
                var userResponse = new LoginRequest(user.getName(), user.getEmail(), user.getBio(),
                        UserMSJ.USER_VERIFY.name(), user.getPicture());
                return ResponseEntity.ok(userResponse);
            } else {
                var dataCreateUser = new DataCreateUserGoogle((String) userInfo.get("name"), (String) userInfo.get("email"), null, "Google", (String) userInfo.get("sub"), (String) userInfo.get("picture"));
                User newUser = new User(dataCreateUser);
                userService.save(newUser);
                var userResponse = new LoginRequest(newUser.getName(), newUser.getEmail(),
                        newUser.getBio(), UserMSJ.REGISTERED_USER.name(), newUser.getPicture());
                return ResponseEntity.ok(userResponse);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseUser> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id).orElse(null);
        if (Objects.nonNull(user)) {
            var dataResponse = new DataResponseUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getBio(), String.valueOf(user.getCreateAt()));
            return ResponseEntity.ok(dataResponse);
        }
        return ResponseEntity.notFound().build();
    }

}
