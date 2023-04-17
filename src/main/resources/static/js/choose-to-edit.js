var buttons = document.querySelectorAll('[id="edit-button"]');
var oValue = document.querySelectorAll('[id="cate-value"]');
var IdValue = document.querySelectorAll('[id="id-value"]');
var nValue = document.getElementById("edit-cate");
var brandId = document.getElementById("hidden-brandId");

for(var i = 0; i < buttons.length; i++) {
	buttons[i].addEventListener('click', function() {
	    var buttonsIndex = Array.from(buttons).indexOf(this);
	    nValue.value = oValue[buttonsIndex].innerHTML;
	    nValue.style.backgroundColor = "white";
		nValue.removeAttribute('readOnly');
		brandId.value = IdValue[buttonsIndex].innerHTML;
	});
}


