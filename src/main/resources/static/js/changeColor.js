const selectElement = document.getElementById('product-select-color');
const inputElement = document.getElementById('add-cate');

selectElement.addEventListener('change', (event) => {
  inputElement.value = event.target.value;
});