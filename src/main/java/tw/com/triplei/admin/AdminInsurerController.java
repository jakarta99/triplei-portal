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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.InsurerSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.service.InsurerService;

@Slf4j
@Controller
@RequestMapping("/admin/insurer")
public class AdminInsurerController {

	@Autowired
	private InsurerService insurerService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/insurer/insurerList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addPage(Model model) {
		return "/admin/insurer/insurerAdd";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {
		
		InsurerEntity dbEntity = insurerService.getOne(id);
		model.addAttribute("entity", dbEntity);
		
		return "/admin/insurer/insurerEdit";
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<InsurerEntity> query(final Model model, final InsurerEntity form, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<InsurerEntity> page;

		
		try {

//			final List<AzaleaCriterion> criterions = Lists.newArrayList();
//
//
//			if (!Strings.isNullOrEmpty(form.getAccount())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "account", form.getAccount() + "%"));
//			}
//
//			if (!Strings.isNullOrEmpty(form.getLocalName())) {
//				criterions.add(new AzaleaCriterion(QueryOpType.LIKE, "localName", "%" + form.getLocalName() + "%"));
//			}
//
//			if (form.getStatus() != null) {
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "status", form.getStatus()));
//			}

			// adminRole 可以管理所有的通路和使用者，userAdminRole 僅可以管理自己 ROOT_ID 之下的通路和使用者
//			if (!RoleUtil.isHaveAdminRoles()) {
//				final SecUser loginUser = (SecUser) SecurityUtils.getSubject().getPrincipal();
//				criterions.add(new AzaleaCriterion(QueryOpType.EQ, "rootId", loginUser.getRootChannelId()));
//			}

			page = insurerService.getAll(new InsurerSpecification(), pageable);

		} catch (final Exception e) {
			return new GridResponse<>(e);
		}

		return new GridResponse<>(page);
	}
	
	@PostMapping
	@ResponseBody
	public AjaxResponse<InsurerEntity> insert(final Model model, @RequestBody final InsurerEntity form) {
		
		AjaxResponse<InsurerEntity> response = new AjaxResponse<InsurerEntity>();
		
		try {
			
			final InsurerEntity insertResult = insurerService.insert(form);
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
	public AjaxResponse<InsurerEntity> update(final Model model, @RequestBody final InsurerEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<InsurerEntity> response = new AjaxResponse<InsurerEntity>();
		
		try {
			
			final InsurerEntity updateResult = insurerService.update(form);
			response.setData(updateResult);
			
		} catch (final Exception e) {
			response.addException(e);
		}

		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<InsurerEntity> delete(@PathVariable(value = "id") final long id) {
		
		log.debug("{}", id);
		
		final AjaxResponse<InsurerEntity> response = new AjaxResponse<InsurerEntity>();
		
		try {			
			insurerService.delete(id);
		
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;
		
				
	}
}
