import {CLIENT_CONTEXT_CHANGED, LOCALE_CHANGED} from '../events/base-events';
import {LocaleUtils} from '../utils/locale.utils';

export class ClientContext {

  private static _instance: ClientContext = new ClientContext();
  private _isLoggedIn = false;
  private _userId = NaN;
  private _locale: string = 'US';

  private constructor() {
  }

  static getInstance(): ClientContext {
    return this._instance;
  }

  get isLoggedIn(): boolean {
    return this._isLoggedIn;
  }

  get userId(): number {
    return this._userId;
  }

  get locale(): string {
    return this._locale;
  }

  public updateContext(userId: number): void {
    this._userId = userId;
    this._isLoggedIn = !isNaN(userId);
    window.dispatchEvent(new Event(CLIENT_CONTEXT_CHANGED, {bubbles: true}));
  }

  public updateLocale(locale: string): void {
    this._locale = locale;
    window.dispatchEvent(new Event(LOCALE_CHANGED, {bubbles: true}));
  }
}
