package cn.bluemobi.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileHelper {
	/**
	 * 返回系统当前时间(精确到毫秒),连接上一个三位的随机数,作为一个唯一的文件名称
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getTimeFileName() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date) + (new Random().nextFloat() + "").substring(2, 6);
	}

	/**
	 * 返回系统当前时间(精确到毫秒),连接上一个三位的随机数,作为一个唯一的文件名称
	 * 
	 * @param fileType文件格式
	 *            ，{.rar|.jpg}
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getTimeFileName(String fileType) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date) + (new Random().nextFloat() + "").substring(2, 6) + fileType;
	}

	/**
	 * 返回UUID,作为一个唯一的文件名称
	 * 
	 * @param fileType文件格式
	 *            ，{.rar|.jpg}
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getUUIDFileName(String fileType) {
		return UUID.randomUUID() + fileType;
	}

	/**
	 * 文件改名字
	 * 
	 * @param oldFile
	 * @param newFile
	 * @return
	 * @throws IOException
	 */
	public static boolean updateFileName(File oldFile, File newFile) throws IOException {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			File dir = newFile.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			in = new BufferedInputStream(new FileInputStream(oldFile), 5096);
			out = new BufferedOutputStream(new FileOutputStream(newFile), 5096);
			int index = 0;
			byte buffer[] = new byte[5096];
			while ((index = in.read(buffer)) != -1) {
				out.write(buffer, 0, index);
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				File file = oldFile;
				if (file.isFile()) {
					file.delete();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 复制一个文件(如果两个文件的路径一致，新的将会覆盖旧文件)
	 * 
	 * @param oldFile
	 *            旧的文件路径
	 * @parm newFile 新的文件路径
	 * @throws IOException
	 */
	public static boolean copyFile(File oldFile, File newFile) {

		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			File dir = newFile.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			in = new BufferedInputStream(new FileInputStream(oldFile), 5096);
			out = new BufferedOutputStream(new FileOutputStream(newFile), 5096);
			int index = 0;
			byte buffer[] = new byte[5096];
			while ((index = in.read(buffer)) != -1) {
				out.write(buffer, 0, index);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 下载网络图片到指定的目录
	 * 
	 * @param webFileUrl
	 * @param path
	 * @return
	 */
	public static boolean downloadWebFile(String webFileUrl, String fileName, String parentPath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			// 如果路径中含有中文,则进行转码操作
			Pattern pa = Pattern.compile("[\\u4e00-\\u9fa5]", Pattern.DOTALL);
			Matcher ma = pa.matcher(webFileUrl);
			String t = null;
			while (ma.find()) {
				t = ma.group();
				webFileUrl = webFileUrl.replace(t, URLEncoder.encode(t, "UTF-8"));
			}
			// 构造URL
			URL url = new URL(webFileUrl);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			in = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(parentPath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			out = new FileOutputStream(parentPath + fileName);
			// 开始读取
			while ((len = in.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤： 1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static String readTxtFile(String filePath) {
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		try {
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				StringBuffer sb = new StringBuffer("");
				while ((lineTxt = bufferedReader.readLine()) != null) {
					sb.append(lineTxt + "\n");
				}
				return sb.toString();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (read != null) {
					read.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * 文本文件的写入操作
	 * 
	 * @param path
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @return
	 */
	public static boolean writeTxtFile(String path, String content) {
		FileWriter fw = null;
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			fw = new FileWriter(path, false);
			fw.write(content);
			fw.flush();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 读取excel文件
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 */
	public static ArrayList<ArrayList<String>> readExcel(String path) {
		ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();
		try {
			Workbook workBook = null;
			try {
				workBook = new XSSFWorkbook(path);
			} catch (Exception ex) {
				workBook = new HSSFWorkbook(new FileInputStream(path));
			}

			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				Sheet sheet = workBook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				// 循环行Row
				for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}

					// 循环列Cell
					ArrayList<String> arrCell = new ArrayList<String>();
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
						arrCell.add(getValue(cell));
					}
					Row.add(arrCell);
				}
			}
		} catch (IOException e) {
			//System.out.println("e:" + e);
			return null;
		}
		return Row;
	}

	// 读取excel文件辅助
	private static String getValue(Cell cell) {
		String value = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				if (date != null) {
					value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
				} else {
					value = "";
				}
			} else {
				value = new DecimalFormat("0").format(cell.getNumericCellValue());
			}
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				value = cell.getStringCellValue();
			} else {
				value = cell.getNumericCellValue() + "";
			}
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			value = "";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			value = (cell.getBooleanCellValue() == true ? "true" : "false");
			break;
		default:
			value = "";
		}
		return value;

	}

	/**
	 * 递归删除目录下所有子目录和文件
	 */
	public static void deleteFile(File file) {
		try {
			File[] listFile = file.listFiles();
			for (File child : listFile) {
				if (child.isFile()) {
					child.delete();
				} else if (child.isDirectory()) {
					deleteChildDirAndFiles(child.listFiles());
				}
			}
			file.delete();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 递归删除目录下所有子目录和文件
	 */
	public static void deleteChildDirAndFiles(File[] childFiles) {
		try {
			for (File file : childFiles) {
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					} else {
						if (file.list().length > 0) {
							deleteChildDirAndFiles(file.listFiles());
							file.delete();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
