<script lang="ts" setup>
    import {useRouter} from "vue-router";
    const router = useRouter()
    defineProps({
        title:String
    })
    function goBack() {
        router.back()
    }
</script>
<template>
    <div id="back">
    <el-page-header @back="goBack">
        <template #content>
            <span class="text-large font-600 mr-3"> {{title}} </span>
        </template>
    </el-page-header>
    </div>
</template>
<style lang="scss" scoped>
#back{
    padding-bottom: var(--el-main-padding);
}
</style>