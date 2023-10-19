<script lang="ts" setup>
    import {ref, reactive, watch} from "vue"
    import {
        _Financing,
    } from "../config/add.massage";
    import {Page} from "../config/page";
    import Operate from "./communal/Operate.vue"
    import Data from "./communal/Data.vue"
    import Pagination from "./communal/Pagination.vue"
    import {request, DataTypes, Operate as RequestOperate} from "../config/request";
    import type {ResponseDataType, Account, Financing} from "../config/format.config";
    import {requestType} from "../config/edit.request";
    import {Drug} from "../config/format.config";
    import {redio} from "../config/redio";

    const requestPage = {
        current: 1,
        size: 10,
        selectValue: ""
    }

    /*
    * 表格数据存储
    * */
    let rebocres = ref<Financing[]>([]);
    /*
    * 分页数据存储备份
    * */
    let seach = ref<Page<Financing>>({})
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
    function pagination(page: Page<Financing>) {
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
    function page(page: Page<Financing>) {
        let promise = requestType.get(RequestOperate.PAGE)!(DataTypes.Financing,page).then(response => {
            seach.value = {...response.data}
            let data = response.data.records
            format(data)
            rebocres.value = data
        })
    }

    function format(value:Financing[]){
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
        <Operate @selector="operate" :delete-ele="selectDeleteEle" :placeholders="_Financing.name" :data-type="DataTypes.Financing" @requestPage="page" :seach="seach1"/>
        <Data :data="rebocres"  :seach="seach1" @requestPage="page" :data-type="DataTypes.Financing" @selectHander="selectHander">
            <template #data>
                <el-table-column fixed prop="ftype" label="财务类型"/>
                <el-table-column prop="name" label="花费说明"/>
                <el-table-column prop="money" label="金额"/>
                <el-table-column prop="state" label="是否失效"/>
                <el-table-column prop="description" label="说明"/>
                <el-table-column prop="comment" label="备注"/>
                <el-table-column prop="person" label="办理人"/>
                <el-table-column prop="departmentPojo.name" label="部门"/>
                <el-table-column prop="accountPojo.name" label="账号"/>
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
        height: calc(100vh - 109px) !important;
        background-color: white !important;
        margin: 0px !important;
    }
</style>