<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        $("#btn").click(function () {
            var titls = $("#customer_tree").combotree("getText");
            var params = $("#customer_tree").combotree("getValues");
            var a = "";
            $.each(params, function (index, param) {
                if (param.length - 1 == index) {
                    a += param;
                } else {
                    a += param + ",";
                }
            })
            $("#customer_form").form("submit", {
                url: "${pageContext.request.contextPath}/user/freeSelect.do",
                queryParams: {
                    titls: titls,
                    params: a,
                }

            })
        })
        var toolbar = [{
            iconCls: 'icon-edit',
            text: "修改",
            handler: function () {
                var sel1 = $("#").edatagrid('getSelected');
                if (sel1 != null) {
                    var index = $("#").edatagrid("getRowIndex", sel1);
                    $("#").edatagrid('editRow', index);
                } else {
                    alert("请选中行");
                }
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "下载用户信息录入模板",
            handler: function () {
                location.href = "${pageContext.request.contextPath}/user/download.do";
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "导出用户信息文件",
            handler: function () {
                location.href = "${pageContext.request.contextPath}/user/export.do";
            }
        }, '-', {
            iconCls: 'icon-help',
            text: "自定义导出",
            handler: function () {
                $("#customer_dd").dialog("open")
            }
        }, '-', {
            iconCls: 'icon-save',
            text: "导入用户信息文件",
            handler: function () {
                $("#poiupload").dialog({
                    title: '上传用户信息文档',
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
                            $("#poiupload1").form('clear');
                        },
                    }, {
                        text: '提交',
                        handler: function () {
                            $('#poiupload1').form('submit', {
                                url: "${pageContext.request.contextPath}/user/import.do",
                                onSubmit: function () {
                                    var isValid = $(this).form('validate');
                                    if (!isValid) {
                                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                    }
                                    return isValid;	// 返回false终止表单提交
                                },
                                success: function (data) {
                                    $('#poiupload').dialog('close');
                                }
                            });
                        }
                    }],
                });
            }
        }]

        $("#userGrid").datagrid({
            url: "${pageContext.request.contextPath}/user/queryAll.do",
            columns: [[
                {field: 'guru_id', title: '所属上师ID', width: 100},
                {field: 'id', title: '自身ID', width: 100},
                {
                    field: 'photoImg', title: '头像', width: 100, align: 'right'
                },
                {field: 'name', title: '名字', width: 100, align: 'right'},
                {field: 'dharmaName', title: '法名', width: 100, align: 'right'},
                {field: 'sex', title: '性别', width: 100, align: 'right'},
                {field: 'province', title: '省份', width: 100, align: 'right'},
                {field: 'city', title: '城市', width: 100, align: 'right'},
                {field: 'sign', title: '签名', width: 100, align: 'right'},
                {field: 'phoneNum', title: '手机号', width: 100, align: 'right'},
                {field: 'password', title: '密码', width: 100, align: 'right'},
                {field: 'status', title: '状态', width: 100, align: 'right'},
                {field: 'registDate', title: '注册日期', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 3,
            pageList: [3, 6, 9, 12],
            toolbar: toolbar
        })

    })
</script>
<table id="userGrid">
</table>
<div id="poiupload" style="display: none;padding:30px ">
    <form id="poiupload1" method="post" enctype="multipart/form-data">
        <div>
            导入文件:<input type="file" name="file1" multiple data-options=""/>
        </div>
    </form>
</div>
<div align="center" id="customer_dd" class="easyui-dialog" title="自定义导出" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <select id="customer_tree" class="easyui-combotree" style="width:200px;"
            data-options="checkbox:true,multiple:true,onlyLeafCheck:true,required:true,
            data:[{
    'id':'custome',
    'checked':false,
    'text': '请选择',
    'children': [
      {
        'id':'id',
        'text': '自身ID',
        'checked': true
      },
      { 'id':'name',
        'text': '名字',
        'checked': true
      },
      { 'id':'sign',
        'text': '签名',
        'checked': true
      }
    ]
  }
]"></select>
    <form action="" method="post" id="customer_form">
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">提交</a>
    </form>
</div>