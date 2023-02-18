package com.project.youknow.member.enumType;

import com.project.youknow.common.codevalue.CodeValue;

public enum MemberRole implements CodeValue {
    ADMIN("ADMIN", "관리자"),
    MEMBER("MEMBER", "일반회원");

    private String code;
    private String value;

    MemberRole(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
