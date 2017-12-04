import {APPLICATION_CONTEXT_CHANGED} from '../events/base-events';

export class ApplicationContext {
  private _appId: string;
  private _browser: string;
  private static _instance: ApplicationContext = new ApplicationContext();

  private constructor() {
  };

  public static getInstance(): ApplicationContext {
    return this._instance;
  }

  public updateApplicationContext(appId: string, browser: string): void {
    this._appId = appId;
    this._browser = browser;
    window.dispatchEvent(new Event(APPLICATION_CONTEXT_CHANGED, {bubbles: true}));
  }

  get appId(): string {
    return this._appId;
  }

  set appId(value: string) {
    this._appId = value;
  }

  get browser(): string {
    return this._browser;
  }

  set browser(value: string) {
    this._browser = value;
  }
}
