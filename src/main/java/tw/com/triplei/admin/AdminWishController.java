package tw.com.triplei.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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

		Page<WishEntity> page;

		try {
			final List<SpecCriterion> criterions = Lists.newArrayList();
//			if(!Strings.isNullOrEmpty(date1)){
//				Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
//				log.debug("!!!!!!!{}",date3.getTime());
//				criterions.add(new SpecCriterion(QueryOpType.GE,"createdTime",date3.getTime()));
//			}
//			if(!Strings.isNullOrEmpty(date2)){
//				Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
//				criterions.add(new SpecCriterion(QueryOpType.LE,"createdTime",date4.getTime()));
//			}
			
			page = wishService.getByCondition(criterions, pageable);


		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

}
