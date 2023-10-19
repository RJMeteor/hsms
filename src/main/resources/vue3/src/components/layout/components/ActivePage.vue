<script lang="ts" setup>
    import {useRouter} from "vue-router";
    import {reactive, defineEmits, watch} from "vue";
    import {activePage} from "../../../config/router.config";

    const router = useRouter()

    function closeActive(index: number) {
        activePage.splice(index, 1)
        router.push({
                path: activePage.length <= 0 ? "/index" : activePage[index - 1]?.path
            }
        )
    }

    function closeActiveAll() {
        activePage.length = 0;
        router.push("/index")
    }
</script>
<template>
    <div id="active_page">
        <el-tag
                size="large"
                class="mx-1"
                color="#222831"
                type="warning"
                @click="closeActiveAll"
        >
            全部关闭
        </el-tag>
        <el-tag
                v-for="(ele,index) in activePage"
                :key="index"
                size="large"
                :color="ele.meta.color"
                class="mx-1"
                closable
                @close="closeActive(index)"
                type="info"
        >
            <RouterLink :to="ele.path" style="color:white;text-decoration: none;">
                {{ele.meta.name}}
            </RouterLink>
        </el-tag>
    </div>
</template>
<style lang="scss" scoped>
    #active_page {
        height: 50px;
        background-color: #fff;
        display: flex;
        align-items: center;
        border: calc(var(--el-main-padding) / 2) solid #EFEFEF;
        border-top: none;
        border-left: none;
        border-right: none;
    }

    .el-tag {
        margin: 0 10px;
    }

    ::v-deep(.el-tag__content) {
        color: white;
    }
</style>