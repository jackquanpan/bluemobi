package cn.bluemobi.util.helper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 描述:JSON解析工具类
 * 
 * @since: 2011-12-22
 * **/
public class JsonHelper {

	/**
	 * 描述:转换成短整型方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Integer:短整型对象
	 * **/
	public static Short convertToShort(String json) {

		return Short.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * 描述:转换成整型方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Integer:整型对象
	 * **/
	public static Integer convertToInteger(String json) {

		return Integer.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * 描述:转换成长整型方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Integer:长整型对象
	 * **/
	public static Long convertToLong(String json) {

		return Long.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * 描述:转换成浮点类型方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Integer:浮点类型对象
	 * **/
	public static Float convertToFloat(String json) {

		return Float.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * 描述:转换成双精度浮点类型方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Integer:双精度浮点类型对象
	 * **/
	public static Double convertToDouble(String json) {

		return Double.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * 描述:转换成对象方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Object:对象
	 * **/
	public static String convertToString(String json) {
		return String.valueOf(JSON.parse(json));
	}

	/**
	 * 描述:转换成对象方法
	 * 
	 * @param json
	 *            : json文本
	 * @return Object:对象
	 * **/
	public static Object convertToObject(String json) {
		return JSON.parse(json);
	}

	/**
	 * 描述:转换成对象方法
	 * 
	 * @param json
	 *            : json文本
	 * @param objClass
	 *            : 自定义类的Class
	 * @return Object:对象
	 * **/
	public static <T> T convertToObject(String json, Class<T> objClass) {
		return JSON.parseObject(json, objClass);
	}

	/**
	 * 描述:转换成JSON数据类型方法
	 * 
	 * @param obj
	 *            : 实体对象
	 * @return JSON: json类型文本数据
	 * **/
	public static String convertToJSON(Object obj,boolean flag) {
		if(flag){
			SerializerFeature[] featureArr = { SerializerFeature.WriteMapNullValue };
			return JSON.toJSONString(obj,featureArr);
		}
		return JSON.toJSONString(obj);
	}

	/**
	 * 描述:转换成列表集合方法
	 * 
	 * @param json
	 *            : json文本
	 * @param objClass
	 *            : 自定义类的Class
	 * @return List<T>:数组对象
	 * **/
	public static <T> List<T> convertToList(String json, Class<T> objClass) {

		return JSON.parseArray(json, objClass);
	}

	/**
	 * 根据网址，返回JSONObject对象 注：只适合请求响应为json格式网址
	 * 
	 * @param src
	 *            来源网址
	 * @param code
	 *            编码方式
	 */
	public static JSONObject getJsonObj(String src, String code) {
		InputStreamReader reader = null;
		BufferedReader in = null;
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE");
			connection.setConnectTimeout(10000);
			reader = new InputStreamReader(connection.getInputStream(), code);
			in = new BufferedReader(reader);
			String line = null; // 每行内容
			int lineFlag = 0; // 标记: 判断有没有数据
			StringBuilder content = new StringBuilder();
			while ((line = in.readLine()) != null) {
				content.append(line);
				lineFlag++;
			}

			return lineFlag == 0 ? null : new JSONObject(content.toString());
		} catch (SocketTimeoutException e) {
			// //System.out.println("连接超时!!!");
			throw new RuntimeException(e);
		} catch (JSONException e) {
			// //System.out.println("网站响应不是json格式，无法转化成JSONObject!!!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			// //System.out.println("连接网址不对或读取流出现异常!!!");
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	


	/**
	 * 根据网址，返回JSONObject对象 注：只适合请求响应为json格式网址
	 * 
	 * @param src
	 *            来源网址
	 * @param code
	 *            编码方式
	 */
	public static String getJsonObjString(String src, String code) throws Exception {
		InputStreamReader reader = null;
		BufferedReader in = null;
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE");
			connection.setConnectTimeout(10000);
			reader = new InputStreamReader(connection.getInputStream(), code);
			in = new BufferedReader(reader);
			String line = null; // 每行内容
			int lineFlag = 0; // 标记: 判断有没有数据
			StringBuilder content = new StringBuilder();
			while ((line = in.readLine()) != null) {
				content.append(line);
				lineFlag++;
			}
			return lineFlag == 0 ? null : content.toString();
		} catch (SocketTimeoutException e) {
			// //System.out.println("连接超时!!!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			// //System.out.println("连接网址不对或读取流出现异常!!!");
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	
	/**
	 * post
	 * @param httpUrl
	 * @param param
	 * @return
	 */
	public static String getJsonObjByPost(String httpUrl,Map<String,String> param) {
		try {

			InputStream in = null;
			BufferedReader rd = null;
			String responseContent = "";
			HttpURLConnection url_con = null;
			try {
				URL url = new URL(httpUrl);
				url_con = (HttpURLConnection) url.openConnection();
				url_con.setRequestMethod("POST");

				url_con.setConnectTimeout(500000000);// （单位：毫秒）jdk
				url_con.setReadTimeout(500000000);// （单位：毫秒）jdk 1.5换成这个,读操作超时

				url_con.setDoOutput(true);

				OutputStream out = url_con.getOutputStream();
				String values = "";
				if (param != null && param.size() > 0) {
					for (Map.Entry<String, String> entry : param.entrySet()) {
						values = values + entry.getKey() + "=" + entry.getValue() + "&";
					}
					values = values.substring(0, values.length() - 1);
					out.write(values.getBytes("UTF-8"));
				}
				out.flush();
				out.close();

				in = url_con.getInputStream();
				rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String tempLine = rd.readLine();
				StringBuffer temp = new StringBuffer();
				String crlf = System.getProperty("line.separator");
				while (tempLine != null) {
					temp.append(tempLine);
					temp.append(crlf);
					tempLine = rd.readLine();
				}
				responseContent = temp.toString();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (url_con != null) {
						url_con.disconnect();
					}
					if (rd != null) {
						rd.close();
					}

					if (in != null) {
						in.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			return responseContent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
