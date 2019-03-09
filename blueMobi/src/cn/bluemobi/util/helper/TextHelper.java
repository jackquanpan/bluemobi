package cn.bluemobi.util.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import cn.bluemobi.util.encryption.Base64Utils;

/**
 * 对文本进行处理的类
 * 
 * @author 雷攀
 * 
 */
public class TextHelper {
	/**
	 * 通用的截取字符串的方法
	 * 
	 * @param length
	 *            截取字符串的长度
	 * @param bool
	 *            是否去除html标签
	 * @param text
	 *            要处理的字符串
	 * @return
	 */
	public static String subString(int length, boolean isReplaceHtml, String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		text = isReplaceHtml ? replaceHTML(text) : text.trim();
		if (text.length() > length) {
			return text.substring(0, length) + "...";
		}
		return text;
	}

	/**
	 * 对文本去除HTML标签
	 * 
	 * @param text
	 * @return
	 */
	public static String replaceHTML(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		return text.replaceAll("\t|\r|\n|<[^>]*?>|&nbsp;", "").trim().replaceAll("\u3000", "");
	}

	/**
	 * 对字符串进行千分位划分 457154==>￥457,154.00
	 * 
	 * @param o
	 * @param isInt
	 *            是否划分为整型 457154==>￥457,154
	 * @return
	 */
	public static String getTextThousands(Object obj, boolean isInt) {
		if (ValidateHelper.isNullOrEmpty(obj))
			return null;
		NumberFormat df = null;
		if (isInt) {
			df = DecimalFormat.getNumberInstance(Locale.CHINA);
		} else {
			df = NumberFormat.getCurrencyInstance(Locale.CHINA);
		}
		return df.format(obj);
	}

	/**
	 * 获取富文本内容中的图片链接地址
	 * 
	 * @return
	 */
	public static List<String> getTextImageSrc(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		String regex = "<\\s*[I|i][m|M][g|G]\\s+([^>]*)\\s*>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(text);
		List<String> list = new ArrayList<String>();
		while (ma.find()) {// 首先判断话题内容中是否有图片
			list.add(ma.group());
		}
		if (list.size() != 0) {// 有图片文件
			List<String> imgSrcList = null;
			String a = null;
			for (String s : list) {
				ma = Pattern.compile("[s|S][R|r][c|C]=[\"|'](.*?)[\"|']").matcher(s);
				if (ma.find()) {
					a = ma.group();
					if (imgSrcList == null)
						imgSrcList = new ArrayList<String>();
				} else {
					a = null;
				}
				if (a != null) {
					a = a.replaceAll("[s|S][R|r][c|C]=[\"|']", "").replaceAll("[\"|']", "");
					imgSrcList.add(a);
				}
			}
			if (imgSrcList != null && imgSrcList.size() != 0)
				return imgSrcList;
			else
				return null;
		} else {
			return null;
		}
	}

	/**
	 * 获取富文本内容中视频封面图片，如果有多个视频，则默认多个视频的封面(只针对于<embed data-image="ccc.jpg">格式的视频)
	 * 
	 * @return
	 */
	public static List<String> getTextVideoCoverSrc(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		String regex = "<\\s*[e|E][m|M][B|b][e|E][d|D]\\s+([^>]*)\\s*>";
		List<String> list = new ArrayList<String>();
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(text);
		while (ma.find()) {
			list.add(ma.group());
		}
		if (list.size() != 0) {
			List<String> videoImageList = null;
			String a = null;
			for (String s : list) {
				ma = Pattern.compile("data-image=[\"|'](.*?)[\"|']").matcher(s);
				if (ma.find()) {
					a = ma.group();
					if (videoImageList == null)
						videoImageList = new ArrayList<String>();
				}
				if (a != null) {
					a = a.replaceAll("data-image=[\"|']", "").replaceAll("[\"|']", "");
					videoImageList.add(a);
				}
			}
			return videoImageList;
		} else {
			return null;
		}
	}

	/**
	 * 获取富文本中的客户端播放视频的地址
	 * 
	 * @return
	 */
	public static List<String> getClientVideoUrl(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		String regex = "<\\s*[e|E][m|M][B|b][e|E][d|D]\\s+([^>]*)\\s*>";
		List<String> list = new ArrayList<String>();
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(text);
		while (ma.find()) {
			list.add(ma.group());
		}
		if (list.size() != 0) {
			List<String> videoImageList = null;
			String a = null;
			for (String s : list) {
				ma = Pattern.compile("client-url=[\"|'](.*?)[\"|']").matcher(s);
				if (ma.find()) {
					a = ma.group();
					if (videoImageList == null)
						videoImageList = new ArrayList<String>();
				}
				if (a != null) {
					a = a.replaceAll("client-url=[\"|']", "").replaceAll("[\"|']", "");
					videoImageList.add(a);
				}
			}
			return videoImageList;
		} else {
			return null;
		}
	}

	/**
	 * 获取富文本中的客户端播放视频的地址
	 * 
	 * @return
	 */
	public static List<String> getVideoSwf(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		String regex = "<\\s*[e|E][m|M][B|b][e|E][d|D]\\s+([^>]*)\\s*>";
		List<String> list = new ArrayList<String>();
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(text);
		while (ma.find()) {
			list.add(ma.group());
		}
		if (list.size() != 0) {
			List<String> videoImageList = null;
			String a = null;
			for (String s : list) {
				ma = Pattern.compile("src=[\"|'](.*?)[\"|']").matcher(s);
				if (ma.find()) {
					a = ma.group();
					if (videoImageList == null)
						videoImageList = new ArrayList<String>();
				}
				if (a != null) {
					a = a.replaceAll("src=[\"|']", "").replaceAll("[\"|']", "");
					videoImageList.add(a);
				}
			}
			return videoImageList;
		} else {
			return null;
		}
	}

	/**
	 * 描述:获得包含中文的字符串长度方法(一个中文为2个字符)
	 * 
	 * @return
	 */
	public static int getTextLength(String text) {
		if (ValidateHelper.isNullOrEmpty(text))
			return 0;
		int len = 0;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) > 127 || text.charAt(i) == 94) {
				len += 2;
			} else {
				len++;
			}
		}
		return len;
	}

	/**
	 * 获取字符串中的数字 如果含有多个数字，默认获取第一个数字
	 */
	public static String getNum(String t) {
		if (ValidateHelper.isNullOrEmpty(t)) {
			return null;
		}
		String regex = "\\d+";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(t);

		if (ma.find()) {// 首先判断话题内容中是否有
			return ma.group();
		}
		return null;
	}

	public static String decode(String text, int count) throws UnsupportedEncodingException {
		if (ValidateHelper.isNullOrEmpty(text))
			return null;
		for (int i = 0; i < count; i++) {
			text = URLDecoder.decode(text, "utf-8");
		}
		return text;
	}

	/**
	 * 对参数进行加密
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeStr(String text) throws UnsupportedEncodingException {
		if (ValidateHelper.isNullOrEmpty(text)) {
			return null;
		}
		String a = Base64Utils.getBASE64(text);
		return URLEncoder.encode(a, "UTF-8");
	}

	/**
	 * 对参数进行解密
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String unencodeStr(String text) throws UnsupportedEncodingException {
		if (ValidateHelper.isNullOrEmpty(text)) {
			return null;
		}
		return new String(Base64Utils.getFromBASE64(new String(decode(text, 1))));
	}

	/**
	 * HTML标签转义方法 —— java代码库
	 * 
	 * @param content
	 * @return
	 */
	public static String html(String content) {
		if (content == null)
			return "";
		String html = content;
		html = StringUtils.replace(html, "'", "&apos;");
		html = StringUtils.replace(html, "\"", "&quot;");
		html = StringUtils.replace(html, "\t", "&nbsp;&nbsp;");// 替换跳格
		// html = StringUtils.replace(html, " ", "&nbsp;");// 替换空格
		html = StringUtils.replace(html, "<", "&lt;");
		html = StringUtils.replace(html, ">", "&gt;");
		return html;
	}


}
