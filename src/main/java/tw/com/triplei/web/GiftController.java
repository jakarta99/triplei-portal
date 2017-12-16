package tw.com.triplei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.enums.GiftType;
import tw.com.triplei.service.GetImageService;
import tw.com.triplei.service.GiftService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/gift")
public class GiftController {

	@Autowired
	private GiftService giftService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GetImageService getImageService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}", userDetails);
			
			UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
			log.debug("getUser : {}", user);
			
			model.addAttribute("userPoint",user.getRemainPoint());
			model.addAttribute("audittingPoint",user.getAudittingPoint());
			model.addAttribute("exchangedPoint",user.getExchangedPoint());
		} catch (Exception e) {
			model.addAttribute("userPoint",0);
			model.addAttribute("audittingPoint",0);
			model.addAttribute("exchangedPoint",0);
			model.addAttribute("users","not null");
		}
		
//		model.addAttribute("modelv", giftService.getTypeTop3("VOUCHERS"));
//		model.addAttribute("modelf", giftService.getTypeTop3("FURNITURES"));
//		model.addAttribute("modele", giftService.getTypeTop3("ELETRONICS"));
//		model.addAttribute("modelod", giftService.getTypeTop3("OUTDOOR"));
//		model.addAttribute("modelw", giftService.getTypeTop3("WOMAN"));
//		model.addAttribute("modelm", giftService.getTypeTop3("MAN"));
//		model.addAttribute("modelot", giftService.getTypeTop3("OTHERS"));
		List<GiftEntity> giftsh =  giftService.getTypeTop4Hot(true);
		for(GiftEntity gifth:giftsh){
			String b64 = getImageService.getImage(gifth.getImage1());
			gifth.setShowImage(b64);
		}
		model.addAttribute("modelh",giftsh);
		
		List<GiftEntity> giftsv =  giftService.getTypeTop4(GiftType.VOUCHERS);
		for(GiftEntity giftv:giftsv){
			String b64 = getImageService.getImage(giftv.getImage1());
			giftv.setShowImage(b64);
		}
		model.addAttribute("modelv",giftsv);
		
		List<GiftEntity> giftsf =  giftService.getTypeTop4(GiftType.FURNITURES);
		for(GiftEntity giftf:giftsf){
			String b64 = getImageService.getImage(giftf.getImage1());
			giftf.setShowImage(b64);
		}
		model.addAttribute("modelf",giftsf);
		
		List<GiftEntity> giftse =  giftService.getTypeTop4(GiftType.ELECTRONICS);
		for(GiftEntity gifte:giftse){
			String b64 = getImageService.getImage(gifte.getImage1());
			gifte.setShowImage(b64);
		}
		model.addAttribute("modele",giftse);
		
		List<GiftEntity> giftsod =  giftService.getTypeTop4(GiftType.OUTDOOR);
		for(GiftEntity giftod:giftsod){
			String b64 = getImageService.getImage(giftod.getImage1());
			giftod.setShowImage(b64);
		}
		model.addAttribute("modelod",giftsod);
		
		List<GiftEntity> giftsw =  giftService.getTypeTop4(GiftType.WOMAN);
		for(GiftEntity giftw:giftsw){
			String b64 = getImageService.getImage(giftw.getImage1());
			giftw.setShowImage(b64);
		}
		model.addAttribute("modelw",giftsw);
		
		List<GiftEntity> giftsm =  giftService.getTypeTop4(GiftType.MAN);
		for(GiftEntity giftm:giftsm){
			String b64 = getImageService.getImage(giftm.getImage1());
			giftm.setShowImage(b64);
		}
		model.addAttribute("modelm",giftsm);
		
		List<GiftEntity> giftsot =  giftService.getTypeTop4(GiftType.OTHERS);
		for(GiftEntity giftot:giftsot){
			String b64 = getImageService.getImage(giftot.getImage1());
			giftot.setShowImage(b64);
		}	
		model.addAttribute("modelot",giftsot);
		
//		giftService.getTypeTop3("FURNITURES");
//		giftService.getTypeTop3("ELETRONICS");
//		giftService.getTypeTop3("OUTDOOR");
//		giftService.getTypeTop3("WOMAN");
//		giftService.getTypeTop3("MAN");
//		giftService.getTypeTop3("OTHERS");
//		model.addAttribute("models", giftService.getAll());

		return "/gift/list";
	}

	@RequestMapping("/{giftType}")
	public String list_Vouchers(@PathVariable("giftType") String giftType, Model model) {

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}", userDetails);
			
			UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
			log.debug("getUser : {}", user);
			
			model.addAttribute("userPoint",user.getRemainPoint());
			model.addAttribute("audittingPoint",user.getAudittingPoint());
			model.addAttribute("exchangedPoint",user.getExchangedPoint());
		} catch (Exception e) {
			model.addAttribute("userPoint",0);
			model.addAttribute("audittingPoint",0);
			model.addAttribute("exchangedPoint",0);
			model.addAttribute("users","not null");
		}
		List<GiftEntity> gifts = giftService.getByGiftType(giftType);
		for(GiftEntity gift:gifts){
			gift.setShowImage(getImageService.getImage(gift.getImage1()));
		}
		model.addAttribute("models", gifts);

		return "/gift/list_class";
	}
	
	@RequestMapping("/true")
	public String list_Hot(Model model) {

		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}", userDetails);
			
			UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
			log.debug("getUser : {}", user);
			
			model.addAttribute("userPoint",user.getRemainPoint());
			model.addAttribute("audittingPoint",user.getAudittingPoint());
			model.addAttribute("exchangedPoint",user.getExchangedPoint());
		} catch (Exception e) {
			model.addAttribute("userPoint",0);
			model.addAttribute("audittingPoint",0);
			model.addAttribute("exchangedPoint",0);
			model.addAttribute("users","not null");
		}
		List<GiftEntity> gifts = giftService.getTypeHot(true);
		for(GiftEntity gift:gifts){
			gift.setShowImage(getImageService.getImage(gift.getImage1()));
		}
		model.addAttribute("models", gifts);

		return "/gift/list_class";
	}

}
