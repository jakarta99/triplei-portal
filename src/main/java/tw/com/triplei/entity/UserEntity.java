package tw.com.triplei.entity;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;
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
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class UserEntity extends GenericEntity implements UserDetails{
	
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber; // 會員帳號 
	
	@Column(name = "ORG_PASSWORD")
	private String orgPassword; // 原始密碼
	
	@Column(name = "PASSWORD")
	private String password; // 密碼
	
	@Column(name = "CHECK_PASSWORD")
	private String checkPassword; // 確認密碼
	
	@Column(name = "NAME")
	private String name; // 會員姓名
	
	@Column(name = "EMAIL")
	private String email; 
	
	@Column(name = "GENDER")
	private String gender; // 會員性別 
	
	@Column(name = "TEL")
	private String tel;  // 電話
	
	
	@Column(name = "BIRTHDATE")
	private LocalDate birthdate; // 生日
	
	@Column(name = "enabled")
	private Boolean enabled; // 啟用
	
	@Column(name = "REGISTERED_CODE")
	private String registeredCode; // 會員驗證碼 
	
	@Column(name = "EDIT_STATE")
	private String editState; // 修改密碼 pw/ 修改會員資料 info
	
	@Column(name = "REMAIN_WISH_TIMES")
	private Integer remainWishTimes;
	
	
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
		return accountNumber;  // can not return null
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
		return enabled;
	}

	@Override
	public String toString() {
		return "UserEntity [accountNumber=" + accountNumber + ", password=" + password + ", name=" + name + ", email="
				+ email + ", enabled=" + enabled + ", roles=" + roles + "]";
	}


	


}
