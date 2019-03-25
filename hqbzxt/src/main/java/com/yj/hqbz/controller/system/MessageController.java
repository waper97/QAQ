package com.yj.hqbz.controller.system;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.system.Message;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.system.MessageService;

@RestController
public class MessageController extends BaseController{

    @Resource
    MessageService msgService;
    
    @GetMapping("/user/message/getMessageList")
    public Object getMessageList(String readStatus,DataGridModel grid){
        Integer status = null;
        if(!StringUtils.isBlank(readStatus)){
            status = Integer.parseInt(readStatus);
        }
        UserInfo user = getTokenUser();
        PageInfo<Message> pageList = msgService.getMessageList(user.getUserid(), status,grid.getPage(), grid.getRows());
        return new BaseRes("获取消息列表成功!",pageList.getTotal(),pageList.getPages(),pageList.getList());  
    }
    
    @PostMapping("/user/message/read")
    public Object updateHaveRead(String ids){
        if(StringUtils.isBlank(ids)){
            return fail("参数错误！");
        }
        msgService.updateHaveRead(ids);
        return success("操作成功！");
    }
    
    @PostMapping("/user/message/delete")
    public Object deleteMsg(String ids){
        if(StringUtils.isBlank(ids)){
            return fail("参数错误！");
        }
        msgService.deleteMessage(ids);
        return success("操作成功！");
    }
    /**
     * 发送消息测试
     */
    @PostMapping("/buyer/message/send")
    public Object sendSysMsg(Message message){
        message.setUserid("4444");
        msgService.sendSysMsg(message);
        return success("发送通知成功！");
    }
}
