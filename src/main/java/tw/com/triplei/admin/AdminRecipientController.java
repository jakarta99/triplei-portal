package tw.com.triplei.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.RecipientSpecification;
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

@Slf4j
@Controller
@RequestMapping("/admin/recipient")
public class AdminRecipientController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ConvenienceStoreDao convenienceStoreDao;

	@Autowired
	private ProductService productService;

	@Autowired
	private RecipientService recipientService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/recipient/recipientList";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		RecipientEntity dbEntity = recipientService.getOne(id);
		log.debug("!!!{}", dbEntity.getCreatedBy());
		model.addAttribute("entity", dbEntity);
		List<UserEntity> saleUsers = new ArrayList<>();
		List<UserEntity> users = userDao.findAll();
		for (UserEntity user : users) {
			Set<RoleEntity> roles = user.getRoles();
			for (RoleEntity role : roles) {
				if (role.getCode().equals("ROLE_SALES")) {
					saleUsers.add(user);
				}
			}
		}
		model.addAttribute("sales", saleUsers);
		return "/admin/recipient/recipientEdit";
	}

	@GetMapping
	@ResponseBody
	public GridResponse<RecipientEntity> query(final Model model, final RecipientEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<RecipientEntity> page;

		try {

			// final List<AzaleaCriterion> criterions = Lists.newArrayList();
			//
			//
			// if (!Strings.isNullOrEmpty(form.getAccount())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account",
			// form.getAccount() + "%"));
			// }
			//
			// if (!Strings.isNullOrEmpty(form.getLocalName())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName",
			// "%" + form.getLocalName() + "%"));
			// }
			//
			// if (form.getStatus() != null) {
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status",
			// form.getStatus()));
			// }

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
			// if (!RoleUtil.isHaveAdminRoles()) {
			// final SecUser loginUser = (SecUser)
			// SecurityUtils.getSubject().getPrincipal();
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId",
			// loginUser.getRootChannelId()));
			// }

			page = recipientService.getAll(new RecipientSpecification(), pageable);
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
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
			emailService.sendAlertEmailToCustomer(owner, insertResultG);
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

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<RecipientEntity> update(final Model model, @RequestParam("pid") long pid,
			@RequestParam(name = "userName") String userName, @RequestParam("address") String address,
			@RequestBody final RecipientEntity form) {

		log.debug("{}", form.getCreatedBy());
		final AjaxResponse<RecipientEntity> response = new AjaxResponse<RecipientEntity>();
		
		try {
			ProductEntity productEntity = productService.getOneAll(pid);
			ConvenienceStoreEntity convenienceStoreEntity = convenienceStoreDao.findByAddress(address);
			UserEntity user = userDao.findByName(userName);
			form.setUser(user);
			log.debug("業務員後{}",form.getUser().getName());
			form.setConvenienceStoreEntity(convenienceStoreEntity);
			form.setProduct(productEntity);
			form.setModifiedTime(new Timestamp(new Date().getTime()));
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			log.debug("可獲得點數: {}", form.getCanGetPoint());
			form.setModifiedBy(userDetails.getUsername());

			
			if (form.getOrderStatus().equals("已完成(含派送點數)") && !form.getAlreadyGetPoint()) {
				UserEntity owner = userDao.findByAccountNumber(form.getCreatedBy());
					int remainPoint = owner.getRemainPoint();
					owner.setAudittingPoint(owner.getAudittingPoint() - form.getCanGetPoint());
					owner.setRemainPoint(remainPoint + form.getCanGetPoint());
					form.setAlreadyGetPoint(true);
				userDao.save(owner);
			} else if (!form.getOrderStatus().equals("已完成(含派送點數)") && !form.getAlreadyAudittedPoint() && !form.getOrderStatus().equals("已見面，未購買(刪除審核中點數)")) {
				UserEntity owner = userDao.findByAccountNumber(form.getCreatedBy());
				int auditting = owner.getAudittingPoint();
				owner.setAudittingPoint(auditting + form.getCanGetPoint());
				form.setAlreadyAudittedPoint(true);
				userDao.save(owner);
			} else if(form.getOrderStatus().equals("已見面，未購買(刪除審核中點數)") && form.getAlreadyAudittedPoint() && !form.getAlreadyDeletedPoint()){
				UserEntity owner = userDao.findByAccountNumber(form.getCreatedBy());
				int auditting = owner.getAudittingPoint();
				owner.setAudittingPoint(auditting - form.getCanGetPoint());
				form.setAlreadyDeletedPoint(true);
				userDao.save(owner);
			}

			final RecipientEntity updateResult = recipientService.update(form);
			response.setData(updateResult);
			//指派業務員 發mail通知 業務員及管理員
			if(updateResult.getUser() != null && form.getOrderStatus().equals("未見面")){
				emailService.sendAlertEmailToSales(user, updateResult);
				UserEntity owner = userDao.findByAccountNumber(userDetails.getUsername());
				emailService.sendAlertEmailToAdmin(owner, user, updateResult);
			}

		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<RecipientEntity> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id);

		final AjaxResponse<RecipientEntity> response = new AjaxResponse<RecipientEntity>();

		try {
			recipientService.delete(id);

		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;

	}
}
