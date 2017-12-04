import {AbstractValidationHandler} from './handlers/abstract.validation.handler';
import {RequiredValidationHandler} from './handlers/required.validation.handler';
import {EmailValidationHandler} from './handlers/email.validation.handler';
import {MaxlengthValidationHandler} from './handlers/maxlength.validation.handler';
import {MinlengthValidationHandler} from './handlers/minlength.validation.handler';

export class ValidationChain {
  private static _instance: ValidationChain = new ValidationChain();

  public static getInstance(): ValidationChain {
    return this._instance;
  }

  private constructor() {
    this._chain = new RequiredValidationHandler();
    const evh = new EmailValidationHandler();
    const mvh = new MaxlengthValidationHandler();
    const mvh2 = new MinlengthValidationHandler();

    this.chain.nextValidationHandler = evh;
    evh.nextValidationHandler = mvh;
    mvh.nextValidationHandler = mvh2;
  }

  private _chain: AbstractValidationHandler;

  get chain(): AbstractValidationHandler {
    return this._chain;
  }
}
