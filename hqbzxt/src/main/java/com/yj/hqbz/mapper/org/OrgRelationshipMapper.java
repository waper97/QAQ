package com.yj.hqbz.mapper.org;

import java.util.List;

import com.yj.hqbz.model.org.OrgRelationship;

public interface OrgRelationshipMapper {
    OrgRelationship getByPrimaryKey(Integer id);
    /**添加往来关系*/
    void addRelation(OrgRelationship relation);
    /**删除往来关系*/
    void deleteRelation(Integer orgid);
    /**根据机构获取往来关系*/
    List<OrgRelationship> getRelationByOrg(Integer orgid);
}