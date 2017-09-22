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
		System.out.println("hilda" + passwordEncoder.encode("user001"));
		System.out.println("hilda" + passwordEncoder.encode("user002"));
		System.out.println("hilda" + passwordEncoder.encode("user003"));
		System.out.println("hilda" + passwordEncoder.encode("user004"));
		System.out.println("hilda" + passwordEncoder.encode("user005"));
		System.out.println("hilda" + passwordEncoder.encode("user006"));
		System.out.println("hilda" + passwordEncoder.encode("user007"));
		System.out.println("hilda" + passwordEncoder.encode("user008"));
		
	}
}
