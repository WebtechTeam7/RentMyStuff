// 1. Variante mit AJAX Jquery

/*function reloadAdverts(){
	$(document).ready(function(){
		  $("Angebote").click(function(){
		    $("#Angebote").load("getAdvertList");
		   });
		});
}

*/
// 2. Variante mit HTML

function reloadAdverts() {
	var requestObj = new XMLHttpRequest();
	requestObj.onreadystatechange = function() {
		if (requestObj.readyState == 4 && requestObj.status == 200) {
			document.getElementById("Angebote").innerHTML = requestObj.responseText;
			requestObj = null;
		}
	}
	requestObj.open("GET","getAdvertList", true);
	requestObj.send();
}

function reloadSearch() {
	var requestObj = new XMLHttpRequest();
	requestObj.onreadystatechange = function() {
		if (requestObj.readyState == 4 && requestObj.status == 200) {
			document.getElementById("Gesuche").innerHTML = requestObj.responseText;
			requestObj = null;
		}
	}
	requestObj.open("GET","getSearchList", true);
	requestObj.send();
}