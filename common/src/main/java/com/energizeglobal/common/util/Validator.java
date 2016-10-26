package com.energizeglobal.common.util;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
public class Validator {

    public static <T> boolean isEmpty(T object) {
        if (object instanceof String) {
            return object == null || ((String) object).trim().length() == 0;
        }
        if (object instanceof List) {
            return object == null || ((List) object).size() == 0;
        }
        return object == null;
    }
}
