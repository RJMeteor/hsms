import {createRouter, createWebHistory, RouteLocationNormalized, RouteRecordRaw} from "vue-router";
import {reactive, watch} from "vue"
import {Operate} from "./request";
import {requestType} from "./edit.request";
import type {Login} from "./format.config";

const activePage: Array<RouteLocationNormalized> = reactive([])

const broadcastChannelAsActiveMenu = new BroadcastChannel("activeMenu")
const channel = new MessageChannel();
/*
* 发送
* */
const send = channel.port1
/*
* 接收
* */
const reception = channel.port2
watch(activePage, value => {
    if (activePage.length > 0) send.postMessage(false)
    else send.postMessage(true)
})

/*
* 左边的菜单栏配置
* */
const manage: Array<RouteRecordRaw> = [
    {
        path: "Account",
        // @ts-ignore
        component: () => import("@/components/Account.vue"),
        meta: {
            name: "账号管理",
            sort: 1
        },
    },
    {
        path: "Financing",
        // @ts-ignore
        component: () => import("@/components/Financing.vue"),
        meta: {
            name: "财务管理",
            sort: 1
        }
    },
    {
        path: "Vendor",
        // @ts-ignore
        component: () => import("@/components/Vendor.vue"),
        meta: {
            name: "供应商管理",
            sort: 1
        }
    },
    {
        path: "User",
        // @ts-ignore
        component: () => import("@/components/User.vue"),
        meta: {
            name: "用户管理",
            sort: 2
        },
    },
    {
        path: "Attendance",
        // @ts-ignore
        component: () => import("@/components/Attendance.vue"),
        meta: {
            name: "考勤管理",
            sort: 2
        }
    },
    {
        path: "Department",
        // @ts-ignore
        component: () => import("@/components/Department.vue"),
        meta: {
            name: "部门管理",
            sort: 2
        }
    },
    {
        path: "Job",
        // @ts-ignore
        component: () => import("@/components/Job.vue"),
        meta: {
            name: "职位管理",
            sort: 2
        }
    },
    {
        path: "Patient",
        // @ts-ignore
        component: () => import("@/components/Patient.vue"),
        meta: {
            name: "患者管理",
            sort: 3
        },
    },
    {
        path: "Office",
        // @ts-ignore
        component: () => import("@/components/Office.vue"),
        meta: {
            name: "会诊科室管理",
            sort: 3
        }
    },
    {
        path: "Drug",
        // @ts-ignore
        component: () => import("@/components/Drug.vue"),
        meta: {
            name: "药品管理",
            sort: 3
        }
    },
    {
        path: "Bad",
        // @ts-ignore
        component: () => import("@/components/Bad.vue"),
        meta: {
            name: "住院床管理",
            sort: 3
        }
    },
    {
        path: "Diagnosis",
        // @ts-ignore
        component: () => import("@/components/Diagnosis.vue"),
        meta: {
            name: "诊断信息管理",
            sort: 3
        }
    },
    {
        path: "Classification",
        // @ts-ignore
        component: () => import("@/components/Classification.vue"),
        meta: {
            name: "药品分类管理",
            sort: 3
        }
    },
    {
        path: "Role",
        // @ts-ignore
        component: () => import("@/components/Role.vue"),
        meta: {
            name: "角色管理",
            sort: 4
        },
    },
    {
        path: "Interface",
        // @ts-ignore
        component: () => import("@/components/Interface.vue"),
        meta: {
            name: "接口管理",
            sort: 4
        }
    },
]


const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        // @ts-ignore
        component: () => import("@/components/login/Login.vue"),
    },
    {
        path: "/index",
        // @ts-ignore
        component: () => import("@/components/layout/Index.vue"),
        children: [
            ...manage,
            {
                path: "AAE/:dataType/:editType/:id",
                name: "AAE",
                // @ts-ignore
                component: () => import("@/components/communal/AAE.vue"),
                props: true
            },
        ]
    },
];


const router = createRouter({
    history: createWebHistory(),
    routes,
});

/*
* 路由前置拦截
* 1：拦截一些跳转路径，保存请求路径到activePage
* 2：改变激活页面的面包屑颜色
* */
let info = JSON.parse(<string>localStorage.getItem("info"))
router.beforeEach((to, from, next) => {
    to.meta.color = "#46458C"
    const valueBy = activePage.filter((value: RouteLocationNormalized, index: number) => {
        value.meta.color = "#6B728E"
        if (value.path == to.path) {
            value.meta.color = "#46458C";
        }
        if (value.path.indexOf(to.path.split("/")[3]) > 0) {
            value.meta.color = "#46458C";
        }
        return value.path == to.path
    })
    if (from.path == "/") {
        activePage.length = 0;
    }
    // if (from.path != '/' || from.fullPath) {
    if (from.path != '/') {
        if (to.path != '/index') {
            let item: any = to.path.split("/")[2];
            // if (item == "AAE") {
            //     item = to.path.split("/")[3];
            //     if (info.indexOf(item) < 0) {
            //         location.href = "/"
            //     }
            // }else{
            //     if (info.indexOf(item) < 0) {
            //         location.href = "/"
            //     }
            // }
            broadcastChannelAsActiveMenu.postMessage(item)
            if (valueBy.length == 0 && to.path.indexOf(Operate.DELETE) < 0
                && to.path.indexOf(Operate.SHOW) < 0
                && to.path.indexOf(Operate.EDIT) < 0
                && to.path.indexOf(Operate.ADD) < 0)
                activePage.push(to)
        }
    }
    next()
})
export {router, activePage, reception}