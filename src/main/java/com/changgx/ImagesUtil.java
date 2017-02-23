package com.changgx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

/**
 * 长歌行
 * changgx2014@163.com
 * 2017/2/22 15:26
 */
public class ImagesUtil {
    public static int index=0;
    public static void main(String[] args) {

        try {
//            cutImages("E:\\comic\\scandal_of_the_witch","E:\\");
            cutImages("E:\\comic\\scandal_of_the_witch","E:\\comic\\scandal_of_the_witch_cut\\");
//            cleanFiles("E:\\comic\\scandal_of_the_witch_cut\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void cutImages(String srcPath, String destPath) throws Exception {
        File file=new File(srcPath);
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File tmp=files[i];
//            System.out.println(tmp.getAbsolutePath());
            String path=tmp.getAbsolutePath();
            java.util.List<Integer> list=ReadColor.getImagePixel(path);
            for (int j = 0; j < list.size(); j++) {
                if(j%2==0){
                    System.out.println(path);
                    int start=list.get(j);
                    int end=list.get(j+1);
                    cutImage(tmp.getAbsolutePath(),destPath+""+index+".jpg",start,end-start);
                    index++;

                }

            }
        }
        cleanFiles(destPath);
    }
    public static void cutImage(String srcPath, String destPath, int y, int height) throws Exception {
        Image img;
        ImageFilter imf;
        BufferedImage bimg;
        int x=0;
        BufferedImage bi = ImageIO.read(new File(srcPath));//读取图像源
        //开始切割
        int width=bi.getWidth();
        Image image = bi.getScaledInstance(width, bi.getHeight(), Image.SCALE_DEFAULT);
        imf = new CropImageFilter(x, y, width, height);
        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imf));
        //创建图片
        bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gi = bimg.getGraphics();
        //0,0 坐标
        gi.drawImage(img, 0, 0, null);
        gi.dispose();
        File file = new File(destPath);
        ImageIO.write(bimg, "JPEG", file);
    }
    public static void cleanFiles(String path){
        File file=new File(path);
        File[] files=file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].length()<1024*10){
                files[i].delete();
            }
        }
    }

}
