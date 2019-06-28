package com.yh.utils;

/**
 * @Title: AppInfo.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午10:07:08
 * @version 1.0
 */
public class AppInfo {
    public static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
    public static final String JDBC_URL = "jdbc:Oracle:thin:@localhost:1521:orcl";
    public static final String JDBC_USERNAME = "SCOTT";
    public static final String JDBC_PASSWORD = "tiger";
    
    
    
    
    // Login
    public static final String LOGIN_TITLE = "用户登录";
    public static final String LOGIN = "登录";
    public static final String EXIT = "退出";
    public static final String LOGIN_USERNAME = "用户名：";
    public static final String LOGIN_PASSWORD = "密    码：";
    public static final String LOGIN_ERROR = "用户名或密码错误";
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
    public static final String REGEX_USERNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{4,16}$";

    // Student
    public static final String STUDENT_TITLE = "学生选课";
    public static final String STUDENT_SELECT = "选课";
    public static final String STUDENT_DROP = "退课";
    public static final String STUDENT_CLOSE = "关闭";
    public static final String STUDENT_INPUT = "请输入课程号";

    public static final String STUDENT_INFO = "学生详细信息";
    public static final String STUDENT_COURSE = "可选课程";
    public static final String STUDENT_SCORE = "已修课程成绩";
    public static final String STUDENT_SELECTED = "已选课程";

    public static final String SNO = "学号";
    public static final String SNAME = "姓名";
    public static final String SEX = "性别";
    public static final String AGE = "年龄";
    public static final String SDEPT = "所在院系";
    public static final String USERNAME = "用户名";
    public static final String PASSWORD = "密码";

    public static final String CNO = "课程ID";
    public static final String CNAME = "课程名";
    public static final String CREDIT = "学分";
    public static final String GRADE = "学分";
    public static final String HOUR = "学时";
    public static final String TERM = "学期";
    public static final String MAX = "最大人数";
    public static final String CDEPT = "开课院系";
    public static final String TNAME = "任课老师";

    public static final String SCORE = "成绩";

    public static final String ERROR = "错误";
    public static final String CNO_NULL_ERROR = "请输入课程号";
    public static final String CNO_NOT_EXIST_ERROR = "课程号不存在！";
    public static final String CNO_SELECTED_ERROR = "此课程已选！";
    public static final String CNO_NOT_SELECTED_ERROR = "此课程未选！";
    public static final String CNO_GRADED_ERROR = "此课程已登分！";

    public static final String VERIFY = "确认";
    public static final String DELETE = "删除";
    public static final String TOTAL_COUNT = "课程总数：";

    // Admin
    public static final String ADMIN_TITLE = "选课管理";
    public static final String ADMIN_QUERY = "查询";
    public static final String ADMIN_INPUT = "登分";
    public static final String ADMIN_SAVE = "保存";
    public static final String ADMIN_CLOSE = "关闭";
    public static final String ADMIN_CHOOSE = "请选择课程名：";
    public static final String ADMIN_SELECTIONINFO = "选修此课程的学生：";

    public static final String ADMIN_CNAME = "课程：";
    public static final String ADMIN_TNAME = "任课教师：";

    public static final String ADMIN_MAINTAIN = "维护";
    public static final String ADMIN_COURSEINFO = "课程信息";
    public static final String ADMIN_STUDENTINFO = "学生信息";

    public static final String ADMIN_COURSEINFO_ADD = "添加";
    public static final String ADMIN_COURSEINFO_DEL = "删除";
    public static final String ADMIN_COURSEINFO_MOD = "修改";
    public static final String ADMIN_COURSEINFO_QUIT = "退出";
    public static final String ADMIN_CNO_EXIST_ERROR = "不能添加，此课程号已存在！";
    public static final String ADMIN_CNO_NOTEXIST_ERROR = "不能删除，此课程号不存在！";
    public static final String ADMIN_COURSESELECTED_ERROR = "不能删除，此课程已有学生选！";

    public static final String ADMIN_SUTDENTINFO = "学生信息";
    public static final String ADMIN_SUTDENTINFO_ADD = "添加学生";
    public static final String ADMIN_SUTDENTINFO_MOD= "修改学生";
    public static final String ADMIN_SUTDENTINFO_DEL = "删除学生";
    public static final String ADMIN_SNO_EXIST_ERROR = "不能添加，此学号已存在！";
    public static final String ADMIN_USER_EXIST_ERROR = "不能研究，此用戶已存在！";
    public static final String ADMIN_SNO_NOTEXIST_ERROR = "不能删除，此学号不存在！";
    public static final String ADMIN_SELECTEDCOURSE_ERROR = "不能刪除，此学生已选课！";

    public static final String REGEX_SNO = "^[a-zA-Z0-9]{1,4}$";
    public static final String REGEX_SNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,8}$";
    public static final String REGEX_SEX = "^(男|女)?$";
    public static final String REGEX_AGE = "^(\\d)*";
    public static final String REGEX_SDEPT = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,10}$";

    public static final String REGEX_CNO = "^[a-zA-Z0-9]{1,4}$";
    public static final String REGEX_CNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,8}$";
    public static final String REGEX_CREDIT = "^(\\d)*";
    public static final String REGEX_CDEPT = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,10}$";
    public static final String REGEX_TNAME = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]{0,8}$";

}
