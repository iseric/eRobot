package com.eric.rb.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

import com.eric.rb.consts.URLConsts;
import com.eric.rb.http.bean.EHttpRequest;
import com.eric.rb.http.bean.EHttpResponse;
import com.eric.rb.http.bean.EHttpResultType;
import com.eric.rb.http.handler.HttpHandler;

/**
 * @FileName: GetVerifyImageAction.java
 * @Description:
 * @author Eric
 * @date 2014年9月27日
 */
public class GetVerifyImageAction extends AbstractAction {

	public EHttpRequest onBuildRequest() {
		EHttpRequest request = new EHttpRequest();
		request.setUrl(MessageFormat.format(URLConsts.GET_CHECK_IAMGE_URL, context.getUser().getQqNum(), Math.random()));
		request.setHttpResultType(EHttpResultType.BYTES);
		return request;
	}

	public EHttpResponse onSubmit(EHttpRequest request) {
		EHttpResponse response = HttpHandler.get(request);
		return response;
	}

	/**
	 * 在当前目录下生成验证码图片
	 */
	public Object onParseResult(EHttpResponse response) {
		BufferedOutputStream buf = null;
		try {
			File verifyCodeImage = new File("verifyImage.jpg");
			buf = new BufferedOutputStream(new FileOutputStream(verifyCodeImage));
			buf.write(response.getByteResult());
			buf.flush();
			
			return verifyCodeImage.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
