package tw.com.triplei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tw.com.triplei.commons.GenericEntity;

/*
 * 
 * 便利商店
 * 
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "CONVENIENCE_STORE")
public class ConvenienceStoreEntity extends GenericEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "STORE_NAME")
	private String storeName;

	@Column(name = "CITY")
	private String city;

	@Column(name = "REGION")
	private String region;

	@Column(name = "STREET")
	private String street;

	@Column(name = "ADDRESS")
	private String address;

	@Override
	public String toString() {
		return "ConvenienceStoreEntity [id=" + id + ", manufacturer=" + manufacturer + ", storeName=" + storeName
				+ ", city=" + city + ", region=" + region + ", street=" + street + ", address=" + address + "]";
	}
}
