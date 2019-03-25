package com.yj.hqbz.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.hqbz.model.goods.OrgSku;
import com.yj.hqbz.model.order.GoodsCart;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.goods.OrgSkuService;
import com.yj.hqbz.services.order.GoodsCartService;
import com.yj.hqbz.services.order.OrderFormService;
import com.yj.hqbz.util.CommUtil;

@RestController
public class GoodsCartController extends BaseController{

    @Resource
    GoodsCartService gcService;
    @Resource
    OrgSkuService orgSkuService;
    @Resource
    OrderFormService orderService;
    
    
    /**
     * 添加购物车
     * @param gc 
     * @return
     */
    @PostMapping("/order/goodsCart/addGoodCarts")
    public Object addGoodsCarts(GoodsCart gc){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(gc.getSkuid())||CommUtil.null2Int(gc.getCount())<=0){
            return fail("参数错误！");
        }
        OrgSku sku = orgSkuService.selectByPrimaryKey(CommUtil.null2Int(gc.getSkuid()));
        if(sku == null||sku.getStatus()!= 0){
            return fail("无效商品！");
        }
        gc.setPrice(sku.getPrice());
        return new BaseRes("添加成功",gcService.addGoodsCart(gc, user.getUserid(), sku.getOrgid()));
    }
    
    /**
     * 更新购物车数量或者销售规格
     * @param gc
     * @return
     */
     
    @PostMapping("/order/goodsCart/updateGoodsCart")
    public Object updateQty(GoodsCart gc){
        if (StringUtils.isBlank(gc.getId())) {
            return fail("参数错误！");
        }
        GoodsCart newGc = gcService.getGoodsCartById(gc.getId());
        if (newGc == null) {
            return fail("无效购物车ID！");
        }
        if ((StringUtils.isNotBlank(gc.getSkuid()) && gc.getCount() != null)
                || (StringUtils.isBlank(gc.getSkuid()) && gc.getCount() == null)) {
            return fail("同时有值或同时无值！");
        }
        if (gc.getCount() != null && gc.getCount() <= 0) {
            return fail("无效数量！");
        }
        
        if (gc.getCount() != null && gc.getCount() > 0) {
            gcService.updateGoodsCartNum(gc);
        } else{
            gcService.updateGoodsCartSku(gc);
        }        
        return success("更新购物车成功！"); 
    }
    /**
     * 删除购物车
     * @param gcid
     * @return
     */
    @PostMapping("/order/goodsCart/delete")
    public Object delete(String gcid){
        if(StringUtils.isBlank(gcid)){
            return fail("参数错误！");
        }
        gcService.deleteGoodsCart(gcid);
        return success("删除成功！");
    }
    /**
     * 购物车列表
     * @return
     */
    @GetMapping("/order/goodsCart/getList")
    public Object getCartList(){
        UserInfo user = getTokenUser();
        return success(gcService.getGoodsCartByUser(user.getUserid()));
    }
    /**
     * 根据购物车ID获取购物车详情
     * @param gcid
     * @return
     */
    @GetMapping("/order/goodsCart/getCartGoodsListByGcid")
    public Object getCartListByGc(String gcid){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(gcid)){
            return fail("参数错误！");
        }
        return success(gcService.getGoodsCartByUserAndGcid(user.getUserid(), gcid));
    }
    /**
     * 创建订单
     * @param addressid  地址ID
     * @param isBuyNow   是否直接购买
     * @param cartInfo   购物车信息
     * @return
     */
    @PostMapping("/order/goodsCart/createOrder")
    public Object createOrder(String addressid,String isBuyNow,String cartInfo){
        UserInfo user = getTokenUser();
        if (StringUtils.isBlank(addressid) || StringUtils.isBlank(cartInfo)
                || StringUtils.isBlank(isBuyNow)
                || (!"0".equals(isBuyNow) && !"1".equals(isBuyNow))) {
            return fail("参数错误！");
        }

        List<Map> cartList = JSONArray.parseArray(cartInfo, Map.class);
        if (cartList.size() <= 0) {
            return fail("参数错误！");
        }
        List<String> orderNoList = null;
        // orderService.getOrderData(user.getUserid(),
        // "a4e8cbfb327546af8bd096ba5ddd4bc9,1619c4215bd746bfb2812d4003bf3239,eeb3285ec8af4e308e4658afe408205a",
        // 5)
        // return new BaseRes("创建订单成功！",
        // orderService.getOrderData(user.getUserid(),
        // "a4e8cbfb327546af8bd096ba5ddd4bc9,1619c4215bd746bfb2812d4003bf3239,eeb3285ec8af4e308e4658afe408205a",
        // 5));
        if("0".equals(isBuyNow)){
            orderNoList = orderService.createOrder(user, addressid,cartList);
        }else{
            orderNoList = orderService.createBuyNowOrder(user, addressid, cartList);
        }
        return new BaseRes("创建订单成功！",orderNoList);
    }
}
