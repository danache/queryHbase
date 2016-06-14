<!DOCTYPE html>
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="danache.demo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	width: 100%;
	height: 500px;
}

p {
	margin-left: 5px;
	font-size: 14px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=kAIoDvNSIzyZlBY8WjKgCbGFu7nkm5WY"></script>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script src="json2.js"></script>
<title>PM2.5地图查询</title>
</head>
<body>
	<div id="allmap"></div>

</body>
</html>
<script type="text/javascript">
	// 百度地图API功能	

	var xmlHttp;
	var filename;
	var url;
	var datainfo = [];
	var markers = []
	var opts = {
		width : 500, // 信息窗口宽度
		height : 120, // 信息窗口高度
		title : "站点名称", // 信息窗口标题
		enableMessage : true
	//设置允许信息窗发送短息
	};
	function createXMLHttp() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.417854, 39.921988), 12);
	map.enableScrollWheelZoom();
	timestamp = Math.round(new Date());
	ComplexCustomOverlay.prototype = new BMap.Overlay();
	getImage("beijing", timestamp);

	getCity();
	function ZoomControl(){
		  // 默认停靠位置和偏移量
		  this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
		  this.defaultOffset = new BMap.Size(40, 10);
		}

		// 通过JavaScript的prototype属性继承于BMap.Control
		ZoomControl.prototype = new BMap.Control();

		// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
		// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
		ZoomControl.prototype.initialize = function(map){
		 
		  // 设置样式
		  var newspan = document.createElement("img");//创建img对象
//设置img对象css样式
            newspan.style.position = "absolute";
            newspan.style.right=0;
            newspan.cssFloat='right';
             newspan.style.right=0;
            newspan.style.top="31px";
            newspan.style.cursor="pointer";
            newspan.src = 'color_bar_chinese.png';
         
		  // 绑定事件,点击一次放大两级
		  		  // 添加DOM元素到地图中
		  map.getContainer().appendChild(newspan);
		  // 将DOM元素返回
		  return newspan;
		}
		// 创建控件
		var myZoomCtrl = new ZoomControl();
		// 添加到地图当中
		map.addControl(myZoomCtrl);
	
	ComplexCustomOverlay.prototype.initialize = function(map) {
		this._map = map;
		var div = this._div = document.createElement("div");
		div.style.position = "absolute";
		div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
		div.style.backgroundColor = this._color;
		div.style.border = "1px solid " + this._color;
		div.style.color = "black";
		div.style.height = "18px";
		div.style.padding = "2px";
		div.style.lineHeight = "18px";
		div.style.whiteSpace = "nowrap";
		div.style.MozUserSelect = "none";
		div.style.fontSize = "14px"
		var span = this._span = document.createElement("span");
		div.appendChild(span);
		span.appendChild(document.createTextNode(this._text));
		var that = this;

		var arrow = this._arrow = document.createElement("div");
		arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
		arrow.style.position = "absolute";
		arrow.style.width = "11px";
		arrow.style.height = "10px";
		arrow.style.top = "22px";
		arrow.style.left = "10px";
		arrow.style.overflow = "hidden";
		div.appendChild(arrow);

		div.onmouseover = function() {
			this.style.backgroundColor = this._color;
			this.style.borderColor = this._color;
			this.getElementsByTagName("span")[0].innerHTML = that._overText;
			arrow.style.backgroundPosition = "0px -20px";
		}

		div.onmouseout = function() {
			this.style.backgroundColor = this._color;
			this.style.borderColor = this._color;
			this.getElementsByTagName("span")[0].innerHTML = that._text;
			arrow.style.backgroundPosition = "0px 0px";
		}

		map.getPanes().labelPane.appendChild(div);

		return div;
	}
	ComplexCustomOverlay.prototype.addEventListener = function(event, fun) {
		this._div['on' + event] = fun;
	}
	ComplexCustomOverlay.prototype.draw = function() {
		var map = this._map;
		var pixel = map.pointToOverlayPixel(this._point);
		this._div.style.left = pixel.x - parseInt(this._arrow.style.left)
				+ "px";
		this._div.style.top = pixel.y - 30 + "px";
	}

	for (var i = 0; i < datainfo.length; i++) {
		var index = i;
		var marker = new ComplexCustomOverlay(new BMap.Point(
				datainfo[index][4], datainfo[index][5]), datainfo[index][0],
				datainfo[index][0] + " " + datainfo[index][3], getColor(datainfo[index][3]));
		marker.x = datainfo[index][4];
		marker.y = datainfo[index][5];
		marker.gra = datainfo[index][2];
		marker.stanname = datainfo[index][0];
		markers.push(marker);

		map.addOverlay(marker);

	}
	for (var i = 0; i < markers.length; i++) {
		(function() {
			var index = i;
			markers[i].addEventListener('click', function() {
				getsgra(markers[index], markers[index].stanname,
						markers[index].gra, markers[index].x, markers[index].y)
				//this.openInfoWindow(new BMap.InfoWindow('我是第'+ (index +1) +'个标注'));
			});
		})();
	}
	function getColor(pm25){
		pm25 = parseInt(pm25);
		if (0 < pm25 && pm25 <= 50){
			return "#00FF00";
		}
		else if (50 < pm25 && pm25 <= 100){
			return "#FFFF00";
		}
		else if (100 < pm25 && pm25 <= 150){
			return "#DAA520";
		}
		else if (150 < pm25 && pm25 <= 200){
			return "#FFA0FF";
		}
		else if (200 < pm25 && pm25 <= 300){
			return "#9932CC";
		}
		else if (300 < pm25 ){
			return "#EEE8AA";
		}
		
		
	}

	function getsgra(e, stanname, gra, x, y) {
		var infoWindow = new BMap.InfoWindow(
				"<img style='float:right;margin:4px' id='imgDemo' src='"+gra+"' width='600 height='480' id='imgDemo' title='"+stanname+"'/>"
						+ "</div>"); // 创建信息窗口对象
		map.openInfoWindow(infoWindow, new BMap.Point(x, y));
		//图片加载完毕重绘infowindow
		/* document.getElementById('imgDemo').onload = function (){
		   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
		 }
		 */
	}
	function getImage(cityname, timestamp) {
		createXMLHttp();
		xmlHttp.open("POST", "queryServlet", false);
		//post方式需要自己设置http的请求头  
		xmlHttp.setRequestHeader("content-Type",
				"application/x-www-form-urlencoded");
		//post方式发送数据  
		xmlHttp.onreadystatechange = callback;
		var beginTime = timestamp - 60 * 60 * 24 * 1000;

		xmlHttp.send("city=" + cityname + "&beginDate=" + beginTime
				+ "&endDate=" + timestamp);
	}
	function callback() {
		//5.接收响应数据  
		//判断对象的状态 是交互完成  
		if (xmlHttp.readyState == 4) {
			//判断http的交互是否成功  
			if (xmlHttp.status == 200) {
				var res = xmlHttp.responseText;
				var jsonarray = [];
				jsonarray = JSON.parse(res);
				for (var i = 0; i < jsonarray.length; i++) {
					datainfo.push([ jsonarray[i][0].stanname,
							jsonarray[i][0].filename, jsonarray[i][0].graphURL,
							jsonarray[i][0].latest ]);
				}

			} else {
				alert("出错了");
			}
		}
	}

	function getCity() {
		createXMLHttp();
		xmlHttp.open("POST", "dataServlet", false);
		xmlHttp.onreadystatechange = getCityCallback;
		xmlHttp.send(null);

	}
	function getCityCallback() {
		if (xmlHttp.readyState == 4) {
			//判断http的交互是否成功  
			if (xmlHttp.status == 200) {
				var res = xmlHttp.responseText;
				var jsons = [];
				jsons = JSON.parse(res);
				for (var i = 0; i < jsons.length; i++) {
					for (var j = 0; j < datainfo.length; j++) {
						if (jsons[i][0].stanname == datainfo[j][0]) {
							datainfo[j].push(jsons[i][0].gpsLog);
							datainfo[j].push(jsons[i][0].gpsLat);
							break;
						}
					}
				}

			}
		}

	}
	// 复杂的自定义覆盖物
	function ComplexCustomOverlay(point, text, mouseoverText, color) {
		this._point = point;
		this._text = text;
		this._overText = mouseoverText;
		this._color = color;
	}
</script>
