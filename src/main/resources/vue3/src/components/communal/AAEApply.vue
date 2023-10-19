<script setup lang="ts">
    import {defineEmits, ref, reactive, watch, computed, toRaw} from "vue";
    import {
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
    } from "../../config/add.massage";
    import Back from "@/components/communal/Back.vue"
    import {redio} from "../../config/redio";
    import {requetParamsType, ResponseDataType, RouterParamsType, Vendor} from "../../config/format.config";
    import {requestType} from "../../config/edit.request";
    import {Operate} from "../../config/request";
    import {ElMessage} from 'element-plus'

    _Account
    _Attendance
    _Bad
    _Classification
    _Department
    _Diagnosis
    _Drug
    _Financing
    _Interface
    _Job
    _Office
    _Patient
    _Role
    _User
    _Vendor


    let props = defineProps<RouterParamsType>()

    /*
    * 数据
    * */
    let requestData: any = reactive<{ data: requetParamsType }>({data: {}});
    let keys: string[];
    let source: any;

    /*
    * 列表赋值
    * */
    const list: any = reactive({})

    /*
    * 列表渲染
    * */
    let showV = reactive<any>({})
    /*
    * 返回样式所有的key
    * */
    function ownkey(ele: any): string[] {
        //@ts-ignore
        return Reflect.ownKeys(ele)
    }

    function dataTypeInterface(ele: string) {
        if (Type.includes(ele)) {
            let promise: any;
            if (Operate.ADD == props.editType) {
                promise = requestType.get(Operate.SHOW)!(props.dataType);
            } else if (Operate.EDIT == props.editType) {
                promise = requestType.get(Operate.SHOW)!(props.dataType, props.id);
            }
            source = eval(`_${ele}`);
            keys = ownkey(eval(`_${ele}`));
            promise?.then((resoult: ResponseDataType<any>) => {
                requestData.data = resoult.data
                for (let key1 of keys) {
                    if (isList(key1)) {
                        for (let ele of requestData.data[key1]) {
                            if (ele.id === requestData.data[key1.split("List")[0]]) {
                                list[key1] = ele.name
                            }
                        }
                    }
                    showV[key1] = true;
                    if (isList(key1)) {
                        showV[key1] = requestData.data[key1].length > 0
                    }
                }
            })
            if (!requestData.data) {
                requestData.data = eval(`_${ele}`);
            }
        } else {
            throw "不是有效的【add.massage.js.Type】类型"
        }
    }

    dataTypeInterface(props.dataType)


    function changeList(value: number, ele: string) {
        // @ts-ignore
        requestData.data[ele.split("List")[0]] = value
    }


    /*
    *判断元素
    * */
    function containELe(ele: string): boolean {
        return Reflect.ownKeys(redio).includes(ele);
    }

    function containChar(ele: string): boolean {
        return ele.indexOf("Time") > 0
    }

    /*
    * 提交事件处理
    * */
    function submit() {
        let promise: any;
        if (Operate.ADD == props.editType) {
            promise = requestType.get(props.editType)!(props.dataType, requestData.data);
        } else if (Operate.EDIT == props.editType) {
            promise = requestType.get(props.editType)!(props.dataType, requestData.data);
        }
        promise.then(response => {
            if (response?.code == 200) {
                props.callback && props.callback()
                dataTypeInterface(props.dataType)
            }
        })
    }

    function isList(value: any): boolean {
        return value.indexOf("List") > 0;
    }


</script>
<template>
    <div id="add">
        <div class="scrollbar-view">
            <el-form class="demo-form-inline">
                <el-form-item v-for="(ele,index) in keys" :label="source[ele]" v-show="showV[ele]">
                    <el-radio-group v-model="requestData.data[ele]" v-if="containELe(ele)">
                        <el-radio :label="0">
                            {{redio[ele][0]}}
                        </el-radio>
                        <el-radio :label="1">
                            {{redio[ele][1]}}
                        </el-radio>
                    </el-radio-group>
                    <el-date-picker
                            v-model="requestData.data[ele]"
                            type="date"
                            v-else-if="containChar(ele)"
                            placeholder="Pick a date"
                            style="width: 100%"
                    />

                    <el-input v-model="requestData.data[ele]" v-else-if="!isList(ele)"
                              :placeholder="source[ele]" clearable/>
                    <el-select v-else
                               v-model="list[ele]"
                               @change="changeList($event,ele)"
                               :placeholder="source[ele]"
                               clearable
                    >
                        <el-option v-for="(_ele,index) in requestData.data[ele]" :value="_ele.id" :label="_ele.name"/>
                    </el-select>
                </el-form-item>
            </el-form>
            <el-form class="demo-form-inline">
                <el-form-item v-for="(ele,index) in keys" :label="source[ele]" v-show="!showV[ele]">
                    <el-input disabled clearable/>
                </el-form-item>
            </el-form>
            <div class="re">
                <el-button color="#46458C" @click="submit" plain>{{props.editType == Operate.ADD ? '添加':'修改'}}
                </el-button>
            </div>
        </div>
    </div>
</template>
<style lang="scss" scoped>
    #add {
        margin: var(--el-main-padding);
    }

    .scrollbar-view {
        height: calc(100vh - 190px) !important;
        overflow-y: auto;
        overflow-x: hidden;
    }

    .scrollbar-view::-webkit-scrollbar {
        width: 0px;
    }

    .el-form {
        margin-top: var(--el-main-padding);
    }
    .re{
        margin-bottom:100px;
    }
    ::v-deep(.el-button) {
        float: right;
    }

    ::v-deep(.el-form-item__label){
        width: 89px;
        justify-content: right;
        font-weight: bold;
    }
</style>