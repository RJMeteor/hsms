<script setup lang="ts">
    import {defineProps,withDefaults} from "vue"
    import Back from "@/components/communal/Back.vue"
    import AEEApply from "@/components/communal/AAEApply.vue"
    import {useRouter} from "vue-router";
    import type {RouterParamsType} from "../../config/format.config";
    import {Operate} from "../../config/request";
    const router = useRouter()

    const props = defineProps<RouterParamsType>()

    function callback() {
    router.back()
    }
</script>
<template>
    <div id="add">
        <Back  :title="props.editType == Operate.ADD ? '添加':'修改'"/>
        <AEEApply :edit-type="props.editType" :data-type="props.dataType" :id="props.id" :callback="callback"/>
    </div>
</template>
<style lang="scss" scoped>
    #add {
        margin: var(--el-main-padding);
    }
    .scrollbar-view{
        height: calc(100vh - 190px) !important;
        overflow-y: auto;
        overflow-x: hidden;
    }
    .scrollbar-view::-webkit-scrollbar {
        width: 0px;
    }

    .el-form {
        margin-top: var(--el-main-padding);
    }

    .el-button {
        margin: var(--el-main-padding);
        margin-buttom:var(--el-main-padding);
        float: right;
    }
</style>