package com.project.youknow.api.member.enumType;

import com.project.youknow.common.codevalue.CodeValueConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberConverter extends CodeValueConverter<MemberStatus> {
    MemberConverter() {
        super(MemberStatus.class);
    }
}
