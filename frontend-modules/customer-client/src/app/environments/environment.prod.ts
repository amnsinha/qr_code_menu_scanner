import {appReleaseInfo} from './app-release-info';

export const environment = {
  production: true,
  api: '/epc-services',
  releaseInfo: appReleaseInfo()
};
