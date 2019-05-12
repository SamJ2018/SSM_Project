package com.cys.ssm.service.impl;

import com.cys.ssm.dao.PermissionDao;
import com.cys.ssm.domain.Permission;
import com.cys.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) throws Exception {
        permissionDao.savePermission(permission);
    }
}
