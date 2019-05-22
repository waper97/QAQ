import axios from 'axios'
import qs from 'qs'
// axios.defaults.baseURL= 'http://localhost:8080'
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
export const insertGoods = params =>{
  return axios.post('apis/shop/goods/insertGoods',qs.stringify(params));
}
// 修改商品
export const updateGoods = params =>{
  return axios.post('apis/shop/goods/insertGoods',qs.stringify(params));
}
/****商品分类******/
export const insertGoodsTypeById = params =>{
  return axios.post('apis/shop/goodsType/insertGoodsTypeById',qs.stringify(params));
}
export const updateGoodsTypeById = params =>{
  return axios.post('apis/shop/goodsType/updateGoodsTypeById',qs.stringify(params));
}
export const deleteGoodTypeById = params =>{
  return axios.post('apis/shop/goodsType/deleteGoodTypeById',qs.stringify(params));
}

export const getGoodsTypeList = params =>{
  return axios.post('apis/shop/goodsType/getGoodsTypeList',qs.stringify(params));
}
