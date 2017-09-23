package tw.com.triplei.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncodeTest extends BaseTest {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void encodeTest() {
		String a = passwordEncoder.encode("user001");
		String b = passwordEncoder.encode("user001");
		System.out.println("hilda" + a);
		System.out.println("hilda" + b);
		
		Boolean c = passwordEncoder.matches("user001", a);
		log.debug("c:{}",c);
		
	}
}
