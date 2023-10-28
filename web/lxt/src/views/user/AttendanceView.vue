<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;">
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card" style="margin-top: 20px;">
                    <div class="card-header">
                        <span style="font-size: 120%;">我的打卡</span>
                        <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal"
                            data-bs-target="#add_attendance_btn">我要打卡</button>

                        <!-- Modal -->
                        <div class="modal fade" id="add_attendance_btn" tabindex="-1">
                            <div class="modal-dialog modal-xl">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">新建打卡</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add_attendance_title" class="form-label">名称</label>
                                            <input v-model="attendance_add.title" type="text" class="form-control"
                                                id="add_attendance_title" placeholder="请输入打卡名称">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add_attendance_description" class="form-label">简介</label>
                                            <textarea v-model="attendance_add.description" class="form-control"
                                                id="add_attendance_description" rows="3" placeholder="请输入简介"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add_attendance_code" class="form-label">代码</label>
                                            <!-- <textarea v-model="attendance_add.content" class="form-control"
                                                id="add_attendance_code" rows="6" placeholder="请编写打卡代码"></textarea> -->
                                                <VAceEditor v-model:value="attendance_add.content"
                                                                @init="editorInit" lang="c_cpp" theme="textmate"
                                                                style="height: 300px" :options="{
                                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                                    enableSnippets: true, // 启用代码段
                                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                                    fontSize: 18, //设置字号
                                                                    tabSize: 4, // 标签大小
                                                                    // showPrintMargin: false, //去除编辑器里的竖线
                                                                    highlightActiveLine: true,
                                                                }" />
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ attendance_add.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_attendance">创建</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="attendance in attendances" :key="attendance.id">
                                    <td>{{ attendance.title }}</td>
                                    <td>{{ attendance.createtime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" style="margin-right: 10px;"
                                            data-bs-toggle="modal"
                                            :data-bs-target="'#update_attendance_modal-' + attendance.id">修改</button>
                                        <button type="button" class="btn btn-danger"
                                            @click="remove_attendance(attendance)">删除</button>

                                        <!-- Modal -->
                                        <div class="modal fade" :id="'update_attendance_modal-' + attendance.id">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="exampleModalLabel">新建打卡</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                            aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="add_attendance_title" class="form-label">名称</label>
                                                            <input v-model="attendance.title" type="text"
                                                                class="form-control" id="add_attendance_title"
                                                                placeholder="请输入打卡名称">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add_attendance_description"
                                                                class="form-label">简介</label>
                                                            <textarea v-model="attendance.description" class="form-control"
                                                                id="add_attendance_description" rows="3"
                                                                placeholder="请输入简介"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="add_attendance_code" class="form-label">代码</label>
                                                            <!-- <textarea v-model="attendance.content" class="form-control"
                                                                id="add_attendance_code" rows="6"
                                                                placeholder="请编写打卡代码"></textarea> -->
                                                            <VAceEditor v-model:value="attendance.content"
                                                                @init="editorInit" lang="c_cpp" theme="textmate"
                                                                style="height: 300px" :options="{
                                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                                    enableSnippets: true, // 启用代码段
                                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                                    fontSize: 18, //设置字号
                                                                    tabSize: 4, // 标签大小
                                                                    // showPrintMargin: false, //去除编辑器里的竖线
                                                                    highlightActiveLine: true,
                                                                }" />
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ attendance.error_message }}</div>
                                                        <button type="button" class="btn btn-primary"
                                                            @click="update_attendance(attendance)">保存修改</button>
                                                        <button type="button" class="btn btn-secondary"
                                                            data-bs-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex'
import { ref, reactive } from 'vue'
import $ from 'jquery'
import { Modal } from 'bootstrap/dist/js/bootstrap';
import { VAceEditor } from 'vue3-ace-editor';
import 'ace-builds/src-noconflict/mode-c_cpp';
import 'ace-builds/src-noconflict/mode-json';
import 'ace-builds/src-noconflict/theme-chrome';
import 'ace-builds/src-noconflict/ext-language_tools';

import ace from 'ace-builds';


export default {
    components: {
        VAceEditor,
    },
    setup() {
        ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")


        const store = useStore();
        let attendances = ref([]);

        const attendance_add = reactive({
            title: "",
            description: "",
            content: "",
            error_message: "",
        });

        const refresh_attendances = () => {
            $.ajax({
                url: 'http://127.0.0.1:3000/user/attendance/getlist/',
                type: 'GET',
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    // console.log(resp);
                    attendances.value = resp;

                },
                error(resp) {
                    console.log(resp);
                }
            })
        }

        const add_attendance = () => {
            attendance_add.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/attendance/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    title: attendance_add.title,
                    description: attendance_add.description,
                    content: attendance_add.content,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        refresh_attendances();
                        Modal.getInstance("#add_attendance_btn").hide();
                        attendance_add.title = "";
                        attendance_add.description = "";
                        attendance_add.content = "";

                    }
                    else {
                        attendance_add.error_message = resp.error_message;
                    }
                },

            });
        }

        const remove_attendance = (attendance) => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/attendance/remove/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    attendance_id: attendance.id,
                },
                success(resp) {
                    if (resp.error_message === "success")
                        refresh_attendances();

                }
            });
        }
        // 到修改辣
        const update_attendance = (attendance) => {
            attendance_add.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3000/user/attendance/update/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    attendance_id: attendance.id,
                    title: attendance.title,
                    description: attendance.description,
                    content: attendance.content,
                },
                success(resp) {
                    if (resp.error_message === "success") {

                        Modal.getInstance('#update_attendance_modal-' + attendance.id).hide();
                        refresh_attendances();
                    }
                    else {
                        attendance_add.error_message = resp.error_message;
                    }
                },

            });
        }

        refresh_attendances();

        return {
            attendances,
            attendance_add,
            add_attendance,
            remove_attendance,
            update_attendance,
        }

    }
}
</script>

<style scoped>
.error-message {
    color: red;
}
</style>