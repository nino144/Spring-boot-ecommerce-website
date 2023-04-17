const priceSelect = document.getElementById("price-select");

priceSelect.addEventListener("change", () => {
  const selectedOptionValue = priceSelect.value;
  var currentPath = window.location.pathname;

  window.location.href = currentPath + selectedOptionValue;
});

