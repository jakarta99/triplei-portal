package tw.com.triplei.dao;

import java.util.List;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity> {

	public List<ArticleEntity> findByBannerRotation(boolean bannerRotation);	//輪播區選擇
	
	public List<ArticleEntity> findByArticleType(Enum articleType);
	
	public ArticleEntity findById(Long Id);
	
	public List<ArticleEntity> findByArticleTypeAndHotArticleAndStoreShelves(Enum articleType, boolean hotArticle, boolean storeShelves);
}
