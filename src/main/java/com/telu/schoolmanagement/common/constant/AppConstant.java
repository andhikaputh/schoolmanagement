package com.telu.schoolmanagement.common.constant;

import com.telu.schoolmanagement.common.appconfig.AppConfig;

public class AppConstant {
    // add constants here
    public static int REDIS_TTL_IN_SECOND = Integer.parseInt(AppConfig.dotenv.get("REDIS_TTL_IN_SECOND"));

    public static final String REDIS_GET_ALL_PROGRAM_LIST = "programList::all";
    public static String REDIS_GETALL_FACULTY = "facultyList::all";
    public static String REDIS_GET_ALL_COURSE = "courseList::all";
    public static String REDIS_GETCOURSE = "course::";
    public static String REDIS_GET_ALL_COURSEREGISTRATION = "courseRegistrationList::all";
    public static String REDIS_GETCOURSEREGISTRATION = "courseRegistration::";
    public static String REDIS_GETALL_ROLES = "rolesList::all";
    public static String REDIS_GETALL_LECTURE = "lectureList::all";
    public static String REDIS_GETALL_STUDENT = "studentList::all";
    public static String REDIS_GETALL_USER = "userList::all";
}
