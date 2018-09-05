package com.baizhi.Util;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
public class FormatFileSize {
    public static String FormetFileSize(long file) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (file < 1024) {
            fileSizeString = df.format((double) file) + "B";
        } else if (file < 1048576) {
            fileSizeString = df.format((double) file / 1024) + "K";
        } else if (file < 1073741824) {
            fileSizeString = df.format((double) file / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) file / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
