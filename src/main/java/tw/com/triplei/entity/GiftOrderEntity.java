package tw.com.triplei.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

@Entity
@Getter
@Setter
@Table(name = "GIFT_ORDER")
public class GiftOrderEntity extends GenericEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ORDER_TIME")
	private LocalDateTime orderTime;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	
//	@ManyToOne
//	@Column(name = "NAME")
//	private GiftEntity giftEntity;
	
}
