$(window).on('load', function () {
	const account = getCurrentAccount();
	if (account) {
		initAccount(account);
	} else {
		$('#username').focus();
		$('#edit-profile').hide();
	}
});