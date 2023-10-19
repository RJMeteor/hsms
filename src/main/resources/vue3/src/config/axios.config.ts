import axios, {AxiosHeaders, AxiosRequestConfig} from 'axios';
import {router} from "../config/router.config"
import Progress from 'nprogress'
import {ElMessage} from 'element-plus'
import type {ResponseDataType} from "./format.config";

const baseURL: string = import.meta.env.HIMS_REQUEST_URL
const headerToken: string = import.meta.env.HIMS_REQUEST_HEADERS_TOKEN_NAME

Progress.configure({
    easing: 'speed',  // 动画方式
    speed: 500,  // 递增进度条的速度
    showSpinner: false, // 是否显示加载ico
    trickleSpeed: 200, // 自动递增间隔
    minimum: 0.3 // 初始化时的最小百分比
});


const axiosInstance = axios.create({
    baseURL,
    method: "POST",
    timeout: 15000,
    headers: {
        "Content-Type": "application/json",
    }
});

axiosInstance.interceptors.request.use(
    (config: any) => {
        Progress.start()
        return {
            ...config,
            headers: {
                ...config.headers,
                Authorization: localStorage.getItem(headerToken) ? localStorage.getItem(headerToken):""
            },
        };
    },
    (error) => {
        Progress.done()
        ElMessage.error("请求失败")
    },
);


axiosInstance.interceptors.response.use(
    function (response: any) {
        Progress.done()
        const dataApply: ResponseDataType<any> = {
            code: response.data.code,
            data: undefined
        }
        if (response.data.code == 5006) {
            for (let ele of response.data.data) {
                ElMessage.error(ele)
            }
            return Promise.resolve(dataApply)
        } else if (response.data.code >= 5000) {
            ElMessage.error(response.data.data)
            // localStorage.clear()
            router.push("/")
            return Promise.resolve(dataApply)
        } else if (response.data.code == 4005) {
            ElMessage.success(response.data.data)
            router.push("/index")
            return Promise.resolve(dataApply)
        } else if (typeof response.data.data == "string") {
            if (response.data.code == 200) {
                ElMessage.success(response.data.data)
            } else {
                ElMessage.error(response.data.data)
            }
            return Promise.resolve(response)
        }
        return Promise.resolve(response);
    },
    function (error) {
        Progress.done()
        ElMessage.error("操作失败")
        // localStorage.clear()
        router.push("/")
    },
);
export default axiosInstance;
