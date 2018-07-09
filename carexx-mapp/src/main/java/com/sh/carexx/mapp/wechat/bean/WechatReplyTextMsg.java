package com.sh.carexx.mapp.wechat.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sh.carexx.common.util.JAXBUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WechatReplyTextMsg extends WechatReplyMsg {
	@XmlElement(name = "Content")
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public static void main(String[] args) {
		WechatReplyTextMsg text = new WechatReplyTextMsg();
		text.setToUserName("1");
		text.setFromUserName("2");
		text.setCreateTime(System.currentTimeMillis() + "");
		text.setMsgType("text");
		text.setContent("你好");
		try {
			System.out.println(JAXBUtils.convert2Xml(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
