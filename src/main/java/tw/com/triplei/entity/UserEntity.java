package tw.com.triplei.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.social.connect.Connection;
import org.springframework.social.security.SocialUserDetails;

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
//public class UserEntity extends GenericEntity implements UserDetails {
public class UserEntity extends GenericEntity implements SocialUserDetails {

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
	
	// FIXME
	// userService :修改密碼 pw, 修改會員資料 info, 忘記密碼 forgetpw
	// adminUserService :刪除 delete, 一般/其他 ""
	@Column(name = "EDIT_STATE")
	private String editState; 
	
	@Column(name = "REMAIN_WISH_TIMES")
	private Boolean remainWishTimes; //剩餘許願池次數
	
	@Column(name = "REMAIN_POINT")
	private Integer remainPoint; //剩餘點數
	
	@Column(name = "AUDITTING_POINT")
	private Integer audittingPoint; //審核中點數
	
	@Column(name = "EXCHANGED_POINT")
	private Integer exchangedPoint; //已兌換點數
	
	
	// FB social
	@Column(name = "PROVIDER")
	private String provider; // 通路
	
	@Column(name = "PROVIDER_USER_ID")
	private String providerUserId; // social login 使用者
	
//	@Column(name = "DISPLAY_NAME")
//	private String displayName; // social login user name
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<RoleEntity> roles; // 角色
	
	public UserEntity() {
	}

	public UserEntity(Connection<?> connection) {
		this.provider = "FB";
		this.providerUserId = connection.getKey().getProviderUserId();
		//this.accountNumber = connection.getKey().getProviderUserId();
		//this.displayName = connection.getDisplayName();
		this.name = connection.getDisplayName();
		this.enabled = true;
	}

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
		return "UserEntity [accountNumber=" + accountNumber + ", orgPassword=" + orgPassword + ", password=" + password
				+ ", checkPassword=" + checkPassword + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", tel=" + tel + ", birthdate=" + birthdate + ", enabled=" + enabled + ", registeredCode="
				+ registeredCode + ", editState=" + editState + ", remainWishTimes=" + remainWishTimes
				+ ", remainPoint=" + remainPoint + ", audittingPoint=" + audittingPoint + ", exchangedPoint="
				+ exchangedPoint + ", roles=" + roles + "]";
	}


	@Override
	public String getUserId() {
		return providerUserId;
	}

	
	// 
}
