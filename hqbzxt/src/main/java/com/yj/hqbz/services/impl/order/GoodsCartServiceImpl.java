package com.yj.hqbz.services.impl.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yj.hqbz.mapper.goods.OrgSkuMapper;
import com.yj.hqbz.mapper.order.GoodsCartMapper;
import com.yj.hqbz.mapper.order.StoreCartMapper;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.order.GoodsCart;
import com.yj.hqbz.model.order.StoreCart;
import com.yj.hqbz.services.order.GoodsCartService;
import com.yj.hqbz.util.CommUtil;
import com.yj.hqbz.util.UUIDUtil;

@Service
public class GoodsCartServiceImpl implements GoodsCartService {

    @Resource
    GoodsCartMapper gcMapper;
    @Resource
    StoreCartMapper scMapper;
    @Resource
    OrgSkuMapper orgSkuMapper;
    
    
    @Transactional
    public String addGoodsCart(GoodsCart gc,String userid,Integer orgid) {
        String gcUUID = "";
        Map<String,Object> params = new HashMap<String,Object>();
        //检查当前购物车中是否存在该商品
        params.put("skuid", gc.getSkuid());
        params.put("userid", userid);
        GoodsCart newCart = gcMapper.checkGoodsCartExist(params);
        if(newCart!=null){  //购物车中已经存在,更新数量           
            gc.setCount(gc.getCount()+newCart.getCount());
            gc.setId(newCart.getId());
            gc.setVersion(newCart.getVersion());
            gcMapper.update(gc);
            gcUUID = newCart.getId();
        }else{
            StoreCart sc = scMapper.getStoreCartBySkuidAndUserid(params);
            if(sc!=null){   //存在StoreCart               
                gc.setScid(sc.getId());                           
            }else{
                StoreCart newSc = new StoreCart();
                String scid = UUIDUtil.getUUID();
                newSc.setId(scid);
                newSc.setOrgid(orgid);
                newSc.setUserid(userid);
                scMapper.addStoreCart(newSc);              
                gc.setScid(scid);
            }
            gcUUID = UUIDUtil.getUUID();
            gc.setId(gcUUID);
            gc.setUserid(userid);
            gcMapper.addGoodsCart(gc);   
        }
        return gcUUID;
    }

    
    @Transactional
    public void deleteGoodsCart(String id) {
        String[] ids = id.split(",");
        for(String s:ids){
            GoodsCart gc = gcMapper.selectByPrimaryKey(s);
            String scid = gc.getScid();
            gcMapper.deleteGoodsCart(s);
            int num = gcMapper.getLastGoodsCart(scid);
            if(num == 0){
                scMapper.deleteStoreCart(scid);
            }                    
        }

    }

    
    public GoodsCart getGoodsCartById(String id) {
        return gcMapper.selectByPrimaryKey(id);
    }

    
    @Transactional
    public void updateGoodsCartNum(GoodsCart gc) { 
        GoodsCart newGc = gcMapper.selectByPrimaryKey(gc.getId());
        gc.setVersion(newGc.getVersion());
        gcMapper.update(gc);        
    }

    
    public List<Map<String, Object>> getGoodsCartByUser(String userid) {
        List<Map<String,Object>> mapList = gcMapper.getGoodsCartListByUserid(userid);      
        return this.getGcDetailList(mapList);
    }

    
    public List<Map<String, Object>> getGoodsCartByUserAndGcid(String userid,
            String gcid) {
        List<String> gcList = Arrays.asList(gcid.split(","));
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userid", userid);
        params.put("gcList", gcList);      
        return this.getGcDetailList(gcMapper.getGoodsCartListByUseridAndGcid(params));
    }
    
    private List<Map<String,Object>> getGcDetailList(List<Map<String,Object>> mapList){
      //分组
        Map<String,List<Map<String,Object>>> groupMap = new HashMap<String,List<Map<String,Object>>>();
        for(Map<String,Object> map:mapList){
            String cartid = CommUtil.null2String(map.get("cartid"));
            if(groupMap.containsKey(cartid)){
                List<Map<String,Object>> oldList = groupMap.get(cartid);
                oldList.add(map);
            }else{
                List<Map<String,Object>> newList = new ArrayList<Map<String,Object>>();
                newList.add(map);
                groupMap.put(cartid, newList);
            }
        }
        //取数据
        List<Map<String, Object>> rstList = new ArrayList<Map<String,Object>>();
        Set<Entry<String,List<Map<String,Object>>>> entrySet = groupMap.entrySet();
        for(Entry<String,List<Map<String,Object>>> entry:entrySet){  
            List<Map<String,Object>> list = entry.getValue();
            Map<String,Object> cartMap = new HashMap<String,Object>();
            if(list!=null&&list.size()>0){
                Map<String,Object> temp = list.get(0);
                cartMap.put("cartid", temp.get("cartid"));
                cartMap.put("orgid", temp.get("orgid"));
                cartMap.put("orgName", temp.get("orgName"));
            }
            List<Map<String,Object>> cartList = new ArrayList<Map<String,Object>>();
            for(Map<String,Object> map:list){
                Map<String,Object> gcMap = new HashMap<String,Object>();
                gcMap.put("gcid", map.get("gcid"));
                gcMap.put("goodsid", map.get("goodsid"));
                gcMap.put("orgSkuid", map.get("orgSkuid"));
                gcMap.put("goodsName", map.get("goodsName"));
                gcMap.put("goodsSpec", map.get("goodsSpec"));
                gcMap.put("businessAttr", map.get("businessAttr"));
                gcMap.put("aliasName", map.get("aliasName"));
                gcMap.put("unit", map.get("unit"));
                gcMap.put("price", map.get("price"));
                gcMap.put("qty", map.get("qty"));
                gcMap.put("priceBasic", map.get("priceBasic"));
                gcMap.put("auixUnit", map.get("auixUnit"));
                gcMap.put("auixRate", map.get("auixRate"));
                gcMap.put("picUrl", map.get("picUrl"));
                gcMap.put("thumbnailUrl", map.get("thumbnailUrl"));
                gcMap.put("status", map.get("status"));
                gcMap.put("spec_info", map.get("spec_info"));
                gcMap.put("realGoodsid", map.get("realGoodsid"));
                cartList.add(gcMap);
            }
            cartMap.put("cartList", cartList);
            rstList.add(cartMap);
        }
        return rstList;
    }

    
    @Transactional
    public void updateGoodsCartSku(GoodsCart gc) {
        OrgSku sku = orgSkuMapper.selectByPrimaryKey(CommUtil.null2Int(gc.getSkuid()));
        gc.setPrice(sku.getPrice());
        GoodsCart newGc = gcMapper.selectByPrimaryKey(gc.getId());
        gc.setVersion(newGc.getVersion());
        gcMapper.update(gc);
        
    }

    
    

}