package com.yj.hqbz.services.order;

public interface OrderStatusConst {

    /**订单状态*/
    int BUYER_CANCEL = 0;                                  //买家取消
    int SELLER_CANCEL = 5;                                 //卖家取消
    int NOT_CONFIRM = 10;                                  //待买家确认
    int HAVE_CONFIRM = 20;                                 //待分拣
    int NOT_DELIVERY = 30;                                 //待发货
    int WAIT_RECEVIE = 40;                                 //已发货
    int CONFIRM_DIFF = 50;                                 //确认差异
    int HAVE_RECEIVE = 60;                                 //已收货
    
    /**差异状态*/    
    enum DIFF_STATUS{ 
        NO_DIFF,                                          //无差异
        HAVE_DIFF,                                        //有差异，包括拒绝
        WAIT_DELIVER_CONFIRM_DIFF,                        //等待配送员确认差异
        WAIT_SELLER_CONFIRM_DIF,                          //等待卖家确认差异
        DIFF_END                                          //差异确认结束，收货完成
    }
    /**付款状态*/
    enum PAY_STATUS{
        NOT_PAY,                                          //未付款
        HAVE_PAY,                                         //已付款
    }

}
