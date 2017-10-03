package tw.com.triplei.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.entity.ConvenienceStoreEntity;
import tw.com.triplei.service.ConvenienceStoreService;

@Slf4j
@Controller
@RequestMapping("/convenienceStore")
public class ConvenienceStoreController {

	@Autowired
	private ConvenienceStoreService convenienceStoreService;
			

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
	

	
}
