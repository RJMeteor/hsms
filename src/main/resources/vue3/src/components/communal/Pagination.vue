<script lang="ts" setup>
    import {reactive, ref, defineEmits, defineProps, withDefaults} from "vue"
    import {
        Plus,
        Delete,
        Search,
        Download
    } from '@element-plus/icons-vue'
    import {Page, PageData} from "../../config/page"

    const pages = defineProps<{
        seach: Page<any>
    }>()
    const emit = defineEmits(["selector"])
    const usePage = ref<number>()
    usePage.value = pages.seach.current
    const useSize = ref<number | undefined>()
    useSize.value = pages.seach.size
    let pageExport: Page<any>;

    /*
    * 处理每页数据长度
    * */
    function handleSizeChange(size: number) {
        pageExport = {
            size: size,
            current: usePage.value,
        }
        emit("selector", pageExport)
    }

    /*
    * 处理第几页
    * */
    function handleCurrentChange(page: number) {
        pageExport = {
            size: useSize.value,
            current: page,
        }
        emit("selector", pageExport)
    }
</script>
<template>
    <div id="pagination">
        <el-pagination
                v-model:current-page="usePage"
                v-model:page-size="useSize"
                :page-sizes="[10, 30, 50, 70]"
                :small="false"
                :background="true"
                layout="sizes, prev, pager, next"
                :total="pages.seach.total ?? 10"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
        />
    </div>
</template>
<style lang="scss" scoped>
    #pagination {
        background-color: #fff;
        margin: var(--el-main-padding);
    }

</style>