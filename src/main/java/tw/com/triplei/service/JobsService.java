package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tw.com.triplei.entity.UserEntity;

@Component
public class JobsService {
	
	@Autowired
	private UserService userService; 
	
	              //秒   分  時  x日  每個月  星期一 
	@Scheduled(cron="0 0 0 ? * 1")
	public void refreshWishTimes(){
		List<UserEntity> users = userService.getAll();
		for(UserEntity user:users){
			user.setRemainWishTimes(true);
			userService.getDao().save(user);
		}
	}
	
}
