// ------------ bookDetails ------------
//jquery vars
var Q_itemsInStock = $("#InStockOnly").get(0);
var Q_qty = $("#Qty");
var Q_carQty = $("#CartQty").get(0);
var Q_addToCart = $("#AddToCart").get(0);
var Q_warningQty = $("#QtyWarning").get(0);
var Q_authorName = $("#AuthorName");
var Q_rateLink = $("#RateCommentArea");
var Q_ratingStars = $("#RatingStars");

//var Q_rateCommentAttr = $("#RateCommentLink[aria-expanded]").val($("#RateCommentLink[aria-expanded]").attr("aria-expanded")).get(0);

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

if (Q_addToCart && Q_qty) {
    Q_qty.change(function () {
        qty = Q_qty.get(0).value;
        addToCartButton(available, qty);
    });

    addToCartButton(available, qty);
}


/* navigate through different tabs*/
if ($('#myTabs a')) {
    $('#myTabs a:first').tab('show') // Select first tab
    $('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')git
    });
    if (Q_authorName) {
        clickThenClick(Q_authorName, $('#author-tab'), Q_ratingStars.get(0));
    }
}


//Enable the star rating system
if (Q_ratingStars.get(0)) {
    if (Q_rateLink.get(0)) {
        Q_ratingStars.get(0).disabled = false;
    } else {
        Q_ratingStars.get(0).disabled = true;
    }
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

function clickNShow(queryClicked, queryShown, pov) {
    queryClicked.click(function () {
        queryShown.show();
        queryShown.each(function () {
            $('html, body').animate({
                scrollTop: $(pov).offset().top
            }, 400);
        });

    });
}

function clickThenClick(fakeClick, reallyClick, pov) {
    fakeClick.click(function () {
        reallyClick.trigger('click');

        reallyClick.each(function () {
            $('html, body').animate({
                scrollTop: $(pov).offset().top
            }, 400);
        });
    });
}


