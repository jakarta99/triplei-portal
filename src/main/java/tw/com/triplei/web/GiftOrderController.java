package tw.com.triplei.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.GiftOrderService;
import tw.com.triplei.service.GiftService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/gift/giftOrder")
public class GiftOrderController {

	@Autowired
	private GiftOrderService giftOrderService;

	@Autowired
	private GiftService giftService;

	@Autowired
	private UserService userService;

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

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> order(String giftName, String quantity1, GiftOrderEntity entity) {

		Map<String, String> response = new HashMap<>();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("userDetails: {}", userDetails);
		log.debug("giftName: {}", giftName);
		log.debug("quantity1: {}", quantity1);
		if (quantity1 != null && quantity1.length() >= 0) {
			try {
				int quantity = Integer.valueOf(quantity1);
				if(quantity<=0) {
					response.put("fail", "請輸入有效數量");
					return response;
				}
				
				UserEntity user = userService.getDao().findByName(userDetails.getUsername());
				log.debug("orderUser : {}", user);

				// 找出USER點數
				int userpoint = user.getRemainPoint();

				GiftEntity giftEntity = giftService.getByName(giftName);
				log.debug("giftEntity: {}", giftEntity);
				int giftpoint = giftEntity.getBonus();

				int remainpoint = (userpoint - giftpoint * quantity);
				log.debug("remainpoint: {}", remainpoint);

				// 檢查剩餘點數並進行扣點與下訂單

				if (remainpoint >= 0) {

					user.setRemainPoint(remainpoint);
					userService.getDao().save(user);
					entity.setQuantity(quantity);
					LocalDateTime orderTime = LocalDateTime.now();
					entity.setOrderTime(orderTime);
					entity.setGiftEntity(giftEntity);
					giftOrderService.insert(entity);
					response.put("success", "訂購成功");

					return response;

				}

				response.put("fail", "剩餘點數不足");
				return response;

			} catch (NumberFormatException e) {
				response.put("數字輸入錯誤", e.toString());
				return response;
			}
		}
		
		response.put("fail", "Process Error");
		return response;
	}
}
