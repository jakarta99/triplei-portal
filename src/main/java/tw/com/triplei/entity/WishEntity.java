package tw.com.triplei.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 許願池
 */
@Entity
@Table(name = "Wish")
public class WishEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "Name")
	private String name; // 商品名稱

	@Column(name = "IMAGE1")
	private String image1; // 商品圖片

	@Column(name = "WISH_TYPE")
	private String wishType;

	@Column(name = "BRAND")
	private String brand;

	@Column(name = "WEEK_USE_COUNT")
	private int weekUseCount; // 累積許願池使用次數
	
	@Column(name = "WISH_TIME")
	private LocalDateTime wishTime; // 許願日期

	@Override
	public String toString() {
		return "WishEntity [id=" + id + ", name=" + name + ", image1=" + image1 + ", wishType=" + wishType + ", brand="
				+ brand + ", weekUseCount=" + weekUseCount + ", wishTime=" + wishTime + "]";
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

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getWishType() {
		return wishType;
	}

	public void setWishType(String wishType) {
		this.wishType = wishType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getWeekUseCount() {
		return weekUseCount;
	}

	public void setWeekUseCount(int weekUseCount) {
		this.weekUseCount = weekUseCount;
	}

	public LocalDateTime getWishTime() {
		return wishTime;
	}

	public void setWishTime(LocalDateTime wishTime) {
		this.wishTime = wishTime;
	}

}
