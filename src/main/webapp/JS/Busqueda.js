let selectCategoriasElement = document.getElementById("categorias");

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