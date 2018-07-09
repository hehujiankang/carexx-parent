package com.sh.carexx.bean.user;

import com.sh.carexx.bean.BasicFormBean;

public class OAuthLoginFormBean extends BasicFormBean {
	private Byte identityType;

	private String identifier;

	private String credential;

	private String nickname;

	private String avatar;

	private Byte sex;

	private String region;

	public Byte getIdentityType() {
		return identityType;
	}

	public void setIdentityType(Byte identityType) {
		this.identityType = identityType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}