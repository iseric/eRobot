package com.eric.rb.http.bean;

/**
 * @FileName: EHttpResultType.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public enum EHttpResultType {
	/**
	 * 字符串方式
	 */
	STRING,

	/**
	 * 字节数组方式
	 */
	BYTES,

	/**
	 * 流
	 * 
	 * 
	 */
	@Deprecated
	STREAM
}
