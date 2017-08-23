package tw.com.triplei.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.test.BaseTest;

@Slf4j
public class InsurerServiceTest extends BaseTest {

	@Autowired
	private InsurerService insurerService;
	
	@Test
	public void testCrud() {
		List<InsurerEntity> insurers = insurerService.getAll();
		
		for(InsurerEntity insurer:insurers) {
			log.debug("{}" , insurer);
		}
	}
}
