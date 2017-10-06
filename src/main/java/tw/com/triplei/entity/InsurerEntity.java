package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 保險公司
 */
@Getter
@Setter
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

	@Column(name = "COMPLAINT_RATIO_DESC")
	private String complaintRatioDesc; // 投訴率介紹

	@Digits(integer = 15, fraction = 4)
	@Column(name = "BIS_RATIO")
	private BigDecimal bisRatio; // 資本適足率 Bank of International Settlement ratio

	@Column(name = "bisRatio_DESC")
	private String bisRatioDesc; // 資本適足率介紹

	@Digits(integer = 15, fraction = 4)
	@Column(name = "PERSISTENCY_RATIO")
	private BigDecimal persistencyRatio; // 保單繼續率

	@Column(name = "PERSISTENCY_RATIO_DESC")
	private String persistencyRatioDesc; // 保單繼續率介紹

	@Digits(integer = 13, fraction = 6)
	@Column(name = "LITIGATION_RATIO")
	private BigDecimal litigationRatio; // 訴訟率

	@Column(name = "LITIGATION_RATIO_DESC")
	private String litigationRatioDesc; // 訴訟率介紹

	@Digits(integer = 13, fraction = 6)
	@Column(name = "APPEAL_RATIO")
	private BigDecimal appealRatio; // 申訴率

	@Column(name = "APPEAL_RATIO_DESC")
	private String appealRatioDesc; // 申訴率介紹

	@Column(name = "INSURANCE_GUARANTY_FUND")
	private Boolean insuranceGuarantyFund; // 保險安定基金

	@Column(name = "CREDIT_RATING")
	private String credit_rating; // 信用評等

	@Column(name = "IMG_SRC")
	private String imgsrc;

	@Digits(integer = 14, fraction = 5)
	@Column(name = "RETURN_ON_ASSETS")
	private BigDecimal returnonAssets;// 資產報酬率

	@Column(name = "DESCRIPTION2")
	private String description2;

	@Column(name = "DESCRIPTION3")
	private String description3;

	@Column(name = "DESCRIPTION4")
	private String description4;

	@Override
	public String toString() {
		return "InsurerEntity [code=" + code + ", name=" + name + ", shortName=" + shortName + ", description="
				+ description + ", sortNo=" + sortNo + ", logo=" + logo + ", complaintRatio=" + complaintRatio
				+ ", complaintRatioDesc=" + complaintRatioDesc + ", bisRatio=" + bisRatio + ", bisRatioDesc="
				+ bisRatioDesc + ", persistencyRatio=" + persistencyRatio + ", persistencyRatioDesc="
				+ persistencyRatioDesc + ", litigationRatio=" + litigationRatio + ", litigationRatioDesc="
				+ litigationRatioDesc + ", appealRatio=" + appealRatio + ", appealRatioDesc=" + appealRatioDesc
				+ ", insuranceGuarantyFund=" + insuranceGuarantyFund + ", credit_rating=" + credit_rating + ", imgsrc="
				+ imgsrc + ", returnonAssets=" + returnonAssets + ", description2=" + description2 + ", description3="
				+ description3 + ", description4=" + description4 + "]";
	}



}
