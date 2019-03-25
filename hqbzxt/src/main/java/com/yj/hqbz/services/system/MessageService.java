package com.yj.hqbz.services.system;

import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.system.Message;


public interface MessageService {

    //发送通知
    void sendSysMsg(Message message);
    //获取消息列表
    PageInfo<Message> getMessageList(String userid,Integer readStatus,int page,int rows);
    //标记消息为已读
    void updateHaveRead(String ids);
    //删除消息
    void deleteMessage(String ids);
}
