package tw.com.triplei.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.dao.ConvenienceStoreDao;
import tw.com.triplei.dao.UserDao;
import tw.com.triplei.entity.ConvenienceStoreEntity;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.RecipientEntity;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.EmailService;
import tw.com.triplei.service.ProductService;
import tw.com.triplei.service.RecipientService;
import tw.com.triplei.service.RoleService;
import tw.com.triplei.service.UserService;
@Slf4j
@Controller
@RequestMapping("/recipient")
public class RecipientController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ConvenienceStoreDao convenienceStoreDao;	

	@Autowired
	private RecipientService recipientService;
	
	@Autowired
	private UserService userService;



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
	
	@PostMapping
	public AjaxResponse<RecipientEntity> insert(final Model model, @RequestParam("name") String name,
			@RequestParam("age") String age, @RequestParam("gender") String gender, @RequestParam("tel") String tel,
			@RequestParam("date_1_1") String date_1_1, @RequestParam("date_1_2") String date_1_2,
			@RequestParam("date_1_3") String date_1_3, @RequestParam("date_2_1") String date_2_1,
			@RequestParam("date_2_2") String date_2_2, @RequestParam("date_2_3") String date_2_3,
			@RequestParam("date_3_1") String date_3_1, @RequestParam("date_3_2") String date_3_2,
			@RequestParam("date_3_3") String date_3_3, @RequestParam("address") String address,
			@RequestParam("pid") String pid, @RequestParam("insureAmount") String insureAmountS,
			@RequestParam("premiumAfterDiscount") String premiumAfterDiscountS,
			@RequestParam("getPoint") String getPointS) {

		AjaxResponse<RecipientEntity> response = new AjaxResponse<RecipientEntity>();
		try {
			RecipientEntity form = new RecipientEntity();
			ProductEntity productEntity = productService.getOneAll(Integer.parseInt(pid));

			productEntity.setInsureAmount(BigDecimal.valueOf(Double.parseDouble(insureAmountS)));
			productEntity.setPremiumAfterDiscount(BigDecimal.valueOf(Double.parseDouble(premiumAfterDiscountS)));
			productEntity.setGetPoint(BigDecimal.valueOf(Double.parseDouble(getPointS)));
			form.setInsureAmount(productEntity.getInsureAmount());
			form.setCode(productEntity.getCode());
			form.setYear(productEntity.getYear()+"");
			form.setName(name);
			form.setGender(gender);
			form.setAge(Integer.parseInt(age));
			form.setTel(tel);
			form.setBookedTime_1(date_1_1 + " " + date_1_2 + " " + date_1_3);
			form.setBookedTime_2(date_2_1 + " " + date_2_2 + " " + date_2_3);
			form.setBookedTime_3(date_3_1 + " " + date_3_2 + " " + date_3_3);
			form.setProduct(productEntity);
			form.setCanGetPoint((int) productEntity.getGetPoint().doubleValue());
			ConvenienceStoreEntity convenienceStoreEntity = convenienceStoreDao.findByAddress(address);
			log.debug("convenienceStoreEntity: {}", convenienceStoreEntity);
			form.setConvenienceStoreEntity(convenienceStoreEntity);
			form.setCreatedTime(new Timestamp(new Date().getTime()));
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			log.debug("userDetails: {}", userDetails.getUsername());
			form.setCreatedBy(userDetails.getUsername());
			form.setAlreadyGetPoint(false);
			form.setAlreadyAudittedPoint(false);
			form.setAlreadyDeletedPoint(false);
			form.setOrderStatus("未指派業務員");
			
			// 一般會員下單後 自動升級成下單會員
			UserEntity owner = userDao.findByAccountNumber(form.getCreatedBy());
			log.debug("升級會員前{}",owner);
			Set<RoleEntity> roles = owner.getRoles();
			RoleEntity role = roleService.getDao().findByCode("ROLE_ORDER");
			if (!roles.contains(role)) {
				roles.add(role);
				owner.setRoles(roles);
				userDao.save(owner);
				log.debug("升級成功!!{}");
			}


			final RecipientEntity insertResult = recipientService.insert(form);
			String formatStr = "%07d";
			String formatAns = String.format(formatStr,insertResult.getId());
			insertResult.setOrderNo(formatAns);
			final RecipientEntity insertResultG = recipientService.insert(insertResult);

			response.setData(insertResultG);
			//發email給顧客
			log.debug("寄信顧客:{}");
			emailService.sendAlertEmailToCustomer(owner, insertResultG);
			log.debug("寄信顧客:{}");
			//發email給管理員
			List<UserEntity> users = userDao.findAll();
			for(UserEntity admin : users){
				Set<RoleEntity> userRole = admin.getRoles();
				RoleEntity roleAdmin = roleService.getDao().findByCode("ROLE_ADMIN");
				if(userRole.contains(roleAdmin)){
					emailService.sendAlertEmailToAdminC(admin , owner, insertResultG);
					log.debug("管理者:{}",admin.getName());
				}
			}
			

		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
			log.debug("~~~~~~~~~~{}");
		} catch (final Exception e) {
			response.addException(e);
			log.debug("---------------{}");
		}

//		 log.debug("{}", response);

		return response;
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
		
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		log.debug("Username: {}", userDetails.getUsername());
		UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
		if(user.getEmail()==null){
			return "redirect:/user/reset/info";
		}
		
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
