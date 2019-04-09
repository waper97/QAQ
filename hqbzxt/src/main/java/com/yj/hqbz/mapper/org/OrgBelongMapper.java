package com.yj.hqbz.mapper.org;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.org.OrgBelong;

public interface OrgBelongMapper {
    OrgBelong getByPrimaryKey(Integer id);
    /**根据食堂ID获取隶属关系*/
    OrgBelong getBelongByOrg(Integer orgid);
    /**根据渝教学校ID获取隶属关系*/
    OrgBelong getBelongByYjOrg(Integer yjOrgid);
    /**新增隶属关系*/
    void addBelong(OrgBelong belong);
    /**修改隶属关系*/
    void updateBelong(OrgBelong belong);
    
    //=========================溯源=========================
    List<Map<String,Object>> getBelongArea();
    
    List<Map<String,Object>> getSchoolByArea(Map<String,Object> paramMap);
    
    List<Map<String,Object>> getOrgBySchool(Integer schoolid);
}