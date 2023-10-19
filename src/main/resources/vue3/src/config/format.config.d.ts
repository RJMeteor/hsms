/*
* 公共接口
* */
interface Time {
    id?: number;
    createTime?: string;
    updateTime?: string;

}


/*
* 账号接口
* */
declare type Account = {
    name?: string;
    person?: string;
} & Time


/*
* 考勤接口
* */
declare type Attendance = {
    type?: string;
    user?: number;
    name?: string;
    astatus?: number;
    abortTime?: Date;
    userPojo?: User,
    userList?: User[];
} & Time


/*
* 住院床接口
* */
declare type Bad = {
    status?: number;
    name?: string;
} & Time

/*
* 药品分类接口
* */
declare type Classification = {
    name?: string;
} & Time

/*
* 部门接口
* */
declare type Department = {
    name?: string;
    phone?: string;
    email?: string;
    address?: string;
    commuting?: Date;
} & Time

/*
* 诊断信息接口
* */
declare type Diagnosis = {
    name?: string;
    patient?: number;
    office?: number;
    bad?: number;
    money?: number;
    drug?: number;
    drugCount?: number;
    payment?: number;
    diagnosis?: string;
    badPojo?: Bad;
    patientPojo?: Patient;
    officePojo?: Office;
    drugPojo?: Drug;
    badList?: Bad[];
    patientList?: Patient[];
    officeList?: Office[];
    drugList?: Drug[];
} & Time

/*
* 药品类
* */
declare type Drug = {
    classify?: number;
    nameDrug?: string;
    barCode?: string;
    model?: string;
    unit?: string;
    quantity?: number;
    label?: string;
    vendor?: number;
    customerPrice?: number;
    costPrice?: number;
    special?: number;
    whether?: number;
    classificationPojo?: Classification;
    vendorPojo?: Vendor;
    classificationList?: Classification[];
    vendorList?: Vendor[];
} & Time

/*
* 财务接口
* */
declare type Financing = {
    ftype?: 0 | 1;
    name?: string;
    department?: number;
    account?: number;
    money?: number;
    state?: number;
    description?: string;
    comment?: string;
    person?: string;
    departmentPojo?: Department;
    accountPojo?: Account;
    departmentList?: Department[];
    accountList?: Account[];
} & Time

/*
* 接口--接口
* */
declare type Interface = {
    role?: number;
    name?: string;
    pathName?:string;
    metaName?:string;
    metaSort?:number;
    requestAddress?: string;
    rolePojo?: Role;
    roleList?: Role[];
} & T

/*
* 职位接口
* */
declare type Job = {
    name?: string;
    level?: number;
} & Time

/*
* 会诊科室接口
* */
declare type Office = {
    name?: string;
} & Time

/*
* 患者
* */
declare type Patient = {
    name?: string;
    sex?: 0 | 1;
    age?: number;
    phoneNumber?: string;
    identityNumber?: string;
    cardNumber?: string;
    user?: number;
    caseHistory?: string;
    userPojo?: User;
    userList?: User[];
} & Time

/*
* 角色接口
* */
declare type Role = {
    name?: string;
    permission?: number;
} & Time

/*
* 用户接口
* */
declare type User = {
    name?: string;
    rreal?: string;
    phone?: string;
    password?: string;
    email?: string;
    homeAddress?: string;
    educationBackground?: string;
    school?: string;
    identityCard?: string;
    bankAccount?: string;
    department?: number;
    sex?: 0 | 1;
    job?: number;
    role?: number;
    salary?: number;
    entryTime?: Date;
    birthdayTime?: Date;
    departmentPojo?: Department;
    rolePojo?: Role;
    jobPojo?: Job;
    departmentList?: Department[];
    roleList?: Role[];
    jobList?: Job[];
} & Time


/*
* 供应商接口
* */
declare type Vendor = {
    name?: string;
    vendorLevel?: number;
    vendorSource?: string;
    phone?: string;
    weixin?: string;
    postcode?: string;
    address?: string;
    businessLicense?: string;
} & Time

/*
* 登录接口
* */
declare type Login = {
    phone?: string | any;
    password?: string | any;
    token?: string | any;
}


/*
* 路由传参
* */
declare  type RouterParamsType = {
    /*
    * 那个页面的
    * */
    dataType?: string;
    /*
    * 操作类型
    * */
    editType?: "add" | "edit" | "show" | "delete" | "page" | "batchDelete";

    /*
    * 操作Id
    * */
    id?: number,

    /*
    * 回调函数
    * */
    callback?: () => void,
}

/*
* 数据格式
* */
declare interface ResponseDataType<T> {
    code?: number;
    data?: T
}

/*
* 用在请求参数上
* */
declare  type requetParamsType = Account
    | Attendance
    | Bad
    | Classification
    | Department
    | Diagnosis
    | Drug
    | Financing
    | Interface
    | Job
    | Office
    | Patient
    | Role
    | User
    | Vendor
    | Login

export {
    Account,
    Attendance,
    Bad,
    Classification,
    Department,
    Diagnosis,
    Drug,
    Financing,
    Interface,
    Job,
    Office,
    Patient,
    Role,
    User,
    Vendor,
    Login,
    RouterParamsType,
    ResponseDataType,
    requetParamsType
}