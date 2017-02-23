/**
 * 
 */

$(document).ready(function () {
	
	$('#blog-slogan').on('change', function () { 
		$(this).parent().parent().removeClass('am-form-success')
		$(this).parent().parent().removeClass('am-form-error')
		var slogan = $(this).val()
		var blog = $('#blog').val()
		var $this = $(this)
		$.ajax({
			type: 'post',
			url: 'blog/updateBlogSolgan',
			contentType: 'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({blogid: blog, slogan: slogan}),
			success: function(res) {
				if (res.status) {
					$this.parent().parent().addClass('am-form-success')
				} else {
					$this.parent().parent().addClass('am-form-error')
				}
				window.location.href = window.location.href
			},
			error: function(err) {
				console.log(err)
			}
		})
		
	})
	
	$('#max-capacity').on('change', function() {
		
		$(this).parent().parent().removeClass('am-form-success')
		$(this).parent().parent().removeClass('am-form-error')
		var capacity = $(this).val()
		var blog = $('#blog').val()
		var $this = $(this)
		
		$.ajax({
			type: 'post',
			url: 'blog/updateIndexMax',
			contentType: 'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({blogid: blog, indexmax: capacity}),
			success: function(res) {
				if (res.status) {
					$this.parent().parent().addClass('am-form-success')
				} else {
					$this.parent().parent().addClass('am-form-error')
				}
				window.location.href = window.location.href
			},
			error: function(err) {
				console.log(err)
			}
		})
	})
	
	$('#btn-add-type').click(function() {
		
		var typename = $('#type-name').val()
		var note = $('#type-note').val()
		
		var $this = $(this)
		$('#modal-submitting').find('#am-modal-hd').text('正在添加…')
		$('#modal-submitting').modal('open')
		$.ajax({
			type: 'post',
			url: 'blog/addType',
			contentType: 'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({title: typename, note: note, visiable: true}),
			success: function(res) {
				$('#modal-submitting').modal('close')
				window.location.href = window.location.href
			},
			error: function(err) {
				console.log(err)
			}
		})
		
	})
	
	$('#delete-type').on('click', function() {
		var id = $('#type-selector').val()
		
		var $this = $(this)
		$('#modal-submitting').find('.am-modal-hd').text('正在删除…')
		$('#modal-submitting').modal('open')
		$.ajax({
			type: 'post',
			url: 'blog/deleteType',
			contentType: 'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({typeid: id}),
			success: function(res) {
				$('#modal-submitting').modal('close')
				if (res.status) {
					$('#alert-result').find('.am-modal-bd').html('删除类型成功！')
					$('#alert-result').modal('open')
					$.each($('#type-selector').children(), function(i, block) {
						if ($(block).val() == id) {
							$(block).remove()
						}
					});
				} else {
					$('#alert-result').find('.am-modal-bd').html('删除失败：' + res.reason)
					$('#alert-result').modal('open')
				}
				
			},
			error: function(err) {
				console.log(err)
			}
		})
		
	})
})
