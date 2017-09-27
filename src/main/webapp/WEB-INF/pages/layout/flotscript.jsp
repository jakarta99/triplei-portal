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
//----------------------抓y軸數值陣列head
var bisratiocollection = new Array(${fn:length(models)});//資本適足率
var bisra = 0;
<c:forEach items="${models}" var="model">
bisratiocollection[bisra]=[${model.bisRatio}];
bisra++;
</c:forEach>

var returnonAssetscollection = new Array(${fn:length(models)});//資產報酬率
var retur = 0;
<c:forEach items="${models}" var="model">
returnonAssetscollection[retur]=[${model.returnonAssets}];
retur++;
</c:forEach>

var persistencyRatiocollection = new Array(${fn:length(models)});//保單繼續率
var persis = 0;
<c:forEach items="${models}" var="model">
persistencyRatiocollection[persis]=[${model.persistencyRatio}];
persis++;
</c:forEach>

var litigationRatiocollection = new Array(${fn:length(models)});//訴訟率
var litig = 0;
<c:forEach items="${models}" var="model">
litigationRatiocollection[litig]=[${model.litigationRatio}];
litig++;
</c:forEach>

var appealRatiocollection = new Array(${fn:length(models)});//申訴率
var appeal = 0;
<c:forEach items="${models}" var="model">
appealRatiocollection[appeal]=[${model.litigationRatio}];
appeal++;
</c:forEach>


//---------------------抓y軸數值陣列foot
var namecollection = new Array(${fn:length(models)});//抓出所有model的公司名稱
var xxxx = 0;
<c:forEach items="${models}" var="model">
namecollection[xxxx]=["${model.shortName}"];
xxxx++;
</c:forEach>


//---------------------------------------五種方法head
function Dobisratioo() {//資本適足率

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {//依序抓出所有checkbox，重設資料串
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([bisratiocollection[position],yy]);//rowdata push
					ticks.push([yy,namecollection[position]]);//ticks push
					yy++;
				}
			})
			$('#flot-bisratioo').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		
function DoreturnonAssetss() {//資產報酬率

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {//依序抓出所有checkbox，重設資料串
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([returnonAssetscollection[position],yy]);//rowdata push
					ticks.push([yy,namecollection[position]]);//ticks push
					yy++;
				}
			})
			$('#flot-returnonAssetss').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		
function DopersistencyRatioo() {//保單繼續率

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {//依序抓出所有checkbox，重設資料串
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([persistencyRatiocollection[position],yy]);//rowdata push
					ticks.push([yy,namecollection[position]]);//ticks push
					yy++;
				}
			})
			$('#flot-placeholder').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		
function DolitigationRatioo() {//訴訟率

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {//依序抓出所有checkbox，重設資料串
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([litigationRatiocollection[position],yy]);//rowdata push
					ticks.push([yy,namecollection[position]]);//ticks push
					yy++;
				}
			})
			$('#flot-litigationRatioo').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		
function DoappealRatioo() {//申訴率

	rawData.length = 0;
	ticks.length = 0;
			
			var yy = 0;
			$("#insurerfilter").find("input[id!='checkall']").each(function() {//依序抓出所有checkbox，重設資料串
				if ($(this).is(":checked")) {

					var position = $(this).attr("id");
					position = (position).substr(3) - 1;
					rawData.push([appealRatiocollection[position],yy]);//rowdata push
					ticks.push([yy,namecollection[position]]);//ticks push
					yy++;
				}
			})
			$('#flot-appealRatioo').css("height",(rawData.length*4)+"vh");
// 			$.plot($("#flot-placeholder"), dataSet, options);
		}
		

		
		
//----------------------------------------五種方法foot

var dataSet = [
    { label: "保單繼續率", data: rawData, color: "#5E5E5E" }
];


//----------------------------------------五種options head

var optionbisRatio = {//資本適足率
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
	        max: 16,min:-4,
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
	
var optionreturnonAssets = {//資產報酬率
		series: {
	    	bars: {
	            show: true,
	            dataLabels: true
	        }
	    },
	    bars: {
	    	show: true,
	    	align: "center",
	        barWidth: 0.2,
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
	        max: 0.05,min:-0.05,
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

var optionpersistencyRatio = {//保單繼續率
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
        max: 1.3,
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
	
var optionlitigationRatio = {//訴訟率
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
	        max: 0.0013,
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
	
var optionappealRatio = {//申訴率
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
	        max: 0.0013,
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


//----------------------------------------五種options foot
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