import {Jogada} from './jogada.model';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor() { }
  
  public gerarJogadaAleatoria(): Jogada {
    const jogada = new Jogada();
    jogada.fatorA = Math.floor(Math.random() * 99);
    jogada.fatorB = Math.floor(Math.random() * 99);
    jogada.operacao = ['+','-','x','/'][Math.floor(Math.random() * 3)];
    return jogada;
  }
  
  public gerarNumeroAleatorios(): number[] {
    return [
      Math.floor(Math.random() * 99),
      Math.floor(Math.random() * 99),
      Math.floor(Math.random() * 99),
      Math.floor(Math.random() * 99)
    ];
  }
}
