package com.baizhi.Util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2018/9/1 0001.
 * 使用说明:需要导入commons-io包
 * 传入的multipartFile 实参必须和页面input name="~~" 值一致,工具类默认创建upload文件夹
 */
public class UploadUtil {
    public static String upload(MultipartFile multipartFile, HttpServletRequest request) {
        //设置保存路径
        //设置唯一文件名
        String path = request.getServletContext().getRealPath("/");//获得当前项目路径
        String realPath = path + "upload";//预先设置好upload文件夹的路径
        File file = new File(realPath);
        if (!file.exists()) {//判断是否存在这个文件夹,不存在创建
            file.mkdir();
        }
        String s = UUID.randomUUID().toString();//利用uuid工具生成一个随机文件名保证唯一性.
        String originalFilename = multipartFile.getOriginalFilename();//获得原始上传名包括后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        String newName = s + "." + extension;//拼接后获得新的名字.
        try {
            multipartFile.transferTo(new File(realPath + "/" + newName));//上传
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newName;
    }
}
