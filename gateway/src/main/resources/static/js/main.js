var user = {};
$('#register').on('click', function () {

	const username = $("input[id='username']").val();
	const password = $("input[id='password']").val();
	if (username.length < 3 || password.length < 6) {
		alert('Имя пользователья должно состоять минимум из трех символов, а пароль - из шести');
		return;
	}
	if (username && password) {
		$.ajax({
			url: 'accounts/',
			datatype: 'json',
			type: 'post',
			contentType: 'application/json',
			data: JSON.stringify({
				username: username,
				password: password
			}),
			success: function (data) {

				requestOauthToken(username, password);
				initAccount(getCurrentAccount());

			},
			error: function (xhr, ajaxOptions, thrownError) {
				if (xhr.status === 400) {
					alert('Такое имя уже использовано')
				} else {
					alert('Возникла ошибка при создании профиля')
				}
			}
		});
	} else {
		alert('Заполните все поля');
	}
});

function getCurrentAccount() {
	var token = getOauthTokenFromStorage();
	let account = null;
	if (token) {
		$.ajax({
			url: 'accounts/current',
			datatype: 'json',
			type: 'get',
			headers: {'Authorization': 'Bearer ' + token},
			async: false,
			success: function (data) {
				account = data;
			},
			error: function () {
				removeOauthTokenFromStorage();
			}
		});
	}
	return account;
}

function initAccount(account) {
	user =
		new User(
			account.name,
			account.dateOfBirth,
			account.firstName,
			account.lastName,
			account.lastSeen,
			account.position,
			account.residence
		);
	user.lastSeen = new Date();
	$('#loginpage').hide();
	$('#firstName').val(user.firstName);
	$('#lastName').val(user.lastName);
	$('#dateOfBirth').val(user.dateOfBirth);
	$('#position').val(user.position);
	$('#residence').val(user.residence);
	$('#edit-profile').show();
}

class User {
	constructor(userName, dateOfBirth, firstName, lastName, lastSeen, position, residence) {
		this.userName = userName;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastSeen = lastSeen;
		this.position = position;
		this.residence = residence;
	}
}

$('#updateAccount').on('click', function () {

	$('#updateAccount')
		.removeClass('btn-outline-success')
		.addClass('btn-outline-primary')
		.value = 'Сохранение...';
	user.firstName = $('#firstName').val();
	user.lastName = $('#lastName').val();
	user.position = $('#position').val();
	user.dateOfBirth = $('#dateOfBirth').val();
	user.residence = $('#residence').val();
	updateAccount();
});

function updateAccount() {
	$.ajax({
		url: 'accounts/current',
		datatype: 'json',
		type: 'put',
		contentType: 'application/json',
		headers: {'Authorization': 'Bearer' + getOauthTokenFromStorage()},
		data: JSON.stringify({
			name: user.userName,
			lastSeen: new Date(),
			firstName: user.firstName,
			lastName: user.lastName,
			position: user.position,
			dateOfBirth: user.dateOfBirth,
			residence: user.residence
		}),
		success: function () {
			$('#updateAccount')
				.removeClass('btn-outline-primary')
				.addClass('btn-outline-success')
				.value = 'Сохранить'
		},
		error: function () {
			alert("Ошибка при сохранении данных, попробуйте позже");
		}
	})
}