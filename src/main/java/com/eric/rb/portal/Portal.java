package com.eric.rb.portal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.eric.rb.EQQClient.EQQClient;
import com.eric.rb.consts.KeyConsts;
import com.eric.rb.core.EModule;
import com.eric.rb.log.LOG;
import com.eric.rb.module.MessageModule;
import com.eric.rb.module.UserModule;

/**
 * @FileName: Portal.java
 * @Description:
 * @author Eric
 * @date 2014年9月30日
 */
public class Portal {
	
	private static EQQClient client = null;
	
	public static void main(String[] args) throws Exception {
		String qqNum = null;
		String password = null;
		try{
			qqNum = String.valueOf(args[0]);
			password = String.valueOf(args[1]);
			
//			qqNum = String.valueOf("");
//			password = String.valueOf("");
			
			if(qqNum == null || "".equals(qqNum) || 
					password ==null || "".equals(password.trim())){
				throw new NullPointerException("账号和密码不能为空");
			}
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}catch (Exception e){
			System.out.println("请输入账号和密码");
			System.exit(0);
		}
		int i = 0;
		while(!login(qqNum, password)){
			LOG.println("第"+i+"次登陆");
		}
		
		MessageModule messageModule = (MessageModule)client.getModule(EModule.Type.MESSAGE);
		messageModule.pollMsg();
		
		
		
		
		
	}
	
	
	public static boolean login(String qqNum, String password){
		try {
			// 初始化
			client = new EQQClient (qqNum, password);
			
			// 验证账号是否需要验证码
			UserModule module = (UserModule)client.getModule(EModule.Type.USER);
			Map<String, String> r = (Map<String, String>)module.checkVerify();
		
			if(KeyConsts.VERIFY_STATE_INVALID.equals(r.get(KeyConsts.VERIFY_STATE))){
				String resu = module.getVerifyImage();
				System.out.println("请打开"+resu+"文件, 在这里输入图片中的验证码！");
				InputStreamReader ins = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(ins);
				String check = br.readLine();
				r.put(KeyConsts.VERIFY_CODE, check);
			};
			
			LOG.println("verify code :" + r.get(KeyConsts.VERIFY_CODE));
			
			client.getUser().setVerifyCode(r.get(KeyConsts.VERIFY_CODE));
			String checkSigUrl = module.login1();
			
			if(checkSigUrl==null){
				LOG.println("错, 错, 错...");
			}else{
				client.getParams().put(KeyConsts.PARAM, checkSigUrl);
				
				// 回调http, 获取关键cookie
				module.checkSig();
				
				// 真实登陆, 这样会导致登陆状态的QQ弹出
				if(module.login2()){
					// 将poll状态设置为true
					client.getSession().setRun(true);
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
