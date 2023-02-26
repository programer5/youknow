package com.project.youknow.api.member.enumType;

import com.project.youknow.common.codevalue.CodeValue;

import javax.persistence.Converter;

public enum MemberStatus implements CodeValue {
    ACTIVATE("ACTIVATE", "활성화"),
    DORMANCY("DORMANCY", "휴면"),
    WITHDRAW("WITHDRAW", "탈퇴");

    private String code;
    private String value;

    MemberStatus(String code, String value) {
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
