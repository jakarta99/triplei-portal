package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.triplei.commons.GenericEntity;

/**
 * 查表: 基本費率
 */
@Entity
@Table(name = "PRODUCT_PREMIUM_RATIO")
public class ProductPremiumRatio extends GenericEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "INS_AGE")
	private Integer insAge;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "PREMIUM_RATIO")
	private BigDecimal premiumRatio; // 基本費率

	@Override
	public String toString() {
		return "ProductPremiumRatio [id=" + id + ", productId=" + productId + ", insAge=" + insAge + ", gender="
				+ gender + ", premiumRatio=" + premiumRatio + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getInsAge() {
		return insAge;
	}

	public void setInsAge(Integer insAge) {
		this.insAge = insAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getPremiumRatio() {
		return premiumRatio;
	}

	public void setPremiumRatio(BigDecimal premiumRatio) {
		this.premiumRatio = premiumRatio;
	}

}
