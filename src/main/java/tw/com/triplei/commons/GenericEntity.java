package tw.com.triplei.commons;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass 
public abstract class GenericEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	protected Long id;
	
	@Column(name = "CREATED_BY")
	protected String createdBy;
	
	@Column(name = "CREATED_TIME")
	protected Timestamp createdTime;
	
	@Column(name = "MODIFIED_BY")
	protected String modifiedBy;
	
	@Column(name = "MODIFIED_TIME")
	protected Timestamp modifiedTime;
	
}
