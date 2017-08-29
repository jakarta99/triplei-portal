package tw.com.triplei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.com.triplei.enums.GiftType;
import tw.com.triplei.service.GiftService;

@Controller
@RequestMapping("/gift")
public class GiftController {

	@Autowired
	private GiftService giftService;

	@RequestMapping("/list")
	public String list(Model model) {

		model.addAttribute("models", giftService.getAll());

		return "/gift/list";
	}

	@RequestMapping("/{giftType}")
	public String list_Vouchers(@PathVariable("giftType") GiftType giftType, Model model) {

		model.addAttribute("models", giftService.getType(giftType));

		return "/gift/list_class";
	}

}
