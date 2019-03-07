package pers.zjc.sams.utils;

import java.text.SimpleDateFormat;

public interface Const {

    long TOKEN_EXPIRES_HOUR = 2 * 60 * 60;
    String AUTHORIZATION = "token";
    String CURRENT_USER_ID = "current_user_id";

    public static final int BYTE = 1;
    public static final int KB   = 1024;
    public static final int MB   = 1048576;
    public static final int GB   = 1073741824;

    interface HttpStatusCode {

        /**
         * 成功
         */
        String HttpStatus_200 = "200";
        /**
         * 未通过身份认证
         */
        String HttpStatus_401 = "401";
        /**
         * 没有访问对应资源的权限
         */
        String HttpStatus_403 = "403";
        /**
         * 未找到资源
         */
        String HttpStatus_404 = "404";
        /**
         * 服务端错误
         */
        String HttpStatus_500 = "500";
    }

    interface DateFormat {

        SimpleDateFormat WITH_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat WITHOUT_HMS = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat WITHOUT_HMS_00 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        SimpleDateFormat HHMM = new SimpleDateFormat("HH:mm");
        SimpleDateFormat HMM = new SimpleDateFormat("H:mm");
        SimpleDateFormat MMDDHHmm = new SimpleDateFormat("MM-dd HH:mm");
        SimpleDateFormat CN_M_D = new SimpleDateFormat("M月d日");
        SimpleDateFormat CN_MM_DD = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat CN_MD_H_m = new SimpleDateFormat("M月d日 H时m分");
        SimpleDateFormat CN_WITHOUT_HMS = new SimpleDateFormat("yyyy年MM月dd日");
    }

    interface DEFAULT_ID {
        int DEFAULT_STU_ID = 10010001;

        int DEFAULT_TEAC_ID = 20010001;
    }

    interface INTERVAL {
        int TODAY = 1;
        int WEEK = 2;
        int MONTH = 3;
    }
}
