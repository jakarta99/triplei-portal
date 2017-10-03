package tw.com.triplei.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.QualifierEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.GiftOrderSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
import tw.com.triplei.entity.GiftOrderEntity;
import tw.com.triplei.service.GiftOrderService;

@Slf4j
@Controller
@RequestMapping("/admin/gift/giftOrder")
public class AdminGiftOrderController {

	@Autowired
	private GiftOrderService giftOrderService;
	
	@RequestMapping("/list")
	public String list(Model model) {
			
		return "/admin/gift/giftOrderList";
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		GiftOrderEntity dbEntity = giftOrderService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/gift/giftOrderEdit";
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<GiftOrderEntity> query(final Model model, final GiftOrderEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<GiftOrderEntity> page;

		try {
			final List<SpecCriterion> criterions = Lists.newArrayList();
			
			if(form.getStatus()!=null){
				criterions.add(new SpecCriterion(QueryOpType.EQ, "status", form.getStatus()));		
			}
			
			page = giftOrderService.getByCondition(criterions, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<GiftOrderEntity> update(final Model model, @RequestBody final GiftOrderEntity form) {

		log.debug("{}", form);

		final AjaxResponse<GiftOrderEntity> response = new AjaxResponse<>();

		try {
			log.debug("{}",form);
			final GiftOrderEntity updateResult = giftOrderService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<GiftOrderEntity> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id);

		final AjaxResponse<GiftOrderEntity> response = new AjaxResponse<>();

		try {
			giftOrderService.delete(id);

		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;

	}
}
