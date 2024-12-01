const btn = document.querySelector("#conteudo--id_btn");
const txtInput = document.querySelector("#txt_input");
const textOutput = document.querySelector("#txt_output");
const resultSection = document.querySelector(".result");
const btnFechar = document.querySelector("#result_conteudo_btns_denovo");
const btnCopiar = document.querySelector("#result_conteudo_btns_copiar");
const linkOutput = document.querySelector("#link_output");

btn.addEventListener("click", getData);
btnFechar.addEventListener("click", reset);
btnCopiar.addEventListener("click", copiar);

async function getData() {
  let codigo = txtInput.value.replace(/\s+/g, '').trim().toUpperCase();

    // Validação: Verificar se o código tem pelo menos 20 caracteres
  if (codigo.length < 20) {
    alert("A sequência de entrada deve ter pelo menos 20 caracteres.");
    return; // Interrompe a execução se a condição não for atendida
  }
  
  let url = "http://host.docker.internal:8080/api/sequences/compare?inputSequence=" + codigo;

  //let url = "http://172.17.0.2:8080/api/sequences/compare?inputSequence=" + codigo;


  // let url = "http://localhost:8080/api/sequences/compare?inputSequence=" + codigo;

  document.querySelector(".conteudo_pesquisa").classList.add("hidden");

  let resultado = await fetch(url, {
    headers: {
      "Content-Type": "application/json",
    },
  });

  if (resultado.status == 200) {
    let dados = await resultado.json();
    console.log(dados);
    let value = dados["value"];
    let name = dados["name"];
    let description = dados["description"];
    let link = dados["link"];

    let formattedValue = parseFloat(value).toFixed(2) + "%";

    if (value < 30) {
      // Lógica para porcentagem inferior a 30%
      document.querySelector("#campo_total").classList.remove("hidden");
      document.querySelector("#campo_errado").classList.remove("hidden");
      document.querySelector("#result_conteudo_btns_copiar").classList.add("hidden");
      return; // Finaliza a execução para não continuar com o código restante
  }
    
    textOutput.value = "Índice Percentual: " + formattedValue + "\n" + "\n" + "Gene: " + name + "\n" + "\n" + "Descrição: " + description;
    linkOutput.innerHTML = "Link: <a href='" + link + "' target='_blank' class='white-link'>" + link + "</a>";

    document.querySelector("#campo_total").classList.remove("hidden");
    document.querySelector("#campo_certo").classList.remove("hidden");
    document.querySelector("#result_conteudo_btns_copiar").classList.remove("hidden");
   
  } else {
    document.querySelector("#campo_total").classList.remove("hidden");
    document.querySelector("#campo_errado").classList.remove("hidden");
    document.querySelector("#result_conteudo_btns_copiar").classList.add("hidden");
  }
}

function reset(){
  document.querySelector(".conteudo_pesquisa").classList.remove("hidden");
  document.querySelector("#campo_total").classList.add("hidden");
  document.querySelector("#campo_certo").classList.add("hidden");
  document.querySelector("#campo_errado").classList.add("hidden");

  txtInput.value = "";
  textOutput.value = "";
}

function copiar(){
  textOutput.select();

  navigator.clipboard.writeText(textOutput.value)
      .catch(err => {
          console.error('Erro ao copiar o texto: ', err);
      });
}
