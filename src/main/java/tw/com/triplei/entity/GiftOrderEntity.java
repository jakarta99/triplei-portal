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
		
	@Column(name = "RECIPIENT")
	private String recipient;

	@Column(name = "RECIPIENT_ADDRESS")
	private String recipientAddress;

	@Column(name = "RECIPIENT_PHONE")
	private String recipientPhone;

	@Column(name = "RECIPIENT_TIME")
	private String recipientTime;
	
	@Column(name = "Gift")
	private GiftEntity giftEntity;
}
