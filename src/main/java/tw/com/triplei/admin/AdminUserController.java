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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.admin.spec.UserSpecification;
import tw.com.triplei.commons.AjaxResponse;
import tw.com.triplei.commons.ApplicationException;
import tw.com.triplei.commons.GridResponse;
import tw.com.triplei.commons.QueryOpType;
import tw.com.triplei.commons.SpecCriterion;
import tw.com.triplei.entity.InsurerEntity;
import tw.com.triplei.entity.RoleEntity;
import tw.com.triplei.entity.UserEntity;
import tw.com.triplei.service.AdminUserService;
import tw.com.triplei.service.RoleService;

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
		
		log.debug("form {}", form);
		Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
		
		Page<UserEntity> page;
		try {
			
			final List<SpecCriterion> criterions = Lists.newArrayList();
			
			if (!Strings.isNullOrEmpty(form.getAccountNumber())) {
				criterions.add(new SpecCriterion(QueryOpType.LIKE, "accountNumber", form.getAccountNumber() + "%"));
			}

			if (!Strings.isNullOrEmpty(form.getName())) {
				criterions.add(new SpecCriterion(QueryOpType.LIKE, "name", "%" + form.getName() + "%"));
			}

			if (form.getEnabled() != null) {
				criterions.add(new SpecCriterion(QueryOpType.EQ, "enabled", form.getEnabled()));
			}
			
			page = userService.getByCondition(criterions, pageable);
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
			UserEntity entity = userService.getOne(form.getId());
			// 不允許後台管理員修改:生日、性別、密碼
			form.setBirthdate(entity.getBirthdate());
			form.setGender(entity.getGender());
			form.setPassword(entity.getPassword());
			if(form.getEditState() == null){
				form.setEditState("");
			}
			
			
			final UserEntity updateResult = userService.update(form);

			log.debug("updateResult {}",updateResult);
			response.setData(updateResult);
			
			
		}  catch (final ApplicationException ex) {
			ex.printStackTrace();
			response.addMessages(ex.getMessages());
			
		} catch (final Exception e) {
			response.addException(e);
		}
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public AjaxResponse<UserEntity> delete(@PathVariable(value = "id") final long id) {
		
		log.debug("{}", id);
		
		final AjaxResponse<UserEntity> response = new AjaxResponse<UserEntity>();
		
		try {		
			UserEntity entity = userService.getOne(id);
			entity.setEditState("delete");
			entity.getRoles().clear();
			userService.delete(id);
			
		} catch (final Exception e) {
			return new AjaxResponse<>(e);
		}
		return response;
	}
	
}
