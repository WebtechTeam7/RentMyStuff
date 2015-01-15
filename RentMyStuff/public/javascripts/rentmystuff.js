function popup(url) {

		fenster = window.open(url, "Inserat erstellen",
				"width=400,height=400, resizable=yes");
		fenster.focus();
	
	return false;
}


//get the address values
var city = getArg('city');
var country = getArg('country');
var postcode = getArg('postcode');
var street = getArg('street');
var address = street + ' ' + city + ' ' + postcode + ' ' + country;
function getArg(name) {
	
	var args = new Object();
	var query = location.search.substring(1);
	var pairs = query.split("&");
	var length = pairs.length-1;
	
	for ( var i = 0; i <= length; i++) {
		var pos = pairs[i].indexOf('=');

		if (pos == -1) {
			continue;
		}

		var argname = pairs[i].substring(0, pos);
		var value = pairs[i].substring(pos + 1);
		args[argname] = value;
	}

	args["NB_OF_ARGS"] = length;

	switch (name) {
	case "ALL_ARGS":
		return args;
		break;
	default:
		if (args[name]) {
			return args[name];
		} else {
			return null;
		}
		break;
	}
}


