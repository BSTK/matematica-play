import {Observable} from 'rxjs';
import {Api} from '../../../app.api';
import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Desafio, DesafioTentativa, DesafioTentativaResposta} from './desafio.model';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private readonly httpClient: HttpClient) { }
  
  public desafioAleatorio(): Observable<Desafio> {
    return this.httpClient.get<Desafio>(Api.URLS.desafios.aleatorio);
  }
  
  public tentativasPorUsuario(apelido: string): Observable<DesafioTentativaResposta[]> {
    return this.httpClient.get<DesafioTentativaResposta[]>(
      Api.URLS.tentativas.tentativasPorUsuario, {
        params: new HttpParams().append('apelido', apelido)
      });
  }
  
  public verificarResposta(tentativa: DesafioTentativa): Observable<DesafioTentativaResposta> {
    return this
      .httpClient
      .post<DesafioTentativaResposta>(Api.URLS.tentativas.verificarResposta, tentativa);
  }
  
}
