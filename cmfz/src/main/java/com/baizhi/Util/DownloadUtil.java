package com.baizhi.Util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2018/9/1 0001.
 * 文件默认从upload文件夹里面下载,请确保下载的文件在upload文件夹里面
 * 下载的新名字包含后缀名例如 雄霸天下.MP4
 * 需要导入commons io包
 * 提供 给用户保存的文件名 数据库保存的文件名
 */
public class DownloadUtil {
    public static void download(String saveName, String dbFileName, HttpServletRequest request, HttpServletResponse response) {
        //获得文件的下载路径
        String path = request.getServletContext().getRealPath("/upload");
        String realPath = path + "/" + dbFileName;//路径获得完成
        String extension = FilenameUtils.getExtension(dbFileName);
        String saveName1 = saveName + "." + extension;
        String okName = null;
        try {
            okName = new String(saveName1.getBytes("utf-8"), "ISO-8859-1");//防止文件名输出时中文乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //为需要下载的文件设置用户能看懂的新名字
        response.setHeader("content-disposition", "attachment;filename=" + okName);//设置响应头信息
        try {
            IOUtils.copy(new FileInputStream(realPath), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
