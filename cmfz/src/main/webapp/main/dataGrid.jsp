<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $("#add").dialog({
                    title: '添加',
                    width: 600,
                    height: 350,
                    collapsible: true,
                    minimizable: true,
                    maximizable: true,
                    modal: true,
                    buttons: [{
                        text: '重置',
                        iconCls: 'icon-reload',
                        handler: function () {
                            $("#addBanner").form('clear');
                        },
                    }, {
                        text: '提交',
                        handler: function () {
                            $('#addBanner').form('submit', {
                                url: "${pageContext.request.contextPath}/banner/add.do",
                                onSubmit: function () {
                                    var isValid = $(this).form('validate');
                                    if (!isValid) {
                                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                    }
                                    return isValid;	// 返回false终止表单提交
                                },
                                success: function (data) {
                                    $('#add').dialog('close');
                                    $("#dg").edatagrid('load');
                                }
                            });
                        }
                    }],


                });
            }
        }, '-', {
            iconCls: 'icon-remove',
            text: "删除",
            handler: function () {
                var sel = $("#dg").datagrid('getSelected');
                var id = sel.id;
                if (confirm("确认要删除选中项吗")) {
                    location.href = "${pageContext.request.contextPath}/banner/delete.do?id=" + id;
                }
                //$("#dg").datagrid('reload');
            }
        }, '-', {
            iconCls: 'icon-edit',
            text: "修改",
            handler: function () {
                var sel1 = $("#dg").edatagrid('getSelected');
                if (sel1 != null) {
                    var index = $("#dg").edatagrid("getRowIndex", sel1);
                    $("#dg").edatagrid('editRow', index);
                } else {
                    alert("请选中行");
                }
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "保存",
            handler: function () {
                $("#dg").edatagrid("saveRow")
            }
        }]

        $("#dg").edatagrid({
            url: "${pageContext.request.contextPath}/banner/banner1.do",
            columns: [[
                {field: 'id', title: 'ID', width: 100},
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'imgPath', title: '图片', width: 100, align: 'right', formatter: function (value) {
                    return '<img src="${pageContext.request.contextPath}/upload' + value + '" style="width:140px;height:160px;"/>';
                }
                },
                {field: 'description', title: '描述', width: 100, align: 'right'},
                {
                    field: 'status', title: '状态', width: 100, align: 'right', editor: {
                    type: "text",
                    options: {
                        required: true
                    }
                }
                },
                {field: 'createDate', title: '创建日期', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 3,
            pageList: [3, 6, 9, 12],
            toolbar: toolbar,
            updateUrl: "${pageContext.request.contextPath}/banner/save.do",
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.title + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        })

    })
</script>

<table id="dg">
</table>
<div style="display: none;padding:30px " id="add">
    <form id="addBanner" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>名称 :</td>
                <td><input style="width: 220px" name="title" class="easyui-textbox" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>图片 :</td>
                <td><input style="width: 220px" class="easyui-filebox" name="imgPath1"
                           data-options="required:true,accept:'image/jpeg'"></td>
            </tr>
            <tr>
                <td> 描述:</td>
                <td>
                    <textarea style="width: 215px;margin-top: 10px" name="description">请输入描述信息!~
                    </textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
