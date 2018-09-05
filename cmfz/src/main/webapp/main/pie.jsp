<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="../js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<html>
<div id="mainn" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    var myChart = echarts.init(document.getElementById('mainn'));
    option = {
        title: {
            text: 'cmfz用户分布统计',
            subtext: '纯属虚构',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data: []
            }
        ]
    };
    myChart.setOption(option)
    $.ajax({
        url: "${pageContext.request.contextPath}/user/queryCity.do",
        type: "post",
        dataType: "JSON",
        success: function (data) {
            myChart.setOption({
                series: [{
                    name: '男',
                    data: data.man
                }, {
                    name: '女',
                    data: data.women
                }]
            })
        }
    })
</script>
</html>