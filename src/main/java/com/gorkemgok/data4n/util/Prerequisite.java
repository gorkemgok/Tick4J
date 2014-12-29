package com.gorkemgok.data4n.util;

/**
 * Created by gorkemgok on 26/12/14.
 */
public class Prerequisite {
    public static void equals(Object obj1, Object obj2, String message) throws PrerequisiteException {
        if (!obj1.equals(obj2)){
            throw new PrerequisiteException(message);
        }
    }
}
