package com.yj.hqbz.model.schoolOperation;

/**
 * @Title
 * @Description:
 * @Author:         wangpeng
 * @CreateDate:     2019/4/16 16:38
 */
public class FoodAdditiveOfParent {

    private Integer typeId;

    private Integer parentId;

    private String typeName;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
