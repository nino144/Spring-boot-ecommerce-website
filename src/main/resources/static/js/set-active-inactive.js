function changeState(state, id){  
    var currentPath = window.location.pathname;  
    window.location.href = currentPath + "/statechange?id=" + id + "&state=" + state;
    
}
