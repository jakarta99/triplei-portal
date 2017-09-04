package tw.com.triplei.test;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {
	@Autowired
	private JavaMailSender mailSender;
//	@Autowired
//	private VelocityEngine velocityEngine;
	
	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("triplei5432@gmail.com");
		message.setTo("dunk800034@gmail.com");
		message.setSubject("主題:測試郵件");
		message.setText("測試郵件內容");
		mailSender.send(message);
	}
	
	
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
	
}
