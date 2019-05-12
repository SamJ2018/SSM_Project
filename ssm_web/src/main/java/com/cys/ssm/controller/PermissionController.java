package com.cys.ssm.controller;

import com.cys.ssm.domain.Permission;
import com.cys.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有角色权限
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("/permission-list");
        return mv;
    }

    /**
     * 添加权限信息
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String savePermission(Permission permission) throws Exception {
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }
}
