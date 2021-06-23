import {Component, OnInit} from '@angular/core';
import {Jogada} from '../../service/jogada.model';
import {HomeService} from '../../service/home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
  public jogada = new Jogada();
  public numerosJogada: number[] = [];
  
  public classAcertoErro: string = '';
  public menssagemAcertoErro: string = '';
  
  constructor(private readonly homeService: HomeService) { }

  ngOnInit(): void {
    this.tentativaResposta();
  }
  
  /// TODO: MOCK RESPOSTA CORRETA
  async jogar(resposta: number) {
    if (resposta < 30) {
      this.acertouResposta();
    } else {
      this.errouResposta();
    }
    
    await new Promise(f => setTimeout(f, 1000));
    
    this.tentativaResposta();
    this.resetar();
  }
  
  private tentativaResposta() {
    this.jogada = this.homeService.gerarJogadaAleatoria();
    this.numerosJogada = this.homeService.gerarNumeroAleatorios();
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
