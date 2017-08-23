package tw.com.triplei.commons;

import java.util.List;

import org.assertj.core.util.Lists;

public class ApplicationException extends RuntimeException {
	
	private List<Message> messages;

	public ApplicationException() {
		
	}
	
	public ApplicationException(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
		if(this.messages == null) {
			messages = Lists.newArrayList();
		}
		
		this.messages.add(message);
	}
	
}
