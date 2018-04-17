package com.kuiiz.matematicaplay.operacao.domain;

public class Operacao {

	private int fatorA;
	private int fatorB;
	private int resultado;
	private Operador operador;
	
	/**
	 * Operacao
	 * @param fatorA
	 * @param fatorB
	 * @param operador
	 */
	public Operacao(int fatorA, int fatorB, Operador operador) {
		this.fatorA = fatorA;
		this.fatorB = fatorB;
		this.operador = operador;
		this.efetuarOperacao();
	}
	
	/**
	 * @return the fatorA
	 */
	public int getFatorA() {
		return fatorA;
	}

	/**
	 * @param fatorA the fatorA to set
	 */
	public void setFatorA(int fatorA) {
		this.fatorA = fatorA;
	}

	/**
	 * @return the fatorB
	 */
	public int getFatorB() {
		return fatorB;
	}

	/**
	 * @param fatorB the fatorB to set
	 */
	public void setFatorB(int fatorB) {
		this.fatorB = fatorB;
	}

	/**
	 * @return the resultado
	 */
	public int getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the operador
	 */
	public Operador getOperador() {
		return operador;
	}

	/**
	 * @param operador the operador to set
	 */
	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	/**
	 * Efetuar Operacao
	 * @param operador
	 * @return
	 */
	private void efetuarOperacao() {
		
		switch (operador) {
			case SOMA : 		
				resultado = fatorA + fatorB; break;
			case SUBTRACAO: 	
				resultado = fatorA - fatorB; break;
			case MULTIPLICACAO: 
				resultado = fatorA * fatorB; break;
			case DIVISAO: 		
				validaDivisaoPorZero();
				resultado = fatorA / fatorB; break;
		}
	}
	
	/**
	 * ValidaDivisaoPorZero
	 */
	private void validaDivisaoPorZero() {
		
		if (operador.equals(Operador.DIVISAO)) {
			if (fatorB == 0) {
				throw new IllegalArgumentException("Na operação de divisão (/) o fatorB não pode ser zero (0)");
			}
		}
		
	}
	
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("Operacao=[ ")
				.append("fatorA = " + fatorA)
				.append(", fatorB = " + fatorB)
				.append(", operador = " + operador.getSimbolo())
				.append(String.format(", resultado (%s %s %s) = %s", fatorA, operador.getSimbolo(), fatorB, resultado))
				.append(" ]")
				.toString();
	}

}
