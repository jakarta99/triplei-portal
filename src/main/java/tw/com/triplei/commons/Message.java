package tw.com.triplei.commons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class Message {

	private String code;
	private String value;
	
	
	@Tolerate
	public Message() {
		
	}
}
