export class LocaleUtils {

  static getDefaultLocale(): string {
    let language: string = null;
    if (window.navigator.languages) {
      language = window.navigator.languages[0];
    } else {
      language = window.navigator.language;
    }
    if (language.length === 4) {
      return language.substr(2, 2).toUpperCase();
    }
    else {
      return language.substr(0, 2).toUpperCase();
    }
  }
}
