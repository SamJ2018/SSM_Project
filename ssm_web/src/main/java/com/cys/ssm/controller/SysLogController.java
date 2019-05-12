package com.cys.ssm.controller;

import com.cys.ssm.domain.SysLog;
import com.cys.ssm.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogList);
        mv.setViewName("/syslog-list");
        return mv;
    }*/


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name="size",required = true,defaultValue = "5") Integer size) throws Exception{
        ModelAndView mv=new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll(page,size);

        PageInfo pageInfo=new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/syslog-list");
        return mv;
    }
}
