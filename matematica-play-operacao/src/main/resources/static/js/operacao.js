var contador = 1;
const menssagemOperacaoCorreta = 'Yearr baby ! Acertou';
const menssagemOperacaoErrada  = 'Oops ! Tente novamente';


/**
 * Atualiza Operacao
 * @returns
 */
function atualizaOperacao() {
	 $.ajax({url: "http://localhost:8080/operacoes/aleatoria"})
	 	.then(function(data) {
	 		$('.fator-a').empty().append(data.fatorA);
	 		$('.fator-b').empty().append(data.fatorB);
	 		$('.operador').empty().append(data.operador);
	 		$("#input-resultado").val("");
	 		contador = 1;
	 });
};


/**
 * Executar Operacao
 * @returns
 */
function executarOperacao() {
	
	atualizaOperacao();
		
	$("#btn-resultado").click(function( event ) {
		 
		 event.preventDefault();
	 
		 var a = $('.fator-a').text();
		 var b = $('.fator-b').text();
		 var operador = $('.operador').text();
		 var resultado   = $("#input-resultado").val();
		 
		 var data = { 
				 		usuario:  { apelido: 'Um Nome' }, 
		 				operacao: { fatorA: a, fatorB: b, operador: operador }, 
		 				resultado: resultado
		 			};
		 
		 console.log('Enviando dados : ', JSON.stringify(data));
	 
		 $.ajax({
			 url: '/resultados',
			 type: 'POST',
			 data: JSON.stringify(data),
			 contentType: "application/json; charset=utf-8",
			 dataType: "json",
			 success: function(resultado){
				 if(resultado.correto) {
					 $('.result-message').empty().append(menssagemOperacaoCorreta);
				 } else {
					 $('.result-message').empty().append(menssagemOperacaoErrada);
				 }
			 }
		 });
	 
		 atualizaOperacao();
		 
	});
	
};


/**
 * Cronometro
 * @returns
 */
function cronometro() {
	
	setInterval(() => {
		$('.result-message-contador').empty().append(contador);
		contador++;
		
		if (contador > 5) {
			executarOperacao();
			$('.result-message').empty().append(menssagemOperacaoErrada);
			contador = 1;
		}	
	}, 1000 * 1);
};


/**
 * Main
 * @returns
 */
$(document).ready(function() {
	
	executarOperacao();
	cronometro();
	
});