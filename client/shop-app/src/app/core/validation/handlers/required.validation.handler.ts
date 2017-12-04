import {AbstractValidationHandler} from './abstract.validation.handler';
import {LocaleManager} from '../../../locale/locale.manager';
import {StringUtils} from '../../utils/string.utils';

export class RequiredValidationHandler extends AbstractValidationHandler {

  constructor(params?: any) {
    super('required', params);
  }

  protected handleValidation(errors: any): string {
    const fieldName: string = this._params['fieldName'];
    let msg = LocaleManager.getValue('RequiredFieldValidationError');
    msg = StringUtils.substitute(msg, fieldName);
    return msg;
  }
}
