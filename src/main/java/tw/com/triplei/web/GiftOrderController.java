package tw.com.triplei.web;

import java.time.LocalDateTime;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.GiftOrderSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.GiftOrderEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.enums.GiftOrderType;
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
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("userDetails: {}", userDetails);
		
		UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
		log.debug("getUser : {}", user);
		
		model.addAttribute("userPoint",user.getRemainPoint());
		model.addAttribute("audittingPoint",user.getAudittingPoint());
		model.addAttribute("exchangedPoint",user.getExchangedPoint());
		
		return "/gift/giftOrderList";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		GiftOrderEntity dbEntity = giftOrderService.getOne(id);
		
		model.addAttribute("entity", dbEntity);
		

		return "/gift/giftOrderEdit";
	}

	@GetMapping
	@ResponseBody
	public GridResponse<GiftOrderEntity> query(final Model model, final GiftOrderEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<GiftOrderEntity> page;

		try {
			
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}", userDetails);
			
			UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
			log.debug("getUser : {}", user);
			
			page = giftOrderService.getByCreatedBy(user.getAccountNumber() , pageable);
			
			

//			page = giftOrderService.getAll(new GiftOrderSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> order(String giftName, String quantity1, String recipient,
			String recipientAddress, String recipientPhone, String recipientTime, GiftOrderEntity entity) {

		Map<String, String> response = new HashMap<>();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("userDetails: {}", userDetails);
		log.debug("giftName: {}", giftName);
		log.debug("quantity1: {}", quantity1);
		log.debug("recipient: {}", recipient);
		log.debug("recipientAddress: {}", recipientAddress);
		log.debug("recipientPhone: {}", recipientPhone);
		log.debug("recipientTime: {}", recipientTime);
		if (quantity1 != null && quantity1.length() >= 0) {
			try {
				int quantity = Integer.valueOf(quantity1);
				if(quantity<=0) {
					response.put("請輸入有效數量", "請輸入有效數量");
					return response;
				}
				
				UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
				log.debug("orderUser : {}", user);

				// 找出USER點數
				int userpoint = user.getRemainPoint();

				GiftEntity giftEntity = giftService.getByName(giftName);
				log.debug("giftEntity: {}", giftEntity);
				int giftpoint = giftEntity.getBonus();

				int remainpoint = (userpoint - giftpoint * quantity);
				int inputPoint = giftpoint * quantity;
				log.debug("remainpoint: {}", remainpoint);

				// 檢查剩餘點數並進行扣點與下訂單

				if (remainpoint >= 0 && inputPoint>0) {

					user.setRemainPoint(remainpoint);
					
					user.setExchangedPoint(user.getExchangedPoint() + (giftpoint * quantity));
					userService.getDao().save(user);
					entity.setQuantity(quantity);
					entity.setRecipient(recipient);
					entity.setRecipientAddress(recipientAddress);
					entity.setRecipientPhone(recipientPhone);
					entity.setRecipientTime(recipientTime);
					entity.setStatus(GiftOrderType.WAITING);
					LocalDateTime orderTime = LocalDateTime.now();
					entity.setOrderTime(orderTime);
					entity.setGiftEntity(giftEntity);
					entity.setCreatedBy(user.getAccountNumber());
//					entity.setUserEntity(user);
					giftOrderService.insert(entity);
					response.put("userPoint", user.getRemainPoint().toString());
					response.put("exchangedPoint", user.getExchangedPoint().toString());
					response.put("訂購成功", "訂購成功");

					return response;

				}else if(inputPoint<0) {
					response.put("數量輸入過大", "數量輸入過大");
				}

				response.put("剩餘點數不足", "剩餘點數不足");
				return response;

			} catch (NumberFormatException e) {
				response.put("數字輸入錯誤", e.toString());
				return response;
			}
		}
		
		response.put("fail", "Process Error");
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/orderCancel/{id}", method=RequestMethod.PUT)
	public Map<String,String> orderCancel(@PathVariable(value = "id") final long id){
		
		Map<String,String> response = new HashMap<>();
		
		log.debug("OrderCancelId : {}", id);
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.debug("userDetails: {}", userDetails);
		
		UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
		
		log.debug("orderUser : {}", user);
		
		try {
			GiftOrderEntity cancelEntity = giftOrderService.getById(id);
			
			//判斷訂單狀態再進行處理(直接退貨完成)
			if(cancelEntity.getStatus()==GiftOrderType.PROCESSING 
				|| cancelEntity.getStatus()==GiftOrderType.WAITING) {
			cancelEntity.setStatus(GiftOrderType.CANCELDONE);
			giftOrderService.update(cancelEntity);
			
			int cancelPoint = cancelEntity.getGiftEntity().getBonus()*cancelEntity.getQuantity();
			log.debug("UserCancelPoint : {}", cancelPoint);
			int remainPoint = user.getRemainPoint();
			log.debug("UserRemainPoint : {}", remainPoint);
			int exchangedPoint = user.getExchangedPoint();
			log.debug("UserExchangedPoint : {}", exchangedPoint);
			int returnPoint = remainPoint+cancelPoint;
			int returnPoint2 = exchangedPoint-cancelPoint;
			user.setRemainPoint(returnPoint);
			user.setExchangedPoint(returnPoint2);
			
			userService.getDao().save(user);
			
			response.put("訂單已取消", "訂單已取消");
			}
			else {
			response.put("已超過取消期限", "已超過取消期限");
			}
		} catch (final Exception e) {
			response.put("ProcessError", "ProcessError");
			return response;
		}
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String,String> update(final Model model, @RequestBody final GiftOrderEntity form) {

		log.debug("{}", form);

		final Map<String,String> response = new HashMap<>();

		try {
			//找出user
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			log.debug("userDetails: {}", userDetails);
			
			UserEntity user = userService.getDao().findByAccountNumber(userDetails.getUsername());
			
			log.debug("orderUser : {}", user);
			
			//查看form
			log.debug("{}",form);
			
			log.debug("form.getStatus() : {}",form.getStatus());
			
			//找出下單gift
			GiftEntity giftEntity = giftService.getByName(form.getGiftEntity().getName());
			
			form.setGiftEntity(giftEntity);
			
			GiftOrderEntity originalOrder = giftOrderService.getById(form.getId());
			
			
			//判斷訂單狀態再進行處理(已收到訂單之外才可操作)
			if(form.getStatus()==GiftOrderType.WAITING) {
			
			//處理點數  先加回去再扣除數量*點數
			int giftPoint = giftEntity.getBonus();
			log.debug("UserCancelPoint : {}", giftPoint);
			int remainPoint = user.getRemainPoint();
			log.debug("UserRemainPoint : {}", remainPoint);
			int exchangedPoint = user.getExchangedPoint();
			log.debug("UserExchangedPoint : {}", exchangedPoint);
			int returnPoint = remainPoint+(originalOrder.getQuantity()*giftPoint)-(form.getQuantity()*giftPoint);
			int returnPoint2 = exchangedPoint-(originalOrder.getQuantity()*giftPoint)+(form.getQuantity()*giftPoint);
			int inputPoint = form.getQuantity()*giftPoint;
			
			if(returnPoint>=0 && inputPoint>0) {
				user.setRemainPoint(returnPoint);
				user.setExchangedPoint(returnPoint2);
				
				userService.getDao().save(user);
				
				final GiftOrderEntity updateResult = giftOrderService.update(form);
				response.put("訂單已修改", "訂單已修改");
				
			}else if(inputPoint<0) {
				response.put("輸入數量過大", "輸入數量過大");
			}else {
				response.put("剩餘點數不足", "剩餘點數不足");
			}
			
			}else if(form.getQuantity()<=0) {
				response.put("請輸入正確資料", "請輸入正確資料");
			}else {
			response.put("已超過修改期限", "已超過修改期限");
			}
			
			
		} catch (final Exception e) {
			response.put("系統發生錯誤", "系統發生錯誤");
		}

		return response;
	}
}
