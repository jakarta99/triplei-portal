package tw.com.triplei.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.GiftOrderSpecification;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.GiftOrderEntity;
import tw.com.triplei.service.GiftOrderService;
import tw.com.triplei.service.GiftService;

@Slf4j
@Controller
@RequestMapping("/gift/giftOrder")
public class GiftOrderController {

	@Autowired
	private GiftOrderService giftOrderService;
	
	@Autowired
	private GiftService giftService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		return "/gift/giftOrderList";	
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<GiftOrderEntity> query(final Model model, final GiftOrderEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<GiftOrderEntity> page;

		try {

			page = giftOrderService.getAll(new GiftOrderSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	
	@RequestMapping(value="/addOrder" , method = RequestMethod.POST)
	@ResponseBody
	public String order(String giftName, String quantity1, GiftOrderEntity entity) {
		
		log.debug("{}", giftName);
		log.debug("{}", quantity1);

		GiftEntity giftEntity = giftService.getByName(giftName);	
		log.debug("{}", giftEntity);
		int quantity = Integer.valueOf(quantity1);
		entity.setQuantity(quantity);
		LocalDateTime orderTime  = LocalDateTime.now();
		entity.setOrderTime(orderTime);
		entity.setGiftEntity(giftEntity);
		giftOrderService.insert(entity);
				
		return "success";
	}
}
