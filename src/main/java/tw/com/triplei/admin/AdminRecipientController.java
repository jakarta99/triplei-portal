package tw.com.triplei.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.ProductService;
import tw.com.triplei.service.RecipientService;

@Slf4j
@Controller
@RequestMapping("/admin/recipient")
public class AdminRecipientController {
	
	@Autowired
	private UserDao userDao;
	
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
		model.addAttribute("entity", dbEntity);
		
		return "/admin/recipient/recipientEdit";
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<RecipientEntity> query(final Model model, final RecipientEntity form, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<RecipientEntity> page;

		
		try {

//			final List<AzaleaCriterion> criterions = Lists.newArrayList();
//
//
//			if (!Strings.isNullOrEmpty(form.getAccount())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account", form.getAccount() + "%"));
//			}
//
//			if (!Strings.isNullOrEmpty(form.getLocalName())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName", "%" + form.getLocalName() + "%"));
//			}
//
//			if (form.getStatus() != null) {
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status", form.getStatus()));
//			}

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
//			if (!RoleUtil.isHaveAdminRoles()) {
//				final SecUser loginUser = (SecUser) SecurityUtils.getSubject().getPrincipal();
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId", loginUser.getRootChannelId()));
//			}
			
			page = recipientService.getAll(new RecipientSpecification(), pageable);
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	@PostMapping
	public AjaxResponse<RecipientEntity> insert(final Model model
			,@RequestParam("name") String name,@RequestParam("gender") String gender
			,@RequestParam("tel") String tel,@RequestParam("date_1_1") String date_1_1
			,@RequestParam("date_1_2") String date_1_2,@RequestParam("date_1_3") String date_1_3
			,@RequestParam("date_2_1") String date_2_1,@RequestParam("date_2_2") String date_2_2
			,@RequestParam("date_2_3") String date_2_3,@RequestParam("date_3_1") String date_3_1
			,@RequestParam("date_3_2") String date_3_2,@RequestParam("date_3_3") String date_3_3
			,@RequestParam("address") String address,@RequestParam("shops") String shops
			,@RequestParam("pid") long pid) {
		
		AjaxResponse<RecipientEntity> response = new AjaxResponse<RecipientEntity>();
		try {
			RecipientEntity form = new RecipientEntity();
			ProductEntity productEntity = productService.getOne(pid);
			System.out.println(productEntity);
			form.setName(name);
			form.setGender(gender);
			form.setTel(tel);
			form.setBookedTime_1(date_1_1+" "+date_1_2+" "+date_1_3);
			form.setBookedTime_2(date_2_1+" "+date_2_2+" "+date_2_3);
			form.setBookedTime_3(date_3_1+" "+date_3_2+" "+date_3_3);
			form.setProduct(productEntity);
			ConvenienceStoreEntity convenienceStoreEntity = convenienceStoreDao.findByAddress(address);
			form.setConvenienceStoreEntity(convenienceStoreEntity);
			final RecipientEntity insertResult = recipientService.insert(form);
			response.setData(insertResult);
		
		} catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
		} catch (final Exception e) {
			response.addException(e);
		}
		
		log.debug("{}", response);
		
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<RecipientEntity> update(final Model model,@RequestParam("pid")long pid,@RequestParam(name="userName") String userName,@RequestBody final RecipientEntity form) {
		
		log.debug("{}", form);
		final AjaxResponse<RecipientEntity> response = new AjaxResponse<RecipientEntity>();
		
		try {
			ProductEntity productEntity = productService.getOne(pid);
			UserEntity user = userDao.findByName(userName);
			form.setUser(user);
			form.setProduct(productEntity);
			System.out.println(form);
			final RecipientEntity updateResult = recipientService.update(form);

			response.setData(updateResult);
			
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