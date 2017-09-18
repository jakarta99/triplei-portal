package tw.com.triplei.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.ConvenienceStoreSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ConvenienceStoreEntity;
import tw.com.triplei.entity.QuestionEntity;
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
		
		return "redirect:/admin/convenienceStore/test" ;
	}
	
	@RequestMapping(value = "/searchRegion/{city}" , method = RequestMethod.GET)
	public AjaxResponse<ConvenienceStoreEntity> searchRegion(final Model model,@PathVariable("city") String city){
		log.debug("{}", city);
		
		final AjaxResponse<ConvenienceStoreEntity> response = new AjaxResponse<>();
		
		ConvenienceStoreEntity data = convenienceStoreService.findByCity(city);
		
		System.out.println(data);
		
		response.setData(data);
		
		return response;
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<ConvenienceStoreEntity> query(final Model model, final ConvenienceStoreEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<ConvenienceStoreEntity> page;

		try {

			page = convenienceStoreService.getAll(new ConvenienceStoreSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
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
