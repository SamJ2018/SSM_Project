package com.cys.ssm.service;

import com.cys.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findAll() throws Exception;

    void savePermission(Permission permission) throws Exception;
}
