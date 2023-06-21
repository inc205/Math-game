const readline = require('readline');

var resultados = [];

function generarNumero() {
  return Math.floor(Math.random() * 90) + 10;
}

function generarOpciones(respuestaCorrecta) {
  var opciones = [];
  opciones.push(respuestaCorrecta);
  opciones.push(respuestaCorrecta + 5);
  opciones.push(respuestaCorrecta - 5);
  opciones.sort(function() {
    return 0.5 - Math.random();
  });
  return opciones;
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

function realizarSuma(numero1, numero2, indice) {
  var respuestaCorrecta = numero1 + numero2;
  var opciones = generarOpciones(respuestaCorrecta);

  console.log(`Suma ${indice + 1}:`);
  console.log(`${numero1} + ${numero2} = ?`);

  opciones.forEach((opcion, index) => {
    console.log(`${index + 1}. ${opcion}`);
  });

  rl.question('La respuesta es: ', (respuestaUsuario) => {
    var resultado = {
      suma: `${numero1} + ${numero2}`,
      respuestaUsuario: parseInt(respuestaUsuario),
      respuestaCorrecta: respuestaCorrecta,
      esCorrecta: parseInt(respuestaUsuario) === respuestaCorrecta
    };
    resultados.push(resultado);

    if (indice < 9) {
      realizarSuma(generarNumero(), generarNumero(), indice + 1);
    } else {
      mostrarResultados();
      rl.close();
    }
  });
}

function mostrarResultados() {
  console.log('Resultados:');
  resultados.forEach((resultado, index) => {
    console.log(`Suma ${index + 1}: ${resultado.suma}`);
    if (resultado.esCorrecta) {
      console.log('Respuesta: Correcta');
    } else {
      console.log(`Respuesta: Incorrecta. La respuesta correcta era ${resultado.respuestaCorrecta}.`);
    }
  });
}

realizarSuma(generarNumero(), generarNumero(), 9);
