package tw.com.triplei.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 許願池
 */
@Getter
@Setter
@Entity
@Table(name = "WISH")
public class WishEntity extends GenericEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "Name")
	private String name; // 商品名稱

	@Column(name = "IMAGE1")
	private String image1; // 商品圖片


	@Column(name = "BRAND") //品牌
	private String brand;

	@Column(name = "WEEK_USE_COUNT")
	private int weekUseCount; // 累積許願池使用次數
	
	@Column(name = "WISH_TIME")
	private String wishTime; // 許願日期
	
	@Column(name = "TIME")
	private Long time;//時間(篩選用)

	@Override
	public String toString() {
		return "WishEntity [id=" + id + ", name=" + name + ", image1=" + image1 + ", brand="
				+ brand + ", weekUseCount=" + weekUseCount + ", wishTime=" + wishTime + "]";
	}

	

}
