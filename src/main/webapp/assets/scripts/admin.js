
$('a[data-toggle=layout-small-menu]').on('click', function(e){
	e.preventDefault(), e.stopPropagation(), $('.layout-fixed-header').toggleClass("layout-small-menu");
});