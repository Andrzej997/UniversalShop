import {LocaleFactory} from './locale.factory';
import {ClientContext} from '../core/base/client-context';

export class LocaleManager {

  private static getLocale(): string {
    return ClientContext.getInstance().locale;
  }

  static getValue(key: string): string {
    return LocaleFactory.getLocaleDefString(this.getLocale()).getValue(key);
  }

  private constructor() {
  };
}
