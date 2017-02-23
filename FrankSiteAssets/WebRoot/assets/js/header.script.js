$(document).ready(function () {
	$('#index').click(function () {
		window.location.href = '.'
	})
	
	$('#types').click(function () {
		window.location.href = 'page/types'
	})
	
	$('#link-settings').click(function () {
		window.location.href = 'page/settings'
	})
	
	$('#link-articles-all').click(function () {
		window.location.href = 'page/allarticles'
	})
	
	$('#link-publish-atricles').click(function() {
		window.open('page/publish')
	})
	
	$('.article-read-link').click(function () {
		var id = $(this).attr('data-target-id')
		window.location.href = 'page/article/' + id
		
	})
	
	$('#btn-search').click(function() {
		
		var keyword = $('#input-keyword').val()
		
		var $form = $('<form></form>')
		$form.attr('action', 'article/searchKeyword')
		$form.attr('method', 'post')
		
		var $keywordInput = $('<input></input>')
		$keywordInput.attr('name', 'keyword')
		$keywordInput.val(keyword)
		
		$keywordInput.appendTo($form)
		
		$form.submit()
	})
	
	$('#am-link-logout').click(function() {
		var ref = $(this).attr('ref')
		var form = $('<form></form>')
		var usernameInput = $('<input></input>')
		form.attr('method', 'post')
		form.attr('action', 'account/logout?ref=' + ref)
		usernameInput.attr('name', 'username')
		usernameInput.val($('#username-hidden').val())
		usernameInput.appendTo(form)
		form.submit()
	})
	
})
