/* --------------- Saludo --------------- */
function obtenerSaludo(hora) {
    if (hora >= 5 && hora < 12) {
        return 'Buenos días';
    } else if (hora >= 12 && hora < 18) {
        return 'Buenas tardes';
    } else {
        return 'Buenas noches';
    }
}

/* --------------- Formato fecha --------------- */
function formatearFecha(fecha) {
    const formato = { day: 'numeric', month: 'long' };
    return new Date(fecha).toLocaleDateString('es-ES', formato);
}

