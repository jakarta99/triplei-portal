package tw.com.triplei.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import tw.com.triplei.admin.spec.ProductSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.ProductEntity;
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

	@GetMapping
	@ResponseBody
	public GridResponse<ProductEntity> query(final Model model, final ProductEntity form,
			@RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);

		Page<ProductEntity> page;

		try {

			// final List<AzaleaCriterion> criterions = Lists.newArrayList();
			//
			//
			// if (!Strings.isNullOrEmpty(form.getAccount())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account",
			// form.getAccount() + "%"));
			// }
			//
			// if (!Strings.isNullOrEmpty(form.getLocalName())) {
			// criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName", "%" +
			// form.getLocalName() + "%"));
			// }
			//
			// if (form.getStatus() != null) {
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status",
			// form.getStatus()));
			// }

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
			// if (!RoleUtil.isHaveAdminRoles()) {
			// final SecUser loginUser = (SecUser)
			// SecurityUtils.getSubject().getPrincipal();
			// criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId",
			// loginUser.getRootChannelId()));
			// }

			page = productService.getAll(new ProductSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}

	@PostMapping("/upload")
	@ResponseBody
	public AjaxResponse<String> insert(final Model model, @RequestParam("file") MultipartFile file) {
		AjaxResponse<String> response = new AjaxResponse<String>();

		if (!file.isEmpty()) {
			try {
				boolean upload= productService.ProductUpload(file);
				if(upload) {
					response.setData("Sucesss");
				}else {
					response.setData("Fail");
				}
			} catch (final Exception e) {
				response.addException(e);
			}
		}
		
		log.debug("{}", file);

		return response;
	}

	
	//// 以下新刪修 (9/2還用不到)
	// @PostMapping
	// @ResponseBody
	// public AjaxResponse<ProductEntity> insert(final Model model, @RequestBody
	//// final ProductEntity form) {
	//
	// AjaxResponse<ProductEntity> response = new AjaxResponse<ProductEntity>();
	//
	// try {
	//
	// final ProductEntity insertResult = productService.insert(form);
	// response.setData(insertResult);
	//
	// } catch (final ApplicationException ex) {
	// ex.printStackTrace();
	// response.addMessages(ex.getMessages());
	// } catch (final Exception e) {
	// response.addException(e);
	// }
	//
	// log.debug("{}", response);
	//
	// return response;
	// }
	//
	// @RequestMapping(method = RequestMethod.PUT)
	// @ResponseBody
	// public AjaxResponse<ProductEntity> update(final Model model, @RequestBody
	//// final ProductEntity form) {
	//
	// log.debug("{}", form);
	//
	// final AjaxResponse<ProductEntity> response = new
	//// AjaxResponse<ProductEntity>();
	//
	// try {
	//
	// final ProductEntity updateResult = productService.update(form);
	// response.setData(updateResult);
	//
	// } catch (final Exception e) {
	// response.addException(e);
	// }
	//
	// return response;
	// }
	//
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// @ResponseBody
	// public AjaxResponse<ProductEntity> delete(@PathVariable(value = "id") final
	//// long id) {
	//
	// log.debug("{}", id);
	//
	// final AjaxResponse<ProductEntity> response = new
	//// AjaxResponse<ProductEntity>();
	//
	// try {
	// productService.delete(id);
	//
	// } catch (final Exception e) {
	// return new AjaxResponse<>(e);
	// }
	// return response;
	// }
}