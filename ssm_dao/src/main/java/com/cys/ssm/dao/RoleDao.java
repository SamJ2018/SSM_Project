package com.cys.ssm.dao;

import com.cys.ssm.domain.Permission;
import com.cys.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleDao {

    /**
     * 根据用户ID查询所有对应的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cys.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色信息
     * @return
     * @throws Exception
     */
    @Select("select * from role")
    List<Role> findAll()throws Exception;

    /**
     * 添加角色信息
     * @param role
     * @throws Exception
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role) throws Exception;

    /**
     * 根据roleId查询当前的角色信息
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;


    /**
     * 查询该角色没有的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(String roleId)throws Exception;


    /**
     * 给用户添加权限
     * @param roleId
     * @param permissionId
     * @throws Exception
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId)throws Exception;

}
