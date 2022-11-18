package com.library_management.constant;

import java.util.Base64;

public class Constant {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String PUBLIC_KEY = new String(Base64.getDecoder().decode(System.getenv("PUBLIC_KEY")));

    public static final String PREFIX_USER_ID = "USER_ID";

    public static final String PREFIX_LOG = "[DEMO_APP] ======> ";

    public static final String ROLE_PREFIX = "ROLE_";
    public static final long JWT_TOKEN_VALIDITY = 2 * 24 * 60 * 60; // 2 days

}
