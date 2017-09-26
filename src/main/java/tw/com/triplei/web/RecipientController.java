package tw.com.triplei.web;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.RecipientEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.ProductService;
import tw.com.triplei.service.RecipientService;
import tw.com.triplei.service.UserService;

@Controller
@RequestMapping("/recipient")
public class RecipientController {
	@Autowired
	private ProductService productService;

	@Autowired
	private RecipientService recipientService;
	
	@Autowired
	private UserService userService;

	// @RequestMapping("/list")
	// public String list(Model model) {
	//
	// model.addAttribute("models", recipientService.getAll());
	//
	// return "/recipient/list";
	// }

	@RequestMapping(value = "/list")
	public String listPerMember(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userName", details.getUsername());
		return "/recipient/list";
	}
	
	@RequestMapping(value = "/sale/list")
	public String listPerSales(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userName", details.getUsername());
		return "/recipient/salesList";
	}
	
	@RequestMapping(value = "/sales/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		RecipientEntity dbEntity = recipientService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/recipient/recipientEdit";
	}

	@GetMapping("/{userName:.+}")
	@ResponseBody
	public GridResponse<RecipientEntity> query(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,
			final Model model, final RecipientEntity form, @PathVariable("userName") String userName) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<RecipientEntity> page;
		try {

			page = recipientService.getDao().findByCreatedBy(userName, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}
	
	@GetMapping("/saless/{userName:.+}")
	@ResponseBody
	public GridResponse<RecipientEntity> salesquery(@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,
			final Model model, final RecipientEntity form, @PathVariable("userName") String userName) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<RecipientEntity> page;
		try {
			System.out.println(userName);
			UserEntity user = userService.getDao().findByAccountNumber(userName);
			System.out.println(user);
			page = recipientService.getDao().findByUser(user, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}

	@RequestMapping(value = "/add/{id}/{bdate}/{gender}/{insureAmount}/{premiumAfterDiscount}/{getPoint}", method = RequestMethod.GET)
	public String addPage(Model model, @PathVariable("id") final Long id, @PathVariable("bdate") final String bdate,
			@PathVariable("gender") final String gender, @PathVariable("insureAmount") final String insureAmountS,
			@PathVariable("premiumAfterDiscount") final String premiumAfterDiscountS,
			@PathVariable("getPoint") final String getPointS) {
		ProductEntity product = productService.getOneAll(id);
		int age = productService.bDateToInt(bdate);
		product.setInsureAmount(BigDecimal.valueOf(Double.parseDouble(insureAmountS)));
		product.setPremiumAfterDiscount(BigDecimal.valueOf(Double.parseDouble(premiumAfterDiscountS)));
		product.setGetPoint(BigDecimal.valueOf(Double.parseDouble(getPointS)));

		model.addAttribute("age", age);
		model.addAttribute("gender", gender);
		model.addAttribute("model", product);
		return "/product/buyProduct";
	}

	@RequestMapping("/filt")
	public String filt(Model model) {

		model.addAttribute("models", recipientService.getAll());

		return "/recipient/filt";
	}

}
