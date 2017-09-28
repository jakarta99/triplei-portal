package tw.com.triplei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.UserEntity;

@Slf4j
public class UserDetailsSocialService implements SocialUserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		final UserEntity user = userDao.findByProviderUserId(userId);
	    if (user == null) {
	      throw new UsernameNotFoundException("帳號不存在");
	    }

	   if (!user.getEnabled()) {
	      throw new DisabledException("此帳號已經失效");
	    }
	   
		return user;
	}

}
