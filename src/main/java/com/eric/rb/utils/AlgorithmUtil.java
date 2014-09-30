package com.eric.rb.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @FileName: AlgorithmUtil.java
 * @Description: 算法工具
 * @author Eric
 * @date 2014年9月27日
 */
@SuppressWarnings("restriction")
public class AlgorithmUtil {
	/**
	 *  QQ密码加密算法
	 * @param uin qq号
	 * @param plain 明文密码
	 * @param verify 自动验证码/图片验证码
	 * @return
	 */
	public static String encrypt(long uin, String plain, String verify) {
		byte[] data = concat(md5(plain.getBytes()), long2bytes(uin));
		String code = byte2HexString(md5(data));
		data = md5((code + verify.toUpperCase()).getBytes());
		return byte2HexString(data);
	}
	/**
	 *  QQ好友列表、群列表查询hash算法
	 *  	ps 此hash算法每隔一段时间会更换, 定期及时更换. 或可根据官网js文件实时获取
	 *  	上一次更新 20140614
	 * @param uin qq号
	 * @param ptwebqq 
	 * @return
	 */
	public static String hash(String uin, String ptwebqq) {
        String s = "";
        try {
//			String js = "P=function(i,a){var j=[];j[0]=i>>24&255;j[1]=i>>16&255;j[2]=i>>8&255;j[3]=i&255;for(var s=[],e=0;e<a.length;++e)s.push(a.charCodeAt(e));e=[];for(e.push(new b(0,s.length-1));e.length>0;){var c=e.pop();if(!(c.s>=c.e||c.s<0||c.e>=s.length))if(c.s+1==c.e){if(s[c.s]>s[c.e]){var J=s[c.s];s[c.s]=s[c.e];s[c.e]=J}}else{for(var J=c.s,l=c.e,f=s[c.s];c.s<c.e;){for(;c.s<c.e&&s[c.e]>=f;)c.e--,j[0]=j[0]+3&255;c.s<c.e&&(s[c.s]=s[c.e],c.s++,j[1]=j[1]*13+43&255);for(;c.s<c.e&&s[c.s]<=f;)c.s++,j[2]=j[2]-3&255;c.s<c.e&&(s[c.e]=s[c.s],c.e--,j[3]=(j[0]^j[1]^j[2]^j[3]+1)&255)}s[c.s]=f;e.push(new b(J,c.s-1));e.push(new b(c.s+1,l))}}s=[\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"];e=\"\";for(c=0;c<j.length;c++)e+=s[j[c]>>4&15],e+=s[j[c]&15];return e},b=function(b,i){this.s=b||0;this.e=i||0}";
        	// 
            StringBuffer sqlSB = new StringBuffer();
            sqlSB.setLength(0);
            sqlSB.append("P = function(b, j) { \n");
            sqlSB.append("\tfor (var a = j + \"password error\", i = \"\", E = [];;) \n");
            sqlSB.append("\t\tif (i.length <= a.length) { \n");
            sqlSB.append("\t\t\tif (i += b, i.length == a.length) \n");
            sqlSB.append("\t\t\t\tbreak \n");
            sqlSB.append("\t\t} else { \n");
            sqlSB.append("\t\t\ti = i.slice(0, a.length); \n");
            sqlSB.append("\t\t\tbreak \n");
            sqlSB.append("\t\t} \n");
            sqlSB.append("\tfor (var c = 0; c < i.length; c++) \n");
            sqlSB.append("\t\tE[c] = i.charCodeAt(c) ^ a.charCodeAt(c); \n");
            sqlSB.append("\ta = [\"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\", \"A\", \"B\", \"C\", \"D\", \n");
            sqlSB.append("\t\t  \"E\", \"F\"]; \n");
            sqlSB.append("  i = \"\"; \n");
            sqlSB.append("  for (c = 0; c < E.length; c++) \n");
            sqlSB.append("    i += a[E[c] >> 4 & 15], i += a[E[c] & 15]; \n");
            sqlSB.append("  return i \n");
            sqlSB.append("} \n");
            String js = sqlSB.toString();
            // end
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr
                    .getEngineByMimeType("application/javascript");
            engine.eval(js);
            Invocable inv = (Invocable) engine;
            s = (String) inv.invokeFunction("P", uin, ptwebqq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

	private static byte[] concat(byte[] bytes1, byte[] bytes2) {
		byte[] big = new byte[bytes1.length + bytes2.length];
		System.arraycopy(bytes1, 0, big, 0, bytes1.length);
		System.arraycopy(bytes2, 0, big, bytes1.length, bytes2.length);
		return big;
	}

	private static byte[] md5(byte[] bytes) {
		MessageDigest dist = null;
		byte[] result = null;
		try {
			dist = MessageDigest.getInstance("MD5");
			result = dist.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

	private static byte[] long2bytes(long i) {
		byte[] b = new byte[8];
		for (int m = 0; m < 8; m++, i >>= 8) {
			b[7 - m] = (byte) (i & 0x000000FF);
		}
		return b;
	}

	private static String byte2HexString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		char[] hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };
		if (b == null)
			return "null";
		int offset = 0;
		int len = b.length;
		// 检查索引范围
		int end = offset + len;
		if (end > b.length)
			end = b.length;
		sb.delete(0, sb.length());
		for (int i = offset; i < end; i++) {
			sb.append(hex[(b[i] & 0xF0) >>> 4]).append(hex[b[i] & 0xF]);
		}
		return sb.toString();
	}
}
