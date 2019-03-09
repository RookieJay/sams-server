package pers.zjc.sams.utils;

public class ConvertUtils {

    public static String byte2FitMemorySize(final long byteSize) {
        if (byteSize < 0) {
            return "shouldn't be less than zero!";
        } else if (byteSize < Const.KB) {
            return String.format("%.2f B", (double) byteSize);
        } else if (byteSize < Const.MB) {
            return String.format("%.2f KB", (double) byteSize / Const.KB);
        } else if (byteSize < Const.GB) {
            return String.format("%.2f MB", (double) byteSize / Const.MB);
        } else {
            return String.format("%.2f GB", (double) byteSize / Const.GB);
        }
    }
}
