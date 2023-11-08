const d = document,
    btnLogin = d.getElementById("btnRegistro");

async function digestMessage(message) {
    const encoder = new TextEncoder();
    const data = encoder.encode(message);
    const hash = await crypto.subtle.digest("SHA-1", data);
    const hashArray = Array.from(new Uint8Array(hash));
    const hashHex = hashArray
        .map((b) => b.toString(16).padStart(2, "0"))
        .join("");

    return hashHex;
}


function login(url, data) {
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then((res) => res.json())
        .catch((error) => console.error("Error:", error))
        .then((res) => {
            sessionStorage.setItem("Authorization", "bearer " + res.jwtToken);
            mensajeConfirmacion(res.jwtToken);
        })
}
// 
d.addEventListener("click", async (e) => {
    if ( e.target.matches("#btnRegistro")) {
        const nombre = d.getElementById("nombre").value;
        const apellidos = d.getElementById("apellidos").value;
        const fechaNacimiento = d.getElementById("fechaNacimiento").value;
        const genero = d.getElementById("genero").value;
        const email = d.getElementById("email").value;
        let password = d.getElementById("clave").value;
        let confirmarClave = document.getElementById('claveConfirmacion').value;

        console.log(nombre);
        console.log(apellidos);
        console.log(fechaNacimiento);
        console.log(genero);
        console.log(email);

        password = await digestMessage(password);
        confirmarClave = await digestMessage(confirmarClave);
        console.log(password);
        console.log(confirmarClave);

        let data = {
            "nombre": nombre,
            "apellido": apellidos,
            "fechaNacimiento": fechaNacimiento,
            "genero": genero,
            "email": email,
            "password": password
        }


        if (password === confirmarClave) {
            login("http://localhost:8080/register", data)
        }
    }
});

function mensajeConfirmacion(s) {
    const msj = d.getElementById("mensajeConfirmacion");
    const correo = d.getElementById("email");

    msj.innerHTML = "";
    msj.style.display = "none";

    if (s) {
        msj.innerHTML = "Registro exitoso";
        msj.style.display = "block";
        window.location.href = "iniciarSesion.html";
    } else if (correo.value != "") {
        msj.innerHTML = "El correo " + email + " ya está registrado";
        msj.style.display = "block";
        correo.value = ""; 
    }
}

