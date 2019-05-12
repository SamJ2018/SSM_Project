package com.cys.ssm.service;

import com.cys.ssm.domain.Permission;
import com.cys.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findAll() throws Exception;

    void saveRole(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;

}
