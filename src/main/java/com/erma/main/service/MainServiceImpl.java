package com.erma.main.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.erma.main.dao.MainDAO;
import com.erma.main.vo.ResponseVO;

@Service("mainService")
public class MainServiceImpl implements MainService {
	Logger log = Logger.getLogger(this.getClass());
    
    @Resource(name="mainDAO")
    private MainDAO mainDAO;

	@Override
	public Map<String, Object> selectApartInfo(Map<String, Object> commandMap) {
		return mainDAO.selectApartInfo(commandMap);
	}

	@Override
	public Map<String, Object> searchApart(String regionCode, String month) throws Exception {
		String key = "v%2Fdfv5MAOi%2Bp0AxTYSuIyqm%2FVHQTKMZ7bP8SrABGm77ARdxVMKfN6zi%2BDJAEsf8%2FzPeDa%2Fnu1ZScmi0PcjxHcw%3D%3D";
		String endPoint = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade";
		
		StringBuilder urlBuilder = new StringBuilder(endPoint); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(regionCode, "UTF-8")); /*지역코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode(month, "UTF-8")); /*검색월*/
        
        log.debug(urlBuilder.toString());
        InputStream xmlStream = getXmlStreamFromUrl(urlBuilder.toString());
        
        ResponseVO response = null;

        try {   
        	JAXBContext jaxbContext = JAXBContext.newInstance(ResponseVO.class);
        	response = (ResponseVO) jaxbContext.createUnmarshaller().unmarshal(xmlStream);  
        } catch (JAXBException e) {
        	e.printStackTrace();
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", response);      
        
		return map;
	}
	
	public InputStream getXmlStreamFromUrl(String requestUrl){	  
		URL url = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream ips = null;

		try {
			url = new URL(requestUrl);   
			URLConnection urlConnection = url.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setRequestMethod("GET");
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setDefaultUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-Type", "text/xml");

			ips = httpUrlConnection.getInputStream();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ips;		
	}

}
