import axios from 'axios'
import qs from 'qs'



export const login = params =>{
  return axios.post('apis/shop/user/login',qs.stringify(params));
}
// 注册
export const register = params =>{
  return axios.post('apis/shop/user/userRegister',qs.stringify(params));
}
/****商品******/
// 商品列表
export const goodList = params =>{
  return axios.post('apis/shop/goods/goodList',qs.stringify(params));
}// 删除商品
export const deleteGoodsById = params =>{
  return axios.post('apis/shop/goods/deleteGoodsById',qs.stringify(params));
}
// 添加商品
export const insertOrUpdateGoods = params =>{
  return axios.post('apis/shop/goods/insertOrUpdateGoods',qs.stringify(params));
  // return axios.post('apis/shop/goods/insertOrUpdateGoods',qs.stringify(params),{headers:{'Content-Type':'application/json'}});
}
// 修改商品
export const updateGoods = params =>{
  return axios.post('apis/shop/goods/insertGoods',qs.stringify(params),{headers:{'Content-Type':'application/json'}});
}
/****商品分类******/
// 添加商品类型
export const insertGoodsType = params =>{
  return axios.post('apis/shop/goodsType/insert',qs.stringify(params));
}
export const updateGoodsType = params =>{
  return axios.post('apis/shop/goodsType/update',qs.stringify(params));
}
export const deleteGoodType = params =>{
  return axios.post('apis/shop/goodsType/deleteGoodType',qs.stringify(params));
}
// 商品分类（分页）
export const getGoodsTypeList = params =>{
  return axios.get('apis/shop/goodsType/getGoodsTypeList',qs.stringify(params));
}
// 商品分类
export const goodsTypeList = params =>{
  return axios.post('apis/shop/goodsType/goodsTypeList',qs.stringify(params));
}
/***用户**/
export const getUserList = params =>{
  return axios.post('apis/shop/user/getUserList',qs.stringify(params));
}
/***用户**/
export const getSession = params =>{
  return axios.post('apis/shop/user/getSession',qs.stringify(params));
}
export const upload = params =>{
  return axios.post('apis/shop/file/uploadFile',qs.stringify(params));
}

export const upload1 = params =>{
  return axios.post('apis/shop/file/uploadFile',
    {
          headers:{
            'Content-Type':'multipart/form-data'
          },
        },params
  );
}
