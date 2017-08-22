package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 查表: 解約金費率
 */
@Entity
@Table(name = "PRODUCT_CANCEL_RATIO")
public class ProductCancelRatio {
	
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
	
	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "CANCEL_RATIO")
	private BigDecimal cancelRatio;  // 解約金費率

	@Override
	public String toString() {
		return "ProductCancelRatio [id=" + id + ", productId=" + productId + ", insAge=" + insAge + ", gender=" + gender
				+ ", year=" + year + ", cancelRatio=" + cancelRatio + "]";
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public BigDecimal getCancelRatio() {
		return cancelRatio;
	}

	public void setCancelRatio(BigDecimal cancelRatio) {
		this.cancelRatio = cancelRatio;
	}
}
