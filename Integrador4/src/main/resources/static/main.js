"use strict";
document.addEventListener("DOMContentLoaded", function () {

	let btn2 = document.getElementById("stock");
	btn2.addEventListener("click", async () => {
		document.getElementById("inicioPag").innerHTML = "Cargando...";

		try {
			let response = await fetch("stock.html");
			console.log(response);
			if (response.ok) {
				let html = await response.text();
				let div = document.getElementById("inicioPag");
				div.innerHTML = html;
				document.querySelector("#addStock").addEventListener("click", postStock);
				document.querySelector("#EditStock").addEventListener("click", putStock);
				document.querySelector("#deleteStock").addEventListener("click", deleteStock);

			}

		} catch (error) {
			document.getElementById("inicioPag").innerHTML = "error";
		}
	});
	let btn3 = document.getElementById("compra");
	btn3.addEventListener("click", async () => {
		document.getElementById("inicioPag").innerHTML = "Cargando...";

		try {
			let response = await fetch("compra.html");
			console.log(response);
			if (response.ok) {
				let html = await response.text();
				let div = document.getElementById("inicioPag");
				div.innerHTML = html;
				document.querySelector("#addCompra").addEventListener("click", postCompra);
				document.querySelector("#editCompra").addEventListener("click", putCompra);
				document.querySelector("#deleteCompra").addEventListener("click", deleteCompra);
				document.querySelector("#addProductoComprar").addEventListener("click", agregarProductoACompra);
				document.querySelector("#finalizarProductoComprar").addEventListener("click", finalizarProductoComprar);

			}

		} catch (error) {
			document.getElementById("inicioPag").innerHTML = "error";
		}
	});
	let btn4 = document.getElementById("reportes");
	btn4.addEventListener("click", async () => {
		document.getElementById("inicioPag").innerHTML = "Cargando...";

		try {
			let response = await fetch("reportes.html");
			console.log(response);
			if (response.ok) {
				let html = await response.text();
				let div = document.getElementById("inicioPag");
				div.innerHTML = html;
				document.querySelector("#getClientes").addEventListener("click", getClientes);
				document.querySelector("#getComprasXDia").addEventListener("click", getComprasXDia);

				document.querySelector("#getProductoMasVendido").addEventListener("click", getProductoMasVendido);

				document.querySelector("#verProductos").addEventListener("click", verProductos);

			}

		} catch (error) {
			document.getElementById("inicioPag").innerHTML = "error";
		}
	});
	let btn5 = document.getElementById("cliente");
	btn5.addEventListener("click", async () => {
		document.getElementById("inicioPag").innerHTML = "Cargando...";

		try {
			let response = await fetch("cliente.html");
			console.log(response);
			if (response.ok) {
				let html = await response.text();
				let div = document.getElementById("inicioPag");
				div.innerHTML = html;
				document.querySelector("#addCliente").addEventListener("click", postCliente);
				document.querySelector("#editCliente").addEventListener("click", putCliente);
				document.querySelector("#deleteCliente").addEventListener("click", deleteCliente);

			}

		} catch (error) {
			document.getElementById("inicioPag").innerHTML = "error";
		}
	});
})
/*---------------------------------------------- EVENT LISTENERS ----------------------------------------------*/

let idProducto = [];

/*---------------------------------------------- FUNCIONES ----------------------------------------------*/

/*---------------------------------------------- PRODUCTO ----------------------------------------------*/

function postProducto() {
	let id = document.querySelector("#idAddProducto").value;
	let nombre = document.querySelector("#idAddNombre").value;
	let precio = document.querySelector("#idAddPrecio").value;

	let producto = {
		"id": id,
		"nombre": nombre,
		"precio": precio
	};
	console.log(producto);
	let url = "producto/add";
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(producto)
	})
}

function putProducto() {
	let id = document.querySelector("#idPutProducto").value;
	let nombre = document.querySelector("#idPutNombre").value;
	let precio = document.querySelector("#idPutPrecio").value;
	let producto = {
		"id": id,
		"nombre": nombre,
		"precio": precio,
	};
	let url = "producto/update/" + id;
	fetch(url, {
		"method": "PUT",
		"headers": { "Content-Type": "application/json" },
		"body": JSON.stringify(producto)
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

function deleteProducto() {
	let id = document.querySelector("#idDeleteProducto").value;
	let url = "producto/delete/" + id;
	fetch(url, {
		"method": "DELETE",
		"headers": { "Content-Type": "application/json" },
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

/*---------------------------------------------- CLIENTE ----------------------------------------------*/

function postCliente() {
	let id = document.querySelector("#idAddCliente").value;
	let nombre = document.querySelector("#nombreAddCliente").value;
	let cliente = {
		"id": id,
		"nombre": nombre
	};
	let url = "cliente/add";
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(cliente)
	})
}

function putCliente() {
	let id = document.querySelector("#idPutCliente").value;
	let nombre = document.querySelector("#nombrePutCliente").value;
	let cliente = {
		"id": id,
		"nombre": nombre
	};
	let url = "cliente/update/" + id;
	fetch(url, {
		"method": "PUT",
		"headers": { "Content-Type": "application/json" },
		"body": JSON.stringify(cliente)
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

function deleteCliente() {
	let id = document.querySelector("#idDeleteCliente").value;
	let url = "cliente/delete/" + id;
	fetch(url, {
		"method": "DELETE",
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

/*---------------------------------------------- COMPRA ----------------------------------------------*/

function postCompra() {
	let id = document.querySelector("#idAddCompra").value;
	let fechaDeCompra = document.querySelector("#fechaAddCompra").value;
	let compra = {
		"id": id,
		"fechaDeCompra": fechaDeCompra
	};
	let url = "compra/add";
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(compra)

	})
}

function putCompra() {
	let id = document.querySelector("#idPutCompra").value;
	let fechaDeCompra = document.querySelector("#fechaPutCompra").value;
	let compra = {
		"id": id,
		"fechaDeCompra": fechaDeCompra
	};
	let url = "compra/update/" + id;
	fetch(url, {
		"method": "PUT",
		"headers": { "Content-Type": "application/json" },
		"body": JSON.stringify(compra)
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

function deleteCompra() {
	let id = document.querySelector("#idDeleteCompra").value;
	let url = "compra/delete/" + id;
	console.log(url);
	fetch(url, {
		"method": "DELETE",
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

/*---------------------------------------------- STOCK ----------------------------------------------*/

function postStock() {
	let id = document.querySelector("#idAddStock").value;
	let cantidad = document.querySelector("#idCantidadStock").value;
	let url = "stock/add/" + id;
	let stock = {
		"id": id,
		"cantidad": cantidad
	};
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(stock)
	})

}

function putStock() {
	let id = document.querySelector("#idEditStock").value;
	let cantidad = document.querySelector("#idEditCantidad").value;
	let url = "stock/update/" + id;
	let stock = {
		"id": id,
		"cantidad": cantidad
	};
	fetch(url, {
		"method": "PUT",
		"headers": { "Content-Type": "application/json" },
		"body": JSON.stringify(stock)
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

function deleteStock() {
	let id = document.querySelector("#idDeleteStock").value;
	let url = "stock/delete/" + id;
	console.log(url);
	fetch(url, {
		"method": "DELETE",
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

/*---------------------------------------------- OTROS ----------------------------------------------*/

async function getClientes() {
	document.querySelector("#contenedorClientes").innerHTML ="";
	let url = "cliente/reporteCompras";
	let r = await fetch(url, {
		'method': 'GET',
		'mode': 'cors'
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorClientes");
	json.forEach(element => {
	contenedor.innerHTML +="-Cliente:"+element.cliente.nombre+"\t-"+ "Gastos:"+element.gastos+"<br>";
});
}

async function getComprasXDia() {
	document.querySelector("#contenedorCompras").innerHTML ="";
	let url = "compra/reporteComprasPorDia";
	let r = await fetch(url, {
		'method': 'GET',
		'mode': 'cors'
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorCompras");
	json.forEach(element => {
		contenedor.innerHTML +="-Id Compra:"+element.compra[0].id+"\t-"+"Fecha de compra:"+element.compra[0].fechaDeCompra+"<br>";
	});
}

function comprar() {
	let id = document.querySelector("#idDeleteStock").value;
	let url = "stock/delete/" + id;
	let productos = document.querySelectorAll("#insertarComentario");
	botoncrearcomentario.forEach();
	fetch(url, {
		"method": "DELETE",
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})
}

async function getProductoMasVendido() {
	document.querySelector("#contenedorProducto").innerHTML ="";
	let url = "producto/masVendido";
	let r = await fetch(url, {
		'method': 'GET',
		'mode': 'cors'
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorProducto");
	contenedor.innerHTML = "-Id:"+json.id + "\t-"+"Nombre:"+json.nombre+"\t-"+"Precio:"+json.precio; 
}

async function verProductos() {
	document.querySelector("#contenedorTodosProductos").innerHTML="";
	let url = "producto/getAll";
	let r = await fetch(url, {
		'method': 'GET',
		'mode': 'cors'
	});
	let json = await r.json();
	console.log(json);
	let contenedor = document.querySelector("#contenedorTodosProductos");
	json.forEach(element => {
		contenedor.innerHTML +="-Id:"+element.id + "\t-"+"Nombre:"+element.nombre+"\t-"+"Precio:"+element.precio+"<br>";
	});
}

function addProductoComprar() {
	let id = document.querySelector("#idProductoComprar");
	idProducto.push(id);
}

async function finalizarProductoComprar() {
	let idCliente = document.querySelector("#idClienteComprar").value;
	let idCompra = document.querySelector("#idCompraComprar").value;
	let today = new Date();
	let date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
	let idProducto = document.querySelector("#idProductoComprar").value;;
	let foo = [
		{ "id": 1, "nombre": "Cepita", "precio": 75.0 },
		{ "id": 2, "nombre": "Arroz", "precio": 15.0 }
	];

	let url = "cliente/comprar/" + idCompra + "/" + idCliente;
	fetch(url, {
		"method": "POST",
		"headers": {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})

}

async function agregarProductoACompra() {
	let idCompra = document.querySelector("#idCompraComprar").value;
	let idProducto = document.querySelector("#idProductoComprar").value;

	let urlAgregarProductoACompra = "compra/addProducto/" + idCompra + "/" + idProducto;
	fetch(urlAgregarProductoACompra, {
		"method": "PUT",
		"headers": {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		}
	}).then(function (r) {
		if (!r.ok) {
			console.log("Error")
		}
		return r.json()
	})
		.then(function (json) {
			console.log("ok");
		})
		.catch(function (e) {
			console.log(e)
		})

}