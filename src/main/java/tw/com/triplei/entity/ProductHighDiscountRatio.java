package tw.com.triplei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 高保費率
 */
@Entity
@Table(name = "PRODUCT_HIGH_DISCOUNT_RATIO")
public class ProductHighDiscountRatio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "MIN_VALUE")
	private BigDecimal minValue; // 下限
	
	@Column(name = "MAX_VALUE")
	private Integer maxValue; //上限
	
	@Column(name = "DISCOUNT_RATIO")
	private BigDecimal discountRatio; // 折扣趴數

	@Override
	public String toString() {
		return "ProductHighDiscountRatio [id=" + id + ", productId=" + productId + ", minValue=" + minValue
				+ ", maxValue=" + maxValue + ", discountRatio=" + discountRatio + "]";
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

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(BigDecimal discountRatio) {
		this.discountRatio = discountRatio;
	}

}
