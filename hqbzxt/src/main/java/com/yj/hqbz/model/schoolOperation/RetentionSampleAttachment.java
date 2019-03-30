package com.yj.hqbz.model.schoolOperation;

/**
 * @Title
 * @Description:    留样附件
 * @Author:         wangpeng
 * @CreateDate:     2019/3/29 11:56
 */
public class RetentionSampleAttachment {
    //附件id
    private String attenId;
    //留样id
    private String  rsid;
    //图片路径
    private  String picUrl;
    //缩略图路径
    private  String thumbnailUrl;

    public String getAttenId() {
        return attenId;
    }

    public void setAttenId(String attenId) {
        this.attenId = attenId;
    }

    public String getRsid() {
        return rsid;
    }

    public void setRsid(String rsid) {
        this.rsid = rsid;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "RetentionSampleAttachment{" +
                "attenId='" + attenId + '\'' +
                ", rsid='" + rsid + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
