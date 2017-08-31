package tw.com.triplei.dao;

import java.util.List;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity> {

	public ArticleEntity findById(Long Id);
	public List<ArticleEntity> findByArticleType(Enum articleType);
}
