import {ValidationErrors} from '@angular/forms';

export abstract class AbstractValidationHandler {

  protected _nextValidationHandler: AbstractValidationHandler;
  protected _name: string;
  protected _params: any;


  constructor(name: string, params?: any) {
    this._name = name;
    this._params = params;
  }

  public handleValidationErrors(errors: ValidationErrors): string {
    if (errors === null) {
      return null;
    }
    const propertyExists = errors.hasOwnProperty(this.name);
    if (propertyExists) {
      return this.handleValidation(errors[this.name]);
    } else if (this._nextValidationHandler !== null) {
      return this._nextValidationHandler.handleValidationErrors(errors);
    } else {
      return this.handleValidation(errors);
    }
  }

  protected handleValidation(errors: any): string {
    return null
  };

  set params(value: any) {
    this._params = value;
    if (this._nextValidationHandler != null) {
      this._nextValidationHandler.params = value;
    }
  }

  get nextValidationHandler(): AbstractValidationHandler {
    return this._nextValidationHandler;
  }

  set nextValidationHandler(value: AbstractValidationHandler) {
    this._nextValidationHandler = value;
  }

  get name(): string {
    return this._name;
  }
}
