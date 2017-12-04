import {AbstractValidationHandler} from './abstract.validation.handler';
import {LocaleManager} from '../../../locale/locale.manager';

export class EmailValidationHandler extends AbstractValidationHandler {

  constructor(params?: any) {
    super('email', params);
  }

  protected handleValidation(errors: any): string {
    return LocaleManager.getValue('NotAValidEmail');
  }

}
