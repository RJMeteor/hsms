<script lang="ts" setup>
import {ref, reactive, watch} from "vue"
import {
  _Drug,
} from "../config/add.massage";
import {Page} from "../config/page";
import Operate from "./communal/Operate.vue"
import Data from "./communal/Data.vue"
import Pagination from "./communal/Pagination.vue"
import {request, DataTypes, Operate as RequestOperate} from "../config/request";
import type {ResponseDataType, Drug} from "../config/format.config";
import {requestType} from "../config/edit.request";
import {Diagnosis} from "../config/format.config";
import {redio} from "../config/redio";

const requestPage = {
  current: 1,
  size: 10,
  selectValue: ""
}

/*
* 表格数据存储
* */
let rebocres = ref<Drug[]>([]);
/*
* 分页数据存储备份
* */
let seach = ref<Page<Drug>>({})
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
function pagination(page: Page<Drug>) {
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
function page(page: Page<Drug>) {
  let promise = requestType.get(RequestOperate.PAGE)!(DataTypes.Drug,page).then(response => {
    seach.value = {...response.data}
    let data = response.data.records
    format(data)
    rebocres.value = data
  })
}

function format(value:Drug[]){
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
    <Operate @selector="operate" :delete-ele="selectDeleteEle" :placeholders="_Drug.name" :data-type="DataTypes.Drug" @requestPage="page" :seach="seach1"/>
    <Data :data="rebocres"  :seach="seach1" @requestPage="page" :data-type="DataTypes.Drug" @selectHander="selectHander">
      <template #data>
        <el-table-column fixed prop="name" label="药品管理"/>
        <el-table-column prop="barCode" label="条形码"/>
        <el-table-column prop="model" label="型号"/>
        <el-table-column prop="unit" label="单位"/>
        <el-table-column prop="quantity" label="数量"/>
        <el-table-column prop="label" label="标签"/>
        <el-table-column prop="customerPrice" label="客户单价"/>
        <el-table-column prop="costPrice" label="成本价"/>
        <el-table-column prop="special" label="特价"/>
        <el-table-column prop="whether" label="推销"/>
        <el-table-column prop="classificationPojo.name" label="药品分类"/>
        <el-table-column prop="vendorPojo.name" label="供应商"/>
      </template>
    </Data>
    <Pagination :seach="seach" @selector="pagination"/>
  </el-scrollbar>
</template>
<style lang="scss" scoped>
#account {
  height: 100vh;
  overflow-y: scroll;
}
.el-scrollbar {
  height: calc(100vh - 150px) !important;
  background-color: white !important;
  margin: 0px !important;
}
</style>