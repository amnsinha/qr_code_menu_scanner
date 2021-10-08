import {environment} from './environment';

/**
 * Returns the service url setting the server from the environment (if defined) in \Client\tools\env
 * @param path - relative path to the service
 * @returns {string} - full url or relative path if base url not defined in environment
 */
export function getServiceUrl(path: string): string {
  if (environment.api) {
    return environment.api + path;
  } else {
    return path;
  }
}

