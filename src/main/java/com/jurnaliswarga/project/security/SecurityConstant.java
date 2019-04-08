package com.jurnaliswarga.project.security;

public class SecurityConstant{
    public static final String SIGN_UP_URL = "/users/**";
    public static final String SECRET = "SecretKey";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30_0000;
}
