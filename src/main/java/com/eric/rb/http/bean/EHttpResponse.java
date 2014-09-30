package com.eric.rb.http.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @FileName: EHttpResponse.java
 * @Description:
 * @author Eric
 * @date 2014年9月23日
 */
public class EHttpResponse {
	private EHttpResultType resultType;

	private String stringResult;
	private byte[] byteResult;
	private InputStream streamResult;

	private String msgType;

	public String getStringResult() {
		return stringResult;
	}

	public void setStringResult(String stringResult) {
		this.stringResult = stringResult;
	}

	public void setStringResult(InputStream is) throws IOException {
		setResultType(EHttpResultType.STRING);

		// 避免使用byte或stream带来的内存耗尽可能, 所以使用string返回类型是, 通过流将返回数据转成string
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		
		String result = "";
		while ((result = br.readLine()) != null) {
			sb.append(result.trim());
		}
		
		setStringResult(sb.toString());
	}

	public byte[] getByteResult() {
		return byteResult;
	}

	public void setByteResult(byte[] byteResult) {
		setResultType(EHttpResultType.BYTES);
		this.byteResult = byteResult;
	}

	public InputStream getStreamResult() {
		return streamResult;
	}

	@SuppressWarnings("deprecation")
	public void setStreamResult(InputStream streamResult) {
		setResultType(EHttpResultType.STREAM);
		this.streamResult = streamResult;
	}

	public EHttpResultType getResultType() {
		return resultType;
	}

	private void setResultType(EHttpResultType resultType) {
		this.resultType = resultType;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Override
	public String toString() {
		return stringResult;
	}

}
