import {Component, ViewChild} from '@angular/core';
import {MatButton, MatDialog, MatSelect} from '@angular/material';
import {BaseComponent} from './core/base/base.component';
import {ClientContext} from './core/base/client-context';
import {LoginViewComponent} from './main-module/views/login-view/login-view.component';
import {LocaleManager} from './locale/locale.manager';
import {FormControl, Validators} from '@angular/forms';
import {LocaleTypesEnum} from './locale/locale.types.enum';
import {LocaleFactory} from './locale/locale.factory';
import {LocaleUtils} from './core/utils/locale.utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent extends BaseComponent {
  private _title = '';
  private _isLoggedIn = false;
  private _langControl: FormControl = new FormControl('', [Validators.required]);
  private _locales: Array<LocaleTypesEnum> = [];

  @ViewChild('btnSearch') private btnSearch: MatButton;
  @ViewChild('accountButton') private accountButton: MatButton;
  @ViewChild('langSelect') private langSelect: MatSelect;

  constructor(public dialog: MatDialog) {
    super('AppComponent');
    this._title = 'YetAnotherShopApp';
  }

  ngOnInit() {
    super.ngOnInit();
    this.setupLanguageHandler();
  }

  private setupLanguageHandler(): void {
    this._locales = LocaleTypesEnum.getLocales();
    let locale = LocaleFactory.getLocaleTypesEnum(LocaleUtils.getDefaultLocale());
    if (locale == null) {
      locale = LocaleFactory.getLocaleTypesEnum('US');
    }
    this._langControl.setValue(locale);
    ClientContext.getInstance().updateLocale(locale.shortname);

    this._langControl.valueChanges.subscribe(
      value => ClientContext.getInstance().updateLocale((value as LocaleTypesEnum).shortname),
      error => console.log(error));
  }

  protected onClientContextChanged(evt: Event): void {
    //this._isLoggedIn = ClientContext.getInstance().isLoggedIn;
    //this.accountButton._elementRef.nativeElement.text = 'My account';
  }

  onInputSearchFocus(): void {
    if (this.btnSearch !== null) {
      this.btnSearch.color = 'primary';
    }
    ClientContext.getInstance().updateContext(10);
  }

  onInputSearchBlur(): void {
    if (this.btnSearch !== null) {
      this.btnSearch.color = 'accent';
    }
  }

  performSearch(text: string): void {
  }

  loginClick(): void {
    const dialogRef = this.dialog.open(LoginViewComponent, {
      width: '350px', height: '400px', id: 'loginDialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  langSelectValueChange(): void {
    ClientContext.getInstance().updateLocale('PL');
  }

  get title(): string {
    return this._title;
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }
}
