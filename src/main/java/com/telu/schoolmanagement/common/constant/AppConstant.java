package com.telu.schoolmanagement.common.constant;

import com.telu.schoolmanagement.common.appconfig.AppConfig;

public class AppConstant {
    // add constants here
    public static int REDIS_TTL_IN_SECOND = Integer.parseInt(AppConfig.dotenv.get("REDIS_TTL_IN_SECOND"));

    public static final String REDIS_GET_ALL_PROGRAM_LIST = "programList::all";
    public static String REDIS_GETALL_FACULTY = "facultyList::all";

    public static String REDIS_GETALL_COURSE = "courseList::all";
    public static String REDIS_GETCOURSE_BY_ID = "course::";
}
