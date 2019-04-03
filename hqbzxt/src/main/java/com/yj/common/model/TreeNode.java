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

}
