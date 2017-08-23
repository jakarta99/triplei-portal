package tw.com.triplei.commons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Message {

	private String code;
	private String value;
	
}
