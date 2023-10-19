import type {Account, ResponseDataType, requetParamsType, Login} from "./format.config";
import {DataTypes, Operate, request} from "./request";
import {Page} from "./page";


let requestType = new Map<String, Function>();


requestType.set(Operate.SHOW,  function (requestController: string, id?: number): Promise<ResponseDataType<requetParamsType>> {
    return  request<Account>({
        method: "get",
        url: `${requestController.toLowerCase()}/show`,
        params: {
            id: id
        }
    })
})

requestType.set(Operate.ADD, function (requestController: string, data: requetParamsType): Promise<ResponseDataType<string>> {
    return  request<string>({
        method: "put",
        url: `${requestController.toLowerCase()}/add`,
        headers: {
            "Content-Type": "application/json"
        },
        data
    })
})

requestType.set(Operate.EDIT,
     function (requestController: string, data: requetParamsType): Promise<ResponseDataType<string>> {
        return  request<string>({
            method: "post",
            url: `${requestController.toLowerCase()}/edit`,
            headers: {
                "Content-Type": "application/json"
            },
            data
        })
    }
)

requestType.set(Operate.DELETE,
     function (requestController: string, id: number):Promise<ResponseDataType<string>> {
        return  request<string>({
            method: "delete",
            url: `${requestController.toLowerCase()}/delete`,
            params: {
                id: id
            }
        })
    }
)

requestType.set(Operate.PAGE,
     function (requestController: string, page: Page<requetParamsType>): Promise<ResponseDataType<Page<requetParamsType>>> {
        return  request<Page<requetParamsType>>({
            method: "get",
            url: `${requestController.toLowerCase()}/page`,
            params: page,
        })
    }
)

requestType.set(Operate.BHDE,
     function (requestController: string, id: number[]):Promise<ResponseDataType<string>> {
        return  request<string>({
            method: "Post",
            url: `${requestController.toLowerCase()}/bachDelete`,
            headers: {
                "Content-Type": "application/json"
            },
           data:id
        })
    }
)

requestType.set(Operate.LOGIN,function (requestController:string,data:Login):Promise<ResponseDataType<string>>  {
    return  request<string>({
        method: "Post",
        url: `crux/${requestController.toLowerCase()}`,
        headers: {
            "Content-Type": "application/json"
        },
        data: data
    })
})


export {
    requestType
}