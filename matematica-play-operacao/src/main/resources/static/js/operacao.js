const URL_RESULTADOS  =  '/resultados';
const URL_OPERACOES_ALEATORIA  = '/operacoes/aleatoria';

var contador = 1;

/**
 * Notificacao
 * @param tipo
 * @returns
 */
function notificacao(tipo) {
	
	var classActive = "active notify-" + tipo;
	$(".notify").toggleClass(classActive);
	$("#notifyType").toggleClass(tipo);
	  
	setTimeout(function(){
	  $(".notify").removeClass(classActive);
	  $("#notifyType").removeClass(tipo);
	},1500);
	
};


/**
 * Atualiza Campos
 * @returns
 */
function atualizaCampos() {
	 $.ajax({url: URL_OPERACOES_ALEATORIA})
	 	.then(function(data) {
	 		$('.fator-a').empty().append(data.fatorA);
	 		$('.fator-b').empty().append(data.fatorB);
	 		$('.operador').empty().append(data.operador);
	 		$("#input-resultado").val("");
	 		contador = 1;
	 });
};


/**
 * Operacao
 * @param event
 * @returns
 */
function operacao(event) {
	 
	 event.preventDefault();

	 var a = $('.fator-a').text();
	 var b = $('.fator-b').text();
	 var operador = $('.operador').text();
	 var resultado = $("#input-resultado").val();
	 
	 var data = { 
		usuario:  { apelido: 'Um Nome' }, 
	 	operacao: { fatorA: a, fatorB: b, operador: operador }, 
	 	resultado: resultado
	 };
	 
	 $.ajax({
		 url: URL_RESULTADOS,
		 type: 'POST',
		 data: JSON.stringify(data),
		 contentType: "application/json; charset=utf-8",
		 dataType: "json",
		 success: function(resultado){
			 if(resultado.correto) {
				 notificacao('success');
			 } else {
				 notificacao('failure');
			 }
		 }
	 });

	 atualizaCampos(); 
};

/**
 * Executar Operacao
 * @returns
 */
function executarOperacao() {
	
	atualizaCampos();
	
	$("#input-resultado").keyup(function (event){
		if(event.keyCode == 13) {
		   operacao(event);
		}
	});
		
	$("#btn-resultado").click(operacao);
	
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
			atualizaCampos();
			notificacao('failure');
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