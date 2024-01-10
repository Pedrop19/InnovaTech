let selectCategoriasElement = document.getElementById("categorias");
let idProductoElement = document.querySelectorAll(".idProducto");
let linkProductoElement = document.querySelectorAll(".linkProducto");
console.log(JSON.stringify({
    accion: "filtrarProductosCategoria",
    categoria: selectCategoriasElement.value
}));


selectCategoriasElement.addEventListener("change", function () {
    fetch('/InnovaTech/AjaxController', {
        method: 'POST',
        body: JSON.stringify({
            accion: "filtrarProductosCategoria",
            categoria: selectCategoriasElement.value
        })
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                window.location.href = data.redirectUrl; 
            });
});

if(linkProductoElement != null || linkProductoElement != undefined){
    for (let i = 0; i < linkProductoElement.length; i++) {
        linkProductoElement[i].addEventListener("click", function (event) {
            event.preventDefault();
            fetch('/InnovaTech/AjaxController', {
                method: 'POST',
                body: JSON.stringify({
                    accion: "filtrarProductosID",
                    idProducto: idProductoElement[i].value
                })
            })
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);
                        window.location.href = data.redirectUrl; 
                    });
        });
    }
}