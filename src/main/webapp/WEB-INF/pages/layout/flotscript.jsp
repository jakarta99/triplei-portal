<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<script type="text/javascript" src="/resources/flot/jquery-1.8.3.min.js"></script>
<!-- [if lte IE 8]><script language="javascript" type="text/javascript" src="/js/flot/excanvas.min.js"></script><![endif] -->

<script type="text/javascript" src="/resources/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="/resources/flot/jquery.flot.time.js"></script>

<script type="text/javascript" src="/resources/flot/jshashtable-2.1.js"></script>
<script type="text/javascript"
	src="/resources/flot/jquery.numberformatter-1.2.3.min.js"></script>
<script type="text/javascript"
	src="/resources/flot/jquery.flot.symbol.js"></script>

<script type="text/javascript"
	src="/resources/flot/jquery.flot.axislabels.js"></script>




<script>





		var rawData = [];
		var ticks = [];

var numbercollection = new Array(${fn:length(models)});
var iiii = 0;
<c:forEach items="${models}" var="model">
numbercollection[iiii]=[${model.persistencyRatio}];
iiii++;
</c:forEach>
// window.document.write(${models});

var namecollection = new Array(${fn:length(models)});
var xxxx = 0;
<c:forEach items="${models}" var="model">
namecollection[xxxx]=["${model.shortName}"];
xxxx++;
</c:forEach>
// window.document.write(namecollection)

function DoToggling() {

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([numbercollection[position],yy]);
					ticks.push([yy,namecollection[position]]);
					yy++;
				}
			})
			$('#flot-placeholder').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		


var dataSet = [
    { label: "保單繼續率", data: rawData, color: "#5E5E5E" }
];



var options = {
    series: {
    	bars: {
            show: true,
            dataLabels: true
        }
    },
    bars: {
    	show: true,
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
        color:"black",
    },
    legend: {
        noColumns: 0,
        labelBoxBorderColor: "white",
        position: "ne",
        sorted:"reverse", 
    },
    grid: {
        borderWidth: 0,      
    },
    valueLabels: {
        show: true
       }
};

$(document).ready(function () {
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



</script>