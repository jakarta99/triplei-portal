package tw.com.triplei.admin;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.UserSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.RoleService;
import tw.com.triplei.service.AdminUserService;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AdminUserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(Model model) {
		return "/admin/user/userList";
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") final Long id, Model model) {
		
		UserEntity dbEntity = userService.getOne(id);
		model.addAttribute("entity", dbEntity);
		
		List<RoleEntity> allRoles = roleService.getAll();
		allRoles.removeAll(dbEntity.getRoles());
		
		model.addAttribute("unselectedRoles", allRoles);
		model.addAttribute("selectedRoles", dbEntity.getRoles());
		
		return "/admin/user/userEdit";
	}
	
	@GetMapping
	@ResponseBody
	public GridResponse<UserEntity> list(final Model model, final UserEntity form, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize) {
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<UserEntity> page;
		try {
			page = userService.getAll(new UserSpecification(), pageable);
		} catch (final Exception e) {
			return new GridResponse<>(e);
		}
		return new GridResponse<>(page);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public AjaxResponse<UserEntity> update(final Model model, @RequestBody final UserEntity form) {
		
		log.debug("{}", form);

		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {
			
			final UserEntity updateResult = userService.update(form);
			log.debug("updateResult {}",updateResult);
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
			userService.delete(id);
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;
	}
	
}
