package com.yj.hqbz.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.common.model.UserRightInfo;
import com.yj.hqbz.model.org.OrgBelong;
import com.yj.hqbz.model.org.Organization;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.order.OrderFormService;
import com.yj.hqbz.services.org.OrganizationService;
import com.yj.hqbz.services.system.SystemRoleAndResService;
import com.yj.hqbz.services.user.UserService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.util.tmthreadpool.TmThreadPool;
import com.yj.hqbz.web.Global;

/**  
 * @Title: UserController.java
 * @Package com.yj.hqbz.controller.system
 * @Description: TODO
 * @author xx
 * @date 2019-2-22
 */
@RestController
public class UserController extends BaseController{
	@Resource
	SystemRoleAndResService authService;
	@Resource
	UserService userService;
	@Resource
	OrganizationService orgService;
	@Resource
	OrderFormService orderService;
	
	@Value("${sms.address}")
    private String smsUrl;
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@GetMapping("/user/getInfo")
	public Object getUserInfo(){
	    UserInfo user = this.getTokenUser();
        if(user == null){
            return fail("对不起，你还未登录！");
        }
//		String userid = user.getUserid();
	    //UserInfo user = userService.selectByPrimaryKey("4444");
		UserRightInfo right = authService.getUserRolesAndRightInfo(user.getUserid(),user.getUserRole());
		if(right == null){
			return fail("操作非法！");
		}
		else{
			
			Map<String,Object> userInfo = new HashMap<String,Object>();
			userInfo.put("userid", user.getUserid());
			userInfo.put("trueName", right.getName());
			userInfo.put("orgName", user.getOrgName());
			userInfo.put("orgid", user.getOrgId());
			userInfo.put("head", user.getHeadThumbnailUrl()); 
			userInfo.put("mobile", user.getMobile());
			userInfo.put("birthday", CommUtil.formatLongDate(user.getBirthday()));
			userInfo.put("sex", user.getSex());
			userInfo.put("userType", user.getUserRole()); //用户类型，包括管理员
			if(user.getUserRole().intValue()==2){
				userInfo.put("schoolManager", user.getOrgManager()) ; //是否为学校管理员
				OrgBelong belong = userService.getBelongSchoolByOrgid(user.getOrgId());
				if(belong!=null){
				    userInfo.put("schoolName", belong.getYjOrgName());
				    userInfo.put("schoolid", belong.getYjOrgid());
				}
			}
			if(right.getRolesAndRights()!=null){
				userInfo.put("menu", right.getRolesAndRights().getMenu());
				return success(userInfo);
			}
			else
				return fail("对不起，你没有相关权限，请与管理员联系！");
		}		
		
	}
	/**
	 * 后台用户登录
	 */
	@PostMapping("/user/login")
	public Object userLogin(String username,String pwd,String code,String type){
	    if(StringUtil.isBlank(username)||StringUtil.isBlank(code)
	    		|| StringUtils.isBlank(type)
	            || ((!"0".equals(type) && !"1".equals(type)))){
	        return fail("参数错误！");
	    }
	    UserInfo userInfo = null;
	    if("0".equals(type)){
	    	if(StringUtil.isBlank(pwd)){
	    		return fail("参数非法");
	    	}
	    	Object kaptcha = WebUtils.getSessionAttribute(request, "KAPTCHA_SESSION_KEY");
	    	if(kaptcha == null){
	    		return fail("请重新获取验证码");
	    	}
	    	request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
	    	
		    String sessionCode = (String) kaptcha;
		    if(!code.equals(sessionCode)){
		        return fail("验证码不正确！");
		    }
		    
		    request.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
		    userInfo = userService.getUserByUserName(username);
		    if(userInfo == null){
		        return fail("用户名不存在！");
		    }
		    Integer orgid = userInfo.getOrgId();
		    if(orgid!=null&&orgid!=-1){
		        Organization org = orgService.getByPrimaryKey(orgid);
		        if(org==null||org.getStatus()==2){
		            return fail("机构不存在！");
		        }
		        if(org.getStatus()==1){
		            return fail("机构已停用！");
		        }
		        
		    }
		    if(!pwd.equalsIgnoreCase(StringUtil.MD5(userInfo.getPassword()))){
		        return fail("用户名或密码不正确！");
		    }
	    }
	    else{ //验证手机号码
	    	Object phoneCodeObj = WebUtils.getSessionAttribute(request, "code");
	    	if(phoneCodeObj == null){
	    		return fail("请重新获取验证码");
	    	}
	    	String phoneCode = (String) phoneCodeObj;
	        if(!code.equals(phoneCode)){
	            return fail("无效验证码！");
	        }
	        userInfo = userService.getUserByPhone(username);
	        if(userInfo == null){
	            return fail("系统中不存在该手机号码！");
	        }
            Integer orgid = userInfo.getOrgId();
            if(orgid!=null&&orgid!=-1){
                Organization org = orgService.getByPrimaryKey(orgid);
                if(org==null||org.getStatus()==2){
                    return fail("机构不存在！");
                }
                if(org.getStatus()==1){
                    return fail("机构已停用！");
                }
                
            }
	        request.getSession().removeAttribute("code");
	    }
	    

	    WebUtils.setSessionAttribute(request, "userInfo", userInfo);

	    Map<String,Object> data = new HashMap<String,Object>();
	    data.put("name", userInfo.getTrueName());
	    data.put("type", userInfo.getUserRole());
	    
	    authService.clearUserRoleAndRight(userInfo.getUserid());
	    authService.getUserRolesAndRightInfo(userInfo.getUserid(),userInfo.getUserRole());
	    saveJournalLog("用户【"+userInfo.getUserName()+"】登录成功", "id:"+userInfo.getUserid());
	    return success(data);
	}
	
	/**
	 * 后台用户退出
	 */
	@PostMapping("/user/common/logout")
	public Object loginOut(){
	    WebUtils.setSessionAttribute(request, "userInfo", null);
	    return success("退出成功！");
	}
	/**
	 * 用户列表
	 */
	@GetMapping("/user/manage/list")
	public Object getUserList(UserInfo user,DataGridModel grid){
	    UserInfo userInfo = getTokenUser();
	    //商家获取本机构人员
	    if(userInfo.getOrgId()!=-1){
	        user.setOrgId(userInfo.getOrgId());
	    }
		PageInfo<UserInfo> pageList = userService.getUserList(user, grid.getPage(), grid.getRows());
	    return new BaseRes("获取用户列表成功!",pageList.getTotal(),pageList.getPages(),pageList.getList());  
	}
	/**
	 * 根据用户ID获取用户基本信息
	 */
	@GetMapping("/user/manage/getUserById")
    public Object getUserById(String userid) {

        if (StringUtils.isBlank(userid)) {
            return fail("参数错误！");
        }
        UserInfo user = userService.getUserById(userid);
        if (user != null) {
            return success(user);
        } else {
            return fail("获取用户信息失败!");
        }

    }
	/**
	 * 保存用户
	 */
    @PostMapping("/user/manage/saveUser")
    public Object saveUser(UserInfo user) {

    	Integer orgid = user.getOrgId();
        Integer roleType = 0;
        Organization org = null;
        if (orgid != null&&orgid!=-1) {
            org = orgService.getByPrimaryKey(orgid.intValue());
            roleType = org.getOrgStyle();
            user.setUserRole(roleType);
        }else{
            user.setOrgId(-1);
            user.setUserRole(0);
        }
        UserInfo newUserInfo = userService.getUserByPhone(user.getMobile());
        if(newUserInfo!=null&&!newUserInfo.getUserid().equals(user.getUserid())){
            return fail("手机号码已存在！");
        }
        newUserInfo = userService.getUserByUserName(user.getUserName());
        if(newUserInfo!=null&&!newUserInfo.getUserid().equals(user.getUserid())){
            return fail("用户名已存在！");
        }
        if (StringUtils.isBlank(user.getUserid())) {
            UserInfo opUser = getTokenUser();
            userService.createUser(user, opUser.getUserid(), roleType);
            if (org == null) {
                saveJournalLog("新增后台管理用户【" + user.getUserName() + "】", "id:"
                        + user.getUserid());
            } else {
                saveJournalLog(
                        "新增【" + org.getName() + "】机构用户【" + user.getUserName()
                                + "】", "id:" + user.getUserid());
            }
        } else {          
            userService.updateUserById(user);
            if (org == null) {
                saveJournalLog("修改后台管理用户【" + user.getUserName() + "】", "id:"
                        + user.getUserid());
            } else {
                saveJournalLog(
                        "修改【" + org.getName() + "】机构用户【" + user.getUserName()
                                + "】", "id:" + user.getUserid());
            }
        }
        return success("保存成功!");

    }
	/**
	 * 重置密码
	 */
	@PostMapping("/user/manage/resetPwd")
    public Object resetPwd(String userid) {
        if (StringUtils.isBlank(userid)) {
            return fail("参数错误！");
        }
        UserInfo user = userService.getUserById(userid);
        if (user == null) {
            return fail("没有这个用户!");
        }
        String pwd = CommUtil.randomString(6);
        userService.resetPwd(userid, pwd);
        saveJournalLog("重置用户【" + user.getUserName() + "】密码为【" + pwd + "】", "id:" + user.getUserid());
        return success("重置密码成功");
    }
	/**
	 * 获取授权列表
	 */
	@GetMapping("/user/manage/getAuthRoleList")
    public Object getAuthListByUser(String userid) {
        if (StringUtils.isBlank(userid)) {
            return fail("参数为空！");
        }
        UserInfo user = userService.getUserById(userid);
        if (user == null) {
            return fail("用户不存在！");
        }
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("roleList", userService.getRoleListByOrgid(user.getOrgId()));
        res.put("haveRoleList", userService.getUserHaveRoles(userid));
        return success(res);

    }
	/**
	 * 保存用户权限
	 */
    @PostMapping("/user/manage/saveRoles")
    public Object saveUserRoles(String userid, String roles) {

        if (StringUtils.isBlank(userid)) {
            return fail("用户ID为空！");
        }
        UserInfo user = userService.getUserById(userid);
        if (user == null) {
            return fail("用户不存在！");
        }
        userService.saveUserRoles(userid, roles);
        saveJournalLog(
                "修改用户【" + user.getUserName() + "】角色，角色ID【" + roles + "】", "id:"
                        + user.getUserid());
        return success("保存数据成功！");

    }
	/**
	 * 前端用户登录
	 */
	@PostMapping("/user/buyer/login")
	public Object buyerLogin(String username,String pwd,String type){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(pwd)
                || StringUtils.isBlank(type)
                || ((!"0".equals(type) && !"1".equals(type)))) {
            return fail("参数错误！");
        }
        UserInfo user = null;
        if ("0".equals(type)) {
            user = userService.getUserByUserName(username);
            if(user == null ||!pwd.equalsIgnoreCase(StringUtil.MD5(user.getPassword()))){
                return fail("用户名或密码不正确！");
            }           
        } else {
            String code = (String) WebUtils.getSessionAttribute(request, "code");
            if(!pwd.equals(code)){
                return fail("无效验证码！");
            }
            user = userService.getUserByPhone(username);
            if(user == null){
                return fail("系统中不存在该手机号码！");
            }
        }

        if(user.getUserRole()!=2){
            return fail("非买家用户不能登录买家！");
        }
        
        //String token = TokenUtil.createAccsessToken(user.getUserid(), getRequestAddr(), Global.getSystemParamIntValueByKey("session_time")); 
        WebUtils.setSessionAttribute(request, "userInfo", user);
        authService.clearUserRoleAndRight(user.getUserid());
        authService.getUserRolesAndRightInfo(user.getUserid(),user.getUserRole());
        return success("登录成功！");
	}
	/**
	 * 验证旧手机
	 */
	@PostMapping("/user/buyer/verifyOldPhone")
	public Object verifyOldPhone(String code){
	    String sessionCode = (String)WebUtils.getSessionAttribute(request, "code");
	    if(StringUtils.isBlank(code)||!code.equals(sessionCode)){
	        return fail("验证码不合法！");
	    }
	    return success("手机验证成功！");	    
	}
	/**
	 * 绑定新手机
	 */
	@PostMapping("/user/buyer/updateMobile")
	public Object changePhone(String mobile,String code){
	    UserInfo user = getTokenUser();
	    if(StringUtils.isBlank(mobile)||StringUtils.isBlank(code)){
	        return fail("参数错误！");
	    }
	    String sessionCode = (String)WebUtils.getSessionAttribute(request, "code");
	    if(!code.equals(sessionCode)){
	        return fail("验证码不合法！");
	    }
	    user.setMobile(mobile);
	    userService.updateUserById(user);
	    return success("更换手机成功！");
	}
	/**
	 * 修改密码
	 */
	@PostMapping("/user/common/changePwd")
	public Object changePwd(String oldPwd,String newPwd){
	    if(StringUtils.isBlank(oldPwd)||StringUtils.isBlank(newPwd)){
	        return fail("参数有误！");
	    }
	    UserInfo user = getTokenUser();
	    if(!oldPwd.equalsIgnoreCase(StringUtil.MD5(user.getPassword()))){
	        return fail("旧密码不正确！");
	    }
	    userService.updatePwd(user.getUserid(), newPwd);
	    return success("修改密码成功！");
	}
	/**
	 * 获取验证码
	 */
	@GetMapping({"/common/code/getVerifyCode"})
    public Object getVerifyCode(String mobile) {	    
	    WebUtils.setSessionAttribute(request, "code", null);
        if (StringUtils.isBlank(mobile)) {
            return fail("电话号码为空！");
        }
        UserInfo user = userService.getUserByPhone(mobile);
        if(user == null){
            return fail("系统中不存在该手机号码！");
        }
        String code = CommUtil.randomInt(6);
        String[] args = new String[1];
        args[0] = code;
        TmThreadPool.sendSms(smsUrl, mobile, "verifyCode",args);
        WebUtils.setSessionAttribute(request, "code", code);
        if (Global.getSystemParamIntValueByKey("is_debug") == 1) {
        	return success(code);
        }
        else{
        	return success("请在30分钟内输入验证码！");
        }
    }
	/**
	 * 个人信息修改
	 */
	@PostMapping("/user/buyer/update")
	public Object updateUserInfo(UserInfo user){
	    UserInfo userInfo = getTokenUser();
	    user.setUserid(userInfo.getUserid());
	    userService.updateUserInfo(user);
	    return success("更新成功！");
	}
    public static void main(String[] args){
        String url = "http://119.84.8.162:8602/center" + "/api/sms/sendSms";
        Map<String,Object> params = new HashMap<String,Object>();
        
    }
    
    /**
     * 根据当前用户ID获取该学校的所有用户
     * @return
     */
    @GetMapping("/user/manage/getSchoolUser")
    public Object getSchoolUser(String keywords){
    	UserInfo user = getTokenUser();
    	if(user.getUserRole().intValue()==2){
    		//取得所有该学校的所有用户
    		Organization userOrg = orgService.getOrgById(user.getOrgId());
    		if(userOrg!=null && userOrg.getStatus()==0 && userOrg.getBelong() == null){
    			OrgBelong belong = userOrg.getBelong();
    			String orgIds = belong.getOrgids();
    			return userService.getOrgUserList(orgIds.split(","), keywords, 1, 0); 
    		}
    		else{
    			return fail("操作非法!");
    		}
    	}
    	else{
    		return fail("操作非法！");
    	}
    }
    /**
     * 用户订单金额和数量汇总
     */
    @GetMapping("/user/buyer/getOrderSummary")
    public Object getOrderSummary(){
        UserInfo user = getTokenUser();
        return success(orderService.getOrderSummary(user.getUserid()));
    }
}