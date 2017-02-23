/**
 * 
 */

$(document).ready(function(){
	$('#blog-title').on('click', function(){
		var articleTitle = $(this).text()
		$(this).parent().html('<input id="article-title-input" class="am-form-field am-article-title-input">')
		$('#article-title-input').attr('placeholder', articleTitle)
		$('#article-title-input').focus()
		jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/editor.script.js')
	})
	
	$('#article-title-input').on('blur', function(){
		var articleTitle = $(this).val()
		$(this).parent().html('<a href="javascript:;" id="blog-title"></a>')
		$('#blog-title').html(articleTitle)
		if (null == articleTitle || articleTitle.trim().length <= 0) {
			$('#blog-title').html("输入您文章的标题")
		}
		jQuery.getScript('http://static.jxufehelper.com/FrankSiteAssets/assets/js/editor.script.js')
	})
	
	$('#btn-publish').click(function() {
		
		if ($('#btn-publish').hasClass('am-disabled')) {
			return;
		}
		
		$('#btn-publish').addClass('am-disabled')
		
		var title 
		try {
			title = $('#blog-title').text().trim()
		}catch (e) {
			
		}
		
		
		var content = editor.getHTML()
		
		var txt = $(content).text()
		
		var obj = {
			typeid: parseInt($('#type-selection').val().trim()),
			title: $('#blog-title').text(),
			shortcut: txt.substring(0, 320),
		}
		
		$.ajax({
			type:"post",
			url:"article/publish",
			contentType: 'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({article: obj, content: content}),
			success: function(res) {
				$('#btn-publish').removeClass('am-disabled')
				if (res.status) {
					console.log('发表成功');
				} else {
					console.log(res.reason)
				}
			},
			error: function(err) {
				$('#btn-publish').removeClass('am-disabled')
			},
			async:true
		});
	})
	
})