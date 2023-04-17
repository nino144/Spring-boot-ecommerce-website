function enable_product() {
	var mySelect = document.querySelectorAll('[id^="product-select"]');
	for (let i = 0; i < mySelect.length; i++) {
  		mySelect[i].disabled = false;
	}

	var mySelect = document.querySelectorAll('[id^="product-price"]');
	for (let i = 0; i < mySelect.length; i++) {
  		mySelect[i].removeAttribute('readOnly');
		mySelect[i].style.backgroundColor = "white";
	}

	var inputSelect = document.getElementsByClassName('edit');
	for (var i = 0; i < inputSelect.length; ++i) {
   		inputSelect[i].removeAttribute('readOnly');
   		inputSelect[i].style.backgroundColor = "white";
	}
}

function enable_account() {
	var nodeList = document.querySelectorAll('[id^="account"]:not([id="account-content"])');
	for (let i = 0; i < nodeList.length; i++) {
  		nodeList[i].removeAttribute('readOnly');
  		nodeList[i].style.backgroundColor="white";
	}
        document.getElementById("male").disabled = false;
        document.getElementById("female").disabled = false;
}