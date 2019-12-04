package com.yj.bj.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.yj.bj.constant.Constaint;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class YJ {

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
	static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	static SimpleDateFormat patternFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static Logger getLog() {
		return Logger.getLogger(YJ.class);
	}

	public static String formatDateWithPattern(Date date) {
		return patternFormat.format(date);
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static String formattime(Date date) {
		return timeFormat.format(date);
	}

	public static Date formatDay(String time) {
		try {
			return dayFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成随机主键
	 * 
	 * @return
	 */
	public static long genItemId() {
		// 取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		// long millis = System.nanoTime();
		// 加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		// 如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}

	public static String formatNum(double sum) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(sum);
	}

	/**
	 * 根据权重生成随机红包金额
	 * 
	 * @param list
	 * @return
	 */
	/*
	 * public static double getRandomMoeny(List<RandomPacket> list) { double
	 * sumWeight = 0; for (RandomPacket ran : list) { //总权重 sumWeight +=
	 * ran.getWeight(); } Random random = new Random(); double randomNumber =
	 * Math.random(); //最低权重 double d1 = 0; //最高权重 double d2 = 0; double result
	 * = 0; double min =0; double max = 0; for (int i = 0; i < list.size(); i++)
	 * { d2 += Double.parseDouble(String.valueOf(list.get(i).getWeight())) /
	 * sumWeight; if (i == 0) { d1 = 0; } else { d1 +=
	 * Double.parseDouble(String.valueOf(list.get(i-1).getWeight())) /
	 * sumWeight; } if (randomNumber >= d1 && randomNumber <= d2) { min =
	 * list.get(i).getLeastMoney(); max = list.get(i).getMaxMoney(); result =
	 * random.nextInt((int) max) % (max - min + 1) + min; break; } } return
	 * result; }
	 */

	/**
	 * 将铂金传入的参数转为map格式
	 * 
	 * @param params
	 * @return
	 */
	public static Map<String, Object> parseParam(String params) {
		String[] p = params.split("&");
		Map<String, Object> m = new HashMap<String, Object>();
		for (String s : p) {
			String[] split = s.split("=");
			m.put(split[0], split[1]);
		}
		return m;
	}

	/**
	 * 判断红包是否过期
	 * 
	 * @param days
	 *            时间
	 * @param pdTime
	 *            红包发生时间
	 * @return
	 */
	public static boolean isInvlid(Date startDay, int days, String pdTime) {
		Date pDate = formatDay(pdTime);
		Date SysDate = xDays(startDay, days);
		// 20170712在20170722 之前为true
		if (SysDate.before(pDate)) {
			return true;
		}
		return false;
	}

	public static boolean isInvlid(int days, String pdTime) {
		Date day = xDays(null, days);
		if (day.before(YJ.formatDay(pdTime))) {
			return false;
		}
		return true;
	}

	public static Date xDays(Date startDay, int days) {
		Calendar c = Calendar.getInstance();
		if (startDay == null) {
			c.setTime(new Date());
		} else {
			c.setTime(startDay);
		}
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	/**
	 * 生成极光推送对象PushPayload（采用java SDK）
	 * @param alias
	 * @param alert
	 * @return PushPayload
	 */
	public static PushPayload buildPushObject_android_ios_alias_alert(String alias, String alert) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.addExtra("type", "infomation")
												.setAlert(alert).build())
								.addPlatformNotification(
										IosNotification.newBuilder()
												.addExtra("type", "infomation")
												.setAlert(alert).build())
								.build())
				.setOptions(Options.newBuilder().setApnsProduction(true)// true-推送生产环境  false-推送开发环境（测试使用参数）
						.setTimeToLive(90)// 消息在JPush服务器的失效时间（测试使用参数）
						.build()).build();
	}
	

	/**
	 * 极光推送方法(采用java SDK)
	 * 
	 * @param alias
	 * @param alert
	 * @return PushResult
	 */
	public static PushResult push(String alias, String alert) {
		JPushClient jpushClient = new JPushClient(Constaint.Secret, Constaint.APPKEY, null,ClientConfig.getInstance());
		PushPayload payload = buildPushObject_android_ios_alias_alert(alias, alert);
		try {
			return jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			YJ.getLog().error("连接失败...", e);
			return null;
		} catch (APIRequestException e) {
			YJ.getLog().error("推送出错", e);
			YJ.getLog().info("HTTP Status: " + e.getStatus());
			YJ.getLog().info("Error Code: " + e.getErrorCode());
			YJ.getLog().info("Error Message: " + e.getErrorMessage());
			YJ.getLog().info("Msg ID: " + e.getMsgId());
			return null;
		}
	}
	 
	public static List<String> getDayListOfMonth() {
		List<String> list = new ArrayList<String>();
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int year = aCalendar.get(Calendar.YEAR);// 年份
		int month = aCalendar.get(Calendar.MONTH) + 1;// 月份
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		for (int i = 1; i <= day; i++) {
			String aDate = String.valueOf(year) + "0" + month;
			if (i <= 9) {
				String aDate1 = aDate + "0" + i;
				list.add(aDate1);
			} else {
				String aDate1 = aDate + i;
				list.add(aDate1);
			}

		}
		return list;
	}

	public static String getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String d = YJ.formatDate(calendar.getTime());
		return d;
	}

	public static String postFile2(String actionUrl,
			Map<String, String> params, Map<String, File> files, String key)
			throws IOException {
		String BOUNDARY = "---------------------------"
				+ System.currentTimeMillis();// 分割符
		String PREFIX = "--"; // 前缀
		String LINEND = "\r\n"; // 换行符
		String MULTIPART_FROM_DATA = "multipart/form-data";// 数据类型
		String CHARSET = "UTF-8";// 字符编码

		URL uri = new URL(actionUrl);
		HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
		conn.setReadTimeout(5 * 1000); // 缓存的最长时间
		conn.setDoInput(true);// 允许输入
		conn.setDoOutput(true);// 允许输出
		conn.setUseCaches(false); // 不允许使用缓存

		// conn.setRequestProperty("Authorization", "Basic " +
		// Base64.encodeBytes((username + ":" + passwd).getBytes()));//认证
		conn.setRequestProperty("Authorization", key);// 认证

		conn.setRequestMethod("POST");
		conn.setRequestProperty("connection", "keep-alive");
		// conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
				+ ";boundary=" + BOUNDARY);

		// 首先组拼文本类型的参数
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(PREFIX);
			sb.append(BOUNDARY);
			sb.append(LINEND);
			sb.append("Content-Disposition: form-data; name=\""
					+ entry.getKey() + "\"" + LINEND);
			// sb.append("Content-Type: text/plain; charset=" + CHARSET +
			// LINEND);
			sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
			sb.append(LINEND);
			sb.append(entry.getValue());
			sb.append(LINEND);

		}
		// System.out.println(sb);

		DataOutputStream outStream = new DataOutputStream(
				conn.getOutputStream());
		outStream.write(sb.toString().getBytes());
		// fos.write(sb.toString().getBytes());
		InputStream in = null;
		// 发送文件数据
		if (files != null) {
			for (Map.Entry<String, File> file : files.entrySet()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				// name是post中传参的键 filename是文件的名称
				sb1.append("Content-Disposition: form-data; name=\""
						+ file.getKey() + "\"; filename=\"" + file.getValue()
						+ "\"" + LINEND);
				sb1.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINEND);
				sb1.append(LINEND);
				// System.out.println(sb1);
				outStream.write(sb1.toString().getBytes());
				// fos.write(sb1.toString().getBytes());
				InputStream is = new FileInputStream(file.getValue());

				int bytesAvailable;
				while ((bytesAvailable = is.available()) > 0) {
					int bufferSize = Math.min(bytesAvailable, 4096);
					byte[] buffer = new byte[bufferSize];
					int bytesRead = is.read(buffer, 0, bufferSize);
					outStream.write(buffer, 0, bytesRead);
				}

				is.close();
				outStream.write(LINEND.getBytes());
				// fos.write(LINEND.getBytes());
			}

			// 请求结束标志

		}
		byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
		outStream.write(end_data);
		// fos.write(end_data);
		outStream.flush();
		// fos.flush();
		// fos.close();
		outStream.close();
		// 得到响应码
		StringBuilder sb2 = null;
		int res = conn.getResponseCode();
		if (res == 200) {
			in = conn.getInputStream();
			int ch;
			sb2 = new StringBuilder();
			while ((ch = in.read()) != -1) {
				sb2.append((char) ch);
			}
			// Util.print("sb222-->"+sb2);
		} else {
			return "error";
		}
		in.close();
		outStream.close();
		conn.disconnect();
		// return sb2.toString();
		String message = new String(sb2.toString().getBytes("iso-8859-1"),
				"UTF-8");
		return message;
	}

	/*
	 * public static void reduceImg(String imgsrc, String imgdist, int
	 * widthdist, int heightdist, Float rate) { try { File srcfile = new
	 * File(imgsrc); // 检查文件是否存在 if (!srcfile.exists()) { return; } //
	 * 如果rate不为空说明是按比例压缩 if (rate != null && rate > 0) { // 获取文件高度和宽度 int[]
	 * results = getImgWidth(srcfile); if (results == null || results[0] == 0 ||
	 * results[1] == 0) { return; } else { widthdist = (int) (results[0] *
	 * rate); heightdist = (int) (results[1] * rate); } } // 开始读取文件并进行压缩 Image
	 * src = javax.imageio.ImageIO.read(srcfile); BufferedImage tag = new
	 * BufferedImage((int) widthdist, (int) heightdist,
	 * BufferedImage.TYPE_INT_RGB);
	 * 
	 * tag.getGraphics().drawImage( src.getScaledInstance(widthdist, heightdist,
	 * Image.SCALE_SMOOTH), 0, 0, null);
	 * 
	 * FileOutputStream out = new FileOutputStream(imgdist); JPEGImageEncoder
	 * encoder = JPEGCodec.createJPEGEncoder(out); encoder.encode(tag);
	 * out.close();
	 * 
	 * } catch (IOException ex) { ex.printStackTrace(); } }
	 */

	public static int[] getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = { 0, 0 };
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图宽
			result[1] = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
