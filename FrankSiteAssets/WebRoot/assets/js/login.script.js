/**
 * 
 */

$(document).ready(function() {
	
	$('input').on('focus', function(){
		$(this).removeClass('am-success-input')
		$(this).parent().removeClass('am-form-error')
	})
	
	// 验证码
	$('#vaidationCodeImage').attr('src', 'validationCode?key=' + Math.random())
	
	$('#vaidationCodeImage').on('click', function(){
		$(this).attr('src', 'validationCode?key=?' + Math.random())
	})
	
	$('#validateCode').on('blur', function(){
		$.ajax({
			type: 'POST',
			url: 'account/validateImgCode',
			contentType:'application/json;charset=utf-8',
			dataType: 'json',
			data: JSON.stringify({code: $('#validateCode').val().trim()}),
			success: function(data) {
				if (data.status) {
					try {
						
						if (data.status) {
							$('#validateCode').parent().addClass('am-form-success')
						} else {
							$('#validateCode').parent().removeClass('am-form-success')
							$('#validateCode').parent().addClass('am-form-error')
						}
						
						$('#validateCode').addClass('am-success-input')
					} catch (e) {
						console.log(e)
					}
				} else {
					console.log(data.reason)
					$('#validateCode').parent().addClass('am-form-error')
				}
			},
			error: function(e) {
				console.log(e)
			}
		})
//		$.post('api/account/validateImgCode', JSON.stringify({code: $('#validateCode').val().trim()}), function(data, status, xhr){
//			try {
//				
//				if (!data.status) {
//					$('#validateCode').addClass('am-warn-group')
//				} else {
//					$('#validateCode').removeClass('am-warn-group')
//				}
//				
//			} catch (e) {
//				console.log(e)
//			}
//		}, 'application/json;charset=utf-8')
	})
	
	$('#form-login').on('submit', function() {
		$(this).attr('method', 'post')
		try {
			var canSubmit = true;
			if ($('#username').val().trim().length <= 0) {
				$('#username').addClass('am-warn-group')
				canSubmit = false
			}
			if ($('#password').val().trim().length <= 0) {
				$('#password').addClass('am-warn-group')
				canSubmit = false
			}

			if ($('#validateCode').val().trim().length <= 0) {
				$('#validateCode').addClass('am-warn-group')
				canSubmit = false
			}

			var ref = $('#form-login').attr('ref')
			$('#form-login').attr('action', 'account/login' + '?ref=' + ref)

			return canSubmit
		} catch (e) {
			console.log(e)
		}
	})
})