package cn.bluemobi.util.file;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	
	/**
	 * 切割图片
	 * 
	 * @param x
	 *            截点横坐标 (从左开始计数)
	 * @param y
	 *            截点纵坐标 (从上开始计数)
	 * @param width
	 *            截取的宽度
	 * @param height
	 *            截取的长度
	 * @param oldpath
	 *            图片位置
	 * @param newpath
	 *            新生成的图片位置
	 */
	public static void cutImage(int x, int y, int width, int height,
			String oldpath, String newpath) throws IIOException, Exception {
		BufferedInputStream  is = null;
		ImageInputStream iis = null;
		ImageReader reader = null;

		// 这个是获取图片扩展名的方法，比如jpg
		int index = oldpath.lastIndexOf(".");
		String imgType = oldpath.substring(index + 1);
		// //System.out.println(imgType);
		try {
			is = new BufferedInputStream(new FileInputStream(oldpath),5096);
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName(imgType);
			reader = it.next();

			iis = ImageIO.createImageInputStream(is);		
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			Point p = new Point();
			p.setLocation(x, y);

			Dimension d = new Dimension();
			d.setSize(width, height);
			Rectangle rect = new Rectangle(p, d);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			ImageIO.write(bi, imgType, new File(newpath));
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (iis != null) {
					iis.close();
				}
				if (reader != null) {
					reader.dispose();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	/**
	 * 缩略图片
	 * 
	 * @param oldpath
	 *            原图片
	 * @param newpath
	 *            新生成的图片存放地址
	 * @param wdith
	 *            缩略后的宽
	 * @param height
	 *            缩略后的高
	 */
	public static void scaleImage(String oldpath, String newpath, int wdith,
			int height) throws IOException, Exception {
		// 获取老的图片
		File oldimg = new File(oldpath);
		BufferedOutputStream out = null;
		try {
			BufferedImage bi = ImageIO.read(oldimg);
			Image temp = bi.getScaledInstance(wdith, height,
					BufferedImage.SCALE_SMOOTH);
			BufferedImage thumbnail = new BufferedImage(wdith, height,
					BufferedImage.TYPE_INT_RGB);
			thumbnail.getGraphics().drawImage(temp, 0, 0, null);
			// 缩略后的图片路径
			File newimg = new File(newpath);
			out = new BufferedOutputStream(new FileOutputStream(newimg),5096);

			// 绘图
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder
					.getDefaultJPEGEncodeParam(thumbnail);
			param.setQuality(1.0f, false);
			encoder.encode(thumbnail);

			bi.flush();
			bi = null;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
	}

	public static void main(String[] args) {
		try {
			scaleImage("D:" + File.separator + "2.jpg", "D:" + File.separator
					+ "3.jpg", 50, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}