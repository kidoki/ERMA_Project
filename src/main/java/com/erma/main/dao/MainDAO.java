package com.erma.main.dao;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.erma.common.dao.AbstractDAO;

@Repository("mainDAO")
public class MainDAO extends AbstractDAO{
	Logger log = Logger.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectApartInfo(Map<String, Object> commandMap) {
		log.debug("DB GET");
		Map<String, Object> returnValue = (Map<String, Object>)selectOne("apart.selectApartInfo", commandMap); 
		return returnValue;
	}

}
