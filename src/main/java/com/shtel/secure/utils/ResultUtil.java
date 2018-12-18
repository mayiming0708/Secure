package com.shtel.secure.utils;

import com.shtel.secure.platform.enumType.model.EnumType;

public class ResultUtil {
    public static String Result(EnumType enumType) {
        return "{\"code\":\"" + enumType.getKey() + "\",\"message\":\"" + enumType.getValue() + "\"}";
    }
}
