package pers.zjc.sams.utils;

import java.text.SimpleDateFormat;

public interface Const {

    long TOKEN_EXPIRES_HOUR = 2 * 60 * 60;
    String AUTHORIZATION = "token";
    String CURRENT_USER_ID = "current_user_id";

    interface HttpStatusCode {

        /**
         * 成功
         */
        int HttpStatus_200 = 200;
        /**
         * 未通过身份认证
         */
        int HttpStatus_401 = 401;
        /**
         * 没有访问对应资源的权限
         */
        int HttpStatus_403 = 403;
        /**
         * 未找到资源
         */
        int HttpStatus_404 = 404;
        /**
         * 服务端错误
         */
        int HttpStatus_500 = 500;
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
