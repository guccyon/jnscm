(function(){
	(function f(s){
		var x=document.createElement('script');
		x.src='http://nanoblops.net/nanoscheme/js/'+s;
		x.type='text/javascript';
		x.charset='utf-8';
		document.body.appendChild(x);
		return f;
	})('console.js')('');
})();
