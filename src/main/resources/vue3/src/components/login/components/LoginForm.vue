<script setup lang="ts">
    import {reactive,onMounted} from "vue"
    import type {Login} from "../../../config/format.config";
    import {DataTypes, Operate} from "../../../config/request";
    import {requestType} from "../../../config/edit.request";
    import {useRouter} from "vue-router"
    import {ElMessage} from 'element-plus'

    const router = useRouter()
    const loginData = reactive<Login>({
        phone:"",
        password:"",
        token:""
    })
    /*
    * login请求
    * */
    function login() {
       const promise =  requestType.get(Operate.LOGIN)!(Operate.LOGIN, loginData);
       promise.then(responese=>{
           if (responese?.code == 200) {
               let singon = new Set();
               if (!responese.data.interfaceo) {
                   ElMessage.error("你没有授权的模块")
                   localStorage.clear()
                   router.push({path:"/"})
               }
               for(let ele of responese.data.interfaceo){
                   singon.add(ele.pathName)
               }
               localStorage.setItem("info",JSON.stringify(Array.from(singon)))
               localStorage.setItem("interface",JSON.stringify(responese.data.interfaceo))
               localStorage.setItem(import.meta.env.HIMS_REQUEST_HEADERS_TOKEN_NAME,responese.data.token)
               localStorage.setItem("user",JSON.stringify(responese.data))
               router.push("/index")
           }
       })
    }

    login()

</script>
<template>
    <el-form>
        <el-form-item label="" prop="username">
            <el-input
                    v-model="loginData.phone"
                    placeholder="请输入电话号码"
                    autoComplete="on"
                    style="position: relative">
                <template #prefix>
                    <el-icon class="el-input__icon">
                        <UserFilled/>
                    </el-icon>
                </template>
            </el-input>
        </el-form-item>

        <el-form-item label="" prop="password">
            <el-input
                    v-model="loginData.password"
                    placeholder="请输入密码"
                    autoComplete="on"
            >
                <template #prefix>
                    <el-icon class="el-input__icon">
                        <GoodsFilled/>
                    </el-icon>
                </template>
            </el-input>
        </el-form-item>

        <el-form-item style="width: 100%">
            <el-button
                    @click="login"
                    class="login-btn"
                    type="success"
            >登录
            </el-button
            >
        </el-form-item>

    </el-form>
</template>


<style lang="scss" scoped>
    .login-btn {
        margin-top: 20px;
        width: 100%;
        height: 47px
    }

    .show-pwd {
        position: absolute;
        right: 10px;
        top: 7px;
        font-size: 16px;
        cursor: pointer;
        user-select: none;
    }

    ::v-deep(.svg-icon) {
        vertical-align: 0;
    }
</style>
