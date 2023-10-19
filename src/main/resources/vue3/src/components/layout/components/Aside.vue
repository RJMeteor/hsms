<script lang="ts" setup>
    import {reactive, ref} from "vue"
    // import {manage} from "../../../config/router.config";
    import {RouteRecordRaw, useRouter} from "vue-router";
    const broadcastChannelAsActiveMenu = new BroadcastChannel("activeMenu")
    let sort: Array<Array<RouteRecordRaw>> = reactive([]);
    const menu = ref()
    let ar:Array<any> = []
    let primary = new Set()
    let sorts = new Set()
    for (let ele of JSON.parse(localStorage.getItem("interface"))) {
        primary.add(ele.name)
        sorts.add(ele.metaSort)
        ar.push({
            path: ele.pathName,
            meta: {
                name: ele.metaName,
                sort: ele.metaSort
            }
        },)
    }
    for (let i = 0; i <= (primary.size - 1); i++) {
        sort.push(ar.filter((value: RouteRecordRaw) => {
            return Number(value.meta!.sort) == Array.from(sorts)[i];
        }))
    }

    const activeValue: Map<string, string> = new Map()
    const activeMenu = ref<string>()

    broadcastChannelAsActiveMenu.onmessage = function (e) {
        activeMenu.value = e.data
    }

    function activeSubMenu(index: string, view: string) {
        activeValue.set(view, index)
        activeMenu.value = activeValue.get(location.href.split("/")[4])
    }
</script>
<template>
    <el-aside width="200px">
        <p style="display:none" :index="index+1" v-for="(ele,index) in sort" :key="index">
           <span v-for="(_ele,_index) in ele" :key="_index">
               {{activeSubMenu(_index+'-'+index+1+'-'+(_index+1),_ele.path)}}
           </span>
        </p>
        <el-scrollbar>
            <el-menu
                    ref="menu"
                    :default-openeds="[0]"
                    :default-active="activeMenu"
                    active-text-color="#ffd04b"
                    text-color="#E7F6F2"
                    background-color="#6B728E"
                    class="el-menu-vertical-demo"
                    unique-opened>
                <el-sub-menu :index="index+1" v-for="(ele,index) in sort" :key="index">
                    <template #title>
<!--                        <el-icon>-->
<!--                            <message/>-->
<!--                        </el-icon>-->
                        {{ele[0].meta.name}}
                    </template>
                    <el-menu-item-group>
                        <RouterLink v-for="(_ele,_index) in ele"
                                    :key="_index"
                                    :to="{path:'/index/'+_ele?.path}">
                            <el-menu-item :index="_index+'-'+index+1+'-'+(_index+1)">
                                {{_ele?.meta.name}}
                            </el-menu-item>
                        </RouterLink>
                    </el-menu-item-group>
                </el-sub-menu>
            </el-menu>
        </el-scrollbar>
    </el-aside>
</template>
<style lang="scss" scoped>

    .layout-container-demo {
        .el-aside {
            color: var(--el-text-color-primary);
            height: 100vh;
        }

        .el-menu {
            border-right: none;
        }
    }

    ::v-deep(.el-scrollbar) {
        background-color: #6B728E;
    }

    ::v-deep(.el-menu-item-group__title) {
        padding: 0 !important;
    }

</style>