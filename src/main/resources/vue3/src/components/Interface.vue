<script lang="ts" setup>
import {ref, reactive, watch} from "vue"
import {
  _Interface,
} from "../config/add.massage";
import {Page} from "../config/page";
import Operate from "./communal/Operate.vue"
import Data from "./communal/Data.vue"
import Pagination from "./communal/Pagination.vue"
import {request, DataTypes, Operate as RequestOperate} from "../config/request";
import type {ResponseDataType, Interface} from "../config/format.config";
import {requestType} from "../config/edit.request";
import {Classification} from "../config/format.config";
import {redio} from "../config/redio";

const requestPage = {
  current: 1,
  size: 10,
  selectValue: ""
}

/*
* 表格数据存储
* */
let rebocres = ref<Interface[]>([]);
/*
* 分页数据存储备份
* */
let seach = ref<Page<Interface>>({})
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
function pagination(page: Page<Interface>) {
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
function page(page: Page<Interface>) {
  let promise = requestType.get(RequestOperate.PAGE)!(DataTypes.Interface,page).then(response => {
    seach.value = {...response.data}
    let data = response.data.records
    format(data)
    rebocres.value = data
  })
}

function format(value:Interface[]){
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
    <Operate @selector="operate" :delete-ele="selectDeleteEle" :placeholders="_Interface.name" :data-type="DataTypes.Interface" @requestPage="page" :seach="seach1"/>
    <Data :data="rebocres"  :seach="seach1" @requestPage="page" :data-type="DataTypes.Interface" @selectHander="selectHander">
      <template #data>
        <el-table-column fixed prop="metaName" label="标题"/>
        <el-table-column prop="requestAddress" label="请求地址"/>
        <el-table-column prop="rolePojo.name" label="角色"/>
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