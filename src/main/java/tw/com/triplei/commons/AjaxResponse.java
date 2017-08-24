package tw.com.triplei.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class AjaxResponse<T extends Serializable> {
	
	private T data;
	
	private List<Message> messages = new ArrayList<Message>();

	public AjaxResponse() {
		
	}
	
	public AjaxResponse(final Iterable<Message> messages) {
		this.messages = Lists.newArrayList(messages);
	}

	public AjaxResponse(final Throwable t) {
		log.error(t.getMessage(), t);
		Message message = Message.builder().code("EXCEPTON").value(t.getMessage()).build();
		this.messages = Lists.newArrayList(message);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public void addMessages(List<Message> messages) {
		this.messages.addAll(messages);
	}
	
	public void addException(Throwable t) {
		Message message = new Message("EXCEPTION", t.getMessage());
		this.messages.add(message);
	}
	
	
}
