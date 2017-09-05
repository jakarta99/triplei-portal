package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.enums.GiftType;
import tw.com.triplei.service.GiftService;

@Controller
@RequestMapping("/gift")
public class GiftController {

	@Autowired
	private GiftService giftService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		
//		model.addAttribute("modelv", giftService.getTypeTop3("VOUCHERS"));
//		model.addAttribute("modelf", giftService.getTypeTop3("FURNITURES"));
//		model.addAttribute("modele", giftService.getTypeTop3("ELETRONICS"));
//		model.addAttribute("modelod", giftService.getTypeTop3("OUTDOOR"));
//		model.addAttribute("modelw", giftService.getTypeTop3("WOMAN"));
//		model.addAttribute("modelm", giftService.getTypeTop3("MAN"));
//		model.addAttribute("modelot", giftService.getTypeTop3("OTHERS"));

		model.addAttribute("modelh",giftService.getTypeTop3Hot(true));
		model.addAttribute("modelv", giftService.getTypeTop3(GiftType.VOUCHERS));
		model.addAttribute("modelf", giftService.getTypeTop3(GiftType.FURNITURES));
		model.addAttribute("modele", giftService.getTypeTop3(GiftType.ELECTRONICS));
		model.addAttribute("modelod", giftService.getTypeTop3(GiftType.OUTDOOR));
		model.addAttribute("modelw", giftService.getTypeTop3(GiftType.WOMAN));
		model.addAttribute("modelm", giftService.getTypeTop3(GiftType.MAN));
		model.addAttribute("modelot", giftService.getTypeTop3(GiftType.OTHERS));
		
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

		model.addAttribute("models", giftService.getType(giftType));

		return "/gift/list_class";
	}
	
	@RequestMapping("/true")
	public String list_Hot(Model model) {

		model.addAttribute("models", giftService.getTypeHot(true));

		return "/gift/list_class";
	}

}
