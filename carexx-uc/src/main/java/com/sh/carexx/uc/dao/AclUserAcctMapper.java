package com.sh.carexx.uc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sh.carexx.bean.acl.AclRegFormBean;
import com.sh.carexx.model.uc.AclUserAcct;

public interface AclUserAcctMapper {
	int insert(AclUserAcct aclUserAcct);

	AclUserAcct selectById(Integer id);

	AclUserAcct selectByAccount(String account);

	Integer selectAclUserCount(AclRegFormBean aclRegFormBean);

	List<Map<String, Object>> selectAclUserList(AclRegFormBean aclRegFormBean);

	int updateStatus(@Param("id") Integer id, @Param("srcStatus") Byte srcStatus,
			@Param("targetStatus") Byte targetStatus);

	int update(AclUserAcct aclUserAcct);
}