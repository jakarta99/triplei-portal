package tw.com.triplei.entity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 保險商品
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="INSURER_ID")
	private InsurerEntity insurer;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "YEAR_CODE")
	private String yearCode;
	
	@Column(name = "LOCAL_NAME")
	private String localName;
	
	@Column(name = "DECLARE_INTEREST_RATE")
	private BigDecimal declareInterestRate; // 宣告利率
	
	
	@Column(name = "PREDICT_INTEREST_RATE")
	private BigDecimal predictInterestRate; // 預定利率
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	private Currency curr; // 幣別
	
	@OneToMany
	@JoinColumn(name = "PRODUCT_ID")
	private Collection<ProductHighDiscountRatio> highDiscountRatios; // 高保費率
	
	@OneToMany
	@JoinColumn(name = "PRODUCT_ID")
	private Collection<ProductPremiumRatio> premiumRatios; // 基本費率
	
	@OneToMany
	@JoinColumn(name = "PRODUCT_ID")
	private Collection<ProductCancelRatio> cancelRatios; // 解約金費率

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", insurer=" + insurer + ", code=" + code + ", yearCode=" + yearCode
				+ ", localName=" + localName + ", declareInterestRate=" + declareInterestRate + ", predictInterestRate="
				+ predictInterestRate + ", curr=" + curr + ", highDiscountRatios=" + highDiscountRatios
				+ ", premiumRatios=" + premiumRatios + ", cancelRatios=" + cancelRatios + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InsurerEntity getInsurer() {
		return insurer;
	}

	public void setInsurer(InsurerEntity insurer) {
		this.insurer = insurer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getYearCode() {
		return yearCode;
	}

	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public BigDecimal getDeclareInterestRate() {
		return declareInterestRate;
	}

	public void setDeclareInterestRate(BigDecimal declareInterestRate) {
		this.declareInterestRate = declareInterestRate;
	}

	public BigDecimal getPredictInterestRate() {
		return predictInterestRate;
	}

	public void setPredictInterestRate(BigDecimal predictInterestRate) {
		this.predictInterestRate = predictInterestRate;
	}

	public Currency getCurr() {
		return curr;
	}

	public void setCurr(Currency curr) {
		this.curr = curr;
	}

	public Collection<ProductHighDiscountRatio> getHighDiscountRatios() {
		return highDiscountRatios;
	}

	public void setHighDiscountRatios(Collection<ProductHighDiscountRatio> highDiscountRatios) {
		this.highDiscountRatios = highDiscountRatios;
	}

	public Collection<ProductPremiumRatio> getPremiumRatios() {
		return premiumRatios;
	}

	public void setPremiumRatios(Collection<ProductPremiumRatio> premiumRatios) {
		this.premiumRatios = premiumRatios;
	}

	public Collection<ProductCancelRatio> getCancelRatios() {
		return cancelRatios;
	}

	public void setCancelRatios(Collection<ProductCancelRatio> cancelRatios) {
		this.cancelRatios = cancelRatios;
	}



}
