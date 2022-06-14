// Call the dataTables jQuery plugin
// $(document).ready(function() {
//   $('#dataTable').DataTable();
// });

$(document).ready(function() {
	$('#dataTable').DataTable({
		dom: 'Bfrtip',
		buttons: [
			'copyHtml5', 'csvHtml5', 'excelHtml5', {
				extend: 'pdfHtml5',
				download: 'open'
			}, 'print'
		],
		"language": {
			"decimal": "",
			"emptyTable": "Không có dữ liệu trong bảng",
			"info": "Hiển thị _START_ đến _END_ của _TOTAL_ bản ghi ",
			"infoEmpty": "Không bản ghi",
			"infoFiltered": "(Được lọc từ _MAX_ bản ghi)",
			"infoPostFix": "",
			"thousands": ",",
			"lengthMenu": "Số bản ghi hiển thị _MENU_",
			"loadingRecords": "Loading...",
			"processing": "Processing...",
			"search": "Tìm kiếm:",
			"zeroRecords": "Không bản ghi nào được tìm thấy",
			"paginate": {
				"first": "Đầu",
				"last": "Cuối",
				"next": ">>",
				"previous": "<<"
			},
			"aria": {
				"sortAscending": ": activate to sort column ascending",
				"sortDescending": ": activate to sort column descending"
			}
		},
	});
	$('#dataTable1').DataTable({
		dom: 'Bfrtip',
		buttons: [
			'copyHtml5', 'csvHtml5', 'excelHtml5', {
				extend: 'pdfHtml5',
				download: 'open'
			}, 'print'
		],
		"language": {
			"decimal": "",
			"emptyTable": "Không có dữ liệu trong bảng",
			"info": "Hiển thị _START_ đến _END_ của _TOTAL_ bản ghi ",
			"infoEmpty": "Không bản ghi",
			"infoFiltered": "(Được lọc từ _MAX_ bản ghi)",
			"infoPostFix": "",
			"thousands": ",",
			"lengthMenu": "Số bản ghi hiển thị _MENU_",
			"loadingRecords": "Loading...",
			"processing": "Processing...",
			"search": "Tìm kiếm:",
			"zeroRecords": "Không bản ghi nào được tìm thấy",
			"paginate": {
				"first": "Đầu",
				"last": "Cuối",
				"next": ">>",
				"previous": "<<"
			},
			"aria": {
				"sortAscending": ": activate to sort column ascending",
				"sortDescending": ": activate to sort column descending"
			}
		},
	});
});
