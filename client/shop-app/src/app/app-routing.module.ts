import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PageNotFoundComponent} from './core/base/page-not-found/page-not-found.component';
import {LoginViewComponent} from './main-module/views/login-view/login-view.component';

const routes: Routes = [
  {
    path: '',
    children: []
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: true, errorHandler: error2 => {
      console.log(error2)
    }
  })],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
