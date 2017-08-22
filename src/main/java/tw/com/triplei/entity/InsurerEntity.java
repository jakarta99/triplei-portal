package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 保險公司
 */
@Entity
@Table(name = "INSURER")
public class InsurerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "INFORMATION")
	private String information;

	@Column(name = "LOGO")
	private String logo;
	
	@Column(name = "SORT_BY_STROKES")
	private int sortByStrokes; // 公司名稱筆畫 依此排序

	@Column(name = "COMPLAINT_RATIO")
	private BigDecimal complaintRatio; // 投訴率

	@Column(name = "BIS_RATIO")
	private BigDecimal bisRatio; // 資本適足率 Bank of International Settlement ratio
	
	@Column(name = "PERSISTENCY")
	private BigDecimal persistency; // 保單繼續率
	
	@Column(name = "LITIGATION_RATE")
	private BigDecimal litigationRate; // 訴訟率
	
	@Column(name = "APPEAL_RATE")
	private BigDecimal appealRate; // 申訴率
	
	@Column(name = "INSURANCE_GUARANTY_FUND")
	private boolean insuranceGuarantyFund; // 保險安定基金
	
	@Column(name = "CREDIT_RATING")
	private String credit_rating; // 信用評等

	@Override
	public String toString() {
		return "InsurerEntity [id=" + id + ", name=" + name + ", information=" + information + ", logo=" + logo
				+ ", sortByStrokes=" + sortByStrokes + ", complaintRatio=" + complaintRatio + ", bisRatio=" + bisRatio
				+ ", persistency=" + persistency + ", litigationRate=" + litigationRate + ", appealRate=" + appealRate
				+ ", insuranceGuarantyFund=" + insuranceGuarantyFund + ", credit_rating=" + credit_rating + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getSortByStrokes() {
		return sortByStrokes;
	}

	public void setSortByStrokes(int sortByStrokes) {
		this.sortByStrokes = sortByStrokes;
	}

	public BigDecimal getComplaintRatio() {
		return complaintRatio;
	}

	public void setComplaintRatio(BigDecimal complaintRatio) {
		this.complaintRatio = complaintRatio;
	}

	public BigDecimal getBisRatio() {
		return bisRatio;
	}

	public void setBisRatio(BigDecimal bisRatio) {
		this.bisRatio = bisRatio;
	}

	public BigDecimal getPersistency() {
		return persistency;
	}

	public void setPersistency(BigDecimal persistency) {
		this.persistency = persistency;
	}

	public BigDecimal getLitigationRate() {
		return litigationRate;
	}

	public void setLitigationRate(BigDecimal litigationRate) {
		this.litigationRate = litigationRate;
	}

	public BigDecimal getAppealRate() {
		return appealRate;
	}

	public void setAppealRate(BigDecimal appealRate) {
		this.appealRate = appealRate;
	}

	public boolean isInsuranceGuarantyFund() {
		return insuranceGuarantyFund;
	}

	public void setInsuranceGuarantyFund(boolean insuranceGuarantyFund) {
		this.insuranceGuarantyFund = insuranceGuarantyFund;
	}

	public String getCredit_rating() {
		return credit_rating;
	}

	public void setCredit_rating(String credit_rating) {
		this.credit_rating = credit_rating;
	}
	

}
