<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            text: "专辑详情",
            iconCls: 'icon-tip',
            handler: function () {
                var row = $("#tt1").treegrid("getSelected");
                if (row != null) {
                    if (row.author != null) {
                        $("#dd").dialog("open")
                        $('#ff').form('load', row);
                        $('#img').prop('src', '${pageContext.request.contextPath}/upload/' + row.corverImg);
                    } else {
                        alert("请选中专辑")
                    }
                } else {
                    alert("请选中行");
                }

            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-save',
            handler: function () {
                $("#car").dialog({
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
                            $("#album").form('clear');
                        },
                    }, {
                        text: '提交',
                        handler: function () {
                            $('#album').form('submit', {
                                url: "${pageContext.request.contextPath}/album/addAlbum.do",
                                onSubmit: function () {
                                    var isValid = $(this).form('validate');
                                    if (!isValid) {
                                        $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                    }
                                    return isValid;	// 返回false终止表单提交
                                },
                                success: function (data) {
                                    $('#car').dialog('close');
                                }
                            });
                        }
                    }],
                });
            }
        }, '-', {
            text: "添加章节",
            iconCls: 'icon-save',
            handler: function () {
                var row = $("#tt1").treegrid("getSelected");
                if (row != null) {
                    if (row.author != null) {
                        $("#chapter").dialog({
                            title: '添加章节',
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
                                    $("#chapter1").form('clear');
                                },
                            }, {
                                text: '提交',
                                handler: function () {
                                    $('#chapter1').form('submit', {
                                        url: "${pageContext.request.contextPath}/chapter/add.do?albumID=" + row.id,
                                        onSubmit: function () {
                                            var isValid = $(this).form('validate');
                                            if (!isValid) {
                                                $.messager.progress('close');	// 如果表单是无效的则隐藏进度条
                                            }
                                            return isValid;	// 返回false终止表单提交
                                        },
                                        success: function (data) {
                                            $('#chapter').dialog('close');
                                        }
                                    });
                                }
                            }],
                        });
                    } else {
                        alert("请选中专辑后操作")
                    }
                } else {
                    alert("请选中行");
                }
            }
        }, '-', {
            text: "下载音频",
            iconCls: 'icon-edit',
            handler: function () {
                var row2 = $("#tt1").treegrid("getSelected");
                if (row2 != null) {
                    if (row2.duration != null) {
                        location.href = "${pageContext.request.contextPath}/chapter/downLoad.do?saveName=" + row2.name + "&dbFileName=" + row2.audioPath;
                    } else {
                        alert("请选中歌曲");
                    }
                } else {
                    alert("请选中歌曲");
                }
            }
        }]

        $("#tt1").treegrid({
            onDblClickRow: function (row) {//双击事件
                $("#audio_dd").dialog({
                    title: '播放音频',
                    width: 500,
                    height: 200,
                    collapsible: true,
                    minimizable: true,
                    maximizable: true,
                    modal: true
                });
                $("audio").prop("src", "${pageContext.request.contextPath}/upload/" + row.audioPath);
                $("h2").html(row.name + ":");
            },
            url: "${pageContext.request.contextPath}/album/queryAll.do",
            idField: 'id',
            treeField: 'name',
            columns: [[
                {field: 'name', title: '名字', width: 60, align: 'middle'},
                {field: 'audioPath', title: '下载路径', width: 80},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'duration', title: '章节时长', width: 80}
            ]],
            fitColumns: true,
            fit: true,
            toolbar: toolbar

        })

    })
</script>

<table id="tt1" style="width:600px;height:400px">
</table>
<div id="dd" class="easyui-dialog" title="展示专辑详情" style="width:700px;height:400px;background-color:grey;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="ff" method="post">
        <div align="center">
            <label for="name">名称:</label>
            <input class="easyui-validatebox" type="text" id="name" name="name" data-options=""/>
        </div>
        <div align="center">
            <label for="count">数量:</label>
            <input class="easyui-validatebox" type="text" id="count" name="count" data-options=""/>
        </div>
        <div align="center">
            <label for="score">评分:</label>
            <input class="easyui-validatebox" type="text" id="score" name="score" data-options=""/>
        </div>
        <div align="center">
            <label for="author">作者:</label>
            <input class="easyui-validatebox" type="text" id="author" name="author" data-options=""/>
        </div>
        <div align="center">
            <label for="broadCast">播音:</label>
            <input class="easyui-validatebox" type="text" id="broadCast" name="broadCast" data-options=""/>
        </div>
        <div align="center">
            <label for="brife">内容简介:</label>
            <input class="easyui-validatebox" type="text" id="brife" name="brife" data-options=""/>
        </div>
        <div align="center">

            封面:<img src="" id="img" alt="" width="200" href="100">
        </div>
    </form>
</div>
<div id="car" style="display: none;padding:30px ">
    <form id="album" method="post" enctype="multipart/form-data">
        <div>
            <label for="name">名称:</label>
            <input class="easyui-validatebox" type="text" name="name" data-options=""/>
        </div>
        <div>
            <label for="count">数量:</label>
            <input class="easyui-validatebox" type="text" name="count" data-options=""/>
        </div>
        <div>
            <label for="score">分数:</label>
            <input class="easyui-validatebox" type="text" name="score" data-options=""/>
        </div>
        <div>
            <label for="author">作者:</label>
            <input class="easyui-validatebox" type="text" name="author" data-options=""/>
        </div>
        <div>
            <label for="broadCast">播音:</label>
            <input class="easyui-validatebox" type="text" name="broadCast" data-options=""/>
        </div>
        <div>
            <label for="brife">内容简介:</label>
            <input class="easyui-validatebox" type="text" name="brife" data-options=""/>
        </div>
        <div>
            上传文件:<input type="file" name="file1" data-options=""/>
        </div>
        <div>
            <label for="publicDate">创作日期:</label>
            <input class="easyui-validatebox" type="date" id="publicDate" name="publicDate1" data-options=""/>
        </div>
        <div>
            <label for="status">专辑状态:</label>
            <input class="easyui-validatebox" type="number" id="status" name="status" data-options=""/>
        </div>
    </form>
</div>
<div id="chapter" style="display: none;padding:30px ">
    <form id="chapter1" method="post" enctype="multipart/form-data">
        <div>
            批量上传章节音频:<input type="file" name="file1" multiple data-options=""/>
        </div>
    </form>
</div>

<div id="audio_dd" style="display: none;padding:30px; ">
    <h2></h2>
    <audio src="someaudio.wav" controls autoplay>
        您的浏览器不支持 audio 标签。
    </audio>
</div>








