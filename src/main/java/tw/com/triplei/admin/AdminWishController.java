package tw.com.triplei.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.WishSpecification;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
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
		return "/admin/wish/wishList";
	}

	
	
	
	@GetMapping
	@ResponseBody
	public GridResponse<WishEntity> query(final Model model, final WishEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize,
			@RequestParam("date1") String date1, @RequestParam("date2") String date2) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		
		log.debug("date1{}",date1);
		log.debug("date2{}",date2);
		Page<WishEntity> page;
		try {
			
			
			final List<SpecCriterion> criterions = Lists.newArrayList();
			if(!Strings.isNullOrEmpty(date1.substring(0, date1.length()-1)) && !Strings.isNullOrEmpty(date2.substring(0, date2.length()-1))){
				String date1t = date1.substring(0, date1.length()-1);
				Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(date1t);
				LocalDateTime triggerTime =
						LocalDateTime.ofInstant(Instant.ofEpochSecond(date3.getTime()),
								TimeZone.getDefault().toZoneId());
				log.debug("{}",date3);
				log.debug("triggerTime{}",triggerTime);
				
				String date2t = date2.substring(0, date2.length()-1);
				Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse(date2t);
				LocalDateTime triggerTime2 =
						LocalDateTime.ofInstant(Instant.ofEpochSecond(date4.getTime()),
								TimeZone.getDefault().toZoneId());
				log.debug("{}",date4);
				log.debug("triggerTime{}",triggerTime2);
				
				LocalDateTime[] values = {triggerTime,triggerTime2};
				
				
				
				
				
				
				criterions.add(new SpecCriterion(QueryOpType.BETWEEN,"time",values));
			}
	
			page = wishService.getByCondition(criterions, pageable);


		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

}
