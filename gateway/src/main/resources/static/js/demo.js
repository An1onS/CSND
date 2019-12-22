$('#demo').click(function () {
	$.ajax({
		url: 'accounts/demo',
		datatype: 'json',
		type: 'get',
		async: false,
		success: function (data) {
			initAccount(data);
		},
		error: function () {
			alert("Произошла ошибка, попробуйте ещё раз")
		}
	});
});