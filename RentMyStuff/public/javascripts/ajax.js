// Eigene Angebote und Gesuche

// Variante mit AJAX Jquery

/*function reloadAdverts(){
	$(document).ready(function(){
		  $("btAngebote").click(function(){
		    $("#Angebote").load("getAdvertList");
		   });
		});
}

/*function ownSearchTest(){
	$(document).ready(function(){
		  $("btAngebote").click(function(){
		    $("#Angebote").load("getAdvertList");
		   });
		});
}


/*function ownAdvert2(){
	$(document).ready(function(){
		  $("btAngebote").click(function(){
		    $("#Angebote").load("@routes.Assets.at("app/views/ajaxEigeneAngebote.html")");
		  });
		});
}*/

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