package tw.com.triplei.admin;

import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.ConvenienceStoreSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
import tw.com.triplei.entity.ConvenienceStoreEntity;
import tw.com.triplei.service.ConvenienceStoreService;

@Slf4j
@Controller
@RequestMapping("/admin/convenienceStore")
public class AdminConvenienceStoreController {

	@Autowired
	private ConvenienceStoreService convenienceStoreService;
			
	
	@RequestMapping(value ="/test")
	public String testSearch(Model model) {
		return "/admin/convenienceStore/test" ;
	}
	
	@RequestMapping(value ="/list")
	public String listPage(Model model) {
		return "/admin/convenienceStore/convenienceStoreList" ;
	}
	
	@RequestMapping(value ="/insertPage")
	public String inertFile (Model model) {
		
		return "/admin/convenienceStore/convenienceStoreAdd" ;
	}
	
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/convenienceStore/convenienceStoreGridAdd";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		ConvenienceStoreEntity dbEntity = convenienceStoreService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/convenienceStore/convenienceStoreEdit";
	}
		
	@RequestMapping(value = "/insertFile" , method = RequestMethod.POST)
	public String inert (final Model model, @RequestParam("files") MultipartFile[] files) {
		AjaxResponse<String> response = new AjaxResponse<String>();

		if (files.length > 0) {
			for(MultipartFile file:files){
				try {
					boolean upload= convenienceStoreService.FileUpload(file);
					if(upload) {
						response.setData("Sucesss");
					}else {
						response.setData("Fail");
					}
					boolean insert = convenienceStoreService.insertXlsxToDB(file);
					if(insert) {
						response.setData("insertSucesss");
					}else {
						response.setData("insertFail");
					}
				} catch (final Exception e) {
					response.addException(e);
				}
			}
		}
		log.debug("{}", files);
		
		return "redirect:/admin/convenienceStore/list" ;
	}
	
	@RequestMapping(value = "/searchRegion" , method = RequestMethod.GET)
	@ResponseBody
	public List<ConvenienceStoreEntity> searchRegion(final Model model, String city){
		log.debug("{}", city);
		
		log.debug("this city is = {}", city);
		
//		final AjaxResponse<ConvenienceStoreEntity> response = new AjaxResponse<>();
		List<ConvenienceStoreEntity> data = convenienceStoreService.findByCity(city);
//		Map<String,Object> resultMap = new HashMap<String, Object>();
		log.debug("{}", "hahaha="+data);
		log.debug("{}", "hahaha="+data.size());
		
//		int counter = 1;
//		Iterator<ConvenienceStoreEntity> iter =  data.iterator();
//		while (iter.hasNext()) {
//			ConvenienceStoreEntity entity = iter.next();
//			resultMap.put("city" + counter++, entity);
//			log.debug("{}", "hahaha=" + entity);
//		}		
//		log.debug("{}","while finished-------------------------------------------------");		
//		
		return data;
	}
	
	@RequestMapping(value = "/searchStreet" , method = RequestMethod.GET)
	@ResponseBody
	public List<ConvenienceStoreEntity> searchStreet(final Model model, String city , String region){
		log.debug("{}", city);
		log.debug("{}", region);
		
		log.debug("this city is = {}", city);
		log.debug("this city is = {}", region);
		
		List<ConvenienceStoreEntity> data = convenienceStoreService.findByCityAndRegion(city, region);
		log.debug("{}", "hahaha="+data);
		log.debug("{}", "hahaha="+data.size());

		return data;
	}
	@RequestMapping(value = "/searchAddress" , method = RequestMethod.GET)
	@ResponseBody
	public List<ConvenienceStoreEntity> searchAddress(final Model model, String city , String region, String street){
		log.debug("{}", city);
		log.debug("{}", region);
		log.debug("{}", street);
		
		log.debug("this city is = {}", city);
		log.debug("this region is = {}", region);
		log.debug("this street is = {}", street);
		
		List<ConvenienceStoreEntity> data = convenienceStoreService.findByCityAndRegionAndStreet(city, region, street);
		log.debug("{}", "hahaha="+data);
		log.debug("{}", "hahaha="+data.size());
		
		return data;
	}
	
	@RequestMapping(value = "/searchStoreName" , method = RequestMethod.GET)
	@ResponseBody
	public ConvenienceStoreEntity searchAddress(final Model model, String address){
		
		log.debug("this address is = {}", address);
		
		ConvenienceStoreEntity data = convenienceStoreService.findByAddress(address);
		log.debug("{}", "hahaha="+data);
		
		return data;
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<ConvenienceStoreEntity> query(final Model model, final ConvenienceStoreEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<ConvenienceStoreEntity> page;

		try {
			final List<SpecCriterion> criterions = Lists.newArrayList();
			
			if(!Strings.isNullOrEmpty(form.getManufacturer())){
				criterions.add(new SpecCriterion(QueryOpType.EQ, "manufacturer", form.getManufacturer()));
			}
			if(!Strings.isNullOrEmpty(form.getAddress())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE, "address", "%"+form.getAddress()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getRegion())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE, "region","%"+form.getRegion()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getCity())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE, "city", "%"+form.getCity()+"%"));	
			}

			page = convenienceStoreService.getByCondition(criterions, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<ConvenienceStoreEntity> insert(final Model model, @RequestBody ConvenienceStoreEntity form) {
		
		AjaxResponse<ConvenienceStoreEntity> response = new AjaxResponse<>();
		
		try {
			
			final ConvenienceStoreEntity insertResult = convenienceStoreService.insert(form);
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
	
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<ConvenienceStoreEntity> update(final Model model, @RequestBody final ConvenienceStoreEntity form) {

		log.debug("{}", form);

		final AjaxResponse<ConvenienceStoreEntity> response = new AjaxResponse<>();

		try {
			log.debug("{}", form);
			final ConvenienceStoreEntity updateResult = convenienceStoreService.update(form);
			response.setData(updateResult);

		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<ConvenienceStoreEntity> delete(@PathVariable(value = "id") final long id) {

		log.debug("{}", id);

		final AjaxResponse<ConvenienceStoreEntity> response = new AjaxResponse<>();

		try {
			convenienceStoreService.delete(id);

		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;

	}
}
