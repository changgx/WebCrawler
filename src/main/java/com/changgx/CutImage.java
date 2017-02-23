package com.changgx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;


/**
 * 以200*200格式切割图片
 *
 * @author Administrator
 * @date 2011-04-28
 */
public class CutImage {

    public static final int srcWidth = 293; //原图的宽度
    public static final int srcHeight = 190;//原图的高度
    public static final int questionX = 117; //验证问题的X起始坐标
    public static final int questionY = 0;//验证问题的y起始坐标
    public static final int questionWidth = 130; //验证问题所占的宽度
    public static final int questionHeight = 30;//验证问题所占的高度
    public static final int codeWidth = 70;//验证码所占的高度
    public static final int codeHeight = 80;//验证码所占的高度
    public static final int whiteWidth = 5;
    public static int width = 70;//切割的宽度
    public static int height = 80;//切割的高度

    public static void main(String[] args) throws Exception {
        //new CutImage().cutImageAction("1.jpg","F://1.jpg",70,100,"F:","//tmp");
        // getQuestion("F://1.jpg","F://tmp//q.jpg");
        getCode("F://1.jpg", "F://tmp");
    }


    /**
     * 获取验证问题
     *
     * @param srcPath
     * @param destPath
     * @throws Exception
     */
    public static void getQuestion(String srcPath, String destPath,int x,int y,int width,int height) throws Exception {
        Image img;
        ImageFilter imf;
        BufferedImage bimg;
        BufferedImage bi = ImageIO.read(new File(srcPath));//读取图像源
        //开始切割
        Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
        imf = new CropImageFilter(questionX, questionY, questionWidth, questionHeight);
        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imf));
        //创建图片
        bimg = new BufferedImage(questionWidth, questionHeight, BufferedImage.TYPE_INT_RGB);
        Graphics gi = bimg.getGraphics();
        //0,0 坐标
        gi.drawImage(img, 0, 0, null);
        gi.dispose();
        File file = new File(destPath);
        ImageIO.write(bimg, "JPEG", file);
    }

    public static void getQuestion2(String srcPath, String destPath) throws Exception {
        Image img;
        ImageFilter imf;
        BufferedImage bimg;
        BufferedImage bi = ImageIO.read(new File(srcPath));//读取图像源
        //开始切割
        Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
        imf = new CropImageFilter(0, 30, 70, 80);
        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imf));
        //创建图片
        bimg = new BufferedImage(70, 80, BufferedImage.TYPE_INT_RGB);
        Graphics gi = bimg.getGraphics();
        //0,0 坐标
        gi.drawImage(img, 0, 0, null);
        gi.dispose();
        File file = new File(destPath);
        ImageIO.write(bimg, "JPEG", file);
    }

    public static void getCode(String srcPath, String destPath) throws Exception {
        Image img;
        ImageFilter imf;
        BufferedImage bi = ImageIO.read(new File(srcPath));//读取图像源
        if (bi == null) {
            System.out.println("图像源为空");
            return;
        }
        BufferedImage bimg;
        Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                //开始切割
                imf = new CropImageFilter((codeWidth + whiteWidth) * j, (codeHeight * i) + questionHeight, codeWidth, codeHeight);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imf));
                //创建图片
                bimg = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
                Graphics gi = bimg.getGraphics();
                //0,0 坐标
                gi.drawImage(img, 0, 0, null);
                gi.dispose();
                File file = new File(destPath + "//" + i + "_" + j + ".jpg");
                ImageIO.write(bimg, "JPEG", file);
            }
        }

    }

    /**
     * 切割图片方法
     *
     * @param fileName 图片名称
     * @param paths    图片路径
     * @param widths   图片切割需要的宽度
     * @param heights  图片切割需要的高度
     * @param dir      存放的盘符
     * @param fold     存放切割图片的文件夹
     * @throws Exception
     */
    public void cutImageAction(String fileName, String paths, int widths, int heights, String dir, String fold) throws Exception {
        Image img;
        ImageFilter imf;
        BufferedImage bi = ImageIO.read(new File(paths));//读取图像源
        if (bi == null) {
            System.out.println("图像源为空");
            return;
        }
        int baseWidth = bi.getWidth();//读取图像源的宽度
        int baseHeight = bi.getHeight();//读取图像源的高度
        width = widths;
        height = heights;

        if (baseWidth > width && baseHeight > height) {
            int rows = 0;//切割的行数
            int cols = 0;//切割的列数
            int row_yu = 0, col_yu = 0;
            Image image = bi.getScaledInstance(baseWidth, baseHeight, Image.SCALE_DEFAULT);
            BufferedImage bimg;
            File file;

            if (baseWidth % width == 0) {
                cols = baseWidth / width;
            } else {
                cols = (int) Math.floor(baseWidth / width) + 1;
                col_yu = baseWidth % width;
            }

            if (baseHeight % height == 0) {
                rows = baseHeight / height;
            } else {
                rows = (int) Math.floor(baseHeight / height) + 1;
                row_yu = baseHeight % height;
            }

            System.err.println("切割的行数=[" + rows + "]切割的行数余数=[" + row_yu + "]----切割的列数=[" + cols + "]切割的列数余数=[" + col_yu + "]");

            StringBuffer sb = new StringBuffer();
            StringBuffer temp1;
            int index1 = 0, index2 = 0, index = 0;
            int x = 0, y = 0;

            //开始对图像源进行切割
            for (int i = 0; i < rows; i++) {
                y = i * height;
                if (rows - index1 == 1) {
                    height = row_yu == 0 ? heights : row_yu;
                }
                temp1 = new StringBuffer();
                temp1.append("<tr>");
                for (int j = 0; j < cols; j++) {
                    x = j * width;
                    if (cols - index2 == 1) {
                        width = col_yu == 0 ? widths : col_yu;
                    }
                    imf = new CropImageFilter(x, y, width, height);
                    img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imf));
                    bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics gi = bimg.getGraphics();
                    gi.drawImage(img, 0, 0, null);
                    gi.dispose();
                    String path = "" + fold + "/" + fileName + "_" + index + ".jpg";//切割后图片保存路径;
                    temp1.append(dealStr(path));

                    file = new File(dir + path);
                    ImageIO.write(bimg, "JPEG", file);

                    index2++;
                    index++;
                }
                temp1.append("</tr>");
                sb.append(temp1);
                index1++;

                width = widths;
                index2 = 0;
            }
            System.out.println(sb);
        }

    }

    /**
     * 处理HTML格式的字符串信息
     *
     * @param path HTML格式要保存的图片信息
     * @return StringBuffer
     */
    private StringBuffer dealStr(String path) {
        String dir2 = "../";
        StringBuffer temp2 = new StringBuffer();
        temp2.append("<td>");
        temp2.append("<img src=" + dir2 + path + " width=" + width + " height=" + height + "/>");
        temp2.append("</td>");
        return temp2;
    }

}