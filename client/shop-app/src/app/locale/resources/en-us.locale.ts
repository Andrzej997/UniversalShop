import {LocaleDef} from '../locale.def';

export const EN_US = {
  SignIn: 'Sign in',
  MyAccount: 'My account',
  Search: 'Search',
  YetAnotherShopApp: 'Yet another shop app',
  Email: 'Email',
  Password: 'Password',
  Cancel: 'Cancel',
  ForgotPassword: 'Forgot your password?',
  RemindMe: 'Remind me!',
  HaveAccountYet: 'Don\'t have account yet?',
  RegisterNow: 'Register now',
  EmailIsEmpty: 'Email must not be empty',
  NotAValidEmail: 'Not a valid email',
  EmailExcedes100: 'Email length exceeds 100',
  PasswordLessThan6: 'Password length is less than 6',
  PasswordIsEmpty: 'Password must not be empty',
  PasswordExceedes20: 'Password length exceeds 20',
  RequiredFieldValidationError: 'Field "{0}" is required',
  MinLengthFieldValidationError: 'Field "{0}" does not contain required number of characters: {1}/{2}',
  MaxLengthFieldValidationError: 'Fields "{0}" contains to much characters: {1}/{2}',
  Language: 'Language'
};

export class EnUsLocale extends LocaleDef {
  private static instance: EnUsLocale = new EnUsLocale();

  public static getLocale(): LocaleDef {
    return this.instance;
  }

  private constructor() {
    super('en_US', EN_US);
  };
}
