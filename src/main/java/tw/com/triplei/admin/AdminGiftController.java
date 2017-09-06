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
import tw.com.triplei.admin.spec.GiftSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.service.GiftService;

@Slf4j
@Controller
@RequestMapping("/admin/gift")
public class AdminGiftController {

	@Autowired
	private GiftService giftService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/gift/giftList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/gift/giftAdd";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		GiftEntity dbEntity = giftService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/gift/giftEdit";
	}

	@GetMapping
	@ResponseBody
	public GridResponse<GiftEntity> query(final Model model, final GiftEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<GiftEntity> page;

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

			page = giftService.getAll(new GiftSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

	@PostMapping
	@ResponseBody
	public AjaxResponse<GiftEntity> insert(final Model model,@RequestBody final GiftEntity form) {

		AjaxResponse<GiftEntity> response = new AjaxResponse<GiftEntity>();

		try {
			String brandnum = form.getBrand().substring(form.getBrand().length()-3);
			String giftnum = form.getName().substring(form.getName().length()-3);
			String colorAndType = form.getColorAndType().substring(form.getColorAndType().length()-2);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//			Date date = sdf.parse(exchangeDate);
//			System.out.println(date);
//			LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
//			form.setExchangeDate(localDateTime);
			final GiftEntity insertResult = giftService.insert(form);
			String formatStr = "%05d";
			String formatAns = String.format(formatStr,insertResult.getId());
			String giftNumber = brandnum+giftnum+colorAndType+formatAns;
			form.setCode(giftNumber);
			final GiftEntity insertResultFinal = giftService.update(form);
			response.setData(insertResultFinal);

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
	public AjaxResponse<GiftEntity> update(final Model model, @RequestBody final GiftEntity form) {

		log.debug("{}", form);

		final AjaxResponse<GiftEntity> response = new AjaxResponse<GiftEntity>();

		try {
			String brandnum = form.getBrand().substring(form.getBrand().length()-3);
			String giftnum = form.getName().substring(form.getName().length()-3);
			String colorAndType = form.getColorAndType().substring(form.getColorAndType().length()-2);
			String formatStr = "%05d";
			String formatAns = String.format(formatStr,form.getId());
			String giftNumber = brandnum+giftnum+colorAndType+formatAns;
			form.setCode(giftNumber);
			final GiftEntity updateResult = giftService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<GiftEntity> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id); 

		final AjaxResponse<GiftEntity> response = new AjaxResponse<GiftEntity>();

		try {
			giftService.delete(id);

		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;

	}

}
