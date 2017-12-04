import {AbstractValidationHandler} from './abstract.validation.handler';
import {LocaleManager} from '../../../locale/locale.manager';
import {StringUtils} from '../../utils/string.utils';

export class MinlengthValidationHandler extends AbstractValidationHandler {
  constructor(params?: any) {
    super('minlength', params);
  }

  protected handleValidation(errors: any): string {
    const fieldName: string = this._params['fieldName'];
    const requiredLength: string = errors['requiredLength'];
    const actualLength: string = errors['actualLength'];
    let msg = LocaleManager.getValue('MinLengthFieldValidationError');
    msg = StringUtils.substitute(msg, fieldName, actualLength, requiredLength);
    return msg;
  }
}
