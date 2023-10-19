<script lang="ts" setup>
import {ref, reactive, watch} from "vue"
import {
  _User,
} from "../config/add.massage";
import {Page} from "../config/page";
import Operate from "./communal/Operate.vue"
import Data from "./communal/Data.vue"
import Pagination from "./communal/Pagination.vue"
import {request, DataTypes, Operate as RequestOperate} from "../config/request";
import type {ResponseDataType, Vendor, User} from "../config/format.config";
import {requestType} from "../config/edit.request";
import {Patient} from "../config/format.config";
import {redio} from "../config/redio";

const requestPage = {
  current: 1,
  size: 10,
  selectValue: ""
}

/*
* 表格数据存储
* */
let rebocres = ref<User[]>([]);
/*
* 分页数据存储备份
* */
let seach = ref<Page<User>>({})
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
function pagination(page: Page<User>) {
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
function page(page: Page<User>) {
  let promise = requestType.get(RequestOperate.PAGE)!(DataTypes.User,page).then(response => {
    seach.value = {...response.data}
    let data = response.data.records
    format(data)
    rebocres.value = data
  })
}

function format(value:User[]){
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
    <Operate @selector="operate" :delete-ele="selectDeleteEle" :placeholders="_User.name" :data-type="DataTypes.User" @requestPage="page" :seach="seach1"/>
    <Data :data="rebocres"  :seach="seach1" @requestPage="page" :data-type="DataTypes.User" @selectHander="selectHander">
      <template #data>
        <el-table-column fixed prop="name" label="用户名"/>
        <el-table-column prop="rreal" label="真实名字"/>
        <el-table-column prop="phone" label="电话"/>
        <el-table-column prop="password" label="密码"/>
        <el-table-column prop="email" label="邮箱"/>
        <el-table-column prop="homeAddress" label="家庭地址"/>
        <el-table-column prop="educationBackground" label="学历"/>
        <el-table-column prop="school" label="毕业院校"/>
        <el-table-column prop="identityCard" label="身份证号"/>
        <el-table-column prop="bankAccount" label="银行账号"/>
        <el-table-column prop="sex" label="性别"/>
        <el-table-column prop="salary" label="工资"/>
        <el-table-column prop="departmentPojo.name" label="部门"/>
        <el-table-column prop="rolePojo.name" label="角色"/>
        <el-table-column prop="jobPojo.name" label="职位"/>
        <el-table-column prop="entryTime" label="入职时间"/>
        <el-table-column prop="birthdayTime" label="生日时间"/>
        <el-table-column prop="createTime" label="登记时间"/>

      </template>
    </Data>
    <Pagination :seach="seach" @selector="pagination"/>
  </el-scrollbar>
</template>
<style lang="scss" scoped>
#user {
    height: 100vh;
    overflow-y: scroll;
}
.el-scrollbar {
    height: calc(100vh - 109px) !important;
    background-color: white !important;
    margin: 0px !important;
}
</style>