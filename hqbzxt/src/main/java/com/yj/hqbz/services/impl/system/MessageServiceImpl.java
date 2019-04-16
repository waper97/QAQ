package com.yj.hqbz.services.impl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.system.MessageMapper;
import com.yj.hqbz.model.system.Message;
import com.yj.hqbz.services.system.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    MessageMapper msgMapper;
    
    
    @Transactional
    public void sendSysMsg(Message message) {
        msgMapper.insertMessage(message);
    }

    
    public PageInfo<Message> getMessageList(String userid,Integer readStatus, int page,
            int rows) {
        PageHelper.startPage(page,rows); 
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("readStatus", readStatus);
        List<Message> msgList = msgMapper.getMessageList(params);
        PageInfo<Message> info = new PageInfo<Message>(msgList);
        return info;
    }

    
    @Transactional
    public void updateHaveRead(String ids) {
        String[] array = ids.split(",");
        for(String id:array){
            msgMapper.updateHaveRead(Integer.parseInt(id));
        }       
    }

    
    @Transactional
    public void deleteMessage(String ids) {
       String[] array = ids.split(",");
       for(String id:array){
           msgMapper.deleteMessage(Integer.parseInt(id));
       }
        
    }
    

}