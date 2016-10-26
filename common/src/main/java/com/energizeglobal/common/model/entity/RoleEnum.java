package com.energizeglobal.common.model.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum RoleEnum {
    ADMIN(1),
    USER(2);


    private int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RoleEnum getById(int id) {
        for (RoleEnum enumConst : RoleEnum.class.getEnumConstants()) {
            if (enumConst.getId() == id) {
                return enumConst;
            }
        }
        throw new IllegalArgumentException("Unknown role id " + id);
    }

    @Converter
    public static class RoleConverter implements AttributeConverter<RoleEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(RoleEnum attribute) {
            return attribute.getId();
        }

        @Override
        public RoleEnum convertToEntityAttribute(Integer dbData) {
            return RoleEnum.getById(dbData);
        }

    }
    }
