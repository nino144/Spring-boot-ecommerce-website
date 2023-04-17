function changeTransport(status, id){  
    if(status != 'COMPLETED'){
        var currentPath = window.location.pathname;  
        window.location.href = currentPath + "/transport?id=" + id;
    }
    
}
