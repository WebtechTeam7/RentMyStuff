// Eigene Angebote und Gesuche

// Variante mit AJAX Jquery

function ownAdvert2(){
	$(document).ready(function(){
		  $("btAngebote").click(function(){
		    $("#Angebote").load("ajaxEigeneAngebote.html");
		   });
		});
}

function ownAdvert2(){
	$(document).ready(function(){
		  $("btAngebote").click(function(){
		    $("#Angebote").load("@routes.Assets.at("app/views/ajaxEigeneAngebote.html")");
		  });
		});
}

// 2. Variante mit HTML

function OwnAdvert() {
	var requestObj = new XMLHttpRequest();
	requestObj.onreadystatechange = function() {
		if (requestObj.readyState == 4 && requestObj.status == 200) {
			document.getElementById("Angebote").innerHTML = requestObj.responseText;
			requestObj = null;
		}
	}
	requestObj.open("GET","ajaxEigeneAngebote.html", true);
	requestObj.send();
}


//


function ownSearch(){
	var g = document.getElementById("Elementauswählen").value;
	sendOwnSearch(g);
}
function sendOwnSearch(g) {
	var requestObj = new XMLHttpRequest();
	requestObj.onreadystatechange = function() {
		if (requestObj.readyState == 4 && requestObj.status == 200) {
			document.getElementById("output").innerHTML = requestObj.responseText;
			requestObj = null;
		}
	}
	requestObj.open("GET", "guess?g=" + g, true);
	requestObj.send();
}


// Kategorien

function category() {
	var g = document.getElementById("Elementauswählen").value;
	sendCategory(g);
}
function sendCategory(g) {
	var requestObj = new XMLHttpRequest();
	requestObj.onreadystatechange = function() {
		if (requestObj.readyState == 4 && requestObj.status == 200) {
			document.getElementById("output").innerHTML = requestObj.responseText;
			requestObj = null;
		}
	}
	requestObj.open("GET", "guess?g=" + g, true);
	requestObj.send();
}