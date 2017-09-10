package tw.com.triplei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/**
 * 積點點數
 */
@Getter
@Setter
@Entity
@Table(name = "POINTS")
public class PointsEntity extends GenericEntity{
	
	private UserEntity userId;
	
	@Column(name = "TOTAL_POINTS")
	private Integer totalPoints; // 積點總點數
	
	@Column(name = "CHECK_POINTS")
	private Integer checkPoints; // 審核中點數
	
	@Column(name = "USED_POINTS")
	private Integer usedPoints; // 已兌換點數
	
	@Column(name = "POINTS")
	private Integer points; // 剩餘可用點數
	
	

}
