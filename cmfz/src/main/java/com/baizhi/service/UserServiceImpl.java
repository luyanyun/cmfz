package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/9/3 0003.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void insert(MultipartFile file) {
        try {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());//利用传入的文件得到输入流
            final Sheet sheet = workbook.getSheet("Sheet1");//拿到有数据的第一张表
            List<User> list = new ArrayList<User>();
            for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {//用表对象获得表内有多少行
                Row row = sheet.getRow(i);//拿到想要的第i行数据
                //由于表内的字段都是已知直接取
                int guru_id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String dharmaName = row.getCell(2).getStringCellValue();
                String sex = row.getCell(3).getStringCellValue();
                String province = row.getCell(4).getStringCellValue();
                String city = row.getCell(5).getStringCellValue();
                String sign = row.getCell(6).getStringCellValue();
                String phoneNum = row.getCell(7).getStringCellValue();
                String password = (int) row.getCell(8).getNumericCellValue() + "";
                String salt = UUID.randomUUID().toString();
                User user = new User(guru_id, null, null, name, dharmaName, sex, province, city, sign, phoneNum, password, salt, 1, new Date());
                list.add(user);
            }
            userDao.insert(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public void export(HttpServletResponse response) {//注意生成表格时 使用对应的create 方法
        List<User> users = userDao.queryAll();
        Workbook workbook = new HSSFWorkbook();
        Sheet user = workbook.createSheet("User");
        Row row = user.createRow(0);
        //注意导出时 区别:需要首先完成第一行 也就是分类栏的填充
        String[] str = {"上师编号", "姓名", "法名", "性别", "省份", "城市", "签名", "手机号", "密码"};
        for (int i = 0; i < str.length; i++) {
            row.createCell(i).setCellValue(str[i]);
        }
        for (int i = 0; i < users.size(); i++) {
            Row row1 = user.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getGuru_id());
            row1.createCell(1).setCellValue(users.get(i).getName());
            row1.createCell(2).setCellValue(users.get(i).getDharmaName());
            row1.createCell(3).setCellValue(users.get(i).getSex());
            row1.createCell(4).setCellValue(users.get(i).getProvince());
            row1.createCell(5).setCellValue(users.get(i).getCity());
            row1.createCell(6).setCellValue(users.get(i).getSign());
            row1.createCell(7).setCellValue(Integer.parseInt(users.get(i).getPhoneNum()));
            row1.createCell(8).setCellValue(Integer.parseInt(users.get(i).getPassword()));
        }
        //让下载的文件随时间生成名字
        String a = new Date().getTime() + ".xls";
        String newName = null;
        try {
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<User> queryAll(Integer page, Integer rows) {
        int newPage = (page - 1) * rows;
        List<User> users = userDao.queryAll1(newPage, rows);
        return users;
    }


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Integer queryCout() {
        Integer cout = userDao.cout();
        return cout;
    }

    @Override
    public void exportFree(HttpServletResponse response, String titls, String params) {
        Workbook workbook = new HSSFWorkbook();
        Sheet user = workbook.createSheet("User");
        Row row = user.createRow(0);
        //接下来拆分标题行,完成标题行得赋值
        String[] split = titls.split(",");
        for (int i = 0; i < split.length; i++) {
            row.createCell(i).setCellValue(split[i]);
        }
        String[] split1 = params.split(",");
        List<User> users = userDao.queryAll();
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);//拿到每一个user对象
            Class<? extends User> aClass = user1.getClass();//拿到每一个对象的类对象
            Row row1 = user.createRow(i + 1);
            for (int j = 0; j < split1.length; j++) {
                //由于用户选择了那些参数我们不知道 用反射来做
                String methodName = "get" + split1[j].substring(0, 1).toUpperCase() + split1[j].substring(1);
                Method method = null;
                try {
                    method = aClass.getMethod(methodName, null);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    Object invoke = method.invoke(user1, null);
                    if (invoke instanceof String) {
                        user.setColumnWidth(j, 21 * 256);
                        row1.createCell(j).setCellValue((String) invoke);
                    } else {
                        row1.createCell(j).setCellValue((Integer) invoke);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        String a = new Date().getTime() + ".xls";
        String newName = null;
        try {
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Integer> queryDate() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list = Arrays.asList(7, 14, 21, 28, 35, 42);
        for (int i = 0; i < list.size(); i++) {
            Integer integer = userDao.queryDate(list.get(i));
            list1.add(integer);
        }
        return list1;
    }

    @Override
    public Map<String, Object> queryCentry() {
        List<UserDTO> man = userDao.queryCentry("男");
        List<UserDTO> women = userDao.queryCentry("女");

        Map<String, Object> map = new HashMap<>();
        map.put("man", man);
        map.put("women", women);
        return map;
    }

}
