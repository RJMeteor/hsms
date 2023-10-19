<script lang="ts" setup>
import {ref, reactive, watch} from "vue"
import {
  _Vendor,
} from "../config/add.massage";
import {Page} from "../config/page";
import Operate from "./communal/Operate.vue"
import Data from "./communal/Data.vue"
import Pagination from "./communal/Pagination.vue"
import {request, DataTypes, Operate as RequestOperate} from "../config/request";
import type {ResponseDataType, Vendor, User} from "../config/format.config";
import {requestType} from "../config/edit.request";
import {redio} from "../config/redio";

const requestPage = {
  current: 1,
  size: 10,
  selectValue: ""
}

/*
* 表格数据存储
* */
let rebocres = ref<Vendor[]>([]);
/*
* 分页数据存储备份
* */
let seach = ref<Page<Vendor>>({})
/*
* 分页数据
* */
let seach1 = ref<{
  current?: number,
  size?: number,
  selectValue?: string
}>({
  current: seach.value.current ?? requestPage.current,
  size: seach.value.size ?? requestPage.size,
  selectValue: seach.value.selectValue ?? requestPage.selectValue
})

/*
* 分页数据传输EMIT
* */
function pagination(page: Page<Vendor>) {
  seach1.value = {
    selectValue: seach1.value.selectValue,
    current: page.current ? page.current : requestPage.current,
    size: page.size ? page.size : requestPage.size
  }
}

/*
* 模糊搜索数据传输EMIT
* */
function operate(selectValue: string) {
  seach1.value = {selectValue: selectValue, current: seach1.value.current, size: seach1.value.size}
}

/*
* page请求
* */
function page(page: Page<Vendor>) {
  let promise = requestType.get(RequestOperate.PAGE)!(DataTypes.Vendor,page).then(response => {
    seach.value = {...response.data}
    let data = response.data.records
    format(data)
    rebocres.value = data
  })
}

function format(value:Vendor[]){
  value.forEach(ele=>{
    for (let eles in ele ){
      if (redio[eles]){
        ele[eles] = redio[eles][ele[eles]]
      }
    }
  })
}

page(requestPage)

watch(seach1, value => {
  page(value)
})

/*
* 处理全选
* */
const selectDeleteEle = ref<number[]>([])
function selectHander(value: number[]) {
  selectDeleteEle.value = value
}
</script>
<template>
  <el-scrollbar>
    <Operate @selector="operate" :delete-ele="selectDeleteEle" :placeholders="_Vendor.name" :data-type="DataTypes.Vendor" @requestPage="page" :seach="seach1"/>
    <Data :data="rebocres"  :seach="seach1" @requestPage="page" :data-type="DataTypes.Vendor" @selectHander="selectHander">
      <template #data>
        <el-table-column fixed prop="name" label="供应商名"/>
        <el-table-column prop="vendorLevel" label="供应商级别"/>
        <el-table-column prop="vendorSource" label="供应商来源"/>
        <el-table-column prop="phone" label="电话号码"/>
        <el-table-column prop="weixin" label="微信"/>
        <el-table-column prop="postcode" label="邮编"/>
        <el-table-column prop="address" label="地址"/>
        <el-table-column prop="businessLicense" label="营业执照"/>
        <el-table-column prop="createTime" label="登记时间"/>
      </template>
    </Data>
    <Pagination :seach="seach" @selector="pagination"/>
  </el-scrollbar>
</template>
<style lang="scss" scoped>
    #vendor {
  height: 100vh;
  overflow-y: scroll;
}
  .el-scrollbar {
       height: calc(100vh - 109px) !important;
      background-color: white !important;
      margin: 0px !important;
}
</style>