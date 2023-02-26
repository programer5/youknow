package com.project.youknow.api.member.enumType;

import com.project.youknow.common.codevalue.CodeValueConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberRoleConverter extends CodeValueConverter<MemberRole> {
    MemberRoleConverter() {
        super(MemberRole.class);
    }
}
