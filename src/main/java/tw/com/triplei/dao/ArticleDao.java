package tw.com.triplei.dao;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity> {

	public ArticleEntity findById(Long Id);
}
