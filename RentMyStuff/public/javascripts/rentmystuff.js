function popup(url) {

	if (url == '') {
		this.close();
	} else {
		fenster = window.open(url, "Inserat erstellen",
				"width=400,height=350, resizable=yes");
		fenster.focus();
	}

	return false;
}

