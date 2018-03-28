var xhr = new XHR();
function $(id) {
	return document.getElementById(id);
}

function test() {
	xhr.get('../Dif',{},{}).then((data) => {
		$('text').innerHTML = data.test;
	});
}

addEventListener('load', test);