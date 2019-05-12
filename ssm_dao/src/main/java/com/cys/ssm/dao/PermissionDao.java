package com.cys.ssm.dao;

import com.cys.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    /**
     * 根据角色查询权限信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;


    /**
     * 查询所有角色权限
     * @return
     * @throws Exception
     */
    @Select("select * from  permission")
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限信息
     * @param permission
     * @throws Exception
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission) throws Exception;
}
