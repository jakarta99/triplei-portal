package tw.com.triplei.commons;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericService<T extends GenericEntity> {

	public abstract GenericDao<T> getDao();

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getDao().findAll();
	}

	@Transactional(readOnly = true)
	public Page<T> getAll(Specification<T> spec, Pageable page) {
		return getDao().findAll(spec, page);
	}

	@Transactional(readOnly = true)
	public T getOne(Long id) {
		return getDao().findOne(id);
	}

	public abstract List<Message> validateInsert(T entity);

	public T handleInsert(T entity) {
		return entity;
	}

	public abstract List<Message> validateUpdate(T entity);

	public T handleUpdate(T entity) {
		return entity;
	}

	@Transactional
	public T insert(T entity) {
		// 進行資料驗證
		List<Message> messages = validateInsert(entity);
		if (messages != null && messages.size() > 0) {
			throw new ApplicationException(messages);
		}

		// 進行資料整理
		entity = handleInsert(entity);

		return getDao().save(entity);
	}

	@Transactional
	public T update(T entity) {
		// 進行資料驗證
		List<Message> messages = validateUpdate(entity);
		if (messages != null && messages.size() > 0) {
			throw new ApplicationException(messages);
		}

		// 進行資料整理
		entity = handleUpdate(entity);

		return getDao().save(entity);
	}

	@Transactional
	public void delete(Long id) {
		getDao().delete(id);
	}

}
