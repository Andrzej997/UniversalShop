import {LocaleDef} from '../locale.def';

export const PL_PL = {
  SignIn: 'Zaloguj się',
  MyAccount: 'Moje konto',
  Search: 'Szukaj',
  YetAnotherShopApp: 'Kolejny sklep',
  Email: 'E-mail',
  Password: 'Hasło',
  Cancel: 'Anuluj',
  ForgotPassword: 'Zapomniałeś hasła?',
  RemindMe: 'Przypomnij mi!',
  HaveAccountYet: 'Nie masz jeszcze konta?',
  RegisterNow: 'Zarejestruj się',
  EmailIsEmpty: 'E-mail nie może być pusty',
  NotAValidEmail: 'To nie jest prawdziwy email',
  EmailExcedes100: 'Wartość nie może przekroczyć 80 znaków',
  PasswordLessThan6: 'Hasło musi mieć więcej niż 6 znaków',
  PasswordIsEmpty: 'Hasło nie może być puste',
  PasswordExceedes20: 'Hasło nie może zawierać więcej niż 20 znaków',
  RequiredFieldValidationError: 'Pole "{0}" jest wymagane',
  MinLengthFieldValidationError: 'Pole "{0}" nie zawiera wymaganej ilości znaków: {1}/{2}',
  MaxLengthFieldValidationError: 'Pole "{0}" zawiera zbyt dużą ilość znaków: {1}/{2}',
  Language: 'Język'
};

export class PlPlLocale extends LocaleDef {
  private static instance: PlPlLocale = new PlPlLocale();

  public static getLocale(): LocaleDef {
    return this.instance;
  }

  private constructor() {
    super('pl_PL', PL_PL);
  }
}
