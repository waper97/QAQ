package com.yj.common.model;

import java.util.List;

/**
 * @Title: TreeNode.java 【修改时需同步商城对象】
 * @Package com.xjt.model
 * @Description: TODO
 * @author xx
 * @date 2016-5-20
 */
public class TreeNode {
	private String id;
	private String text;
	private String code;
	private String type;
	private String url;
	private String state = "open";
	private String iconCls = "";

	private Integer scope;  //权限范围
	private String remark;
	private Integer status; //状态
	private Integer sortno;  //排序号
	private Integer isshow; //是否显示
	private String apiUrl;  //接口访问地址
	private String htmlUrl; //网页访问地址
	private String parentid;//父权限ID

	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public String getName() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public String getHtmlUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
}