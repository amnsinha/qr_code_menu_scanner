import {HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';

/**
 * This class defines a base service class
 * Defines a pattern to get the base url for service calls
 */
export class BaseService {

  /**
   * Returns headers for static get operations, disables cache for get
   */
  protected getNoCacheRequestOptions(responseType ?: any, loading?: string): {} {
    return {
      headers: new HttpHeaders({
        'Cache-control': 'no-cache,no-store',
        Expires: '0',
        Pragma: 'no-cache',
        loading: loading ? loading : ''
      }),
      withCredentials: true,
      responseType: responseType
    };
  }

  protected getHttpHeaders() {
    return new HttpHeaders({
      'Content-type': 'application/json',
    });
  }

  /**
   * Returns the service url setting the server from the environment (if defined) in \Client\tools\env
   * @param path - relative path to the service
   */
  protected getServiceUrl(path: string): string {
    if (environment.api) {
      return environment.api + path;
    } else {
      return path;
    }
  }

  /**
   *  Return headers required for post operations, disables caching for post
   *
   */
  protected getPostRequestOptions(contentType: string = null, responseType?: any, loading?: string): {} {
    if (!contentType) {
      return {
        headers: new HttpHeaders({
          'Content-Type': 'application/x-www-form-urlencoded',
          'Cache-control': 'no-cache,no-store',
          'Expires': '0',
          'Pragma': 'no-cache'
        }),
        withCredentials: true,
        responseType: responseType
      };
    } else {
      return {
        headers: new HttpHeaders(
          {
            'Content-Type': contentType,
            'Cache-control': 'no-cache,no-store',
            'Expires': '0',
            'Pragma': 'no-cache',
          }),
        withCredentials: true,
        responseType: responseType
      };
    }
  }
}
