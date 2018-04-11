var xhr = new XHR();
function $(id) {
	return document.getElementById(id);
}

function get_session() {
	xhr.get('../cart',{},{}).then((data) => {
		console.log(data);
	})
}

function add() {
	xhr.post('../cart',{choice:$('add').value, title:$('title').value},{'Content-Type':'application/json'}).then((data) => {
		$('text').innerHTML = data.test;
		console.log(data);
	});
}

function show() {
	xhr.post('../cart',{choice:$('show').value},{'Content-Type':'application/json'}).then((data) => {
		$('text').innerHTML = data.test;
		console.log(data);
	});
}

function erase() {
	xhr.post('../cart',{choice:$('delete').value},{'Content-Type':'application/json'}).then((data) => {
		$('text').innerHTML = data.test;
	});
}

addEventListener('load', get_session);
$('add').addEventListener('click', add);
$('show').addEventListener('click', show);
$('delete').addEventListener('click', erase);
