package tw.com.triplei.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.entity.WishEntity;
import tw.com.triplei.service.ArticleService;
import tw.com.triplei.service.WishService;

@Slf4j
@Controller
@RequestMapping("/wish")
public class WishController {

	@Autowired
	private WishService wishService;
	

	@PostMapping
	@ResponseBody
	public AjaxResponse<WishEntity> insert(final Model model,@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "brand") String brand,@RequestParam(value = "name") String name,WishEntity form) {

		AjaxResponse<WishEntity> response = new AjaxResponse<WishEntity>();
		try {
			
			if(!file.isEmpty()){
				String location = wishService.imageUpload(file);
				form.setImage1(location);
			}else{
				form.setImage1("");
			}

			form.setBrand(brand);
			form.setName(name);
			String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
			form.setWishTime(date);
			log.debug("userDetails: {}",date);
			form.setTime(new Date().getTime());
			log.debug("time() {}",form.getTime());
			
			form.setCreatedTime(new Timestamp(new Date().getTime()));
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			form.setCreatedBy(userDetails.getUsername());
			log.debug("CreatedBy() {}",form.getCreatedBy());
			log.debug("userDetails.getUsername() {}",userDetails.getUsername());
			boolean result = wishService.makeAWish(userDetails.getUsername());
			log.debug("result {}",result);
			
			if(result){
				final WishEntity insertResult = wishService.insert(form);				
				response.setData(insertResult);				
			}
			
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}

		log.debug("{}", response);

		return response;
	}
	

}
