import {PlPlLocale} from './resources/pl-pl.locale';
import {EnUsLocale} from './resources/en-us.locale';
import {LocaleDef} from './locale.def';

export enum Locale {
  PL,
  US
}

export class LocaleTypesEnum {
  static PL: LocaleTypesEnum = new LocaleTypesEnum('Polski', 'PL', 'pl_PL', function (): LocaleDef {
    return PlPlLocale.getLocale();
  });
  static US: LocaleTypesEnum = new LocaleTypesEnum('English US', 'US', 'en_US', function (): LocaleDef {
    return EnUsLocale.getLocale();
  });

  static getLocales(): Array<LocaleTypesEnum> {
    return [LocaleTypesEnum.PL, LocaleTypesEnum.US];
  }

  private _name: string;
  private _shortname: string;
  private _locale: string;
  getLocale = function (): LocaleDef {
    return null
  };

  constructor(name: string, shortname: string, locale: string, locFuntion: () => LocaleDef) {
    this._name = name;
    this._shortname = shortname;
    this._locale = locale;
    this.getLocale = locFuntion;
  }

  static getLocaleTypeByShortname(locale: Locale): LocaleTypesEnum {
    switch (locale) {
      case Locale.PL:
        return this.PL;
      case Locale.US:
        return this.US;
    }
  }

  get name(): string {
    return this._name;
  }

  get shortname(): string {
    return this._shortname;
  }

  get locale(): string {
    return this._locale;
  }
}
