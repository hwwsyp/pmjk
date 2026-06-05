import request from '../../request'
import requestUrl from '../../requestUrl'
import requestParam from '../../requestParam'
import isInteger from 'lodash/isInteger'
import axios from 'axios'

/**
 *  1、请将该文件拷贝到vue项目下   src/api/modules下
 *  2、在api文件夹下的index.js文件中添加：
 *    2.1、import * as ${function} from './modules/${prefix}/${function}'
 *    2.2、在export default中追加   该模块       ,${function} //${tableComment}
 *  3、在router文件夹下的index.js文件中添加
 *     ,{ path: '/${function}',  component: _import('${prefix}/${function}/index'), name: '${function}', desc: '${tableComment}', meta: { isTab: true } }
 */

//上传文件
export function upload (params){
  return axios.post(requestUrl('/${prefix}/${function}/upload'), params,
    {headers: {'Content-Type': 'multipart/form-data'}});
}

// 获取列表-分页数据
export function list (params) {
  return request({
    url: requestUrl('/${prefix}/${function}/list'),
    method: 'get',
    params: requestParam(params, 'get')
  })
}

// 获取列表-全部数据
// 一般基础数据表会用到
export function get${entityName}List (params) {
  return request({
    url: requestUrl('/${prefix}/${function}/get${entityName}List'),
    method: 'get',
    params: requestParam(params, 'get')
  })
}

// 获取详情信息
export function info (id) {
  return request({
    url: requestUrl('/${prefix}/${function}/info' + (isInteger(id) ? `/${r'${'}id${r'}'}` : '')),
    method: 'get',
    params: requestParam({}, 'get')
  })
}

// 新增
export function save (params) {
  return request({
    url: requestUrl('/${prefix}/${function}/save'),
    method: 'post',
    data: requestParam(params,"post",false)
  })
}

// 修改
export function update (params) {
  return request({
    url: requestUrl('/${prefix}/${function}/update'),
    method: 'post',
    data: requestParam(params,"post",false)
  })
}

// 删除
export function del (params) {
  return request({
    url: requestUrl('/${prefix}/${function}/delete'),
    method: 'post',
    data: requestParam(params, 'post', false)
  })
}
