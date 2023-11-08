const d = document,
    btnLogin = d.getElementById("btnLogin");
    
let jwt;

async function digestMessage(message){
    const encoder = new TextEncoder();
    const data = encoder.encode(message);
    const hash = await crypto.subtle.digest("SHA-1", data);
    const hashArray = Array.from(new Uint8Array(hash));
    const hashHex = hashArray
                    .map((b)=> b.toString(16).padStart(2,"0"))
                    .join("");
    
    return hashHex;
}


function login(url, data){
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

d.addEventListener("click",async (e)=>{
    if(d.querySelectorAll('.was-validated') && e.target.matches("#btnLogin")){
        email = d.getElementById("email").value,
        password = d.getElementById("clave").value;
        
        // encriptar
        password = await digestMessage(password);

        let data = { username: email , password: password };

        login("http://localhost:8080/login", data);
        
        
    }
});

function cerrarSesion() {
    sessionStorage.removeItem('Authorization');
    window.location.href = '/iniciarSesion.html';
}

function mensajeConfirmacion(s) {
    const msj = d.getElementById("mensajeConfirmacion");
    const correo = d.getElementById("email");
    const contrasena = d.getElementById("clave");

    msj.innerHTML = "";
    msj.style.display = "none";

    if (s) {
        msj.innerHTML = "Inicio de sesión exitoso";
        msj.style.display = "block";
        window.location.href = "inicio.html";
    } else if (correo.value != "" || contrasena.value != "") {
        msj.innerHTML = "Credenciales incorrectas";
        msj.style.display = "block";
        correo.value = ""; 
    }
}
