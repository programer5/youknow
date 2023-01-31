package com.project.youknow.common;

import com.project.youknow.member.enumType.MemberStatus;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberStatusConverter extends CodeValueConverter<MemberStatus> {
    public MemberStatusConverter() {
        super(MemberStatus.class);
    }
}
