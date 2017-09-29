package tw.com.triplei.dao;

import java.util.List;

import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity> {
	
	/*輪播區選擇(不要新聞)*/
	public List<ArticleEntity> findByArticleTypeAndBannerRotationAndStoreShelves(Enum articleType,boolean bannerRotation, boolean storeShelves);
	
	/*各文章分類*/
	public List<ArticleEntity> findByArticleTypeAndStoreShelves(Enum articleType, boolean storeShelves);
	
	/*Read Article*/	
	public ArticleEntity findById(Long Id);
	
	/*顯示在文章專欄的兩篇文章*/	
	public List<ArticleEntity> findByArticleTypeAndHotArticleAndStoreShelves(Enum articleType, boolean hotArticle, boolean storeShelves);

}
