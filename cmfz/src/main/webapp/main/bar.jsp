<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="../js/echarts.min.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<html>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '持明法洲APP用户活跃图'
        },
        tooltip: {},
        legend: {
            data: ['用户数']
        },
        xAxis: {
            data: ["一周", "两周", "三周", "四周", "五周", "六周"]
        },
        yAxis: {},
        series: [{
            name: 'cout',
            type: 'bar',
            data: []
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        type: "post",
        url: "${pageContext.request.contextPath}/user/queryCount",
        dataType: "json",
        success: function (data) {
            myChart.setOption({
                series: [{
                    name: '',
                    data: data
                }]
            })
        }
    })
</script>
</html>