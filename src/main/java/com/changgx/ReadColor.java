package com.changgx;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ReadColor {


    /**
	 * 读取一张图片的RGB值
	 * 
	 * @throws Exception
	 */
	public static List<Integer> getImagePixel(String image) throws Exception {
		int[] rgb = new int[3];
		File file = new File(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int width = bi.getWidth();
		int height = bi.getHeight();
//		System.out.println(width);
//		System.out.println(height);
		int minx = bi.getMinX();
		int miny = bi.getMinY();
		List<Boolean> list=new ArrayList();
		for (int i = miny; i <height; i++) {
			boolean flag=true;
			int pixel = bi.getRGB(0,i);
			for (int j = minx; j < width; j++) {
				int tmp = bi.getRGB(j,i); // 下面三行代码将一个数字转换为RGB数字
//				rgb[0] = (pixel & 0xff0000) >> 16;
//				rgb[1] = (pixel & 0xff00) >> 8;
//				rgb[2] = (pixel & 0xff);
				if(pixel!=tmp){
					flag=false;
					break;
				}
			}
			list.add(flag);
		}
		boolean start=true;
		List<Integer> result=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)!=start){
				start=list.get(i);
				result.add(i);
			}
		}
		if(result.size()%2!=0){
            result.add(height);
        }
		return result;
	}


	/**
	 * 返回屏幕色彩值
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws AWTException
	 */
	public int getScreenPixel(int x, int y) throws AWTException { // 函数返回值为颜色的RGB值。
		Robot rb = null; // java.awt.image包中的类，可以用来抓取屏幕，即截屏。
		rb = new Robot();
		Toolkit tk = Toolkit.getDefaultToolkit(); // 获取缺省工具包
		Dimension di = tk.getScreenSize(); // 屏幕尺寸规格
		System.out.println(di.width);
		System.out.println(di.height);
		Rectangle rec = new Rectangle(0, 0, di.width, di.height);
		BufferedImage bi = rb.createScreenCapture(rec);
		int pixelColor = bi.getRGB(x, y);

		return 16777216 + pixelColor; // pixelColor的值为负，经过实践得出：加上颜色最大值就是实际颜色值。
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
        System.out.println(getImagePixel("E:\\comic\\scandal_of_the_witch\\00011.jpg"));
		int x = 0;
//		ReadColorTest rc = new ReadColorTest();
//		x = rc.getScreenPixel(100, 345);
//		System.out.println(x + " - ");
////		rc.getImagePixel("E:\\tmp\\4.jpg");
//		rc.getImagePixel("E:\\comic\\scandal_of_the_witch\\00002.jpg");
	}

}

