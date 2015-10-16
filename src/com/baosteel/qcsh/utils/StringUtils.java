package com.baosteel.qcsh.utils;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.TextUtils;

/**
 * @author blue
 * 
 */
public class StringUtils {
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	private final static String PHONE = "^1[3,4,5,7,8]\\d{9}$";

	private final static String ID_CARD = "([0-9]{17}([0-9]|X))|([0-9]{15})";

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> HourFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}
	};

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		if (StringUtils.isBlank(sdate))
			return null;
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param sdata
	 * @return
	 */
	public static String toHour(String starttime, String endtime, String curtime) {

		Date start = StringUtils.toDate(starttime);
		Date end = StringUtils.toDate(endtime);
		Date nowTime = StringUtils.toDate(curtime);

		long daymin = 1000 * 60 * 60 * 24;
		long startSecond = start.getTime();
		long endSecond = end.getTime();
		long nowSecond = nowTime.getTime();

		if (nowSecond < startSecond) {
			long sec = startSecond - nowSecond;
			if (sec > 0 && sec < daymin) {
				return HourFormater.get().format(start);
			}
			if (sec > 0 && sec < daymin * 2) {
				return "明天" + HourFormater.get().format(start);
			}
			if (sec > 0 && sec < daymin * 3) {
				return "后天" + HourFormater.get().format(start);
			}
			if (sec > 0) {
				return "即将开始";
			}
		} else if (nowSecond < endSecond) {
			long sec = nowSecond - startSecond;
			if (sec > 0 && sec < daymin) {
				return HourFormater.get().format(start);
			}
			if (sec > 0 && sec < daymin * 2) {
				return "昨天" + HourFormater.get().format(start);
			}
			if (sec > 0 && sec < daymin * 3) {
				return "前天" + HourFormater.get().format(start);
			}
			if (sec > 0) {
				return "正在进行";
			}
		} else if (nowSecond > endSecond) {
			return "已经结束";
		}

		// Date startTime = toDate(starttime);
		// Date today = new Date();
		// Date endTime = toDate(endtime);
		// long sec = startTime.getTime() - today.getTime();
		// if (sec > 0 && sec < 1000 * 60 * 60 * 24) {
		// return HourFormater.get().format(startTime);
		// }
		// if (sec > 0 && sec < 1000 * 60 * 60 * 24 * 2) {
		// return "明天" + HourFormater.get().format(startTime);
		// }
		// if (sec > 0 && sec < 1000 * 60 * 60 * 24 * 3) {
		// return "后天" + HourFormater.get().format(startTime);
		// }
		// if (sec > 0) {
		// return "即将开始";
		// }
		// if (sec < 0 && sec > -1000 * 60 * 60 * 24) {
		// return "昨天" + HourFormater.get().format(startTime);
		// }
		// if (sec < 0 && sec > -1000 * 60 * 60 * 24 * 2) {
		// return "前天" + HourFormater.get().format(startTime);
		// }
		// if (sec < 0) {
		// return "已经结束";
		// }
		return "Unknown";
	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input) || "null".equals(input) || "-1".equals(input) || "NULL".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmpty(Object arg0) {
		if (arg0 == null)
			return true;
		return false;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 判断是否为手机号码
	 * 
	 * @param phoneNumber
	 * @return
	 */
	public static boolean isPhoneNumber(String phoneNumber) {

		return phoneNumber.matches(PHONE);
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	public static String getURLEncoder(String eStr) {
		String str = "";
		try {
			str = URLEncoder.encode(eStr, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * is null or its length is 0 or it is made by space
	 * 
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot;  &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 * @return if string is null or its size is 0 or it is made by space, return
	 *         true, else return false.
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 周期转换 描述这个方法的作用
	 * 
	 * @param arg0
	 * @return
	 * @Exception 异常对象
	 */
	public static String formapOrderDateForDay(String arg0) {
		String temp = "";

		if (arg0 == null) {
			return temp;
		}

		String[] tempArr = arg0.split(",");

		if (tempArr.length == 0) {
			return temp;
		}

		for (String string : tempArr) {
			String _tempName = "";
			int tempIndex = Integer.parseInt(string);
			switch (tempIndex) {
			case 1:
				_tempName = "周一";
				break;
			case 2:
				_tempName = "周二";
				break;
			case 3:
				_tempName = "周三";
				break;
			case 4:
				_tempName = "周四";
				break;
			case 5:
				_tempName = "周五";
				break;
			case 6:
				_tempName = "周六";
				break;
			case 7:
				_tempName = "周日";
				break;

			default:
				break;
			}
			temp = temp + _tempName + "、";
		}

		return temp.substring(0, temp.lastIndexOf("、"));
	}

	/**
	 * 像素值转换成dp
	 * 
	 * @param mContext
	 * @param px
	 * @return
	 */
	public static int PxToDp(Context mContext, int px) {
		final float scale = mContext.getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/**
	 * DP转成像素
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int DpToPx(Context context, int dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale);
	}

	public static String formapOrderDateForDayTag(String arg0) {
		String temp = "";

		if (arg0 == null) {
			return temp;
		}

		String[] tempArr = arg0.split(",");

		if (tempArr.length == 0) {
			return temp;
		}

		for (String string : tempArr) {
			temp = temp + string + ",";
		}

		return temp.substring(0, temp.lastIndexOf(","));
	}

	private static long lastClickTime;

	/**
	 * 防止按钮连续点击,导致界面或方法执行多次
	 * 
	 * @return
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	// 将绝对路径转为
	public static String replaceImgSrc(String content, String replaceHttp) {
		// content = content.replace("\n", "");
		// content = content.replace("\r", "");
		// content = content.replace("\t", "");
		// String patternStr = "^.*<img\\s*.*\\s*src=\\\"(.*?)\\\"\\s*.*>.*$";
		// Pattern pattern = Pattern.compile(patternStr);
		if (content != null) {
			Pattern pattern = Pattern.compile("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"]?\\s.*?>", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(content);
			// 如果匹配到了img
			while (matcher.find()) {
				if (matcher.group(1).indexOf("http") < 0)
					content = content.replaceAll(matcher.group(1), (replaceHttp + matcher.group(1)));
			}
		}
		return content;
	}

	public static String formatHtml(String content) {
		String data = "<style>img{ max-width:100%;height:auto;} </style>" + "<body>" + content + "</body";
		return data;
	}

	/**
	 * 判断是否是一个http url
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isHttpUrl(String url) {
		if (TextUtils.isEmpty(url)) {
			return false;
		}
		return url.startsWith("http") || url.startsWith("www.");
	}

	/**
	 * 解析一个url地址，将其参数以map方式返回
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> parseHttpParams(String url) {
		Map<String, String> params = new HashMap<String, String>();

		if (url.contains("?") && url.contains("=")) {
			String paramsArray = url.substring((url.indexOf("?") + 1));
			String[] array = paramsArray.split("&");
			String[] keyValue = null;
			for (int i = 0; i < array.length; i++) {
				keyValue = array[i].split("=");
				params.put(keyValue[0], keyValue[1]);
			}
		}
		return params;
	}

	/*
	 * 将手机号码的中间四位替换为“*”
	 */
	public static String ensurePhoneNum(String phoneNum) {
		if (null != phoneNum) {
			try{
				if (phoneNum.contains("*")) {
	
					// 字符串本身已经是带*号的，不做处理，直接返回
					return phoneNum;
				} else {
					return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11);
				}
			}catch(Exception e){
				//防止字符串截取，越界异常
				return phoneNum;
			}
		}
		return phoneNum;
	}

	/*
	 * 将邮箱号码的中间四位替换为“*”
	 */
	public static String ensureEmailAddress(String emailAddr) {
		try {
			if (emailAddr.contains("***")) {
				return emailAddr;
			}
			return emailAddr.substring(0, 2) + "****" + emailAddr.substring(6, emailAddr.length());
		} catch (Exception e) {
			return emailAddr;
		}
	}

	/*
	 * 判断密码强度 1、纯数字或者字母或特殊字符的时候为弱 2、数字+字母，数字+特殊字符，字母+特殊字符为中 3、数字+字母+特殊字符为强
	 */
	public static int checkPwLevel(String pw) {
		int numCount = 0;
		int letterCount = 0;
		int specialChar = 0;
		if (pw.length() <= 6) {
			return 1;
		} else {
			for (int i = 0; i < pw.length(); i++) {
				if (Character.isDigit(pw.charAt(i))) {
					numCount = 1;
				} else if (Character.isLetter(pw.charAt(i))) {
					letterCount = 1;
				} else {
					specialChar = 1;
				}
			}
			return numCount + letterCount + specialChar;
		}

	}

	/**
	 * 
	 * unicode 转换成 中文
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {

			aChar = theString.charAt(x++);

			if (aChar == '\\') {
				aChar = theString.charAt(x++);

				if (aChar == 'u') {

					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {

						aChar = theString.charAt(x++);

						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}
	
	/**
	 * 给imgUrl添加图片尺寸
	 * @param imgUrls
	 * @param picSize
	 * @return
	 */
	public static ArrayList<String> getImageUrls(ArrayList<String> imgUrls, String picSize){
		ArrayList<String> newUrls = new ArrayList<String>();
		for(int i=0; i<imgUrls.size(); i++){
			
			String newUrl = addPicSizeInImgUrl(imgUrls.get(i), picSize);
			newUrls.add(i, newUrl);
		}
		return newUrls; 
	}
	
	/**
	 * 给imgUrl添加图片尺寸
	 * @param originUrl
	 * @param picSize
	 * @return
	 */
	public static String addPicSizeInImgUrl(String originUrl, String picSize){

		if(TextUtils.isEmpty(picSize)){
			return originUrl;
		}

		int index = originUrl.lastIndexOf("/");
		int point = originUrl.lastIndexOf(".");
		
    	String pre = originUrl.substring(0, index+1);
    	String picName = originUrl.substring(index+1, point);
    	String end = originUrl.substring(point);
    	
    	//则说明图片本身就是带了nnnXnnn尺寸的图片url，需要重新处理
    	if(picName.contains("_") && picName.contains("X")){
    		picName = picName.split("_")[0];
    	}
    	
    	originUrl = pre + picName + "_" + picSize + end;
    	return originUrl;
	}

	/**
	 * 将手机号码中间几位替换成*
	 * @param phoneNum
	 * @return
	 */
	public static String changePhoneForrmat(String phoneNum){
		if(TextUtils.isEmpty(phoneNum)){
			return null;
		}else if(phoneNum.length()<11){
			return null;
		}
		String forrmatNum="";
		int leghth=phoneNum.length();
		String start=phoneNum.substring(0, 3);
		String end=phoneNum.substring(leghth-4,leghth);
		forrmatNum=start+"******"+end;
		return forrmatNum;
	}

	/**
	 * 将邮箱名2位以后的替换成*
	 * @param email
	 * @return
	 */
	public static String changeEmailForrmat(String email){
		if(TextUtils.isEmpty(email)){
			return null;
		}
		if(!email.contains("@")){
			return null;
		}
		String forrmatNum="";
		int index=email.indexOf("@");
		String end=email.substring(index, email.length());
		String start="";
		StringBuffer sb=new StringBuffer();
		if(index<3){
			for (int i=0;i<6-index;i++){
				sb.append("*");
			}
			start=email.substring(0,index)+sb;
		}else{
			sb.append("****");
			start=email.substring(0,2)+sb;
		}
		forrmatNum=start+end;
		return forrmatNum;
	}


	/**
	 * 判断是否为身份证号码
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCard(String idCard) {

		return idCard.matches(ID_CARD);
	}
}
