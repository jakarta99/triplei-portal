package tw.com.triplei.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 角色
 */
@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "ROLE")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class RoleEntity extends GenericEntity implements GrantedAuthority{

	@Column(name = "NAME")
	private String name; // 角色名稱
	
	@Column(name = "CODE")
	private String code; // 角色代碼
	
	
	@ManyToMany(mappedBy="roles")
	private Set<UserEntity> users; // 會員

	@JsonIgnore
	@Override
	public String getAuthority() {
		return code;
	}

	// 避免循環呼叫，toString()不放 roles
	@Override
	public String toString() {
		return "RoleEntity [name=" + name + ", code=" + code +"]";
	}
}
