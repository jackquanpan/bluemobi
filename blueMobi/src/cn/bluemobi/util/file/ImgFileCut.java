package cn.bluemobi.util.file;

import java.io.File;

/**
 * 对图片文件进行裁剪
 * 
 * @author 雷攀
 * 
 */
public class ImgFileCut {

	/**
	 * 对图片进行等比例裁剪
	 * 
	 * @param file 原始文件
	 * @param src 新文件
	 * @param width 裁剪宽度
	 * @param height 裁剪高度 
	 * 	如果宽带和高度都为null的话，则不进行裁剪
	 * @return
	 */
	public static boolean cut(File file, String src, Integer width, Integer height) {
		try {
			File srcFile = new File(src);
			File dir = srcFile.getParentFile();
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			if(width!=null&&height!=null){
				IconCompressUtil.compressPic(srcFile.getPath(),srcFile.getPath().replace(srcFile.getName(), width+"x"+height+"/"+srcFile.getName()), width, height, false);
			}else if(width!=null){
				IconCompressUtil.compressPic(srcFile.getPath(),srcFile.getPath().replace(srcFile.getName(), width+"/"+srcFile.getName()), width);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	
	

}
