/*
* 账号接口
* */
const _Account = {
    name: "账户名",
    person: "账户人"
}


/*
* 考勤接口
* */
const _Attendance = {
    type: "请假类型",
    name: "备注",
    astatus: "是否迟到",
    abortTime: "请假截止时间",
    userList: "用户",
}


/*
* 住院床接口
* */
const _Bad = {
    status: "是否被占用",
    name: "床位"
}

/*
* 药品分类接口
* */
const _Classification = {
    name: "名称"
}

/*
* 部门接口
* */
const _Department = {
    name: "名称",
    phone: "电话",
    email: "邮箱",
    address: "地址",
    commuting: "上下班时间",
}

/*
* 诊断信息接口
* */
const _Diagnosis = {
    name: "病情",
    money: "金额",
    drugCount: "药品数量",
    payment: "是否付款",
    diagnosis: "诊断内容",
    badList: "床位",
    patientList: "患者",
    officeList: "会诊科室",
    drugList: "药品",
}

/*
* 药品类
* */
const _Drug = {
    name: "药物名称",
    barCode: "条形码",
    model: "型号",
    unit: "单位",
    quantity: "数量",
    label: "标签",
    customerPrice: "客户单价",
    costPrice: "成本价",
    special: "特价",
    whether: "推销",
    classificationList: "药品分类",
    vendorList: "供应商",
}

/*
* 财务接口
* */
const _Financing = {
    ftype: "财务类型",
    name: "花费的说明",
    money: "金额",
    state: "是否失效",
    description: "说明",
    comment: "备注",
    person: "办理人",
    departmentList: "部门",
    accountList: "账号",
}

/*
* 接口--接口
* */
const _Interface = {
    name: "标题",
    requestAddress: "请求地址",
    pathName:"请求英文名",
    metaName:"请求中文名",
    metaSort:"层级",
    roleList: "角色",
}

/*
* 职位接口
* */
const _Job = {
    name: "职位名称",
    level: "层级",
}

/*
* 会诊科室接口
* */
const _Office = {
    name: "会诊科室名",
}

/*
* 患者
* */
const _Patient = {
    name: "患者名称",
    sex: "性别",
    age: "年龄",
    phoneNumber: "手机号码",
    identityNumber: "身份证号",
    cardNumber: "医保卡号",
    caseHistory: "病历",
    userList: "用户",
}

/*
* 角色接口
* */
const _Role = {
    name: "角色名字",
    permission: "权限值",
}

/*
* 用户接口
* */
const _User = {
    name: "用户名",
    rreal: '真实名字',
    phone: "电话",
    password: "密码",
    email: "邮箱",
    homeAddress: "家庭地址",
    educationBackground: "学历",
    school: "毕业院校",
    identityCard: "身份证号",
    bankAccount: "银行账号",
    sex: "性别",
    salary: "工资",
    entryTime: "入职时间",
    birthdayTime: "生日时间",
    departmentList: "部门",
    roleList: "角色",
    jobList: "职位",
}


/*
* 供应商接口
* */
const _Vendor = {
    name: "供应商名",
    vendorLevel: "供应商级别",
    vendorSource: "供应商来源",
    phone: "电话号码",
    weixin: "微信",
    postcode: "邮编",
    address: "地址",
    businessLicense: "营业执照",
}

const Type: string[] = [
    "Account",
    "Attendance",
    "Bad",
    "Classification",
    "Department",
    "Diagnosis",
    "Drug",
    "Financing",
    "Interface",
    "Job",
    "Office",
    "Patient",
    "Role",
    "User",
    "Vendor"
]

export {
    _Account,
    _Attendance,
    _Bad,
    _Classification,
    _Department,
    _Diagnosis,
    _Drug,
    _Financing,
    _Interface,
    _Job,
    _Office,
    _Patient,
    _Role,
    _User,
    _Vendor,
    Type
}