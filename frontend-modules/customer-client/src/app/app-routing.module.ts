import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {HomeComponent} from './home/home.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'menu',
    pathMatch: 'full'
  },
  {path: 'home', component: HomeComponent},
  {path: 'menu/start/:token', loadChildren: () => import(`./menu-page/menu-page.module`).then(m => m.MenuPageModule)}
];

@NgModule({
  imports: [BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]

})
export class AppRoutingModule {
}
