package com.cys.ssm.service;

import com.cys.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    public void saveLog(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer size) throws Exception;
}
