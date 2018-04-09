// ------------ bookDetails ------------
//jquery vars
var Q_itemsInStock = $("#InStockOnly").get(0);
var Q_qty = $("#Qty");
var Q_carQty = $("#CartQty").get(0);
var Q_addToCart = $("#AddToCart").get(0);
var Q_description = $("#Description");
var Q_otherBooks = $("#OtherBooks");
var Q_warningQty = $("#QtyWarning").get(0);
var Q_authorName = $("#AuthorName");

//regular vars with initialization
var itemsInStock = (Q_itemsInStock) ? parseInt("" + Q_itemsInStock.textContent) : 0;
var carQty = (Q_carQty) ? (parseInt("" + Q_carQty.textContent)) : 0;
var qty = (Q_qty.get(0)) ? (Q_qty.get(0).value) : 0;
var available = itemsInStock;

available = itemsInStock - carQty;


/* change value of available items
and enable/disable Add to Cart button*/

if (Q_itemsInStock) {
    if (Q_warningQty) {
        Q_warningQty.disabled = !(available < 0);
    }
    Q_itemsInStock.textContent = (available < 0) ? 0 : available;
}

Q_qty.change(function () {
    qty = Q_qty.get(0).value;
    addToCartButton(available, qty);
});

addToCartButton(available, qty);

/* List of books by */
if (Q_authorName && Q_description && Q_otherBooks) {
    clickNHide(Q_authorName, Q_description);
    clickNShow(Q_authorName, Q_otherBooks)
}
if ($('#myTabs a')) {
    $('#myTabs a:first').tab('show') // Select first tab
    $('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    });

    Q_authorName.click(function () {
        $('#author-tab').trigger('click');
    })
}

//function definitions
function addToCartButton(available, qty) {
    Q_addToCart.disabled = ((available - qty) < 0);
}

function clickNHide(queryClicked, queryHidden) {
    queryClicked.click(function () {
        queryHidden.hide();
    });
}

function clickNShow(queryClicked, queryShown) {
    queryClicked.click(function () {
        queryShown.show();
    });
}

