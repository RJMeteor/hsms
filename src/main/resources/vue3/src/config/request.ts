import axiosInstance from "./axios.config";
import {AxiosRequestConfig} from "axios";
import type {ResponseDataType} from "./format.config";
import {watch,reactive} from "vue"


const request = async <T>(config: AxiosRequestConfig): Promise<ResponseDataType<T>> => {
    let response = await  axiosInstance(config);
    return response.data
}


/*
* 请求数据类型枚举
* */
enum DataTypes {
    Account = "Account",
    Attendance = "Attendance",
    Bad = "Bad",
    Classification = "Classification",
    Department = "Department",
    Diagnosis = "Diagnosis",
    Drug = "Drug",
    Financing = "Financing",
    Interface = "Interface",
    Job = "Job",
    Office = "Office",
    Patient = "Patient",
    Role = "Role",
    User = "User",
    Vendor = "Vendor"
}

enum Operate {
    SHOW = "show",
    EDIT = "edit",
    DELETE = "delete",
    ADD = "add",
    PAGE = "page",
    BHDE = "bachDelete",
    LOGIN = "login",
    LOGOUT = "logout"
}

export {
    request,
    DataTypes,
    Operate
}

