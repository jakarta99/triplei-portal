package tw.com.triplei.admin;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.RecipientSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
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
			final List<SpecCriterion> criterions = Lists.newArrayList();
			
			if(!Strings.isNullOrEmpty(form.getOrderNo())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"orderNo","%"+form.getOrderNo()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getCreatedBy())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"createdBy","%"+form.getCreatedBy()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getName())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"name","%"+form.getName()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getOrderStatus())){
				criterions.add(new SpecCriterion(QueryOpType.EQ,"orderStatus",form.getOrderStatus()));			
			}
			if(!Strings.isNullOrEmpty(form.getUserNamee())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"userNamee","%"+form.getUserNamee()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getCode())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"code","%"+form.getCode()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getYear())){
				criterions.add(new SpecCriterion(QueryOpType.EQ,"year",form.getYear()));
			}

			
			page = recipientService.getByCondition(criterions, pageable);
			
			
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
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
			Collection<UserEntity> users = userDao.findByName(userName);
			for(UserEntity user:users){
				RoleEntity role = roleService.getDao().findByCode("ROLE_SALES");
				if(user.getRoles().contains(role)){
					form.setUser(user);
					form.setUserNamee(user.getName());
				}
			}
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
				UserEntity sales = userDao.findByAccountNumber(form.getUser().getAccountNumber());
				double iii = form.getCanGetPoint().doubleValue()*1.17D;
				sales.setRemainPoint(sales.getRemainPoint()-(int)BigDecimal.valueOf(iii).setScale(0,BigDecimal.ROUND_HALF_UP).doubleValue());
				userDao.save(owner);
				userDao.save(sales);
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
				emailService.sendAlertEmailToSales(updateResult.getUser(), updateResult);
				UserEntity owner = userDao.findByAccountNumber(userDetails.getUsername());
				emailService.sendAlertEmailToAdmin(owner, updateResult.getUser(), updateResult);
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
