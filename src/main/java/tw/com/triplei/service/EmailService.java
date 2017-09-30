package tw.com.triplei.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.RecipientEntity;
import tw.com.triplei.entity.UserEntity;

@Slf4j
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private AdminUserService userService;

	public void sendReplyEmail(String email, String replycontent, LocalDateTime postTime, 
			String name, String askContent) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

	    String formatDateTime = postTime.format(formatter);
	    
		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
				.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
				.append("<title></title><meta charset='utf-8' /></head>")
				.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("親愛的"+name+"您好，<br /><br />")
				.append("我們已經於"+formatDateTime+"收到您的提問內容：<br /><br />")
				.append(askContent).append("<br /><br />").append("我們的回覆如下：<br />")
				.append(replycontent).append("<br /><br />").append("希望以上內容對您有所幫助，若還有任何關於保險的相關問題，歡迎再次到TRIPLE I的線上客服提問，<br />")
				.append("也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />").append("祝您順心<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>").append("</div></body></html>");

		String content = sBuffer.toString();

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("triplei");
			helper.setTo(email);
			helper.setSubject("問題回覆信函");
			helper.setText(content, true);
			// FileSystemResource file = new
			// FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
			// helper.addInline("virusattack", file);
			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	public void sendConfirmEmail(String email, LocalDateTime postTime, String name, String askContent) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");

	    String formatDateTime = postTime.format(formatter);
		
		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
				.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
				.append("<title></title><meta charset='utf-8' /></head>")
				.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("親愛的"+name+"您好，<br /><br />")
				.append("我們已經於"+formatDateTime+"收到您的提問內容：<br /><br />")
				.append(askContent).append("<br /><br />").append("我們會於24小時內回覆至您的信箱，謝謝!<br />")
				.append("祝您順心<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>").append("</div></body></html>");
		
		String content = sBuffer.toString();
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			
			helper.setFrom("triplei");
			helper.setTo(email);
			helper.setSubject("問題確認信函");
			helper.setText(content, true);
			// FileSystemResource file = new
			// FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
			// helper.addInline("virusattack", file);
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	public void sendRegisteredEmail(UserEntity entity) {

		String registeredCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
		entity.setRegisteredCode(registeredCode);
		userService.update(entity);
		log.debug("{}", entity);

		String url = "http://localhost:8080/registered/checked?uid=" + registeredCode;

		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
				.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
				.append("<title></title><meta charset='utf-8' /></head>")
				.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />")
				.append("親愛的 " + entity.getName() + " 您好，<br/><br/>").append("恭喜您完成TRILPE I的會員申請，只差最後一步囉！<br />")
				.append("請點擊下方連結完成認證程序：<br />")
				// .append("<a href=\"https://www.google.com.tw/\"></a>
				// <br><br>")
				.append("<a href=\"" + url + " \">" + url + "</a> <br><br>")
				.append("<br /> 提醒您，成功認證後可至網站的會員專區，更改您的密碼及基本資料。<br /><br />")
				.append("如有任何疑問請至網站的線上客服洽詢，也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

		String content = sBuffer.toString();

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("triplei");
			helper.setTo(entity.getEmail());
			helper.setSubject("Triple-I會員申請認證信函");
			helper.setText(content, true);
			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void sendNewPassword(UserEntity entity) {

		UserEntity userEntity = userService.getOne(entity.getId());
		log.debug("{}", userEntity);

		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
				.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
				.append("<title></title><meta charset='utf-8' /></head>")
				.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />")
				.append("親愛的 " + entity.getName() + " 您好，<br/><br/>").append("您的臨時密碼為:" + userEntity.getCheckPassword())
				.append("<br /> 提醒您，使用臨時密碼登入後，請更改您的密碼及基本資料。<br /><br />")
				.append("如有任何疑問請至網站的線上客服洽詢，也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

		String content = sBuffer.toString();

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("triplei");
			helper.setTo(userEntity.getEmail());
			helper.setSubject("Triple-I會員臨時密碼");
			helper.setText(content, true);
			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	//指派業務員通知業務員
		public void sendAlertEmailToSales(UserEntity entity, RecipientEntity recipient) {

			String name = entity.getName();
			log.debug("業務員姓名:{}", name);
			Date date = new Date(recipient.getModifiedTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String dateString = sdf.format(date);
			log.debug("時間:{}", dateString);
			String productName = recipient.getProduct().getLocalName();// 商品名稱
			String productCode = recipient.getProduct().getCode();// 商品代碼
			String payYear = recipient.getProduct().getYear() + "";// 年期
			String yearCode = recipient.getProduct().getYearCode();// 第n年
			String insureAmount = recipient.getProduct().getInsureAmount().toString();
			log.debug("保額:{}", insureAmount);
			String orderNo = recipient.getOrderNo(); //訂單標號
			String contactName = recipient.getName(); // 聯絡人
			String contactMethod = recipient.getTel(); // 聯絡方式
			String contactDate1 = recipient.getBookedTime_1(); // 聯絡時間1
			
			
			String contactDate2 = recipient.getBookedTime_2(); // 聯絡時間2
			if(contactDate2.endsWith("00")){
				contactDate2 = recipient.getBookedTime_2();
			}else{
				contactDate2 = "無";
			}
			
			String contactDate3 = recipient.getBookedTime_3(); // 聯絡時間3
			if(contactDate3.endsWith("00")){
				contactDate3 = recipient.getBookedTime_2();
			}else{
				contactDate3 = "無";
			}
			
			String toAge = recipient.getAge() + "";// 被保險人年齡
			String gender = "";
			// 被保險人性別
			if (recipient.getGender().equals("Male")) {
				gender = "男";
			} else if (recipient.getGender().equals("Female")) {
				gender = "女";
			}
			// 地址
			String address = recipient.getConvenienceStoreEntity().getCity()
					+ recipient.getConvenienceStoreEntity().getRegion()
					+ recipient.getConvenienceStoreEntity().getAddress();
			// 超商門市
			String storeName = recipient.getConvenienceStoreEntity().getStoreName();
			// 超商廠商
			String manufacturer = recipient.getConvenienceStoreEntity().getManufacturer();

			StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
					.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
					.append("<title></title><meta charset='utf-8' /></head>")
					.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("業務員 " + name + " 您好，<br /><br />")
					.append("管理員已經於 " + dateString + " 指派訂單給您：<br /><br />")
					.append("聯絡人資訊：<br />")
					.append("姓名：" + contactName + "     電話:" + contactMethod + "<br />")
					.append("1.方便聯絡時間 " + contactDate1 + "<br />").append("2.方便聯絡時間 " + contactDate2 + "<br />")
					.append("3.方便聯絡時間 " + contactDate3 + "<br /><br />")
					.append("被保人資料       性別:" + gender + "      年齡:" + toAge + " 歲<br />").append("訂單詳細如下:<br />")
					.append("訂單編號："+ orderNo +"<br />")
					.append("商品資訊<br /> " + " 年度 :" + yearCode + "年    " + productCode + " " + productName + "<br />")
					.append("保額：" + insureAmount + "萬     ").append("商品年期：" + payYear + "年<br /><br />")
					.append("預約地點：<br />").append(" " + manufacturer + " " + storeName + "門市<br />").append("預約地點：<br />")
					.append(" " + address + "<br /><br />").append("若還有任何關於訂單的相關問題，麻煩請與管理員聯絡!<br />")
					.append("祝您順心<br /><br />").append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

			String content = sBuffer.toString();

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom("triplei");
				helper.setTo(entity.getEmail());
				helper.setSubject("接收訂單通知");
				helper.setText(content, true);
				// FileSystemResource file = new
				// FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
				// helper.addInline("virusattack", file);
				mailSender.send(mimeMessage);

			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}
		
		//指派業務員通知管理員
		public void sendAlertEmailToAdmin(UserEntity admin,UserEntity entity, RecipientEntity recipient) {

			String name = entity.getName();
			log.debug("業務員姓名:{}", name);
			Date date = new Date(recipient.getModifiedTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String dateString = sdf.format(date);
			log.debug("時間:{}", dateString);
			String productName = recipient.getProduct().getLocalName();// 商品名稱
			String productCode = recipient.getProduct().getCode();// 商品代碼
			String payYear = recipient.getProduct().getYear() + "";// 年期
			String yearCode = recipient.getProduct().getYearCode();// 第n年
			String insureAmount = recipient.getProduct().getInsureAmount().toString();
			log.debug("保額:{}", insureAmount);
			String orderNo = recipient.getOrderNo(); //訂單標號
			String contactName = recipient.getName(); // 聯絡人
			String contactMethod = recipient.getTel(); // 聯絡方式
			String contactDate1 = recipient.getBookedTime_1(); // 聯絡時間1
			String contactDate2 = recipient.getBookedTime_2(); // 聯絡時間2
			if(contactDate2.endsWith("00")){
				contactDate2 = recipient.getBookedTime_2();
			}else{
				contactDate2 = "無";
			}
			
			String contactDate3 = recipient.getBookedTime_3(); // 聯絡時間3
			if(contactDate3.endsWith("00")){
				contactDate3 = recipient.getBookedTime_2();
			}else{
				contactDate3 = "無";
			}
			String toAge = recipient.getAge() + "";// 被保險人年齡
			String gender = "";
			// 被保險人性別
			if (recipient.getGender().equals("Male")) {
				gender = "男";
			} else if (recipient.getGender().equals("Female")) {
				gender = "女";
			}
			// 地址
			String address = recipient.getConvenienceStoreEntity().getCity()
					+ recipient.getConvenienceStoreEntity().getRegion()
					+ recipient.getConvenienceStoreEntity().getAddress();
			// 超商門市
			String storeName = recipient.getConvenienceStoreEntity().getStoreName();
			// 超商廠商
			String manufacturer = recipient.getConvenienceStoreEntity().getManufacturer();

			StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
					.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
					.append("<title></title><meta charset='utf-8' /></head>")
					.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("管理員 " + admin.getName() + " 您好，<br /><br />")
					.append("您已經於 " + dateString + " 指派訂單給業務員： "+name+"<br /><br />")
					.append("聯絡人資訊：<br />")
					.append("姓名：" + contactName + "     電話:" + contactMethod + "<br />")
					.append("1.方便聯絡時間 " + contactDate1 + "<br />").append("2.方便聯絡時間 " + contactDate2 + "<br />")
					.append("3.方便聯絡時間 " + contactDate3 + "<br /><br />")
					.append("被保人資料       性別:" + gender + "      年齡:" + toAge + " 歲<br />").append("訂單詳細如下:<br />")
					.append("訂單編號："+ orderNo +"<br />")
					.append("商品資訊<br /> " + " 年度 :" + yearCode + "年    " + productCode + " " + productName + "<br />")
					.append("保額：" + insureAmount + "萬     ").append("商品年期：" + payYear + "年<br /><br />")
					.append("預約地點：<br />").append(" " + manufacturer + " " + storeName + "門市<br />").append("預約地點：<br />")
					.append(" " + address + "<br /><br />")
					.append("祝您順心<br /><br />").append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

			String content = sBuffer.toString();

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom("triplei");
				helper.setTo(admin.getEmail());
				helper.setSubject("訂單通知");
				helper.setText(content, true);
				// FileSystemResource file = new
				// FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
				// helper.addInline("virusattack", file);
				mailSender.send(mimeMessage);

			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

		// public static void main(String[] args) {
		// String a = UUID.randomUUID().toString().replaceAll("-", "").substring(0,
		// 15);
		// System.out.println(a);
		// }
		
		
		//顧客下訂單寄給顧客
		public void sendAlertEmailToCustomer(UserEntity customer,RecipientEntity recipient) {

			
			
			String name = customer.getName();//會員姓名
			String nameC = recipient.getName();//聯絡人
			
			Date date = new Date(recipient.getCreatedTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String dateString = sdf.format(date);
			log.debug("時間:{}", dateString);
			String productName = recipient.getProduct().getLocalName();// 商品名稱
			String productCode = recipient.getProduct().getCode();// 商品代碼
			String payYear = recipient.getProduct().getYear() + "";// 年期
			String yearCode = recipient.getProduct().getYearCode();// 第n年
			String insureAmount = recipient.getProduct().getInsureAmount().toString();
			log.debug("保額:{}", insureAmount);
			String orderNo = recipient.getOrderNo(); //訂單標號
			String contactMethod = recipient.getTel(); // 聯絡方式
			String contactDate1 = recipient.getBookedTime_1(); // 聯絡時間1
			String contactDate2 = recipient.getBookedTime_2(); // 聯絡時間2
			if(contactDate2.endsWith("00")){
				contactDate2 = recipient.getBookedTime_2();
			}else{
				contactDate2 = "無";
			}
			
			String contactDate3 = recipient.getBookedTime_3(); // 聯絡時間3
			if(contactDate3.endsWith("00")){
				contactDate3 = recipient.getBookedTime_2();
			}else{
				contactDate3 = "無";
			}
			String toAge = recipient.getAge() + "";// 被保險人年齡
			String gender = "";
			// 被保險人性別
			if (recipient.getGender().equals("Male")) {
				gender = "男";
			} else if (recipient.getGender().equals("Female")) {
				gender = "女";
			}
			// 地址
			String address = recipient.getConvenienceStoreEntity().getCity()
					+ recipient.getConvenienceStoreEntity().getRegion()
					+ recipient.getConvenienceStoreEntity().getAddress();
			// 超商門市
			String storeName = recipient.getConvenienceStoreEntity().getStoreName();
			// 超商廠商
			String manufacturer = recipient.getConvenienceStoreEntity().getManufacturer();

			StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
					.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
					.append("<title></title><meta charset='utf-8' /></head>")
					.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("親愛的會員  " + name + " 您好，<br /><br />")
					.append("您已經於 " + dateString + " 選擇這筆保險商品：<br /><br />")
					.append("聯絡人資訊:<br />")
					.append("姓名:" + nameC + "<br />")
					.append("電話:" + contactMethod + "<br />")
					.append("1.方便聯絡時間 " + contactDate1 + "<br />").append("2.方便聯絡時間 " + contactDate2 + "<br />")
					.append("3.方便聯絡時間 " + contactDate3 + "<br /><br />")
					.append("被保人資料       性別:" + gender + "      年齡:" + toAge + " 歲<br />")
					.append("訂單詳細如下:<br />")
					.append("訂單編號："+ orderNo +"<br />")
					.append("商品資訊<br /> " + " 年度 :" + yearCode + "年    " + productCode + " " + productName + "<br />")
					.append("保額：" + insureAmount + "萬     ").append("商品年期：" + payYear + "年<br /><br />")
					.append("預約地點：<br />").append(" " + manufacturer + " " + storeName + "門市<br />").append("預約地點：<br />")
					.append(" " + address + "<br /><br /><br />")
					.append("如有任何疑問請至網站的線上客服洽詢，也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
					.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
					.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
					.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

			String content = sBuffer.toString();

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom("triplei");
				helper.setTo(customer.getEmail());
				helper.setSubject("選購保險通知");
				helper.setText(content, true);
				mailSender.send(mimeMessage);

			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}
		
		//顧客下訂單寄給管理員
		public void sendAlertEmailToAdminC(UserEntity admin,UserEntity customer,RecipientEntity recipient) {

			String name = admin.getName();//管理員姓名
			String cName = customer.getName();//會員姓名
			String fName = recipient.getName();//聯絡人姓名
			Date date = new Date(recipient.getCreatedTime().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String dateString = sdf.format(date);
			log.debug("時間:{}", dateString);
			String productName = recipient.getProduct().getLocalName();// 商品名稱
			String productCode = recipient.getProduct().getCode();// 商品代碼
			String payYear = recipient.getProduct().getYear() + "";// 年期
			String yearCode = recipient.getProduct().getYearCode();// 第n年
			String insureAmount = recipient.getProduct().getInsureAmount().toString();
			log.debug("保額:{}", insureAmount);
			String orderNo = recipient.getOrderNo(); //訂單標號
			String contactMethod = recipient.getTel(); // 聯絡方式
			String contactDate1 = recipient.getBookedTime_1(); // 聯絡時間1
			String contactDate2 = recipient.getBookedTime_2(); // 聯絡時間2
			if(contactDate2.endsWith("00")){
				contactDate2 = recipient.getBookedTime_2();
			}else{
				contactDate2 = "無";
			}
			
			String contactDate3 = recipient.getBookedTime_3(); // 聯絡時間3
			if(contactDate3.endsWith("00")){
				contactDate3 = recipient.getBookedTime_2();
			}else{
				contactDate3 = "無";
			}
			String toAge = recipient.getAge() + "";// 被保險人年齡
			String gender = "";
			// 被保險人性別
			if (recipient.getGender().equals("Male")) {
				gender = "男";
			} else if (recipient.getGender().equals("Female")) {
				gender = "女";
			}
			// 地址
			String address = recipient.getConvenienceStoreEntity().getCity()
					+ recipient.getConvenienceStoreEntity().getRegion()
					+ recipient.getConvenienceStoreEntity().getAddress();
			// 超商門市
			String storeName = recipient.getConvenienceStoreEntity().getStoreName();
			// 超商廠商
			String manufacturer = recipient.getConvenienceStoreEntity().getManufacturer();

			StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
					.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
					.append("<title></title><meta charset='utf-8' /></head>")
					.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />").append("親愛的管理員  " + name + " 您好，<br /><br />")
					.append("會員 "+ cName +" 已經於 " + dateString + " 選擇這筆保險商品：<br /><br />")
					.append("聯絡資訊:<br />")
					.append("姓名:" + fName + "<br />")
					.append("電話:" + contactMethod + "<br />")
					.append("1.方便聯絡時間 " + contactDate1 + "<br />").append("2.方便聯絡時間 " + contactDate2 + "<br />")
					.append("3.方便聯絡時間 " + contactDate3 + "<br /><br />")
					.append("被保人資料       性別:" + gender + "      年齡:" + toAge + " 歲<br />")
					.append("訂單詳細如下:<br />")
					.append("訂單編號："+ orderNo +"<br />")
					.append("商品資訊<br /> " + " 年度 :" + yearCode + "年    " + productCode + " " + productName + "<br />")
					.append("保額：" + insureAmount + "萬     ").append("商品年期：" + payYear + "年<br /><br />")
					.append("預約地點：<br />").append(" " + manufacturer + " " + storeName + "門市<br />").append("預約地點：<br />")
					.append(" " + address + "<br /><br /><br />")
					.append("<h4>請盡速指派業務員處理!!!!!!!!!</h4><br /><br /><br />")
					.append("如有任何疑問請至網站的線上客服洽詢，也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
					.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
					.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
					.append("TRIPLE I<br />").append("最專業的儲蓄險網站<br /> </div></body></html>");

			String content = sBuffer.toString();

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			try {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				helper.setFrom("triplei");
				helper.setTo(admin.getEmail());
				helper.setSubject("會員選購保險通知");
				helper.setText(content, true);
				mailSender.send(mimeMessage);

			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

	}


	