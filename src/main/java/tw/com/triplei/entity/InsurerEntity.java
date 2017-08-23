package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tw.com.triplei.commons.GenericEntity;

/**
 * 保險公司
 */
@Entity
@Table(name = "INSURER")
public class InsurerEntity extends GenericEntity {
	
	@Column(name = "CODE", unique = true)
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SHORT_NAME")
	private String shortName;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "SORT_NO")
	private Integer sortNo; // 公司名稱筆畫 依此排序

	@Column(name = "LOGO")
	private String logo;
	
	@Column(name = "COMPLAINT_RATIO")
	private BigDecimal complaintRatio; // 投訴率

	@Column(name = "BIS_RATIO")
	private BigDecimal bisRatio; // 資本適足率 Bank of International Settlement ratio
	
	@Column(name = "PERSISTENCY_RATIO")
	private BigDecimal persistencyRatio; // 保單繼續率
	
	@Column(name = "LITIGATION_RATIO")
	private BigDecimal litigationRatio; // 訴訟率
	
	@Column(name = "APPEAL_RATIO")
	private BigDecimal appealRatio; // 申訴率
	
	@Column(name = "INSURANCE_GUARANTY_FUND")
	private Boolean insuranceGuarantyFund; // 保險安定基金
	
	@Column(name = "CREDIT_RATING")
	private String credit_rating; // 信用評等

	@Override
	public String toString() {
		return "InsurerEntity [code=" + code + ", name=" + name + ", shortName=" + shortName + ", description="
				+ description + ", sortNo=" + sortNo + ", logo=" + logo + ", complaintRatio=" + complaintRatio
				+ ", bisRatio=" + bisRatio + ", persistencyRatio=" + persistencyRatio + ", litigationRatio="
				+ litigationRatio + ", appealRatio=" + appealRatio + ", insuranceGuarantyFund=" + insuranceGuarantyFund
				+ ", credit_rating=" + credit_rating + ", id=" + id + ", createdBy=" + createdBy + ", createdTime="
				+ createdTime + ", modifiedBy=" + modifiedBy + ", modifiedTime=" + modifiedTime + "]";
	}





}
