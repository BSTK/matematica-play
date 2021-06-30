import {environment} from '../environments/environment';

export class Api {
  
  static url(path: string | string[]) {
    return environment.httpWfinanceHost
      .concat(environment.httpWfinanceApiV1)
      .concat(...path);
  }
  
  static readonly URLS = Object.freeze({
    desafios: {
      aleatorio: Api.url('/desafios/aleatorio')
    },
    
    tentativas: {
      verificarResposta: Api.url('/tentativas'),
      tentativasPorUsuario: Api.url('/tentativas'),
    }
  });
  
}
