package com.cys.ssm.controller;

import com.cys.ssm.domain.Permission;
import com.cys.ssm.domain.Role;
import com.cys.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用于角色管理的controller
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("/role-list");
        return mv;
    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.saveRole(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查询role，并查询出可以添加的权限
     *
     * @param roleId
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(value = "id", required = true) String roleId) throws Exception {
        //根据roleId查询role
        Role role = roleService.findById(roleId);

        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/role-permission-add");
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        return mv;
    }


    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(value = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
