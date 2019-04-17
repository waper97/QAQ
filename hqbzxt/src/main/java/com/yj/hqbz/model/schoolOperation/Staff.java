package com.yj.hqbz.model.schoolOperation;

/**
 * @Title  
 * @Description:    工作人员统计
 * @Author:         wangpeng
 * @CreateDate:     2019/4/12 11:19
 */
public class Staff {

    //总人数
    private Integer total;
    //团长
    private Integer head;
    //库管
    private Integer libraryManagement;
    //内勤
    private Integer  backOffice;
    //其他服务人员
    private Integer otherServicePerson;
    //厨师
    private Integer cooker;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getLibraryManagement() {
        return libraryManagement;
    }

    public void setLibraryManagement(Integer libraryManagement) {
        this.libraryManagement = libraryManagement;
    }

    public Integer getBackOffice() {
        return backOffice;
    }

    public void setBackOffice(Integer backOffice) {
        this.backOffice = backOffice;
    }

    public Integer getOtherServicePerson() {
        return otherServicePerson;
    }

    public void setOtherServicePerson(Integer otherServicePerson) {
        this.otherServicePerson = otherServicePerson;
    }

    public Integer getCooker() {
        return cooker;
    }

    public void setCooker(Integer cooker) {
        this.cooker = cooker;
    }
}
