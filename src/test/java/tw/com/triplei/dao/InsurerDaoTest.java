package tw.com.triplei.dao;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.test.BaseTest;

@Slf4j
public class InsurerDaoTest extends BaseTest {

	@Autowired
	private InsurerDao insurerDao;
	
	@Test
	public void testFindAll() {
		Collection<InsurerEntity> insurers = insurerDao.findAll();
		
		for(InsurerEntity insurer:insurers) {
			log.debug("{}", insurer);
		}
		
		
	}

}
