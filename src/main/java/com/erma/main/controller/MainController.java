package com.erma.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.erma.common.util.CommandMap;
import com.erma.main.service.MainService;
import com.erma.main.vo.ItemVO;
import com.erma.main.vo.ResponseVO;

@Controller
public class MainController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="mainService")
    private MainService mainService;
    
    @RequestMapping(value="/main/mainView.do")
    public ModelAndView main(Map<String,Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/main/main");
        log.debug("Main Ŭ���� ȣ��");
        
        Map<String,Object> selectInfo = mainService.selectApartInfo(commandMap);
        mv.addObject("map", selectInfo);
         
        return mv;
    }
    
    @RequestMapping(value="/main/aptGet.do")
    public ModelAndView aptGet(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("jsonView");
        Map<String,Object> map = new HashMap<String,Object>();
        ResponseVO response = new ResponseVO();
        
        log.debug("����Ʈ�ŸŰ� ��ȸ");
        
        if(commandMap.isEmpty() == false){
        	map = commandMap.getMap(); 
        }
        
        String regionCode = (String)map.get("regionCode");
        String month = (String)map.get("month");
        
        Map<String,Object> apartInfo = mainService.searchApart(regionCode, month);
        response = (ResponseVO)apartInfo.get("info");
        List<ItemVO> ItemVO = response.getBody().getItems().getItem();
        
        mv.addObject("name", ItemVO.get(0).get����Ʈ());
        mv.addObject("floor", ItemVO.get(0).get��());
        mv.addObject("amount", ItemVO.get(0).get�ŷ��ݾ�().trim());
         
        return mv;
    }
}
