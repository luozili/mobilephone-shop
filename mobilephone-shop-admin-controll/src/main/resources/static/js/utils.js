function loadUploadHtml(pagePath) {
	$.get(pagePath, function(data) {
		$('.container').html(data);
	});
};