package com.changgx;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenPageDemo {
	   public static void main(String[] args) {
		   //启用cmd运行IE的方式来打开网址。
		   String str = "cmd /c start iexplore http://blog.csdn.net/powmxypow";
		   try {
			   Runtime.getRuntime().exec(str);
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   //启用系统默认浏览器来打开网址。
		   try {
			   URI uri = new URI("http://lofi.e-hentai.org/s/172d0283bf/1013629-75");
			   Desktop.getDesktop().browse(uri);
		   } catch (URISyntaxException e) {
			   e.printStackTrace();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
	   }
}
