import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginViewComponent} from './main-module/views/login-view/login-view.component';
import {MaterialModule} from './core/material/material.module';
import {PageNotFoundComponent} from './core/base/page-not-found/page-not-found.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatNativeDateModule} from '@angular/material';
import {ValueChangeDirective} from './core/directives/value-change/value-change.directive';
import {LocaleChangeDirective} from './core/directives/locale-change/locale-change.directive';
import {LabelComponent} from './core/controls/label/label.component';
import {ValidationErrorDirective} from './core/directives/validation-error/validation-error.directive';

@NgModule({
  declarations: [
    AppComponent,
    LoginViewComponent,
    PageNotFoundComponent,
    ValueChangeDirective,
    LocaleChangeDirective,
    LabelComponent,
    ValidationErrorDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [LoginViewComponent]
})
export class AppModule {
}
