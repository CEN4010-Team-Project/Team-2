// ------------ bookDetails ------------

var Q_itemsInStock = $("#InStockOnly").get(0);
var Q_qty = $("#Qty");
var carQty = 0;
var Q_addToCart = $("#AddToCart").get(0);
var itemsInStock = parseInt("" + Q_itemsInStock.textContent);
var qty = 0;
var available = itemsInStock;


qty = Q_qty.get(0).value;

if ($("#CartQty").get(0)) {
    carQty = parseInt("" + $("#CartQty").get(0).textContent)
}

available = itemsInStock - carQty;

if (Q_itemsInStock) {
    if (available < 0) {
        $("#QtyWarning").get(0).disabled = false;
        Q_itemsInStock.textContent = 0;
    } else {
        $("#QtyWarning").get(0).disabled = true;
        Q_itemsInStock.textContent = available;
    }
}

Q_qty.change(function () {
    qty = Q_qty.get(0).value;
    addToCartButton(available, qty);
});

addToCartButton(available, qty);


function addToCartButton(available, qty) {
    Q_addToCart.disabled = ((available - qty) < 0);
}

