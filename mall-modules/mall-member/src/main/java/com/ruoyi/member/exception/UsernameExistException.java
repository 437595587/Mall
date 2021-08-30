package com.ruoyi.member.exception;

public class UsernameExistException extends RuntimeException {

    public UsernameExistException() {
        super("手机号存在");
    }
}
