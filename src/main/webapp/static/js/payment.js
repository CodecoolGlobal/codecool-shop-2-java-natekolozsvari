function GetURLParameter() {
    let sPageURL = window.location.search.substring(1);
    let sURLVariables = sPageURL.split('&');
    for (let i = 0; i < sURLVariables.length; i++) {
        let sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === "order_id") {
            let inputField = document.querySelector('#orderId');
             inputField.value = sParameterName[1];
        }
    }
}

GetURLParameter();
