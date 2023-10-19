<script lang="ts" setup>
    import AAEApply from "../communal/AAEApply.vue"
    import {Operate} from '../../config/request';

    import {DataTypes} from '../../config/request';
    import {reactive, ref, defineExpose, watch, defineEmits} from "vue"

    const active = ref<boolean>(false)

    defineExpose({
        active
    })
    const emit = defineEmits(["drawer"])

    watch(active, value => {
        emit("drawer", active.value)
    })
    let userData = ref<number>(JSON.parse(localStorage.getItem('user'))?.id)

    function callback() {
        localStorage.clear()
        active.value = false
    }
</script>
<template>
    <el-drawer v-model="active" :show-close="false">
        <template #header="{ close, titleId, titleClass }">
            <h4 :id="titleId" :class="titleClass">个人信息</h4>
            <el-button type="danger" @click="close">
                <el-icon class="el-icon--left">
                    <CircleCloseFilled/>
                </el-icon>
                Close
            </el-button>
        </template>
        <AAEApply :edit-type="Operate.EDIT" :data-type="DataTypes.User" :id="userData" :callback="callback"/>
    </el-drawer>
</template>
<style lang="scss" scoped>
</style>