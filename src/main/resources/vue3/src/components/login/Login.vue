<script setup lang="ts">
    import LoginForm from './components/LoginForm.vue'
</script>

<template>
    <div class="login-container">
        <div class="login-box">
            <div class="login-left">
                <img src="@/assets/login/side-logo.png">
            </div>
            <div class="login-form">
                <div class="login-title">
                    <h2 class="title">医院管理系统</h2>
                </div>
                <LoginForm/>
            </div>
        </div>
    </div>
</template>


<style lang="scss" scoped>
    .login-container {
        background-color: #f0f2f5;
        height: 100%;
        width: 100%;
        overflow: hidden;
        display: flex;
        background-image: url("../../assets/login/login_bg.svg");
        justify-content: center;
        align-items: center;
        padding: 25px 25px;
        box-sizing: border-box;
    }

    .login-box {
        position: relative;
        width: 100%;
        height: 100%;
        background-color: #fffc;
        border-radius: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .login-left {
        width: 50%;

        img {
            width: 100%;
            max-width: 900px;
        }
    }

    .login-form {
        max-width: 480px;
        width: 50%;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
        box-sizing: border-box;
    }

    ::v-deep(.el-input__inner) {
        height: 40px;
    }

    .login-title {
        display: flex;
        align-items: center;
        justify-content: space-around;
        margin-bottom: 30px;

        .title {
            margin: 0;
            font-size: 30px;
            white-space: nowrap;
        }

        .icon {
            width: 60px;
        }
    }


</style>
