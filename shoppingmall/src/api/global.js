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
  return axios.post('apis/shop/goods/insertOrUpdateGoods',qs.stringify(params),{headers:{'Content-Type':'application/json'}});
}
// 修改商品
export const updateGoods = params =>{
  return axios.post('apis/shop/goods/insertGoods',qs.stringify(params),{headers:{'Content-Type':'application/json'}});
}
/****商品分类******/
export const insertOrUpdateGoodsTypeById = params =>{
  return axios.post('apis/shop/goodsType/insertOrUpdateGoodsTypeById',qs.stringify(params));
}
export const deleteGoodTypeById = params =>{
  return axios.post('apis/shop/goodsType/deleteGoodTypeById',qs.stringify(params));
}
// 商品分类（分页）
export const getGoodsTypeList = params =>{
  return axios.post('apis/shop/goodsType/getGoodsTypeList',qs.stringify(params));
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
