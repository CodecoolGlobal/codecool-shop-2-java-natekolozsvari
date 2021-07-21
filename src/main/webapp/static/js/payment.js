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

function initCardNumberSplit() {
    let CardNumInput = document.getElementById('cNum');
    CardNumInput.addEventListener('input', function (e) {
        let foo = CardNumInput.value.split("-").join("");
        foo = foo.replace(/\D/g,'');
        if (foo.length > 0) {
            foo = foo.match(new RegExp('.{1,4}', 'g')).join("-");
        }
        CardNumInput.value = foo;
    });
}

function initExpirationDateSplit() {
    let ExpDateInput = document.getElementById('expDate');
    ExpDateInput.addEventListener('input', function (e) {
        let foo = ExpDateInput.value.split("/").join("");
        foo = foo.replace(/\D/g,'');
        if (foo.length > 0) {
            foo = foo.match(new RegExp('.{1,2}', 'g')).join("/");
        }
        ExpDateInput.value = foo;
    });
}

GetURLParameter();
initCardNumberSplit();
initExpirationDateSplit();
