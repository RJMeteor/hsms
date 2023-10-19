<script lang="ts" setup>
    import {
        Plus,
        Delete,
        Search,
        Download
    } from '@element-plus/icons-vue'
    import {defineEmits, ref, watch} from "vue";
    import {useRouter} from "vue-router";
    import { Operate } from '../../config/request';
    import {Page} from "../../config/page";
    import {requestType} from "../../config/edit.request";
    import type { ResponseDataType } from '../../config/format.config';

    const emit = defineEmits(["selector","requestPage"])
    const props = defineProps<{
        dataType:string
        deleteEle:number[],
        seach:Page<any>,
        placeholders:string
    }>()
    const selectValue = ref<string>('')

    watch(selectValue, value => {
        emit("selector", value)
    })

    const router = useRouter()
    /*
    * 添加
    * */
    function addInformation(operate:string) {
        router.push({
            path: `/index/AAE/${props.dataType}/${operate}/1`,
        })
    }

    /*
    * 批量删除
    * */
    function confirmDelete() {
        if (props.deleteEle.length) {
            requestType.get(Operate.BHDE)!(props.dataType,props.deleteEle).then((response:ResponseDataType<any>)=>{
                if (response.data) {
                    emit("requestPage",props.seach)
                }
            });
        }
    }

</script>
<template>
    <div id="operate">
        <div>
            <el-tooltip
                    class="box-item"
                    effect="dark"
                    content="添加"
                    placement="bottom"
            >
                <el-button type="success" :icon="Plus" @click="addInformation(Operate.ADD)" circle/>
            </el-tooltip>
            <el-tooltip
                    class="box-item"
                    effect="dark"
                    content="批量删除"
                    placement="bottom"
            >
                <el-popconfirm
                        confirm-button-text="Yes"
                        :icon="InfoFilled"
                        icon-color="#626AEF"
                        title="是否确认批量删除该些数据？"
                        @confirm="confirmDelete"
                >
                    <template #reference>
                        <el-button type="danger"  :icon="Delete" circle/>
                    </template>
                </el-popconfirm>
            </el-tooltip>
<!--            <el-tooltip-->
<!--                    class="box-item"-->
<!--                    effect="dark"-->
<!--                    content="excel导出"-->
<!--                    placement="bottom"-->
<!--            >-->
<!--                <el-button type="danger" :icon="Download" circle/>-->
<!--            </el-tooltip>-->
        </div>
        <div>
            <el-input
                    v-model="selectValue"
                    class="w-50 m-2"
                    :placeholder="placeholders+'模糊匹配'"
                    :suffix-icon="Search"
            />
        </div>

    </div>
</template>
<style lang="scss" scoped>
    #operate {
        height: 50px;
        background-color: #fff;
        display: flex;
        align-items: center;
        margin: var(--el-main-padding);
        margin-bottom: 0px;
        padding: 0 10px;
        display: flex;
        justify-content: space-between;
    }

</style>