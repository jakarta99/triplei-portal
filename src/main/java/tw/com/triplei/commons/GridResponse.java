package tw.com.triplei.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class GridResponse<T extends Serializable> {

	
	private long itemsCount = 0;
	
	private int pageAt = 0;
	
	private long totalPages = 1;
	
	private List<T> data;
	
	private List<Message> messages = new ArrayList<Message>();
	
	public GridResponse(final Page<T> page) {
		this.pageAt = page.getNumber();
		this.itemsCount = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.data = page.getContent();
	}

	public GridResponse(final Iterable<Message> messages) {
		this.messages = Lists.newArrayList(messages);
	}

	public GridResponse(final Page<T> page, final Iterable<Message> messages) {
		this.pageAt = page.getNumber();
		this.itemsCount = page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.data = page.getContent();
		
		this.messages = Lists.newArrayList(messages);
		
	}
	
	public GridResponse(final Throwable t) {
		log.error(t.getMessage(), t);
		Message message = Message.builder().code("EXCEPTON").value(t.getMessage()).build();
		this.messages = Lists.newArrayList(message);
	}
}
