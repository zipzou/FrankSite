/**
 * 
 */

$(document).ready(function(){
	$('.am-collapse').on('open.collapse.amui', function(){
		var destElem = $(this).parent().find('i[class*=am-icon]').addClass('am-rotate-down')
	})
	$('.am-collapse').on('close.collapse.amui', function(){
		var destElem = $(this).parent().find('i[class*=am-icon]').removeClass('am-rotate-down')
	})
	
	
	$('.am-link-read-article').click(function(){
		var id = $(this).parent().parent().parent().attr('id')
		window.location.href = 'page/article/' + id;
	})
	
	$('#all-article').addClass('am-active')
})