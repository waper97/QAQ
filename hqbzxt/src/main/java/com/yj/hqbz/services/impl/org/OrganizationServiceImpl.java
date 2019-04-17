package com.yj.hqbz.services.impl.org;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.area.AreaMapper;
import com.yj.hqbz.mapper.org.CustomerBannerMapper;
import com.yj.hqbz.mapper.org.OrgBelongMapper;
import com.yj.hqbz.mapper.org.OrgRelationshipMapper;
import com.yj.hqbz.mapper.org.OrganizationMapper;
import com.yj.hqbz.mapper.user.UserInfoMapper;
import com.yj.hqbz.model.org.CustomerBanner;
import com.yj.hqbz.model.org.OrgBelong;
import com.yj.hqbz.model.org.OrgRelationship;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.org.OrganizationService;
import com.yj.hqbz.util.StringUtil;
@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Resource
	private OrganizationMapper orgMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	@Resource
	private OrgRelationshipMapper orgRelationshipMapper;
	@Resource
	private CustomerBannerMapper customerBannerMapper;
	@Resource
	private OrgBelongMapper orgBelongMapper;
	@Resource
	private AreaMapper areaMapper;

	public Organization getByPrimaryKey(Integer orgid) {
		return orgMapper.getByPrimaryKey(orgid);
	}

	public List<Organization> getOrgByOrgType(Integer orgTypeid) {
		return orgMapper.getOrgByOrgType(orgTypeid);
	}
	
	public PageInfo<Organization> getOrgBill(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Organization> list = orgMapper.getOrgBill(param);
		for (Organization org : list) {
			org.setAreaName(areaMapper.getAreaNameById(org.getArea()));
		}
		PageInfo<Organization> info=new PageInfo<Organization>(list);
		return info;
	}
	
	
	public PageInfo<Organization> getOrgSimpleBill(Map<String, Object> param, int page, int rows) {
		PageHelper.startPage(page, rows);
		List<Organization> list = orgMapper.getOrgSimpleBill(param);
		PageInfo<Organization> info=new PageInfo<Organization>(list);
		return info;
	}

	public Organization getOrgById(Integer orgid) {
		Organization org = orgMapper.getOrgById(orgid);
		if(org!=null) {
			org.setAreaName(areaMapper.getAreaNameById(org.getArea()));
			org.setOrgRelation(orgRelationshipMapper.getRelationByOrg(orgid));
			org.setBanner(customerBannerMapper.getBannerByOrg(orgid));
			org.setBelong(orgBelongMapper.getBelongByOrg(orgid));
		}
		return org;
	}
	
	public List<CustomerBanner> getOrgBanner(Integer orgid) {
		return customerBannerMapper.getBannerByOrg(orgid);
	}
	
	
	public Organization getOrgByNameAndStyle(Organization org) {
		return orgMapper.getOrgByNameAndStyle(org);
	}
	
	@Transactional
	public int addOrg(Organization org) {
		int count = orgMapper.addOrg(org);
		if(count>0) {
			//添加往来机构关系
			addRelation(org.getOrgRelation(),org.getOrgid());
			//客户添加banner
			addBanner(org.getBanner(), org.getOrgid());
			//客户添加隶属学校（先删除隶属关系再添加）
			addBelong(org.getBelong(), org.getOrgid());
		}
		return count;
	}
	
	@Transactional
	public int updateOrg(Organization org) {
		int count = orgMapper.updateOrg(org);
		if(count>0) {
			//添加往来机构关系
			orgRelationshipMapper.deleteRelation(org.getOrgid());
			addRelation(org.getOrgRelation(),org.getOrgid());
			//客户添加banner
			customerBannerMapper.deleteBanner(org.getOrgid());
			addBanner(org.getBanner(), org.getOrgid());
			//客户添加隶属学校（先删除隶属关系再添加）
			addBelong(org.getBelong(), org.getOrgid());
		}
		return count;
	}
	
	//添加往来机构关系
	private void addRelation(List<OrgRelationship> orgRelation,Integer orgnOrgid) {
		if(orgRelation!=null&&orgRelation.size()>0) {
			for (OrgRelationship relation : orgRelation) {
				if(relation.getTargetOrgid()!=null) {
					relation.setOrgnOrgid(orgnOrgid);
					orgRelationshipMapper.addRelation(relation);
				}
			}
		}
	}
	
	//客户添加Banner
	private void addBanner(List<CustomerBanner> customerBanner ,Integer orgid) {
		if(customerBanner!=null&&customerBanner.size()>0) {
			for (CustomerBanner banner : customerBanner) {
				if(StringUtil.isNotBlank(banner.getPicUrl())&&StringUtil.isNotBlank(banner.getThumbnailUrl())) {
					banner.setOrgid(orgid);
					customerBannerMapper.addBanner(banner);
				}
			}
		}
	}
	
	//客户添加隶属关系（先删除隶属关系再添加）
	private void addBelong(OrgBelong belong,Integer orgid) {
		if(belong!=null&&belong.getYjOrgid()!=null&&StringUtil.isNotBlank(belong.getYjOrgName())&&belong.getAreaid()!=null) {
			OrgBelong belong1 = orgBelongMapper.getBelongByOrg(orgid);
			OrgBelong belong2=orgBelongMapper.getBelongByYjOrg(belong.getYjOrgid());
			//食堂belong1无记录，学校belong2无记录，则新增
			if(belong1==null&&belong2==null) {
				belong.setOrgids(orgid.toString());
				belong.setStatus(0);
				belong.setOpenDate(new Date());
				orgBelongMapper.addBelong(belong);
			}
			//食堂belong1无记录，学校belong2有记录
			if(belong1==null&&belong2!=null) {
				String orgids = StringUtil.isBlank(belong2.getOrgids())?orgid.toString():belong2.getOrgids()+","+orgid;
				belong2.setOrgids(orgids);
				orgBelongMapper.updateBelong(belong2);
			}
			//食堂belong1有记录，学校belong2无记录
			if(belong1!=null&&belong2==null) {
				//先修改
				String orgids = (","+belong1.getOrgids()+",").replaceAll(","+orgid+",", ",");
				if(orgids.equals(",")) {
					belong1.setOrgids(null);
				}else {
					belong1.setOrgids(orgids.substring(1, orgids.length()-1));
				}
				orgBelongMapper.updateBelong(belong1);
				//再添加
				belong.setOrgids(orgid.toString());
				belong.setStatus(0);
				belong.setOpenDate(new Date());
				orgBelongMapper.addBelong(belong);
			}
			//食堂belong1有记录，学校belong2有记录,且不是同一记录
			if(belong1!=null&&belong2!=null&&belong1.getId().intValue()!=belong2.getId().intValue()) {
				//先修改
				String orgids = (","+belong1.getOrgids()+",").replaceAll(","+orgid+",", ",");
				if(orgids.equals(",")) {
					belong1.setOrgids(null);
				}else {
					belong1.setOrgids(orgids.substring(1, orgids.length()-1));
				}
				orgBelongMapper.updateBelong(belong1);
				//再修改
				String orgids2 = StringUtil.isBlank(belong2.getOrgids())?orgid.toString():belong2.getOrgids()+","+orgid;
				belong2.setOrgids(orgids2);
				orgBelongMapper.updateBelong(belong2);
			}
		}else {
			//类似删除
			OrgBelong belong1 = orgBelongMapper.getBelongByOrg(orgid);
			if(belong1!=null) {
				String orgids = (","+belong1.getOrgids()+",").replaceAll(","+orgid+",", ",");
				if(orgids.equals(",")) {
					belong1.setOrgids("");
				}else {
					belong1.setOrgids(orgids.substring(1, orgids.length()-1));
				}
				orgBelongMapper.updateBelong(belong1);
			}
		}
	}
	
	
	@Transactional
	public int updateStatus(Organization org) {
		return orgMapper.updateStatus(org);
	}
	
	public boolean haveUserByOrg(Integer orgid) {
		List<UserInfo> user = userInfoMapper.getUserByOrg(orgid);
		if(user!=null&&user.size()>0) {
			return true;
		}else {
			return false;
		}
	}
}
