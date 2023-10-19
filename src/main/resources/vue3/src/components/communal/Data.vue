<script lang="ts" setup>
    import {
        Delete,
        Edit,
    } from '@element-plus/icons-vue'
    import {ref, defineProps} from "vue"
    import {useRouter} from "vue-router";
    import {Operate, request} from "../../config/request";
    import {requestType} from "../../config/edit.request";
    import {ElMessage} from "element-plus";
    import type {ResponseDataType} from "../../config/format.config";

    const props = defineProps(["data", "dataType", "seach"])
    const emit = defineEmits(["selectHander", "requestPage"])
    const router = useRouter()

    function confirmDelete(id: number) {
        requestType.get(Operate.DELETE)!(props.dataType, id).then((result:ResponseDataType<any>) => {
            if (result.data) {
                emit("requestPage",props.seach)
            }
        })
    }

    /*
    * 编辑数据
    * */
    function editInformation(id: number) {
        router.push({
            path: `/index/AAE/${props.dataType}/${Operate.EDIT}/${id}`
        })
    }

    /*
    * 处理全选
    * */
    function handleSelectionChange(value: any[]) {
        value = value.map(ele => {
            return ele.id
        }, [])
        emit("selectHander", value)
    }
</script>
<template>
    <div id="data">
        <el-table :data="props.data" max-height="500" style="width: 100%;" @selection-change="handleSelectionChange">
            <el-table-column type="selection"/>
            <slot name="data"></slot>
            <el-table-column fixed="right" width="120" label="Operations">
                <template #default="props">
                    <el-tooltip
                            class="box-item"
                            effect="dark"
                            content="修改"
                            placement="bottom"
                    >
                        <el-button type="primary" @click="editInformation(props.row.id)" :icon="Edit" circle/>
                    </el-tooltip>
                    <el-tooltip
                            class="box-item"
                            effect="dark"
                            content="删除"
                            placement="bottom"
                    >
                        <el-popconfirm
                                confirm-button-text="Yes"
                                :icon="InfoFilled"
                                icon-color="#626AEF"
                                title="是否确认删除该数据？"
                                @confirm="confirmDelete(props.row.id)"
                        >
                            <template #reference>
                                <el-button type="danger" :icon="Delete" circle/>
                            </template>
                        </el-popconfirm>
                    </el-tooltip>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<style lang="scss" scoped>
    #data {
        width: calc(100vw - 200px) !important;
    }
</style>