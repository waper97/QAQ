package com.yj.hqbz.model.schoolOperation;

/**
 * @Title
 * @Description:    留样附件
 * @Author:         wangpeng
 * @CreateDate:     2019/3/29 11:56
 */
public class RetentionSampleAttachment {
    //附件id
    private String attachid;
    //留样id
    private String  rsid;
    //图片路径
    private  String picUrl;
    //缩略图路径
    private  String thumbnailUrl;


    public String getAttachid() {
        return attachid;
    }

    public void setAttachid(String attachid) {
        this.attachid = attachid;
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

    public String toString() {
        return "RetentionSampleAttachment{" +
                "attenId='" + attachid + '\'' +
                ", rsid='" + rsid + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
