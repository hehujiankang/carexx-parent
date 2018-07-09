package com.sh.carexx.mapp.wechat.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sh.carexx.common.util.JAXBUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WechatReplyNewsMsg extends WechatReplyMsg {
	@XmlElement(name = "ArticleCount")
	private String ArticleCount;

	@XmlElementWrapper(name = "Articles")
	@XmlElement(name = "item")
	private List<WechatReplyNewsArticle> Articles;

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<WechatReplyNewsArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<WechatReplyNewsArticle> articles) {
		Articles = articles;
	}

	public static void main(String[] args) {
		WechatReplyNewsMsg text = new WechatReplyNewsMsg();
		text.setToUserName("1");
		text.setFromUserName("2");
		text.setCreateTime(System.currentTimeMillis() + "");
		text.setMsgType("news");
		List<WechatReplyNewsArticle> articles = new ArrayList<WechatReplyNewsArticle>();
		WechatReplyNewsArticle article = new WechatReplyNewsArticle();
		article.setTitle("hi");
		article.setDescription("world");
		article.setPicUrl("www.baidu.com");
		article.setUrl("www.zmbeidiao.com");
		articles.add(article);
		text.setArticleCount(articles.size() + "");
		text.setArticles(articles);
		try {
			System.out.println(JAXBUtils.convert2Xml(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
