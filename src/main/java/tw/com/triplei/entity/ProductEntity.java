package tw.com.triplei.entity;

import java.math.BigDecimal;
import java.util.Collection;

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
import javax.persistence.Transient;

import tw.com.triplei.commons.GenericEntity;
import tw.com.triplei.enums.Currency;

/**
 * 保險商品
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends GenericEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="INSURER_ID")
	private InsurerEntity insurer;
	
	@Column(name = "LOCAL_NAME")
	private String localName;
	
	@Column(name = "CODE")
	private String code; // 商品代碼
	
	@Column(name = "YEAR_CODE")
	private String yearCode; // 年度代碼
	
	@Column(name = "YEAR")
	private int year; // 年期

	@Column(name = "DECLARE_INTEREST_RATE")
	private BigDecimal declareInterestRate; // 宣告利率
	
	@Column(name = "PREDICT_INTEREST_RATE")
	private BigDecimal predictInterestRate; // 預定利率
	
	@Column(name = "TYPE_INTEREST_RATE")
	private String interestRateType; //利率別
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENCY")
	private Currency curr; // 幣別
	
//	@OneToMany
//	@JoinColumn(name = "PRODUCT_ID")
	@Transient
	private Collection<ProductHighDiscountRatio> highDiscountRatios; // 高保費率
	
//	@OneToMany
//	@JoinColumn(name = "PRODUCT_ID")
	@Transient
	private Collection<ProductPremiumRatio> premiumRatios; // 基本費率
	
//	@OneToMany
//	@JoinColumn(name = "PRODUCT_ID")
	@Transient
	private Collection<ProductCancelRatio> cancelRatios; // 解約金費率
	
	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod; // 繳費方式
	
	@Column(name = "INSURE_AMOUNT")
	private BigDecimal insureAmount; // 保額
	
	@Column(name = "PREMIUM")
	private BigDecimal premium; // 原本保費
	
	@Column(name = "PREMIUM_AFTER_DISCOUNT")
	private BigDecimal premiumAfterDiscount; // 折扣後保費
	

	@Column(name = "CASHVALUE")
	private BigDecimal cashValue; // 解約金  
	
	@Column(name = "STORE_SHELVES")
	private Boolean storeShelves; // 商品上架
	
	@Column(name = "BONUS_POINT")
	private BigDecimal bonusPoint; //點數趴數
	
	@Column(name="GET_POINT")
	private BigDecimal getPoint; //獲得點數
	
	@Column(name="IRR")
	private double irr;  //IRR



	public BigDecimal getPremiumAfterDiscount() {
		return premiumAfterDiscount;
	}
	
	public void setPremiumAfterDiscount(BigDecimal premiumAfterDiscount) {
		this.premiumAfterDiscount = premiumAfterDiscount;
	}

	public double getIrr() {
		return irr;
	}

	public void setIrr(double irr) {
		this.irr = irr;
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

	public String getInterestRateType() {
		return interestRateType;
	}

	public void setInterestRateType(String interestRateType) {
		this.interestRateType = interestRateType;
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
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getInsureAmount() {
		return insureAmount;
	}

	public void setInsureAmount(BigDecimal insureAmount) {
		this.insureAmount = insureAmount;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getCashValue() {
		return cashValue;
	}

	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}

	public Boolean getStoreShelves() {
		return storeShelves;
	}

	public void setStoreShelves(Boolean storeShelves) {
		this.storeShelves = storeShelves;
	}

	public BigDecimal getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(BigDecimal bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public BigDecimal getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(BigDecimal getPoint) {
		this.getPoint = getPoint;
	}

	@Override
	public String toString() {
		return "ProductEntity [id=" + id + ", insurer=" + insurer + ", localName=" + localName + ", code=" + code
				+ ", yearCode=" + yearCode + ", year=" + year + ", declareInterestRate=" + declareInterestRate
				+ ", predictInterestRate=" + predictInterestRate + ", interestRateType=" + interestRateType + ", curr="
				+ curr + ", highDiscountRatios=" + highDiscountRatios + ", premiumRatios=" + premiumRatios
				+ ", cancelRatios=" + cancelRatios + ", paymentMethod=" + paymentMethod + ", insureAmount="
				+ insureAmount + ", premium=" + premium + ", premiumAfterDiscount=" + premiumAfterDiscount
				+ ", cashValue=" + cashValue + ", storeShelves=" + storeShelves + ", bonusPoint=" + bonusPoint
				+ ", getPoint=" + getPoint + ", irr=" + irr + "]";
	}



}
