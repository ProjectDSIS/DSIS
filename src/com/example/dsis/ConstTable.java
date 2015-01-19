package com.example.dsis;

/**
 * Created by jaehunPC on 2014-12-31.
 */
public class ConstTable {
    public static class BOARD{
        public static final int NOTICE = 1;
        public static final int BACHELOR_NOTICE = 2;
        public static final int RESTAURANT = 3;
        public static final int FREE_BOARD = 4;
        public static final int NOT_BOARD = 5;
    }

    public static class HEX_COLOR{
        public static final String RED ="#FFCBCB";
        public static final String ORANGE = "#FFD3B0";
        public static final String BLUE = "#D6F0FF";
        public static final String YELLOW = "#FFFFA1";
        public static final String PULPLE = "#F5D6FF";
        public static final String GREEN = "#BCFFB5";
        public static final String SKY_BLUE = "#D6FFFF";
        public static final String LIGHT_PULPLE = "E8D9FF";
        public static final String GRAY = "#D8D8D8";
        public static final String PINK = "#FFD6FF";
    }
    
    public static class ID{
    	public static final String ID = "txtStudentCd";
    	public static final String PW = "txtPasswd";
    	public static final String VIEW_STATE = "__VIEWSTATE";
    	public static final String LOGIN_BTN_X = "ibtnLogin.x";
    	public static final String LOGIN_BTN_Y = "ibtnLogin.y";
    }
    
    public static class VALUE{
    	public static final String VIEW_STATE_VAL = "dDwxNjcyNzcxNDU2O3Q8O2w8aTwwPjs+O2w8dDw7bDxpPDExPjtpPDEzPjs+O2w8dDxwPHA8bDxOYXZpZ2F0ZVVybDs+O2w8amF2YXNjcmlwdDp3aW5PcGVuKCk7Pj47Pjs7Pjt0PHA8O3A8bDxvbmNsaWNrOz47bDxqYXZhc2NyaXB0OmZybUxvZ2luLnJlc2V0KClcO3JldHVybiBmYWxzZVw7Oz4+Pjs7Pjs+Pjs+PjtsPGlidG5Mb2dpbjtJbWFnZUJ1dHRvbjE7Pj5ImIpoeshh3TEd2zfLXcmtEp7woA==";
    	public static final String LOG_IN_BTN_VAL = "0";
    }
    
    public static class URL{
    	public static final String LOG_IN = "http://student.donga.ac.kr/Login.aspx";
    	public static final String TIME_TABLE = "";
    	public static final String STUDENT_INFO = "https://student.donga.ac.kr/Univ/SUD/SSUD0000.aspx?m=1";
    }
}
