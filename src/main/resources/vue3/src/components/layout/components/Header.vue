<script lang="ts" setup>
    import {ref, defineExpose, watch} from "vue"
    import {fullScreen} from "../../../utils/driver";
    import {requestType} from "../../../config/edit.request";
    import {Operate} from "../../../config/request";
    import {router} from "../../../config/router.config";

    const active = ref<boolean>(false)
    defineExpose({
        active
    })
    const emit = defineEmits(
        ["header"]
    )
    watch(active, value => {
        emit("header", active.value)
    })

    function logout(){
        let user = JSON.parse(localStorage.getItem("user"));
        console.log(user)
        requestType.get(Operate.LOGIN)!(Operate.LOGOUT,{
            phone:user?.phone,
            password: user?.password,
            token:localStorage.getItem(import.meta.env.HIMS_REQUEST_HEADERS_TOKEN_NAME)
        }).then(response=>{
            if (response.code == 200) {
                localStorage.clear()
                router.push("/")
            }
        })
    }

</script>
<template>
    <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
    >
        <el-menu-item index="0">医院管理系统</el-menu-item>
        <div class="flex-grow"/>
        <el-menu-item index="1" style="padding-top:1px;">
            <el-icon @click="fullScreen">
                <FullScreen/>
            </el-icon>
            <span style="visibility: hidden;">1</span></el-menu-item>
        <el-dropdown>
    <span class="el-dropdown-link">
        <el-icon>       <UserFilled/></el-icon>
      <el-icon class="el-icon--right">
        <arrow-down/>
      </el-icon>
    </span>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item @click="active = !active">个人信息</el-dropdown-item>
                    <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </el-menu>


</template>
<style lang="scss" scoped>
    .layout-container-demo .toolbar {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        height: 100%;
        right: 20px;
        color: #E7F6F2;
    }


    .el-dropdown {
        color: #E7F6F2;
        display: flex;
        align-items: center;
        margin-right: 20px;

        &:hover {
            color: #A1CCD1 !important;
        }
    }

    .el-menu--horizontal {
        border: none;

        > .el-menu-item {
            color: white;

            &.is-active[data-v-10581e47] {
                color: white !important;
            }

            &:first-child {
                color: white !important;
                cursor: default;

                &:hover {
                    color: white !important;
                    cursor: default;
                }
            }

            &.is-active:first-child {
                color: white !important;
                cursor: default;
            }

            &:hover {
                color: #A1CCD1 !important;
            }

            &.is-active {
                border: none;
                color: #A1CCD1 !important;
            }

            &:not(.is-disabled):focus {
                background-color: transparent;
            }

            &:not(.is-disabled):hover {
                background-color: transparent;
            }
        }
    }

    .el-icon .el-sub-menu__icon-arrow {
        color: white !important;
    }

    .el-menu {
        background-color: #50577A;
    }

    .flex-grow {
        flex-grow: 1;
    }


</style>