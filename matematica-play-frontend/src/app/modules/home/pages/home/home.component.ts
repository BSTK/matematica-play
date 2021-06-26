import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../service/home.service';
import {Desafio, DesafioTentativa, DesafioTentativaResposta} from '../../service/desafio.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
  /// TODO: USAR API DE AVATAR: https://avatars.dicebear.com/api/human/avatar-a.svg
  
  public desafio: Desafio = new Desafio();
  
  public classAcertoErro: string = '';
  public menssagemAcertoErro: string = '';
  
  constructor(private readonly homeService: HomeService) { }

  ngOnInit(): void {
    this.novoDesafio();
  }
  
  jogar(resposta: number) {
    const tentativa = new DesafioTentativa();
    tentativa.resposta = resposta;
    tentativa.apelido = 'Bruno Luz';
    tentativa.fatorA = this.desafio.fatorA;
    tentativa.fatorB = this.desafio.fatorB;
    tentativa.operacao = this.desafio.operacao;
    
    this.homeService
      .verificarResposta(tentativa)
      .subscribe((resposta: DesafioTentativaResposta) => {
        if (resposta && resposta.correta) {
          this.acertouResposta();
        } else {
          this.errouResposta();
        }
        
        setTimeout(() => {
          this.resetar();
          this.novoDesafio();
        }, 1000);
      });
  }
  
  private novoDesafio() {
    this.homeService
      .desafioAleatorio()
      .subscribe((desafio: Desafio) => {
        if (desafio) {
          this.desafio = desafio;
        }
      });
  }
  
  private acertouResposta() {
    this.classAcertoErro = 'acertou';
    this.menssagemAcertoErro = 'Acertou !!';
  }
  
  private errouResposta() {
    this.classAcertoErro = 'errou';
    this.menssagemAcertoErro = 'Errou !!';
  }
  
  private resetar() {
    this.classAcertoErro = '';
    this.menssagemAcertoErro = '';
  }

}
