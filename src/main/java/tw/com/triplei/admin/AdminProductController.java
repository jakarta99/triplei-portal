package tw.com.triplei.admin;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.entity.ProductCancelRatio;
import tw.com.triplei.entity.ProductEntity;
import tw.com.triplei.entity.ProductHighDiscountRatio;
import tw.com.triplei.entity.ProductPremiumRatio;
import tw.com.triplei.enums.Currency;
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
	
	
	/*編輯(熱門屬性)頁面*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {
		
		ProductEntity dbEntity = productService.getOne(id);
		model.addAttribute("entity", dbEntity);
		
		return "/admin/product/productEdit";
	}
	
	/*查看高保費率*/
	@RequestMapping(value = "/highDiscountRatio/{id}", method = RequestMethod.GET)
	public String highDiscountRatiosPage(@PathVariable("id") final Long id, Model model) {

		return "/admin/product/highDiscountRatio";
	}
	
	/*查看基本費率*/
	@RequestMapping(value = "/premiumRatio/{id}", method = RequestMethod.GET)
	public String premiumRatiosPage(@PathVariable("id") final Long id, Model model) {

		return "/admin/product/premiumRatio";
	}
	
	/*查看解約金費率*/
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
			final List<SpecCriterion> criterions = Lists.newArrayList();

			if(!Strings.isNullOrEmpty(form.getCode())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"code","%"+form.getCode()+"%"));
			}
			if(!Strings.isNullOrEmpty(form.getInterestRateType())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"interestRateType","%"+form.getInterestRateType()+"%"));
			}
			if (form.getCurr() != null) {
				criterions.add(new SpecCriterion(QueryOpType.EQ, "curr", form.getCurr()));
			}
			if(form.getYear()!=0){
				criterions.add(new SpecCriterion(QueryOpType.EQ,"year",form.getYear()));
			}
			if(!Strings.isNullOrEmpty(form.getLocalName())){
				criterions.add(new SpecCriterion(QueryOpType.LIKE,"localName","%"+form.getLocalName()+"%"));
			}
				
			page = productService.getByCondition(criterions, pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}

	/*上傳檔案*/
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

		return "redirect:/admin/product/list";
	}

	
	/*進編輯頁面修改熱門分類*/
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<ProductEntity> update(@RequestParam("id")String uid,@RequestParam("hotProduct")Boolean hotProduct,ProductEntity productEntity) {
		
		AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
		log.debug("{}{}{}",uid,hotProduct);
		try {
			Long id= Long.parseLong(uid);
			
			ProductEntity update= productService.getOne(id);
			update.setHotProduct(hotProduct);
			log.debug("ProductUpdate{}",update);
			
			final ProductEntity updateResult = productService.update(update);;
			response.setData(updateResult);
			
		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<ProductEntity> delete(@PathVariable(value = "id") final long id) {
		
		log.debug("{}", id);
		
		final AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
		
		try {			
			boolean result = productService.deleteAll(id);
			log.debug("result = "+result);
		
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;
				
	}

}