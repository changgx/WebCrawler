package com.kunteng.crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Util {

    public static void stringToFile(String str,String path) throws IOException {
        File file = new File(path); // 相对路径，如果没有则要建立一个新的output。txt文件
        if (!file.isFile()){
            file.createNewFile(); // 创建新文件
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
        out.write(str+"\r\n"); // \r\n即为换行
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件
    }

    public static void main(String[] args) {
        try {
            stringToFile("123","F://123.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}