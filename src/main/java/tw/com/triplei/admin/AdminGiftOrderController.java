package tw.com.triplei.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.entity.GiftOrderEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.enums.GiftOrderType;
import tw.com.triplei.service.GiftOrderService;
import tw.com.triplei.service.GiftService;
import tw.com.triplei.service.UserService;

@Slf4j
@Controller
@RequestMapping("/admin/gift/giftOrder")
public class AdminGiftOrderController {

	@Autowired
	private GiftOrderService giftOrderService;

	@Autowired
	private GiftService giftService;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public String list(Model model) {
			
		return "/admin/gift/giftOrderList";
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		GiftOrderEntity dbEntity = giftOrderService.getOne(id);
		
//		if(dbEntity.getStatus()==GiftOrderType.CANCEL) {
//			model.addAttribute("entityStatus", GiftOrderType.CANCELDONE);
//		}else if(dbEntity.getStatus()==GiftOrderType.PROCESSING) {
//			List<GiftOrderType> list = new ArrayList<>();
//			list.add(GiftOrderType.SHIPORDER);
//			list.add(GiftOrderType.DONE);
//			model.addAttribute("entityStatus", list);
//		}else if(dbEntity.getStatus()==GiftOrderType.SHIPORDER) {
//			model.addAttribute("entityStatus", GiftOrderType.DONE);
//		}else if(dbEntity.getStatus()==GiftOrderType.DONE) {
//			model.addAttribute("entityStatus", GiftOrderType.DONE);
//		}else {
//			model.addAttribute("entityStatus", GiftOrderType.CANCELDONE);
//		}
		
		
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
	public Map<String,String> update(final Model model, @RequestBody final GiftOrderEntity form) {

		log.debug("{}", form);

		final Map<String,String> response = new HashMap<>();

		GiftOrderEntity giftOrderEntity = giftOrderService.getById(form.getId());
		try {
			log.debug("form : {}",form);
			
			UserEntity user = userService.getDao().findByAccountNumber(form.getCreatedBy());
			log.debug("user before cancel : {}",user.getRemainPoint());
			
			//如果訂單從原本不是取消訂單修改成取消訂單 則 把點數加回user
			if(form.getStatus()==GiftOrderType.CANCELDONE
				&& giftOrderEntity.getStatus()!=GiftOrderType.CANCELDONE) {
				
				int userExchangedPoint = user.getExchangedPoint();
				int userPoint = user.getRemainPoint();
				int giftPoint = form.getGiftEntity().getBonus()*giftOrderEntity.getQuantity();
				
				user.setRemainPoint(userPoint+giftPoint);
				user.setExchangedPoint(userExchangedPoint-giftPoint);
				userService.getDao().save(user);
				
				log.debug("user after cancel : {}",user.getRegisteredCode());
				
				GiftEntity giftEntity = giftService.getByName(form.getGiftEntity().getName());
				
				form.setGiftEntity(giftEntity);
				
				giftOrderService.update(form);
				response.put("修改成功", "修改成功");
			}
			// 如果有修改數量  則修改user點數
			else if (form.getQuantity() != giftOrderEntity.getQuantity() && form.getQuantity()>0 ) {
				
				int userExchangedPoint = user.getExchangedPoint();
				int userPoint = user.getRemainPoint();
				int originalGiftPoint = form.getGiftEntity().getBonus()*giftOrderEntity.getQuantity();
				int newGiftPoint = giftOrderEntity.getGiftEntity().getBonus()*form.getQuantity();
				
				user.setRemainPoint(userPoint+originalGiftPoint-newGiftPoint);
				user.setExchangedPoint(userExchangedPoint-originalGiftPoint+newGiftPoint);
				if(user.getRemainPoint()>=0 && newGiftPoint>0) {
					userService.getDao().save(user);
					
					log.debug("user after cancel : {}",user.getRegisteredCode());
					
					GiftEntity giftEntity = giftService.getByName(form.getGiftEntity().getName());
					
					form.setGiftEntity(giftEntity);
					
					giftOrderService.update(form);
					response.put("修改成功", "修改成功");
				}else if(newGiftPoint<0) {
					response.put("輸入數量過大", "輸入數量過大");
				}else {
					response.put("剩餘點數不足", "剩餘點數不足");
				}
			}else {
				GiftEntity giftEntity = giftService.getByName(form.getGiftEntity().getName());
				
				form.setGiftEntity(giftEntity);
				
				giftOrderService.update(form);
				response.put("修改成功", "修改成功");
			}
			

		} catch (final Exception e) {
			response.put("exception", "exception");
			return response;
		}

		return response;
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String,String> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id);

		final Map<String,String> response = new HashMap<>();
		GiftOrderEntity originalEntity = giftOrderService.getById(id);
		try {
			
			//定單未處理完時把點數加回去
			if(originalEntity.getStatus()!=GiftOrderType.CANCELDONE
			&&originalEntity.getStatus()!=GiftOrderType.DONE) {
				
			UserEntity user = userService.getDao().findByAccountNumber(originalEntity.getCreatedBy());
			log.debug("deleteOrder user : {}",user);
			
			int userRemainPoint = user.getRemainPoint();
			int userExchangePoint = user.getExchangedPoint();
			int giftPoint = originalEntity.getQuantity()*originalEntity.getGiftEntity().getBonus();
			
			user.setRemainPoint(userRemainPoint+giftPoint);
			user.setExchangedPoint(userExchangePoint-giftPoint);

			log.debug("deleteOrder user : {}",user);
			
			userService.getDao().save(user);
			}
			giftOrderService.delete(id);
			response.put("刪除成功", "刪除成功");
		} catch (final Exception e) {
			response.put("exception", "exception");
			return response;
		}
		return response;

	}
}
