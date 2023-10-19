<script setup lang="ts">

</script>
<template>
    <RouterView/>
</template>

<style lang="scss">
    * {
        margin: 0;
        padding: 0;
    }

    body, html {
        width: 100vw;
        height: 100vh;
        box-sizing: border-box;
        overflow:hidden;
    }
    #nprogress .bar{
        background-color: #89BEB3 !important;
        height:2px !important;
    }
</style>
