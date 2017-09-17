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
		
		StringBuffer sBuffer = new StringBuffer("<!DOCTYPE html><html>").append(
				"<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>").append(
				"<title></title><meta charset='utf-8' /></head>").append(
				"<body><h1>你好</h1>").append("<div style=''> 歡迎加入triplei   <br>").append(
				" 如果您的email信箱不支援連結點擊，請將上面的地址拷貝至您的瀏覽器(如Chrome)的網址欄進入。  <br>")
				.append( 
				"如果您還有任何問題，可以聯繫管理員(EMAIL) <br>").append(
				"我們對您產生的不便，深表歉意。  <br>").append(	
				"希望您在triplei度過快樂的時光!  <br>").append( 
				"<br><hr><div name='replycontent'>")
				.append(replycontent).append(
						"</div><hr>(這是一封自動產生的email，請勿回覆。)").append(
				"</div></body></html>");
		
	 	String content = sBuffer.toString();
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("triplei");
			helper.setTo(email);
			helper.setSubject("主題:回覆Email");
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
				.append("<body><div style=''> 親愛的用戶 您好 :  <br><br>")
				.append("歡迎加入Triple-I，請點選以下連結完成驗證手續以啟用您會員功能。  <br><br>")
				//.append("<a href=\"https://www.google.com.tw/\"></a> <br><br>")
				.append("<a href=\"" + url + " \">" + url + "</a> <br><br>")
				.append("希望您在triplei度過快樂的時光!  <br>")
				.append("Triple-I 團隊 歡迎您<br>")
				.append("<hr>")
				.append("(這是一封自動產生的email，請勿回覆。)</div></body></html>");
		
	 	String content = sBuffer.toString();
		
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom("triplei");
			helper.setTo(entity.getEmail());
			helper.setSubject("Triple-I會員註冊驗證信");
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
