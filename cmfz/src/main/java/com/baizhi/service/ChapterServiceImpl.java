package com.baizhi.service;

import com.baizhi.Util.FormatFileSize;
import com.baizhi.Util.Mp3Util;
import com.baizhi.aspect.LogAnnotation;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @LogAnnotation(name = "批量插入专辑")
    public void insert(MultipartFile[] files, Integer albumID, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/");
        String realPath = path + "upload";
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }
        Chapter chapter = new Chapter();
        for (int i = 0; i < files.length; i++) {
            String s = UUID.randomUUID().toString();
            String originalFilename = files[i].getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            String newName = s + "." + extension;
            try {
                files[i].transferTo(new File(realPath + "/" + newName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String i1 = UUID.randomUUID().toString();//为了避免和专辑的id重复,让easyUI treeGrid正常展示
            chapter.setId(i1);//id赋值
            String baseName = FilenameUtils.getBaseName(originalFilename);
            chapter.setName(baseName);//文件原始名字赋值
            long size = files[i].getSize();
            String newSize = FormatFileSize.FormetFileSize(size);
            chapter.setSize(newSize);//文件大小赋值
            String duration = Mp3Util.getDuration(new File(realPath + "/" + newName));
            chapter.setDuration(duration);//音频时长赋值
            chapter.setAudioPath(newName);//音频路径赋值
            chapter.setAlbum_id(albumID);//父id赋值
            chapterDao.insert(chapter);
        }

    }
}
