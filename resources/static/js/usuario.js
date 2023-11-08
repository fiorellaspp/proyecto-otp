document.addEventListener('DOMContentLoaded', function () {
    obtenerUsuario();
    cargarUsuarioData();
});

function obtenerUsuario() {
    var token = sessionStorage.getItem("Authorization");

    var tokenPayload = atob(token.split(".")[1]);
    var usuario = JSON.parse(tokenPayload).sub;

    if (token) {
        var tokenPayload = atob(token.split(".")[1]);
        var usuario = JSON.parse(tokenPayload).sub;

        document.getElementById("usuario").innerText = usuario;
    } else {
        document.getElementById("usuario").innerText = "";
    }
    return usuario;
}

function obtenerUsuarioData() {
    const usuarioCorreo = obtenerUsuario();
    return hacerSolicitud(`/usuarios/perfil?email=${usuarioCorreo}`, 'GET');
}

function cargarUsuarioData() {
    obtenerUsuarioData()
        .then(usuario => {
            const nombre = document.getElementById("nombreUsuario");
            const apellido = document.getElementById("apellidoUsuario");
            const genero = document.getElementById("generoUsuario");
            const fechaNacimiento = document.getElementById("fechaNacimientoUsuario");

            if (nombre) {
                nombre.innerText = usuario.nombreUsuario;
            }
            if (apellido) {
                apellido.innerText = usuario.apellidosUsuario;
            }
            if (genero) {
                genero.innerText = usuario.generoUsuario;
            }
            if (fechaNacimiento) {
                fechaNacimiento.innerText = usuario.fechaNacimientoUsuario;
            }
        })
}