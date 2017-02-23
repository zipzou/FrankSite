/**
 * 
 */

$(document).ready(function() {

	$('#types').addClass('am-active')

	$('.am-link-typedetail').click(function() {
		var id = $(this).attr('target-id')
		window.location.href = 'page/types/' + id
	})
})