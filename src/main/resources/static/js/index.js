function showAndClose(ChooseID) {
  var select = document.getElementById(ChooseID);
  if (select.style.display === "none") {
    select.style.display = "block";
  } else {
    select.style.display = "none";
  }
}

function showAndCloseNavBar() {
  var panelWraps = document.querySelector('.wrap');
  panelWraps.style.transition = ".5s";
  let temp = panelWraps.style.transform;
  let character = temp.charAt(12);
  if (character == "1"){
    panelWraps.style.transform ="translateX(-236%)";
  }   
  else{
    panelWraps.style.transform ="translateX(-118%)";
  }
}
