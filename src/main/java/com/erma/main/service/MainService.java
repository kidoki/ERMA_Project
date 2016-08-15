package com.erma.main.service;

import java.util.Map;

import com.erma.common.util.CommandMap;

public interface MainService {

	Map<String, Object> selectApartInfo(Map<String, Object> commandMap);

	Map<String, Object> searchApart(String regionCode, String month) throws Exception;

}
