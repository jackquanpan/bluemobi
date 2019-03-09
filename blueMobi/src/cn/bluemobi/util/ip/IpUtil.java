package cn.bluemobi.util.ip;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import cn.bluemobi.util.helper.JsonHelper;

/**
 * ip工具类
 * 
 * @author Jack
 * 
 */
@SuppressWarnings({"unused"})
public class IpUtil {

	private static String sinaUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=";

	private static final int IPV6Length = 8; // IPV6地址的分段
	private static final int IPV4Length = 4; // IPV6地址分段
	private static final int IPV4ParmLength = 2; // 一个IPV4分段占的长度
	private static final int IPV6ParmLength = 4; // 一个IPV6分段占的长

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		if (ip != null && ip.length() > 15) {
			if (ip.indexOf(",") > 0) {
				ip = ip.substring(0, ip.indexOf(","));
			}
		}
		return ip;
	}

	/**
	 * 获取IP地址的详细信息
	 * 
	 * @param ip
	 * @return
	 */
	public static Ip getIpInfo(String ip) {
		try {
			String content = sendGet(sinaUrl + ip);
			int index = content.indexOf("{");
			if(index>-1){
				content = content.substring(index,content.lastIndexOf("}") + 1);
				return jsonToObject(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	
	private static Ip jsonToObject(String json) {
		return JsonHelper.convertToObject(json, Ip.class);
	}

	private static String sendGet(String url) throws ClientProtocolException,
			IOException {
		String result = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		InputStream in = null;
		try {
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				entity = new BufferedHttpEntity(entity);
				in = entity.getContent();
				byte[] read = new byte[1024];
				byte[] all = new byte[0];
				int num;
				while ((num = in.read(read)) > 0) {
					byte[] temp = new byte[all.length + num];
					System.arraycopy(all, 0, temp, 0, all.length);
					System.arraycopy(read, 0, temp, all.length, num);
					all = temp;
				}
				result = new String(all, "UTF-8");
			}
		} finally {
			if (in != null)
				in.close();
			get.abort();
		}

		return result;
	}

	/**
	 * IPV6、IPV4转化为十六进制串
	 * 
	 * @param ipAddress
	 * @return
	 */
	private static String buildKey(String ipAddress) {
		String Key = "";
		// ipv4标识 。判断是否是ipv4地址
		int dotFlag = ipAddress.indexOf(".");
		// ipv6标识 。判断是否是ipv6地址
		int colonFlag = ipAddress.indexOf(":");
		// ipv6标识 。判断是否是简写的ipv6地址
		int dColonFlag = ipAddress.indexOf("::");
		// 将v6或v4的分隔符用&代替
		ipAddress = ipAddress.replace(".", "&");
		ipAddress = ipAddress.replace(":", "&");
		// ipv4 address。将ipv4地址转换成16进制的形式
		if (dotFlag != -1 && colonFlag == -1) {
			String[] arr = ipAddress.split("&");
			// 1、 ipv4转ipv6，前4组数补0或f
			for (int i = 0; i < IPV6Length - IPV4ParmLength; i++) {
				// 根据v4转v6的形式，除第4组数补ffff外，前3组数补0000
				if (i == IPV6Length - IPV4ParmLength - 1) {
					Key += "ffff";
				} else {
					Key += "0000";
				}
			}
			// 2、将ipv4地址转成16进制
			for (int j = 0; j < IPV4Length; j++) {
				// 1)将每组ipv4地址转换成16进制
				arr[j] = Integer.toHexString(Integer.parseInt(arr[j]));
				// 2) 位数不足补0，ipv4地址中一组可转换成一个十六进制，两组数即可标识ipv6中的一组，v6中的一组数不足4位补0
				for (int k = 0; k < (IPV4ParmLength - arr[j].length()); k++) {
					Key += "0";
				}
				Key += arr[j];
			}
		}
		// Mixed address with ipv4 and ipv6。将v4与v6的混合地址转换成16进制的形式
		if (dotFlag != -1 && colonFlag != -1 && dColonFlag == -1) {
			String[] arr = ipAddress.split("&");

			for (int i = 0; i < IPV6Length - IPV4ParmLength; i++) {
				// 将ip地址中每组不足4位的补0
				for (int k = 0; k < (IPV6ParmLength - arr[i].length()); k++) {
					Key += "0";
				}
				Key += arr[i];
			}

			for (int j = 0; j < IPV4Length; j++) {
				arr[j] = Integer.toHexString(Integer.parseInt(arr[j]));
				for (int k = 0; k < (IPV4ParmLength - arr[j].length()); k++) {
					Key += "0";
				}
				Key += arr[j];
			}
		}
		// Mixed address with ipv4 and ipv6,and there are more than one
		// '0'。将v4与v6的混合地址(如::32:dc:192.168.62.174)转换成16进制的形式
		// address param
		if (dColonFlag != -1 && dotFlag != -1) {
			String[] arr = ipAddress.split("&");
			// 存放16进制的形式
			String[] arrParams = new String[IPV6Length + IPV4ParmLength];
			int indexFlag = 0;
			int pFlag = 0;
			// 1、将简写的ip地址补0
			// 如果ip地址中前面部分采用简写，做如下处理
			if ("".equals(arr[0])) {
				// 1)如果ip地址采用简写形式，不足位置补0，存放到arrParams中
				for (int j = 0; j < (IPV6Length + IPV4ParmLength - (arr.length - 2)); j++) {
					arrParams[j] = "0000";
					indexFlag++;
				}
				// 2)将已有值的部分(如32:dc:192.168.62.174)存放到arrParams中
				for (int i = 2; i < arr.length; i++) {
					arrParams[indexFlag] = arr[i];
					indexFlag++;
				}
			} else {
				for (int i = 0; i < arr.length; i++) {
					if ("".equals(arr[i])) {
						for (int j = 0; j < (IPV6Length + IPV4ParmLength
								- arr.length + 1); j++) {
							arrParams[indexFlag] = "0000";
							indexFlag++;
						}
					} else {
						arrParams[indexFlag] = arr[i];
						indexFlag++;
					}
				}
			}
			// 2、ip(去除ipv4的部分)中采用4位十六进制数表示一组数，将不足4位的十六进制数补0
			for (int i = 0; i < IPV6Length - IPV4ParmLength; i++) {
				// 如果arrParams[i]组数据不足4位，前补0
				for (int k = 0; k < (IPV6ParmLength - arrParams[i].length()); k++) {
					Key += "0";
				}
				Key += arrParams[i];
				// pFlag用于标识位置，主要用来标识ipv4地址的起始位
				pFlag++;
			}
			// 3、将ipv4地址转成16进制
			for (int j = 0; j < IPV4Length; j++) {
				// 1)将每组ipv4地址转换成16进制
				arrParams[pFlag] = Integer.toHexString(Integer
						.parseInt(arrParams[pFlag]));
				// 2)位数不足补0，ipv4地址中一组可转换成一个十六进制，两组数即可标识ipv6中的一组，v6中的一组数不足4位补0
				for (int k = 0; k < (IPV4ParmLength - arrParams[pFlag].length()); k++) {
					Key += "0";
				}
				Key += arrParams[pFlag];
				pFlag++;
			}
		}
		// ipv6 address。将ipv6地址转换成16进制
		if (dColonFlag == -1 && dotFlag == -1 && colonFlag != -1) {
			String[] arrParams = ipAddress.split("&");
			// 将v6地址转成十六进制
			for (int i = 0; i < IPV6Length; i++) {
				// 将ipv6地址中每组不足4位的补0
				for (int k = 0; k < (IPV6ParmLength - arrParams[i].length()); k++) {
					Key += "0";
				}

				Key += arrParams[i];
			}
		}

		if (dColonFlag != -1 && dotFlag == -1) {
			String[] arr = ipAddress.split("&");
			String[] arrParams = new String[IPV6Length];
			int indexFlag = 0;
			if ("".equals(arr[0])) {
				for (int j = 0; j < (IPV6Length - (arr.length - 2)); j++) {
					arrParams[j] = "0000";
					indexFlag++;
				}
				for (int i = 2; i < arr.length; i++) {
					arrParams[indexFlag] = arr[i];
					i++;
					indexFlag++;
				}
			} else {
				for (int i = 0; i < arr.length; i++) {
					if ("".equals(arr[i])) {
						for (int j = 0; j < (IPV6Length - arr.length + 1); j++) {
							arrParams[indexFlag] = "0000";
							indexFlag++;
						}
					} else {
						arrParams[indexFlag] = arr[i];
						indexFlag++;
					}
				}
			}
			for (int i = 0; i < IPV6Length; i++) {
				for (int k = 0; k < (IPV6ParmLength - arrParams[i].length()); k++) {
					Key += "0";
				}
				Key += arrParams[i];
			}
		}
		return Key;
	}

	/**
	 * 十六进制串转化为IP地址
	 * 
	 * @param key
	 * @return
	 */
	private static String splitKey(String key) {
		String IPV6Address = "";
		String IPAddress = "";
		String strKey = "";
		String ip1 = key.substring(0, 24);
		String tIP1 = ip1.replace("0000", "").trim();
		if (!"".equals(tIP1) && !"FFFF".equals(tIP1)) {
			// 将ip按：分隔
			while (!"".equals(key)) {
				strKey = key.substring(0, 4);
				key = key.substring(4);
				if ("".equals(IPV6Address)) {
					IPV6Address = strKey;
				} else {
					IPV6Address += ":" + strKey;
				}
			}
			IPAddress = IPV6Address;
		}
		return IPAddress;
	}
	
	  public static long ipToLong(String strIp){   
	        long[] ip = new long[4];   
	        //先找到IP地址字符串中.的位置   
	        int position1 = strIp.indexOf(".");   
	        int position2 = strIp.indexOf(".", position1 + 1);   
	        int position3 = strIp.indexOf(".", position2 + 1);   
	        //将每个.之间的字符串转换成整型   
	         ip[0] = Long.parseLong(strIp.substring(0, position1));   
	         ip[1] = Long.parseLong(strIp.substring(position1+1, position2));   
	         ip[2] = Long.parseLong(strIp.substring(position2+1, position3));   
	         ip[3] = Long.parseLong(strIp.substring(position3+1));   
	        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];   
	     }   
	       
	    //将十进制整数形式转换成127.0.0.1形式的ip地址   
	    public static String longToIP(long longIp){   
	         StringBuffer sb = new StringBuffer("");   
	        //直接右移24位   
	         sb.append(String.valueOf((longIp >>> 24)));   
	         sb.append(".");   
	        //将高8位置0，然后右移16位   
	         sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));   
	         sb.append(".");   
	        //将高16位置0，然后右移8位   
	         sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));   
	         sb.append(".");   
	        //将高24位置0   
	         sb.append(String.valueOf((longIp & 0x000000FF)));   
	        return sb.toString();   
	     }   
	    
	    public static void main(String[] args){//3232238593 3232235788
	    	try{
	    		String ip ="59.174.79.135";
	    		Ip i =  getIpInfo(ip);
	    		//System.out.println(i);
	    		//System.out.println("国家:"+i.getCountry());
	    		//System.out.println("省份:"+i.getProvince());
	    		//System.out.println("城市:"+i.getCity());
	    		//System.out.println("区域:"+i.getArea());
	    		//System.out.println("ips:"+i.getIsp());
	    		/*long ipnumber = ipToLong(ip);
	    		//System.out.println("ip numbeer:"+ipnumber);
	    		ip = longToIP(ipnumber);
	    		//System.out.println("ip:"+ip);
	    		ipnumber = 54548545;
	    		ip = longToIP(ipnumber);
	    		//System.out.println("ip:"+ip);
	    		String a = "0:0:0:0:0:0:0:1";
	    		//System.out.println(buildKey(a)+" "+buildKey("127.0.0.1"));
	    		String b= "00000000000000000000ffff7f000001";
	    		String c = "00000000000000000000000000000001";
	    		//System.out.println(splitKey(b)+" "+splitKey(c));*/
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
}
