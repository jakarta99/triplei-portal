package tw.com.triplei.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 會員
 */
@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "USERMEMBER")
public class UserEntity extends GenericEntity implements UserDetails{
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber; // 會員帳號 
	
	@Column(name = "PASSWORD")
	private String password; // 密碼
	
	@Column(name = "NAME")
	private String name; // 會員姓名
	
	@Column(name = "EMAIL")
	private String email; 
	
	@Column(name = "ACTIVE")
	private String active;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<RoleEntity> roles; // 角色

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// return 授予的權限
		if (roles == null){
			return Lists.newArrayList();
		}
		return roles;  
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return name;  // can not return null
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true; // 帳戶是否過期
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;   // 密碼是否過期
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "UserEntity [accountNumber=" + accountNumber + ", password=" + password + ", name=" + name + ", email="
				+ email + ", active=" + active + ", roles=" + roles + "]";
	}


}
