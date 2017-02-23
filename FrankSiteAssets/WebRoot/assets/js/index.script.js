$(document).ready(function() {
	
	$('#index').addClass('am-active')
	
	$('.am-link-article-detail').click(function() {
		var id = $(this).attr('target-id')
		
		window.location.href = 'page/article/' + id
	})
	
	
	
//	$('#am-link-logout').click(function() {
//		var ref = $(this).attr('ref')
//		var form = $('<form></form>')
//		var usernameInput = $('<input></input>')
//		form.attr('method', 'post')
//		form.attr('action', 'account/logout?ref=' + ref)
//		usernameInput.attr('name', 'username')
//		usernameInput.val($('#username-hidden').val())
//		usernameInput.appendTo(form)
//		form.submit()
//	})
})
