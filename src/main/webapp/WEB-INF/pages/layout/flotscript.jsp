<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<!-- <script type="text/javascript" src="/resources/flot/jquery-1.8.3.min.js"></script> -->
<!-- [if lte IE 8]><script language="javascript" type="text/javascript" src="/js/flot/excanvas.min.js"></script><![endif] -->
<script type="text/javascript" src="/resources/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.time.js"></script>    
<!-- <script type="text/javascript" src="/resources/flot/jshashtable-2.1.js"></script>     -->
<!-- <script type="text/javascript" src="/resources/flot/jquery.numberformatter-1.2.3.min.js"></script> -->
<script type="text/javascript" src="/resources/flot/jquery.flot.symbol.js"></script>
<!-- <script type="text/javascript" src="/resources/flot/jquery.flot.axislabels.js"></script> -->




<script>
var rawData = new Array(${fn:length(models)});
var i = 0;
<c:forEach items="${models}" var="model">
rawData[i]=[${model.persistencyRatio},i];
i++;
</c:forEach>



var ticks = new Array(${fn:length(models)});
var x = 0;
<c:forEach items="${models}" var="model">
ticks[x]=[x,"${model.shortName}"];
x++;
</c:forEach>


var dataSet = [
    { label: "保單繼續率", data: rawData, color: "#5E5E5E" }
];



var options = {
    series: {
        bars: {
            show: true
        }
    },
    bars: {
        align: "center",
        barWidth: 0.3,
        horizontal: true,
        fillColor: { colors: [{ opacity: 0.5 }, { opacity: 1}] },
        lineWidth: 1
    },
    xaxis: {
        axisLabel: "Price (USD/oz)",
        axisLabelUseCanvas: true,
        axisLabelFontSizePixels: 12,
        axisLabelFontFamily: 'Verdana, Arial',
        axisLabelPadding: 10,
        max: 1,
        tickColor: "#FAF7F7",                        
        color:"black"
    },
    yaxis: {
        axisLabel: "Precious Metals",
        axisLabelUseCanvas: true,
        axisLabelFontSizePixels: 12,
        axisLabelFontFamily: 'Verdana, Arial',
        axisLabelPadding: 3,
        tickColor: "#FAF7F7",        
        ticks: ticks, 
        color:"black"
    },
    legend: {
        noColumns: 0,
        labelBoxBorderColor: "white",
        position: "ne"
    },
    grid: {
        hoverable: true,  
        borderWidth: 0,      
    }
};

$(document).ready(function () {
    $.plot($("#flot-placeholder"), dataSet, options);    
    $("#flot-placeholder").UseTooltip();
});



var previousPoint = null, previousLabel = null;

$.fn.UseTooltip = function () {
    $(this).bind("plothover", function (event, pos, item) {
        if (item) {
            if ((previousLabel != item.series.label) || 
                 (previousPoint != item.dataIndex)) {
                previousPoint = item.dataIndex;
                previousLabel = item.series.label;
                $("#tooltip").remove();

                var x = item.datapoint[0];
                var y = item.datapoint[1];

                var color = item.series.color;
                //alert(color)
                //console.log(item.series.xaxis.ticks[x].label);                
                
                showTooltip(item.pageX,
                        item.pageY,
                        color);                
            }
        } else {
            $("#tooltip").remove();
            previousPoint = null;
        }
    });
};

function showTooltip(x, y, color, contents) {
    $('<div id="tooltip">' + contents + '</div>').css({
        position: 'absolute',
        display: 'none',
        top: y - 10,
        left: x + 10,
        border: '2px solid ' + color,
        padding: '3px',
        'font-size': '9px',
        'border-radius': '5px',
        'background-color': '#fff',
        'font-family': 'Verdana, Arial, Helvetica, Tahoma, sans-serif',
        opacity: 0.9
    }).appendTo("body").fadeIn(200);
}


</script>