var roleSelect2 = document.querySelectorAll('[id="select-role"]');

for(var i = 0; i < roleSelect2.length; i++) {
	  roleSelect2[i].addEventListener("change", (event) => {
    const selectedOptionValue = event.target.value;;
    var currentPath = window.location.pathname;
  
    window.location.href = currentPath + selectedOptionValue;
  });
}

