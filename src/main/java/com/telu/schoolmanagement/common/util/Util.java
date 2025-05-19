package com.telu.schoolmanagement.common.util;

public class Util {
    public static String setSlugFromName(String name) {
        if (name != null) {
            return name.trim().toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("^-|-$", "");
        } else {
            return null;
        }
    }
}
