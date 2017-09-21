package tw.com.triplei.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.ProductHighDiscountRatioSpecification;
import tw.com.triplei.admin.spec.ProductPremiumRatioSpecification;
import tw.com.triplei.admin.spec.ProductSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ProductCancelRatio;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.ProductHighDiscountRatio;
import tw.com.triplei.entity.ProductPremiumRatio;
import tw.com.triplei.service.ProductService;

@Slf4j
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/product/productList";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/product/productAdd";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {

		ProductEntity dbEntity = productService.getOne(id);
		model.addAttribute("entity", dbEntity);

		return "/admin/product/productEdit";
	}
	
	@RequestMapping(value = "/highDiscountRatio/{id}", method = RequestMethod.GET)
	public String highDiscountRatiosPage(@PathVariable("id") final Long id, Model model) {

		return "/admin/product/highDiscountRatio";
	}
	
	@RequestMapping(value = "/premiumRatio/{id}", method = RequestMethod.GET)
	public String premiumRatiosPage(@PathVariable("id") final Long id, Model model) {

		return "/admin/product/premiumRatio";
	}
	
	@RequestMapping(value = "/cancelRatio/{id}", method = RequestMethod.GET)
	public String cancelRatioPage(@PathVariable("id") final Long id, Model model) {
		
		return "/admin/product/cancelRatio";
	}

	@GetMapping("/cancelRatio/load/{id}")
	@ResponseBody
	public GridResponse<ProductCancelRatio> findCancelRatio(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,final Model model, ProductCancelRatio form,@PathVariable("id") Long id) {
		
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<ProductCancelRatio> page;

		try {
			
			page = productService.getProductCancelRatioDao().findByProductId(id, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}
	
	@GetMapping("/highDiscountRatio/load/{id}")
	@ResponseBody
	public GridResponse<ProductHighDiscountRatio> findHighDiscountRatio(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,final Model model, ProductHighDiscountRatio form,@PathVariable("id") Long id) {
		
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<ProductHighDiscountRatio> page;
		
		try {
			
			page = productService.getProductHighDiscountRatioDao().findByProductId(id, pageable);
			
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}
	
	@GetMapping("/premiumRatio/load/{id}")
	@ResponseBody
	public GridResponse<ProductPremiumRatio> findProductPremiumRatio(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageSize")int pageSize,final Model model, ProductPremiumRatio form,@PathVariable("id") Long id) {
		
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<ProductPremiumRatio> page;
		
		try {
			
			page = productService.getProductPremiumRatioDao().findByProductId(id, pageable);
			
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
		
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<ProductEntity> query(final Model model, final ProductEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<ProductEntity> page;

		try {
			page = productService.getAll(new ProductSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}

	@PostMapping
	public String insert(final Model model, @RequestParam("files") MultipartFile[] files) {
		AjaxResponse<String> response = new AjaxResponse<String>();

		if (files.length > 0) {
			for(MultipartFile file:files){
				try {
					boolean upload= productService.productUpload(file);
					if(upload) {
						response.setData("Sucesss");
					}else {
						response.setData("Fail");
					}
					boolean insert = productService.insertXlsxToDB(file);
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

		return "redirect:/admin/product/list";
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<ProductEntity> delete(@PathVariable(value = "id") final long id) {
		
		log.debug("{}", id);
		
		final AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
		
		try {			
			productService.delete(id);
		
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;
				
	}

}