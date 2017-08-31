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

import tw.com.triplei.commons.GenericEntity;
import tw.com.triplei.enums.GiftType;

/**
 * 積點商品
 */
@Entity
@Table(name = "GIFT")
public class GiftEntity extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "GIFT_TYPE")
	private GiftType giftType;

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
	private String colorAndType; // 備註

	@Column(name = "REMARKS")
	private String remarks; // 備註

	@Column(name = "GIFT_NUMBER")
	private String giftNumber; // 備註
	
	@Override
	public String toString() {
		return "GiftEntity [id=" + id + ", giftType=" + giftType + ", name=" + name + ", brand=" + brand + ", image1="
				+ image1 + ", image2=" + image2 + ", image3=" + image3 + ", bonus=" + bonus + ", exchangeCount="
				+ exchangeCount + ", exchangePersonMax=" + exchangePersonMax + ", exchangeDate=" + exchangeDate
				+ ", hotGift=" + hotGift + ", remarks=" + remarks + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GiftType getGiftType() {
		return giftType;
	}

	public void setGiftType(GiftType giftType) {
		this.giftType = giftType;
	}

	public String getColorAndType() {
		return colorAndType;
	}

	public void setColorAndType(String colorAndType) {
		this.colorAndType = colorAndType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getExchangeCount() {
		return exchangeCount;
	}

	public void setExchangeCount(int exchangeCount) {
		this.exchangeCount = exchangeCount;
	}

	public int getExchangePersonMax() {
		return exchangePersonMax;
	}

	public void setExchangePersonMax(int exchangePersonMax) {
		this.exchangePersonMax = exchangePersonMax;
	}

	public LocalDateTime getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(LocalDateTime exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	public boolean isHotGift() {
		return hotGift;
	}

	public void setHotGift(boolean hotGift) {
		this.hotGift = hotGift;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGiftNumber() {
		return giftNumber;
	}

	public void setGiftNumber(String giftNumber) {
		this.giftNumber = giftNumber;
	}

}
