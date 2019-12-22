function requestOauthToken(username, password) {
	var success = false;
	$.ajax({
		url: 'uaa/oauth/token',
		datatype: 'json',
		type: 'post',
		headers: {'Authorization': 'Basic YnJvd3Nlcjo='},
		async: false,
		data: {
			scope: 'ui',
			username: username,
			password: password,
			grant_type: 'password'
		},
		success: function (data) {
			localStorage.setItem('token', data['access_token']);
			console.log(data.access_token);
			success = true;
		},
		error: function () {
			removeOauthTokenFromStorage();
		}
	});
}

function getOauthTokenFromStorage() {
	return localStorage.getItem('token');
}

function removeOauthTokenFromStorage() {
	return localStorage.removeItem('token');
}