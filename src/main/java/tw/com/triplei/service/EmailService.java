package tw.com.triplei.service;

import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.UserEntity;

@Slf4j
@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;

	public void sendEmail(String email, String replycontent) {
		
		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>")
				.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>")
				.append("<title></title><meta charset='utf-8' /></head>")
				.append("<body><div style=''>   <hr>(此為系統發送信件，請勿回覆)<hr />")
				.append("親愛的xxx您好，<br /><br />")
				.append("我們已經於 xxxxxxxxxx年xxxxxxx月xxxxx日 xx午xx:xx收到您的提問內容：<br /><br />")
				.append("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br /><br />")
				.append("我們的回覆如下：<br />")
				.append(replycontent)
				.append("希望以上內容對您有所幫助，若還有任何關於保險的相關問題，歡迎再次到TRIPLE I的線上客服提問，<br />")
				.append("也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
				.append("祝您順心<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />")
				.append("最專業的儲蓄險網站<br /> </div></body></html>")
				.append("</div></body></html>");
		
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
				.append("親愛的xxx您好，<br /><br />")
				.append("恭喜您完成TRILPE I的會員申請，只差最後一步囉！<br />")
				.append("請點擊下方連結完成認證程序：<br />")
				//.append("<a href=\"https://www.google.com.tw/\"></a> <br><br>")
				.append("<a href=\"" + url + " \">" + url + "</a> <br><br>")
				.append("<br /> 提醒您，成功認證後可至網站的會員專區，更改您的密碼及基本資料。<br /><br />")
				.append("如有任何疑問請至網站的線上客服洽詢，也歡迎您加入TRIPLE I 粉絲專頁 關注我們的最新消息！<br /><br />")
				.append("找不到適合自己的儲蓄險嗎？立即使用TRIPLE I的商品查詢功能吧！<br /><br />")
				.append("<p><a href=\"http://localhost:8080/product/list\">→立即試算←</a></p><br />")
				.append("TRIPLE I<br />")
				.append("最專業的儲蓄險網站<br /> </div></body></html>");
		
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
	
//	public static void main(String[] args) {
//		String a = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 15);
//		System.out.println(a);
//	}

}
