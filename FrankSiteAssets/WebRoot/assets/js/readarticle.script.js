/**
 * 
 */

$(document).ready(function(){
	
	var target_id
	// 回复
	$('.am-link-reply').on('click', function() {
		var nickname = $(this).parent().parent().find('.am-comment-author').text()
		$('#nickname').val('@' + nickname)
		$('#nickname').attr('disabled', '')
		target_id  = $(this).parent().parent().attr('target-id')
		$('#editormd').scrollTo('#editormd', 100)
		mScroll('nickname')
		editor.focus()
	})
	
	// 删除评论
	$('.am-link-del').click(function(){
		var id = $(this).parent().parent().attr('target-id')
		var $form = $('<form></form>')
		var $commentidInput = $('<input></input>')
		$commentidInput.attr('name', 'commentid')
		$commentidInput.val(id)
		$commentidInput.appendTo($form)
		$form.attr('method', 'post')
		$form.attr('action', 'article/deleteComment')
		$form.submit()
	})
	
	$('#index').addClass('am-active')
	
	$('pre').addClass('highlight-code')
	
	$('#link-comment').click(function() {
		$('#nickname').removeAttr('disabled')
		$('#nickname').val('')
		$('#nickname').focus()
		$('#nickname').scrollTo('#nickname', 100)
	})
	
	$('#btn-submit').on('click', function() {
		// 评论
		// 获取内容
		var content = editor.getHTML()
		var nickname = $('#nickname').val()
		if(null != nickname) {
			nickname = nickname.trim()
		} else {
			$('#nickname').focus()
			return;
		}
		var articleid = $('.am-article').attr('target-id')

		var $form = $('<form></form>')
		var $contentInput = $('<input></input>')
		$contentInput.attr('name', 'content')
		$contentInput.val(content)
		var $nicknameInput = $('<input></input>')
		$nicknameInput.attr('name', 'nickname')
		$nicknameInput.val(nickname)
		var $articleIdInput = $('<input></inpu>')
		$articleIdInput.attr('name', 'articleid')
		$articleIdInput.val(articleid)
		$articleIdInput.appendTo($form)
		$contentInput.appendTo($form)
		$nicknameInput.appendTo($form)
		$form.attr('method', 'post')
		
		if(typeof($('#nickname').attr('disabled')) == 'undefined') {
			$form.attr('action', 'article/comment')
		} else {
			// 回复
			var $targetInput = $('<input></input>')
			$targetInput.attr('name', 'parentcomment')
			$targetInput.val(target_id)
			$targetInput.appendTo($form)
			$form.attr('action', 'article/reply')
		}

		$form.submit()
	})
	
	
	function mScroll(id) {
	$("html,body").stop(true);
	$("html,body").animate({
		scrollTop: $("#" + id).offset().top
	}, 100);
}
})