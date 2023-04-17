var quantity = document.querySelectorAll('[id^="quantity-input"]');
var paginationBox = document.querySelector('[id="pagination-box-input"]');



for(var i = 0; i < quantity.length; i++) {
	quantity[i].addEventListener('change', function() {
                changeQuantity(this.value,this.id);
	});
}

paginationBox.addEventListener('change', function() {
    changePage(this.value);
});

function changePage(value){  
    value -= 1;
    var currentPath = window.location.pathname;
    const params = new URLSearchParams(window.location.search);
    var query = params.get('page');
    
    if(params !== null){
        if(query == null){
            var newUrl = currentPath + "?page=" + value + "&" + params;
        }
        else{
            var url = currentPath  + params;
            var newUrl = url.replace(/page=\d+/, "?page=" + value);
        }      
        window.location.href = newUrl; 
    }
    else{
        window.location.href = currentPath + "?page=" + value;
    }
}

function changeQuantity(value, id){
    var item_id = id.substring(15);
    var currentPath = window.location.pathname;
    const params = new URLSearchParams(window.location.search);
    var query = params.get('page');
    
    if(query !== null){
        window.location.href = currentPath + "?page=" + query + "&item-id=" + item_id + "&quantity=" + value;
    }
    else{
        window.location.href = currentPath + "?item-id=" + item_id + "&quantity=" + value;
    }
}
