function hacerSolicitud(url, metodo, datos = null) {
    const opciones = {
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        body: datos ? JSON.stringify(datos) : null
    };

    return fetch(url, opciones)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al realizar la solicitud a ' + url);
            }
            if (metodo === 'DELETE') {
                return null;
            }
            return response.json();
        })
        .catch(error => {
            console.error("Error encontrado: " + error);
            return null;
        });
}
