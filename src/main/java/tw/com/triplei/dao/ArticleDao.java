package tw.com.triplei.dao;

import java.util.List;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity> {

	public List<ArticleEntity> findByBannerRotationAndStoreShelves(boolean bannerRotation, boolean storeShelves);	//輪播區選擇
	
	public List<ArticleEntity> findByArticleTypeAndStoreShelves(Enum articleType, boolean storeShelves); //文章個別分類
	
	public ArticleEntity findById(Long Id);
	
	public List<ArticleEntity> findByArticleTypeAndHotArticleAndStoreShelves(Enum articleType, boolean hotArticle, boolean storeShelves);

}
