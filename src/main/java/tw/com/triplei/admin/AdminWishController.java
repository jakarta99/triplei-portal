package tw.com.triplei.admin;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.GiftSpecification;
import tw.com.triplei.admin.spec.WishSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.WishEntity;
import tw.com.triplei.service.WishService;

@Slf4j
@Controller
@RequestMapping("/admin/wish")
public class AdminWishController {

	@Autowired
	private WishService wishService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/wish/wishlist";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/wish/wishadd";
	}
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<WishEntity> insert(final Model model,@RequestBody final WishEntity form) {

		AjaxResponse<WishEntity> response = new AjaxResponse<WishEntity>();

		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date date = new Date();
//			LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
//			form.setWishTime(localDateTime);
//			System.out.println(form.getWishTime());
			
//			form.setWishTime(System.currentTimeMillis());
//			System.out.println(form.getWishTime());
			
			String date = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date());
			System.out.println(date);
			form.setWishTime(date);
			
			
			final WishEntity insertResult = wishService.insert(form);
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
	@GetMapping
	@ResponseBody
	public GridResponse<WishEntity> query(final Model model, final WishEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<WishEntity> page;

		try {

			// final List<AzaleaCriterion> criterions = Lists.newArrayList();
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
			//
			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
			// if (!RoleUtil.isHaveAdminRoles()) {
			// final SecUser loginUser = (SecUser)
			// SecurityUtils.getSubject().getPrincipal();
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId",
			// loginUser.getRootChannelId()));
			// }

			page = wishService.getAll(new WishSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

}
