<script setup lang="ts">
    import {ref, onMounted, reactive} from "vue"
    import Header from "./components/Header.vue"
    import Aside from "./components/Aside.vue"
    import Main from "./components/Main.vue"
    import Drawer from "./Drawer.vue"
    import {requestType} from "../../config/edit.request";
    import {Operate} from "../../config/request";
    import {Login} from "../../config/format.config";

    const drawer = ref(null)
    const header = ref(null)
    const aside = ref(null)

    function activeDrawer(active: number) {
        drawer.value.active = active;
    }

    function activeHeader(active: number) {
        header.value.active = active;
    }


    const loginData = reactive<Login>({
        phone:"",
        password:"",
        token:""
    })
    onMounted(()=>{
        if (!localStorage.getItem("user"))
        requestType.get(Operate.LOGIN)!(Operate.LOGIN, loginData);
    })
</script>
<template>
    <el-container class="layout-container-demo">
        <el-header style="text-align: right; font-size: 12px">
            <Header ref="header" @header="activeDrawer"/>
        </el-header>
        <el-container>
            <Aside ref="aside"/>
            <Main/>
        </el-container>
        <Drawer ref="drawer" @drawer="activeHeader"/>
    </el-container>
</template>
<style lang="scss" scoped>
    .el-container {
        &.layout-container-demo {
            width: 100vw;
            hight: 100vh !important;

            .el-header {
                position: relative;
                background-color: #50577A;
                padding: 0;
            }
        }
    }
</style>

