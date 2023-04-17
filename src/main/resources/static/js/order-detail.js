const totalSelect = document.getElementById("total");

totalSelect.addEventListener("change", () => {
  const selectedOptionValue = totalSelect.value;
  var currentPath = window.location.pathname;

  window.location.href = currentPath + selectedOptionValue;
});

