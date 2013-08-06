package com.szwx.yht.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据检测工具类
 * @author zhangyj
 * @date Mar 22, 2012
 */
public class RegexCheck {
	
	private static RegexCheck check=null;
	
	private RegexCheck(){
		
	}

	public static RegexCheck newInstance(){
		
		if(null==check){
			check=new RegexCheck();
		}
		
		return check;
	}
	
	public boolean startCheck(String reg, String string) {
		boolean tem = false;

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);

		tem = matcher.matches();
		return tem;
	}

	/**
	 * 检验整数,适用于正整数、负整数、0，负整数不能以-0开头, 正整数不能以0开头
	 * 
	 */
	public boolean checkNr(String nr) {
		String reg = "^(-?)[1-9]+\\d*|0";
		return startCheck(reg, nr);
	}

	/**
	 * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
	 */
	public boolean checkCellPhone(String cellPhoneNr) {
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	/**
	 * 检验空白符
	 */
	public boolean checkWhiteLine(String line) {
		String regex = "(\\s|\\t|\\r)+";

		return startCheck(regex, line);
	}

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符
	 */
	public boolean checkEmailWithSuffix(String email) {
		// 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线   
		// 2、(\\w+\\.)表示域名. 因为新浪邮箱域名是sina.com.cn 所以后面{1,3}表示可以出现一次或两次或者三次.  
		   String regular = "\\w+@(\\w+\\.){1,3}\\w+";
		   //String regular = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
	       Pattern pattern = Pattern.compile(regular);   
	       boolean flag = false;
	       if(email != null ){               
	           Matcher matcher = pattern.matcher(email);      
	           flag = matcher.matches();          
	       } 
		   return flag;   

	}
	
	/**
	 * 检查手机号码
	 */
	public boolean checkMobilePhone(String MobilePhone) {
     //当前运营商号段分配   
     //中国移动号段 1340-1348 135 136 137 138 139 150 151 152 157 158 159 187 188 147   
     //中国联通号段 130 131 132 155 156 185 186 145   
     //中国电信号段 133 1349 153 180 189
	   String regular = "1[3,4,5,8]{1}\\d{9}";
	   Pattern pattern = Pattern.compile(regular);    
       boolean flag = false;   
       if( MobilePhone != null ){              
           Matcher matcher = pattern.matcher(MobilePhone);   
           flag = matcher.matches();         
        }  
       return flag;   
	}
	
	

	/**
	 * 检查EMAIL地址 用户名和网站名称必须>=1位字符 地址结尾必须是2位以上，如：cn,test,com,info
	 */
	public boolean checkEmail(String email) {
		if (email.indexOf("@") > 0 && email.indexOf(".") > 0 && email.indexOf(".") > email.indexOf("@")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查邮政编码(中国),6位，第一位必须是非0开头，其他5位数字为0-9
	 */
	public boolean checkPostcode(String postCode) {
		String regex = "^[1-9]\\d{5}";
		return startCheck(regex, postCode);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 用户名有最小长度和最大长度限制，比如用户名必须是4-20位
	 */
	public boolean checkUsername(String username, int min, int max) {
		String regex = "[\\w\u4e00-\u9fa5]{" + min + "," + max + "}(?<!_)";
		return startCheck(regex, username);
	}
	/**
	 * 检验汉字
	 * @author zhangyj
	 * @date Jun 26, 2012
	 * @param HZ
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean checkHZ(String HZ, int min, int max) {
		String regex = "[\u4e00-\u9fa5]{" + min + "," + max + "}";
		return startCheck(regex, HZ);
	}
	
	/**
	 * 检验是否包含有汉字
	 * @author zhangyj
	 * @date Jun 26, 2012
	 * @param HZ
	 * @param min
	 * @param max
	 * @return
	 */
	public boolean checkHZ(String HZ) {
		String regex = "[\\w]*[\u4e00-\u9fa5]+[\\w]*";
		return startCheck(regex, HZ);
	}
	
	
	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾 有最小位数限制的用户名，比如：用户名最少为4位字符
	 */
	public boolean checkUsername(String username, int min) {
		// [\\w\u4e00-\u9fa5]{2,}?
		String regex = "[\\w\u4e00-\u9fa5]{" + min + ",}(?<!_)";

		return startCheck(regex, username);
	}

	/**
	 * 检验用户名 取值范围为a-z,A-Z,0-9,"_",汉字 最少一位字符，最大字符位数无限制，不能以"_"结尾
	 */
	public boolean checkUsername(String username) {
		String regex = "[\\w\u4e00-\u9fa5]+(?<!_)";
		return startCheck(regex, username);
	}
	
	
	/**
	 * 检验密码 取值范围为a-z,A-Z,0-9,不能以"_"结尾
	 */
	public boolean checkPassword(String pwd,int min,int max) {
//		String regex = "[\\w\u4e00-\u9fa5]*";
		String regex = "[\\w]{" + min + "," + max + "}(?<!_)";
		return startCheck(regex, pwd);
	}

	/**
	 * 查看IP地址是否合法
	 */
	public boolean checkIP(String ipAddress) {
		String regex = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."
				+ "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";

		return startCheck(regex, ipAddress);
	}

	/**
	 * 验证国内电话号码 格式：010-67676767，区号长度3-4位，必须以"0"开头，号码是7-8位
	 */
	public boolean checkPhoneNr(String phoneNr) {
		String regex = "^[0]\\d{2,3}\\d{7,8}";
		return startCheck(regex, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：6767676, 号码位数必须是7-8位,头一位不能是"0"
	 */
	public boolean checkPhoneNrWithoutCode(String phoneNr) {
		String reg = "^[1-9]\\d{6,7}";
		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内电话号码 格式：0106767676，共11位或者12位，必须是0开头
	 */
	public boolean checkPhoneNrWithoutLine(String phoneNr) {
		String reg = "^[0]\\d{10,11}";
		return startCheck(reg, phoneNr);
	}

	/**
	 * 验证国内身份证号码：15或18位，由数字组成，不能以0开头
	 */
	public boolean checkIdCard(String idNr) {
		String reg = "^[1-9](\\d{14}|\\d{17}|d{16}[xX]{1})";
		return startCheck(reg, idNr);
	}

	/**
	 * 网址验证<br>
	 * 符合类型：<br>
	 * http://www.test.com<br>
	 * http://163.com
	 */
	public boolean checkWebSite(String url) {
		// http://www.163.com
		String reg = "^(http)\\://(\\w+\\.\\w+\\.\\w+|\\w+\\.\\w+)";

		return startCheck(reg, url);
	}
	
	/**
	 * 验证电话号码、手机号码，电话号码可以带区号、也可以不带区号
	 */
	public boolean checkTelephone(String telephone) {
		String reg = "^[0]\\d{10,11}";
		String regular = "1[3,4,5,8]{1}\\d{9}";
		String reg3 = "^[1-9]\\d{6,7}";
		boolean b1 = startCheck(reg, telephone);
		boolean b2 = startCheck(regular, telephone);
		boolean b3 = startCheck(reg3, telephone);
		if (b1 == true || b2 == true || b3 == true) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 验证字符转是否全是数字
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()) {
			return false;
		}
		return true;
	} 


	
	public static void main(String[] args) throws Exception {  
//		RegexCheck regexChk = new RegexCheck();
//		System.out.println(regexChk.checkEmailWithSuffix("csds.dsina.com@sdsd.com.com.cn"));
		
//		System.out.println(regexChk.checkUsername("dasda?_sdas", 8, 12));
		
		System.err.println(RegexCheck.newInstance().checkHZ("sssss我s"));
//		String reg = "1[3,4,5,8]{1}\\d{9}";
//		Pattern pattern = Pattern.compile(reg);
//		Matcher matcher = pattern.matcher("051288880865");
//		System.out.println(matcher.matches());
    }  
}
