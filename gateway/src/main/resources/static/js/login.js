function login() {
	const username = $("input[id='username']").val();
	const password = $("input[id='password']").val();

	if (requestOauthToken(username, password)) {
		initAccount(getCurrentAccount());
	} else {
		$('#username').focus();
		alert("Проверьте правильность ввода")
	}
}

$('#logout').click(function () {
	logout();
});
$('#signIn').click(function () {
	login();
});

function logout() {
	removeOauthTokenFromStorage();
	location.reload();
}