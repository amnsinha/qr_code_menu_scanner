import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {AuthGuardService} from './service/auth-guard.service';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {
    path: '',
    loadChildren: () => import('./main/main.module').then(m => m.MainModule),
    canActivate: [AuthGuardService]
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true}), DragDropModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
