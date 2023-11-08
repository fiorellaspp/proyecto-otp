document.addEventListener('DOMContentLoaded', function () {
    cargarTareasIniciales();
});

function cargarTareasIniciales() {
    obtenerUsuarioData()
        .then(data => {
            const idUsuario = data.idUsuario;
            // hacerSolicitud(`/tareas/usuario/${idUsuario}`, 'GET')
            hacerSolicitud(`/tareas`, 'GET')                            // PRUEBAS
                .then(data => {
                    mostrarTareasTabla(data);
                    mostrarTareasPanel(data);
                    llenarSelectConListas(data, "idListaTareaCrear");
                    llenarSelectConListas(data, "idListaTareaActualizar");
                });
        });
}

function mostrarTareasTabla(data) {
    if (data) {
        const tabla = document.getElementById('mostrarTareasLista');
        tabla.innerHTML = ''
        data.forEach(tarea => {
            tarea.fechaCreacionTarea = "30 de octubre";                      // --------------------REVISAR
            const finalizado = tarea.finalizadoTarea ? "Finalizado" : "Incompleto";
            const fechaFormateada = formatearFecha(tarea.fechaEntregaTarea);
            const fila = crearFilaTarea(tarea);

            tabla.appendChild(fila);
        });
    }
}

function crearFilaTarea(tarea) {
    tarea.fechaCreacionTarea = "30 de octubre";                                // --------------------REVISAR
    const finalizado = tarea.finalizadoTarea ? "Finalizado" : "Incompleto";
    const fechaFormateada = formatearFecha(tarea.fechaEntregaTarea);

    const filaTr = document.createElement('tr');
    filaTr.classList.add('filaTareaTr');
    filaTr.setAttribute('data-tarea-id', tarea.idTarea);

    const nombreTd = document.createElement('td');
    nombreTd.innerHTML = tarea.nombreTarea;

    const fechaCreaciónTd = document.createElement('td');
    fechaCreaciónTd.innerHTML = tarea.fechaCreacionTarea;

    const fechaEntregaTd = document.createElement('td');
    fechaEntregaTd.innerHTML = fechaFormateada;

    const estadoTd = document.createElement('td');
    estadoTd.innerHTML = finalizado;

    const ListaTd = document.createElement('td');
    ListaTd.innerHTML = tarea.listaTarea.nombreLista;

    filaTr.appendChild(nombreTd);
    filaTr.appendChild(fechaCreaciónTd);
    filaTr.appendChild(fechaEntregaTd);
    filaTr.appendChild(estadoTd);
    filaTr.appendChild(ListaTd);

    filaTr.setAttribute('data-bs-target', '#actualizarTareaModal');
    filaTr.setAttribute('data-bs-toggle', 'modal');

    filaTr.addEventListener("click", function () {
        cargarDetallesTarea(tarea);
    });

    return filaTr;
}

function mostrarTareasPanel(data) {
    const tablero = document.getElementById('mostrarTareasPanel')
    tablero.classList.add('facet-container');
    tablero.innerHTML = '';
    const listas = {};

    data.forEach(tarea => {
        if (!listas[tarea.listaTarea.nombreLista]) {
            listas[tarea.listaTarea.nombreLista] = [];
        }
        listas[tarea.listaTarea.nombreLista].push(tarea);
    });

    for (const nombreLista in listas) {
        const listaTareas = listas[nombreLista];
        const listaDivMayor = document.createElement('div');
        listaDivMayor.classList.add('listaDivMayor');

        const nombreListaDiv = document.createElement('label');
        nombreListaDiv.classList.add('nombreLista');
        nombreListaDiv.innerHTML = nombreLista;
        listaDivMayor.appendChild(nombreListaDiv);

        const cardTareasMayor = document.createElement('ul');
        cardTareasMayor.classList.add('cardTareasMayor');
        cardTareasMayor.classList.add('facet-list');
        listaDivMayor.appendChild(cardTareasMayor);

        listaTareas.forEach(tarea => {
            const cardTarea = crearCardTarea(tarea);

            $(function () {
                $(".facet-list, #userFacets").sortable({
                    connectWith: "ul",
                    placeholder: "placeholder",
                    delay: 150,
                    stop: function (event, ui) {
                        const tareaId = ui.item.data('tarea-id');
                        const listaId = ui.item.data('tarea-lista-id');
                        console.log(tareaId);
                        console.log(listaId);
                        actualizarLista(tareaId, listaId);
                        console.log("BD no actualizada");                   // REVISAR
                    }
                })
                    .disableSelection();
            });
            cardTareasMayor.appendChild(cardTarea);
        });
        tablero.appendChild(listaDivMayor);
    }
    const crearNuevaListaDiv = document.createElement('div');
    crearNuevaListaDiv.classList.add('listaDivMayor');
    crearNuevaListaDiv.id = 'crearNuevaLista';
    const nombreCrearNuevaListaDiv = document.createElement('label');
    nombreCrearNuevaListaDiv.classList.add('nombreLista');
    nombreCrearNuevaListaDiv.innerHTML = 'Crear nueva lista';
    crearNuevaListaDiv.appendChild(nombreCrearNuevaListaDiv);
    tablero.appendChild(crearNuevaListaDiv);

    const crearNuevaLista = document.getElementById('crearNuevaLista');
    crearNuevaLista.addEventListener('click', function () {
        $('#crearListaModal').modal('show');
    });
}

function crearLista() {
    const nuevaListaNombre = document.getElementById('nombreListaNueva').value;
        console.log('Crear lista' + nuevaListaNombre)

}

function obtenerTareaPorId(idTarea) {
    return hacerSolicitud('/tareas/' + idTarea, 'GET');
}

function actualizarLista(idTarea, lista) {
    // --------------------REVISAR
}

function crearCardTarea(tarea) {
    tarea.fechaCreacionTarea = "30 de octubre";                             // --------------------REVISAR
    const finalizado = tarea.finalizadoTarea ? "Finalizado" : "Incompleto";
    const fechaFormateada = formatearFecha(tarea.fechaEntregaTarea);

    const cardTareaDiv = document.createElement('li');
    cardTareaDiv.classList.add('cardTareaDiv');
    cardTareaDiv.classList.add('facet');
    cardTareaDiv.setAttribute('data-bs-target', '#actualizarTareaModal');
    cardTareaDiv.setAttribute('data-bs-toggle', 'modal');
    cardTareaDiv.setAttribute('data-tarea-id', tarea.idTarea);
    cardTareaDiv.setAttribute('data-tarea-lista-id', tarea.listaTarea.idLista);

    const nombreDiv = document.createElement('div');
    nombreDiv.classList.add('nombreTareaDiv');
    nombreDiv.innerHTML = tarea.nombreTarea;
    cardTareaDiv.appendChild(nombreDiv);

    const estadoDiv = document.createElement('div');
    estadoDiv.classList.add('estadoTareaDiv');
    estadoDiv.innerHTML = finalizado;
    cardTareaDiv.appendChild(estadoDiv);

    const fechaEntregaDiv = document.createElement('div');
    fechaEntregaDiv.classList.add('fechaEntregaTareaDiv');
    fechaEntregaDiv.innerHTML = fechaFormateada;
    cardTareaDiv.appendChild(fechaEntregaDiv);

    cardTareaDiv.addEventListener("click", function () {
        cargarDetallesTarea(tarea);
    });

    return cardTareaDiv;
}

function cargarDetallesTarea(tarea) {
    const nombre = document.getElementById("nombreTareaActualizar");
    const descripcion = document.getElementById("descripcionTareaActualizar");
    const fechaEntrega = document.getElementById("fechaEntregaTareaActualizar");
    const estado = document.getElementById("finalizadoTareaActualizar");
    const lista = document.getElementById("idListaTareaActualizar");

    nombre.value = tarea.nombreTarea;
    descripcion.value = tarea.descripcionTarea;
    fechaEntrega.value = new Date(tarea.fechaEntregaTarea).toISOString().slice(0, 10);
    estado.checked = tarea.finalizadoTarea;
    lista.value = tarea.listaTarea.idLista;

    const textoModalA = document.getElementById('nombreTareaModalA');
    textoModalA.innerHTML = nombre.value;
    const btnActualizar = document.getElementById('btnActualizarTarea');
    const btnConfirmarActualizacion = document.getElementById('btnConfimarActualizacion');
    const actualizarTareaYRemoverEvento = function () {
        actualizarTarea(tarea.idTarea);
        btnConfirmarActualizacion.removeEventListener('click', actualizarTareaYRemoverEvento);
    };
    btnConfirmarActualizacion.addEventListener('click', actualizarTareaYRemoverEvento);

    const textoModalE = document.getElementById('nombreTareaModalE');
    textoModalE.innerHTML = nombre.value;
    const btnEliminar = document.getElementById('btnEliminarTarea');
    const btnConfimarEliminacion = document.getElementById('btnConfimarEliminacion');
    const eliminarTareaYRemoverEvento = function () {
        eliminarTarea(tarea.idTarea);
        btnConfimarEliminacion.removeEventListener('click', eliminarTareaYRemoverEvento);
    };
    btnConfimarEliminacion.addEventListener('click', eliminarTareaYRemoverEvento);
}

function llenarSelectConListas(data, id) {
    const selectElement = document.getElementById(id);
    while (selectElement.firstChild) {
        selectElement.removeChild(selectElement.firstChild);
    }
    const optionDefault = document.createElement('option');
    optionDefault.value = "";
    optionDefault.textContent = "Seleccionar";
    optionDefault.selected = true;
    selectElement.appendChild(optionDefault);

    const listaDeIds = new Set();
    data.forEach(tarea => {
        if (!listaDeIds.has(tarea.listaTarea.idLista)) {
            const optionElement = document.createElement('option');
            optionElement.value = tarea.listaTarea.idLista;
            optionElement.textContent = tarea.listaTarea.nombreLista;
            selectElement.appendChild(optionElement);
            listaDeIds.add(tarea.listaTarea.idLista);
        }
    });
}

function crearTarea() {
    const nombre = document.getElementById('nombreTareaCrear').value;
    const descripcion = document.getElementById('descripcionTareaCrear').value;
    const fechaEntrega = document.getElementById('fechaEntregaTareaCrear').value;
    const finalizado = document.getElementById('finalizadoTareaCrear').checked;
    const idLista = document.getElementById('idListaTareaCrear').value;

    obtenerUsuarioData()
        .then(data => {
            const dataCrearTarea = {
                nombreTarea: nombre,
                descripcionTarea: descripcion,
                fechaEntregaTarea: fechaEntrega,
                finalizadoTarea: finalizado,
                listaTarea: {
                    idLista: idLista
                },
                detalles: [
                    {
                        usuario: {
                            idUsuario: data.idUsuario
                        }
                    }
                ]
            };

            hacerSolicitud('/tareas', 'POST', dataCrearTarea)
            cargarTareasIniciales();
        });
}

function actualizarTarea(idTarea) {
    const nombre = document.getElementById("nombreTareaActualizar").value;
    const descripcion = document.getElementById("descripcionTareaActualizar").value;
    const fechaEntrega = document.getElementById("fechaEntregaTareaActualizar").value;
    const estado = document.getElementById("finalizadoTareaActualizar").checked;
    const lista = document.getElementById("idListaTareaActualizar").value;

    obtenerUsuarioData()
        .then(data => {
            const dataActualizarTarea = {
                idTarea: idTarea,
                nombreTarea: nombre,
                descripcionTarea: descripcion,
                fechaEntregaTarea: fechaEntrega,
                finalizadoTarea: estado,
                listaTarea: {
                    idLista: lista
                },
                detalles: [
                    {
                        usuario: {
                            idUsuario: data.idUsuario
                        }
                    }
                ]
            };

            hacerSolicitud('/tareas/' + idTarea, 'PUT', dataActualizarTarea)
            cargarTareasIniciales();
        });
}


function eliminarTarea(idTarea) {
    hacerSolicitud('/tareas/' + idTarea, 'DELETE')
        .then(() => cargarTareasIniciales());
}

document.addEventListener('DOMContentLoaded', function () {
    const botonGuardar = document.getElementById('btnCrearTarea');
    const modal = new bootstrap.Modal(document.getElementById('crearTareaModal'));

    botonGuardar.addEventListener('click', function (event) {
        const formulario = document.getElementById('crearTareaForm');
        if (formulario.checkValidity()) {
            crearTarea();
            modal.hide();
            
        } else {
        }
        event.preventDefault();
        formulario.classList.add('was-validated');
        limpiarFormulario("crearTareaForm");
    });
});

document.addEventListener('DOMContentLoaded', function () {       //-------- REVISAR
    const botonGuardarLista = document.getElementById('btnCrearLista'); 
    const modal = new bootstrap.Modal(document.getElementById('crearListaModal'));

    botonGuardarLista.addEventListener('click', function (event) {
        const formulario = document.getElementById('crearListaForm');
        if (formulario.checkValidity()) {
            crearLista(); 
            modal.hide(); 
        } else {
        }
        event.preventDefault(); 
        formulario.classList.add('was-validated');
    });
});

function limpiarFormulario(formularioId) {
    const formulario = document.getElementById(formularioId);
    if (formulario) {
        formulario.reset();
    }
}




