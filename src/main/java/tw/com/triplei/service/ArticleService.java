package tw.com.triplei.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;
import tw.com.triplei.commons.GenericDao;
import tw.com.triplei.commons.GenericService;
import tw.com.triplei.commons.Message;
import tw.com.triplei.dao.ArticleDao;
import tw.com.triplei.entity.ArticleEntity;
import tw.com.triplei.enums.ArticleType;

@Slf4j
@Service
public class ArticleService extends GenericService<ArticleEntity> {

	@Autowired
	private ArticleDao dao;

	@Override
	public GenericDao<ArticleEntity> getDao() {
		return dao;
	}

	@Override
	public List<Message> validateInsert(ArticleEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ArticleEntity dbEntity = dao.findById(entity.getId());

		if (dbEntity != null) {
			messages.add(Message.builder().code("code").value("ID不可重複").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public List<Message> validateUpdate(ArticleEntity entity) {

		List<Message> messages = Lists.newArrayList();

		ArticleEntity dbEntity = dao.findOne(entity.getId());

		if (dbEntity == null) {
			messages.add(Message.builder().code("id").value("ID 不存在").build());
		}

		log.debug("{}", messages);

		return messages;
	}

	@Override
	public ArticleEntity handleUpdate(ArticleEntity entity) {
		ArticleEntity dbEntity = dao.findOne(entity.getId());

		// 以資料庫資料為主, 忽略不得修改的欄位
		BeanUtils.copyProperties(entity, dbEntity, new String[] { "id", "code", "createdBy", "createdTime" });

		return dbEntity;
	}

	public String imageUpload(MultipartFile file) {
		byte[] bytes;
		if (file.toString() != "") {
			try {
				bytes = file.getBytes();
				String path = "src/main/webapp/userfiles/bannerImage";
				String path1 = "/userfiles/bannerImage";
				File dir = new File(path);
				if (!dir.exists())
					dir.mkdirs();

				String date = DateTimeFormatter.ofPattern("MM-dd_HHmmss").format(LocalDateTime.now());
				File serverFile = new File(dir.getAbsolutePath() + File.separator + date + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				String url = path1 + "/" + date + file.getOriginalFilename();
				stream.write(bytes);
				stream.close();

				return url;
			} catch (IOException e) {
				e.printStackTrace();
				return "Upload Fail";
			}
		}
		return "";
	}

	public List<ArticleEntity> getBannerRotationArticles(boolean bannerRotation) { // 輪播區選擇
		List<ArticleEntity> list = dao.findByBannerRotation(bannerRotation);
		List<ArticleEntity> sortList;
		if (list.size() > 3) {
			sortList = list.subList(0, 2);
		} else {
			sortList = list;
		}
		return sortList;
	}

	public List<ArticleEntity> getArticlesByTypes(Enum articleType) {
		List<ArticleEntity> articles = dao.findByArticleType(articleType);
		return articles;
	}

	public List<ArticleEntity> getArticlesByHotArticle(Enum articleType, boolean hotArticle, boolean storeShelves) {
		List<ArticleEntity> list = dao.findByArticleTypeAndHotArticleAndStoreShelves(articleType, hotArticle,
				storeShelves);
		List<ArticleEntity> sortList;
		if (list.size() > 2) {
			sortList = list.subList(0, 2);
		} else {
			sortList = list;
		}

		return sortList;
	}
	
	/*首頁用*/
	public List<ArticleEntity> getHotArticles(boolean hotArticle, boolean storeShelves) {
		List<ArticleEntity> editorList = dao.findByArticleTypeAndHotArticleAndStoreShelves(ArticleType.EDITOR_CHOICE, hotArticle,storeShelves);
		List<ArticleEntity> goodList = dao.findByArticleTypeAndHotArticleAndStoreShelves(ArticleType.GOODREAD, hotArticle,storeShelves);
		List<ArticleEntity> investmentList = dao.findByArticleTypeAndHotArticleAndStoreShelves(ArticleType.INVESTMENT_TIPS, hotArticle,storeShelves);
		
		List<ArticleEntity> mixedList = new ArrayList<ArticleEntity>();
		mixedList.addAll(editorList);
		mixedList.addAll(goodList);
		mixedList.addAll(investmentList);
		log.debug("mixedList{}",mixedList);
		mixedList.sort(Comparator.comparingInt(ArticleEntity::getClickCount));
		Collections.reverse(mixedList);
		log.debug("reverse mixedList{}",mixedList);
		if (mixedList.size() > 4) {
		
			mixedList = mixedList.subList(0, 4);
			return mixedList;
		} else {
			return mixedList;
		}
	}

}