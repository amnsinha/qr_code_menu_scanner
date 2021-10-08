declare const require: any;

export function appReleaseInfo(): any {
  let pkg = require('../../../package.json');
  let clientReleaseInfo: any = {
    releaseName: pkg.name,
    releaseVersion: pkg.version,
    releaseDate: new Date(Date.now()),
    dependencies: [
      {
        name: 'Angular',
        version: pkg.dependencies['@angular/core'],
        licenseName: 'MIT',
        licenseURL: 'https://angular.io/license'
      },
      {name: 'ag-grid-angular', version: pkg.dependencies['ag-grid-angular'], licenseName: 'MIT'},
      {name: 'angular2-hotkeys', version: pkg.dependencies['angular2-hotkeys'], licenseName: 'MIT'},
      {
        name: 'font-awesome',
        version: pkg.dependencies['font-awesome'],
        licenseName: 'SIL OFL 1.1',
        licenseURL: 'http://fontawesome.io/license/'
      },
      {name: 'ng-lazyload-image', version: pkg.dependencies['ng-lazyload-image'], licenseName: 'MIT'},
      {name: 'ngx-translate', version: pkg.dependencies['@ngx-translate/core'], licenseName: 'MIT'},
      {name: 'primeng', version: pkg.dependencies['primeng'], licenseName: 'MIT'},
      {
        name: 'rxjs',
        version: pkg.dependencies['rxjs'],
        licenseName: 'Apache 2.0',
        licenseURL: 'http://www.apache.org/licenses/LICENSE-2.0'
      },
      {name: 'ImageViewer', version: pkg.dependencies['snapon-imageviewer'], licenseName: '', licenseURL: ''}
    ]
  };
  return clientReleaseInfo;
}
