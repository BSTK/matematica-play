import {environment} from '../environments/environment';

export class Api {
  
  static readonly URLS = Object.freeze({
    desafios: {
      aleatorio: Api.urlDesafios('/desafios')
    },
    
    tentativas: {
      verificarResposta: Api.urlDesafios('/tentativas'),
      tentativasPorUsuario: Api.urlDesafios('/tentativas'),
    },
  
    lideres: {
      lideres: Api.urlGameficacao('/lideres')
    }
  });
  
  private static urlDesafios(path: string | string[]) {
    return environment.httpDesafiosHost
      .concat(environment.httpDesafiosApiV1)
      .concat(...path);
  }
  
  private static urlGameficacao(path: string | string[]) {
    return environment.httpGameficacaoHost
      .concat(environment.httpGameficacaoApiV1)
      .concat(...path);
  }
  
}
