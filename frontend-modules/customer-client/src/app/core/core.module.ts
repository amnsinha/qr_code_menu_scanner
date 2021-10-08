import {NgModule, Optional, SkipSelf} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ToastService} from './services/toast/toast.service';

/**
 * The CoreModule is a pure services module with no declarations and contains providers for the singleton services
 * which are loaded when the application starts.  The CoreModule is imported in the root AppModule only.
 * Never import the CoreModule in any module other than the root AppModule.
 *
 * @author Gus Tsesmelis
 * Refer to https://angular.io/docs/ts/latest/cookbook/ngmodule-faq.html
 */

@NgModule({
  imports: [CommonModule],
  declarations: [],
  exports: [],
  providers: [
    ToastService
  ]
})

export class CoreModule {

  // Prevents a feature module from importing the CoreModule
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error('CoreModule is already loaded. Import in the root AppModule only.');
    }
  }
}
