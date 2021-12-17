package com.example.demo.constant;

import java.util.Base64;

public class Constant {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String PUBLIC_KEY = new String(Base64.getDecoder().decode(System.getenv("PUBLIC_KEY")));

    public static final String PREFIX_USER_ID = "USER_ID";

    public static final String PREFIX_LOG = "[DEMO_APP] ======> ";

}
