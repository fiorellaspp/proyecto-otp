function cerrarSesion() {
    sessionStorage.removeItem('Authorization');
    window.location.href = '/index.html';
}

document.addEventListener('DOMContentLoaded', function () {
    const token = sessionStorage.getItem('Authorization');
    if (!token) {
        window.location.href = 'iniciarSesion.html';
    }
});