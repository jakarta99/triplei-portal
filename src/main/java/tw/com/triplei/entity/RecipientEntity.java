package tw.com.triplei.entity;



import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

@Setter
@Getter
@Entity
@Table(name = "RECIPIENT")
public class RecipientEntity extends GenericEntity {

	@Column(name="PRODUCT")
	private ProductEntity product; //商品

	@Column(name="NAME")
	private String name; //姓名
	
	@Column(name="GENDER")
	private String gender; //性別
	
	@Column(name="AGE")
	private int age; //年齡
	
	@Column(name="TEL")
	private String tel; //連絡電話
	
	@Column(name = "BOOKED_TIME_1")
	private String bookedTime_1; // 預定時間1
	
	@Column(name = "BOOKED_TIME_2")
	private String bookedTime_2; // 預定時間2
	
	@Column(name = "BOOKED_TIME_3")
	private String bookedTime_3; // 預定時間3
	
	@Column(name="CONVENIENCESTORE")
	private ConvenienceStoreEntity convenienceStoreEntity; //超商
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserEntity user; //業務員
	
	@Column(name="ORDERSTATUS")
	private String orderStatus; //訂單狀態
	
	@Column(name="ORDERNO")
	private String orderNo; //訂單號碼
	
	@Column(name="CAN_GET_POINT")
	private Integer canGetPoint; //可獲得點數
	
	@Column(name = "ALREADY_GET_POINT")
	private Boolean alreadyGetPoint; //是否已得到點數
	
	@Column(name = "ALREADY_AUDITTED_POINT")
	private Boolean alreadyAudittedPoint;//是否已審核點數
	
	@Column(name = "ALREADY_DELETED_POINT")
	private Boolean alreadyDeletedPoint; //是否已刪除點數
	
	@Column(name = "USER_NAME")
	private String userNamee;//業務員姓名(篩選用)
	
	@Column(name = "PCODE")
	private String code;//商品代碼(篩選用)
	
	@Column(name = "YEAR")
	private String year;//年期(篩選用)

	@Override
	public String toString() {
		return "RecipientEntity [product=" + product + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", tel=" + tel + ", bookedTime_1=" + bookedTime_1 + ", bookedTime_2=" + bookedTime_2
				+ ", bookedTime_3=" + bookedTime_3 + ", convenienceStoreEntity=" + convenienceStoreEntity + ", user="
				+ user + ", orderStatus=" + orderStatus + ", orderNo=" + orderNo + ", canGetPoint=" + canGetPoint
				+ ", alreadyGetPoint=" + alreadyGetPoint + ", alreadyAudittedPoint=" + alreadyAudittedPoint
				+ ", alreadyDeletedPoint=" + alreadyDeletedPoint + ", userNamee=" + userNamee + ", code=" + code
				+ "]";
	}






	


	
	
}
