package tw.com.triplei.test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {
	@Autowired
	private JavaMailSender mailSender;
//	@Autowired
//	private VelocityEngine velocityEngine;
	
//	@Test
//	public void sendSimpleMail() throws Exception {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("triplei5432@gmail.com");
//		message.setTo("triplei5432@gmail.com");
//		message.setSubject("主題:測試郵件");
//		message.setText("測試郵件內容");
//		mailSender.send(message);
//	}
	
	
//	@Test
//	public void sendAttachmentsMail() throws Exception {
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		helper.setFrom("triplei5432@gmail.com");
//		helper.setTo("triplei5432@gmail.com");
//		helper.setSubject("主題:測試附件");
//		helper.setText("有附件的郵件");
//		FileSystemResource file = new FileSystemResource(new File("C:/Users/Student/Desktop/virusattack.jpg"));
//		helper.addAttachment("附件-1.jpg", file);
//		helper.addAttachment("附件-2.jpg", file);
//		mailSender.send(mimeMessage);
//	}
	
	
	
//	@Test
//	public void sendInlineMail() throws Exception {
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		helper.setFrom("triplei5432@gmail.com");
//		helper.setTo("monkey81817@gmail.com");
//		helper.setSubject("主題:嵌入靜態資源");
//		helper.setText("<html><body><h1 style=\"color:red;\">這是一個病毒網頁</h1></br></br><img src=\"cid:virusattack\" ></body></html>", true);
//		FileSystemResource file = new FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
//		helper.addInline("virusattack", file);
//		mailSender.send(mimeMessage);
//	}
	
	
//	@Test
//	public void sendTemplateMail() throws Exception {
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		helper.setFrom("triplei5432@gmail.com");
//		helper.setTo("triplei5432@gmail.com");
//		helper.setSubject("主題:模板郵件");
//		Map<String, Object> model = new HashedMap();
//		model.put("username", "abc");
//		String text = VelocityEngineUtils.mergeTemplateIntoString(
//				velocityEngine, "template.vm", "UTF-8", model);
//		FileSystemResource file = new FileSystemResource("C:/Users/Student/Desktop/virusattack.jpg");
//		helper.addInline("virusattack", file);
//		helper.setText(text, true);
//		mailSender.send(mimeMessage);
//	}
	
	
	@Test
public void sendEmail() {
		
	String email= "triplei5432@gmail.com";
	String replycontent = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
