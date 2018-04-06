var xhr = new XHR();
function $(id) {
	return document.getElementById(id);
}

function add() {
	xhr.get('../cart',{value:$('add').value, title:$('title').value},{}).then((data) => {
		$('text').innerHTML = data.test;
	});
}

function show() {
	xhr.get('../cart',{value:$('show').value},{}).then((data) => {
		$('text').innerHTML = data.test;
	});
}

function erase() {
	xhr.get('../cart',{value:$('delete').value},{}).then((data) => {
		$('text').innerHTML = data.test;
	});
}

$('add').addEventListener('click', add);
$('show').addEventListener('click', show);
$('delete').addEventListener('click', erase);
