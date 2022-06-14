function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			e.preventDefault();
			$('.image #image').attr('src', e.target.result).width(270);
			console.log($('#image').attr('src'));
		};
		reader.readAsDataURL(input.files[0]);
	}
}
function readURLs(input) {
	if (input.files && input.files[0]) {
		$('.product_images').html("");
		for (var i = 0; i < input.files.length; i++) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = $("<img />");
				img.attr("src", e.target.result).width(50).attr("style", "padding-right:10px;width:50px;");
				e.preventDefault();
				$('.product_images').append(img);
			};
			reader.readAsDataURL(input.files[i]);
		}

	}
}
CKEDITOR.replace('editor');
CKEDITOR.replace('descriptionShort');

$(".show-btn").click(function() {
	if ($(".input-password").attr("type") == "password") {
		$(".input-password").attr("type", "text");

	} else {
		$(".input-password").attr("type", "password");
	}
});
function matchpass() {
	var firstpassword = $("#inputUserNewPassword").val();
	var secondpassword = $("#inputUserR-NewPassword").val();

	if (firstpassword == secondpassword) {
		return true;
	} else {
		alert("Nhập lại mật khẩu k đúng!");
		return false;
	}
}

$("#selectProvince").change(function() {
	var id = $("#selectProvince").find('option:selected').data('id');
	/*alert(id);*/
	$.ajax({
		type: "GET",
		url: "/api/province",
		success: function(response) {
			var s = '<option value="" data-display="Select">Chọn huyện</option>';
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
			var s = '<option value="" data-display="Select">Chọn xã</option>';
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

(function () {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })
})()

$('#inputDiscountProduct').bootstrapDualListbox({
  preserveSelectionOnMove: 'moved',
  moveOnSelect: true,
  filterTextClear: 'Tất cả',
  infoTextEmpty: 'Danh sách trống',
  infoText: 'F{0}',
});
