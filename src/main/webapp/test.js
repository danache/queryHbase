var xmlHttp = false;
try {
	xmlHttp = new XMLHttpRequest();
} catch (trymicrosoft) {
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (othermicrosoft) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (failed) {

		}
	}
}

function callServer(smallLei) {
	var url = "<%=request.getContextPath()%>/SmallLei?lei=" + smallLei;
	xmlHttp.open("get", url, "true");
	xmlHttp.onreadystatechange = upsmlei;
	xmlHttp.send(null);
}

function upsmlei() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			var smlei = document.getElementByIdx_x("smallLei");
			smlei.options.length = 0;
			var arr = xmlHttp.responseText.split("|");
			for (var i = 0; i < arr.length - 1; i++) {
				smlei.options.add(new Option(arr[i], arr[i]));
			}
		}
	}
}
