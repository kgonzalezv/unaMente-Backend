package com.unamenteblog.user_service.domain;

public enum UserMSJ {

    USER_VERIFY("USUARIO VERIFICADO"),
    USER_NOT_VERIFY("USUARIO NO VERIFICADO"),
    REGISTERED_USER("USUARIO REGISTRADO");
    private String msj;

    UserMSJ(String msj) {
        this.msj = msj;
    }
}
