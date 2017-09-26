package tw.com.triplei.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;
import tw.com.triplei.enums.GiftType;

/**
 * 積點商品
 */
@Entity
@Getter
@Setter
@Table(name = "GIFT")
public class GiftEntity extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "GIFT_TYPE")
	private GiftType giftType;

	@Column(name = "CODE")
	private String code; // 商品代碼: brand id + name id + color id + 流水號
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "BRAND")
	private String brand;

	@Column(name = "IMAGE1")
	private String image1; // 預設可放3張積點商品的圖片

	@Column(name = "IMAGE2")
	private String image2;

	@Column(name = "IMAGE3")
	private String image3;

	@Column(name = "BONUS")
	private int bonus; // 商品兌換點數

	@Column(name = "EXCHANGE_COUNT")
	private int exchangeCount; // 累積兌換次數

	@Column(name = "EXCHANGE_PERSON_MAX")
	private int exchangePersonMax; // 最大購買數量

	@Column(name = "EXCHANGE_DATE")
	private LocalDateTime exchangeDate; // 兌換日期

	@Column(name = "HOT_GIFT")
	private boolean hotGift; // 熱門積點商品
	
	@Column(name = "COLOR_AND_TYPE")
	private String colorAndType; // 顏色/花樣

	@Column(name = "REMARKS")
	private String remarks; // 備註

	
	@Override
	public String toString() {
		return "GiftEntity [id=" + id + ", giftType=" + giftType + ", name=" + name + ", brand=" + brand + ", image1="
				+ image1 + ", image2=" + image2 + ", image3=" + image3 + ", bonus=" + bonus + ", exchangeCount="
				+ exchangeCount + ", exchangePersonMax=" + exchangePersonMax + ", exchangeDate=" + exchangeDate
				+ ", hotGift=" + hotGift + ", remarks=" + remarks + "]";
	}
	
}
