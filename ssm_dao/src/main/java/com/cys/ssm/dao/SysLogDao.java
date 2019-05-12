package com.cys.ssm.dao;


import com.cys.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    /*问题：ip插不进去*/
    @Insert("insert into syslog(visitTime,username,url,executionTime,method) values(#{visitTime},#{username},#{url},#{executionTime},#{method})")
    public void saveLog(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}
