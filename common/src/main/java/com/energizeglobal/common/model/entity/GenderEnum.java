package com.energizeglobal.common.model.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by test on 7/26/2016.
 */
public enum GenderEnum {
    MALE(1),
    FEMALE(2);

    private int id;

    GenderEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GenderEnum getById(int id) {
        for (GenderEnum enumConst : GenderEnum.class.getEnumConstants()) {
            if (enumConst.getId() == id) {
                return enumConst;
            }
        }
        throw new IllegalArgumentException("Unknown Gender id " + id);
    }

    @Converter
    public static class GenderConverter implements AttributeConverter<GenderEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(GenderEnum attribute) {
            return attribute.getId();
        }

        @Override
        public GenderEnum convertToEntityAttribute(Integer dbData) {
            return GenderEnum.getById(dbData);
        }

    }
}
