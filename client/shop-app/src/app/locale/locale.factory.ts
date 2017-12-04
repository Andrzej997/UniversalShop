import {LocaleDef} from './locale.def';
import {Locale, LocaleTypesEnum} from './locale.types.enum';

export class LocaleFactory {

  public static getLocaleDefString(localeShortname: string): LocaleDef {
    return this.getLocaleTypesEnum(localeShortname).getLocale();
  }

  public static getLocaleTypesEnum(localeShortname: string): LocaleTypesEnum {
    let locale: Locale = null;
    switch (localeShortname.toUpperCase()) {
      case 'PL':
        locale = Locale.PL;
        break;
      case 'US':
        locale = Locale.US;
        break;
    }
    return LocaleTypesEnum.getLocaleTypeByShortname(locale);
  }

  public static getLocaleDef(localeEnum: Locale): LocaleDef {
    return LocaleTypesEnum.getLocaleTypeByShortname(localeEnum).getLocale();
  }
}
