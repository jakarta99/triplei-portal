package tw.com.triplei.admin;

import java.sql.Timestamp;
import java.util.Date;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.GiftSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.GiftEntity;
import tw.com.triplei.enums.GiftType;
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

			page = giftService.getAll(new GiftSpecification(), pageable);

		return new GridResponse<>(page);
	}

	@PostMapping
	@ResponseBody
	public AjaxResponse<GiftEntity> insert(@RequestParam(value = "upload-file",required=false) MultipartFile file, @RequestParam(value = "upload-file1",required=false) MultipartFile file1,@RequestParam(value = "upload-file2",required=false) MultipartFile file2,
			@RequestParam(value="brand",required=false)String brand, @RequestParam(value="name",required=false) String name,
			@RequestParam(value="colorAndType",required=false)String colorAndType1,@RequestParam(value="bonus",required=false)Integer bonus,
			@RequestParam(value="remarks",required=false)String remarks,@RequestParam(value="giftType",required=false)GiftType giftType,
			@RequestParam(value="hotGift",required=false)Boolean hotGift, GiftEntity giftEntity) {

		AjaxResponse<GiftEntity> response = new AjaxResponse<GiftEntity>();

		try {
			String brandnum = brand.substring(brand.length()-3);
			String giftnum = name.substring(name.length()-3);
			String colorAndType = colorAndType1.substring(colorAndType1.length()-2);
			
			if(!file.isEmpty()){
				String imagePath= giftService.imageUpload(file);
				giftEntity.setImage1(imagePath);
				}else{
					giftEntity.setImage1("");
				}
			if(!file1.isEmpty()){
				String imagePath= giftService.imageUpload(file1);
				giftEntity.setImage2(imagePath);
			}else{
				giftEntity.setImage2("");
			}
			if(!file2.isEmpty()){
				String imagePath= giftService.imageUpload(file2);
				giftEntity.setImage3(imagePath);
			}else{
				giftEntity.setImage3("");
			}
			
			giftEntity.setBonus(bonus);
			giftEntity.setBrand(brand);
			giftEntity.setColorAndType(colorAndType1);
			giftEntity.setName(name);
			giftEntity.setRemarks(remarks);
			giftEntity.setGiftType(giftType);
			giftEntity.setHotGift(hotGift);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//			Date date = sdf.parse(exchangeDate);
//			System.out.println(date);
//			LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),ZoneId.systemDefault());
//			form.setExchangeDate(localDateTime);
			final GiftEntity insertResult = giftService.insert(giftEntity);
			String formatStr = "%05d";
			String formatAns = String.format(formatStr,insertResult.getId());
			String giftNumber = brandnum+giftnum+colorAndType+formatAns;
			giftEntity.setCode(giftNumber);
			giftEntity.setCreatedTime(new Timestamp(new Date().getTime()));
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}",userDetails);
			giftEntity.setCreatedBy(userDetails.getUsername());
			final GiftEntity insertResultFinal = giftService.update(giftEntity);
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
			form.setModifiedTime(new Timestamp(new Date().getTime()));
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("userDetails: {}",userDetails);
			form.setModifiedBy(userDetails.getUsername());
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
