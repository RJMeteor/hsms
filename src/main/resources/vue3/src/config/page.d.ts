/*
* 页码配置
* */
declare  interface Page<T>{
    /*
    * 每页条数数据
    * */
    size?:number;
    /*
    * 第几页
    * */
    current?:number;
    /*
    * 总数据
    * */
    total?:number;

    /*
    * 数据列表
    * */
    records?:T[],

    /*
    * 模糊查询值
    * */
    selectValue?:string
}

declare type PageData = {
    page:Page;
}

export {
    PageData,
    Page
}