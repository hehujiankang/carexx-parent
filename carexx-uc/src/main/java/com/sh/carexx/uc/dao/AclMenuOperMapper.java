package com.sh.carexx.uc.dao;

import java.util.List;

import com.sh.carexx.model.uc.AclMenuOper;

public interface AclMenuOperMapper {
	List<AclMenuOper> selectAllByMenuId(Integer menuId);
}