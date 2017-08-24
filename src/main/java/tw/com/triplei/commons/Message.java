package tw.com.triplei.commons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
@ToString
public class Message {

	private String code;
	private String value;
	
	
	@Tolerate
	public Message() {
		
	}
}
