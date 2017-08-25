package tw.com.triplei.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.QuestionDao;
import tw.com.triplei.entity.QuestionEntity;

@Slf4j
@Service
public class QuestionService extends GenericService<QuestionEntity> {

	@Autowired
	private QuestionDao dao;

	@Override
	public GenericDao<QuestionEntity> getDao() {
		return dao;
	}

	@Override
	public QuestionEntity handleInsert(QuestionEntity entity) {
		// TODO Auto-generated method stub
		return super.handleInsert(entity);
	}

	@Override
	public QuestionEntity handleUpdate(QuestionEntity entity) {
		QuestionEntity dbEntity = dao.findOne(entity.getId());
		
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "code", "createdBy", "createdTime" });

		return dbEntity;
	}

	@Override
	public List<Message> validateInsert(QuestionEntity entity) {

		List<Message> messages = Lists.newArrayList();

		// if (entity.getId() != null) {
		// messages.add(Message.builder().code("id").value("新增不可有ID").build());
		// }

		log.debug("【debug訊息】", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(QuestionEntity entity) {

		List<Message> messages = Lists.newArrayList();

		QuestionEntity questionEntity = dao.findOne(entity.getId());

		if (questionEntity == null) {
			messages.add(Message.builder().code("id").value("ID不存在").build());
		}

		log.debug("【debug訊息】", messages);

		return messages;
	}

}
