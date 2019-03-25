package com.yj.hqbz.mapper.system;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.system.Message;


public interface MessageMapper {
    Message selectByPrimaryKey(Integer id);
    //获取消息列表，已读，未读，所有
    List<Message> getMessageList(Map<String,Object> map);
    //标记为已读
    void updateHaveRead(Integer id);
    //标记为删除
    void deleteMessage(Integer id);
    //发送消息 
    void insertMessage(Message message);
}