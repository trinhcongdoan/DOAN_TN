function addToCart(productid) {
	$.ajax({
		type: "POST",
		url: "/add-to-cart",
		data: {
			"productid": productid
		},
		success: function(data) {
			var obj = JSON.parse(data);
			$("#cart_quantity").html(obj.cart_quantity);
			$("#cart_total").html(obj.cart_total);
		},
		error: function(e) {
			alert('Bạn chưa đăng nhập');
		}
	});
};

function updateCart(productid) {
	var id = "_quantityCartItem" + productid;
	var quantity = $("#" + id).val();
	$.ajax({
		type: "POST",
		url: "/update-cart",
		data: {
			"productid": productid,
			"quantity": quantity
		},
		success: function(data) {
			location.reload();
		},
		error: function(e) {
			alert('Bạn chưa đăng nhập');
		}
	});
};

function deleteCart(productid) {
	$.ajax({
		type: "POST",
		url: "/delete-cart",
		data: {
			"productid": productid
		},
		success: function(data) {
			location.reload();
		},
		error: function(e) {
			alert('Bạn chưa đăng nhập');
		}
	});
};

$.ajax({
	type: "POST",
	url: "/api/cart-item",
	success: function(data) {
		var obj = JSON.parse(data);
		$("#cart_quantity").html(obj.cart_quantity);
		$("#cart_total").html(obj.cart_total.toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
	}
});

$("#filterProduct").click(function(e) {
	var min = $("#minamount").val();
	var max = $("#maxamount").val();
	/*alert(min + "-" + max);*/
	/*alert(window.location.pathname);*/
	window.location.href = window.location.pathname + "?start=" + min + "&end=" + max;
});

$("#star-5").click(function() {
	$("#ratings-hidden").val(5);
})
$("#star-4").click(function() {
	$("#ratings-hidden").val(4);
})
$("#star-3").click(function() {
	$("#ratings-hidden").val(3);
})
$("#star-2").click(function() {
	$("#ratings-hidden").val(2);
})
$("#star-1").click(function() {
	$("#ratings-hidden").val(1);
})

$("#selectProvince").change(function() {
	var id = $("#selectProvince").find('option:selected').data('id');
	/*alert(id);*/
	$.ajax({
		type: "GET",
		url: "/api/province",
		success: function(response) {
			var s = '<option value="0" data-display="Select">Chọn huyện</option>';
			$.each(response, function(key, value) {
				if (value.id == id) {
					$.each(value.district, function(key, value) {
						s += '<option value="' + value._name + '" data-id="' + value.id + '">' + value._name + '</option>';
					})
				}
			})
			$("#selectDistrict").html(s);
		},
		error: function(e) {
			alert('Failed!: ' + e);
		}
	});
});

$("#selectDistrict").change(function() {
	var id = $("#selectDistrict").find('option:selected').data('id');
	/*alert(id);*/
	$.ajax({
		type: "GET",
		url: "/api/province",
		success: function(response) {
			var s = '<option value="0" data-display="Select">Chọn xã</option>';
			$.each(response, function(key, value) {
				$.each(value.district, function(key_d, value_d) {
					if (value_d.id == id) {
						$.each(value_d.ward, function(key_w, value_w) {
							/*console.log(key_w + " - " + value_w._name);*/
							s += '<option value="' + value_w._name + '" data-id="' + value_w.id + '">' + value_w._name + '</option>';
						});
					}
				});

			});
			$("#selectWard").html(s);
		},
		error: function(e) {
			alert('Failed!: ' + e);
		}
	});
});
$('#owl-carousel').owlCarousel({
	loop: true,
	margin: 30,
	dots: false,
	nav: false,
	items: 2,
	autoplay: true,
	autoplayTimeout: 5000
})
$('#payment').click(function() {
	$('#paypal').prop('checked', false);
})
$('#paypal').click(function() {
	$('#payment').prop('checked', false);
})
function checkout() {
	if ($('#payment').is(':checked')) {
		return true;
	} else if ($('#paypal').is(':checked')) {
		return true;
	} else {
		alert("Chọn hình thức thanh toán");
		return false;
	}
}

var v1 = $("#last_total").html();
$.ajax({
	url: 'https://free.currconv.com/api/v7/convert?q=USD_VND&compact=ultra&apiKey=8ebcdc2dc425c438a6b8',
	dataType: 'jsonp',
	success: function(data) {
		var currency = data.USD_VND;
		var number = Number(v1.replace(/[^0-9.-]+/g, ""));
		console.log(currency + "/" + number);
		$("#price").val(number / currency);
	}
})
