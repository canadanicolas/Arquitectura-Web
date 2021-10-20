"use strict";
document.getElementById("submitPostEstudiante").addEventListener("click", postEstudiante);

function postEstudiante() {
	let nombre = document.querySelector("#idNombreInsert").value;
	let apellido = document.querySelector("#idApellidoInsert").value;
	let edad = document.querySelector("#idEdadInsert").value;
	let genero = document.querySelector("#idGeneroInsert").value;
	let ciudad = document.querySelector("#idCiudadInsert").value;
	let libreta = document.querySelector("#idLibretaInsert").value;
	let dni = document.querySelector("#idDniInsert").value;


	let estudiante = {
		"dni": dni,
		"apellido": apellido,
		"nombre": nombre,
		"edad": edad,
		"genero": genero,
		"ciudad_residencia": ciudad,
		"numero_libreta": libreta
	};

	let url = "entregable/estudiante/add";
	fetch(url, {
		'method': 'POST',
		'headers': {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		'body': JSON.stringify(estudiante)
	})

}