import {Component, Inject} from '@angular/core';
import {BaseComponent} from '../../../core/base/base.component';
import {FormControl, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.scss']
})
export class LoginViewComponent extends BaseComponent {

  email = new FormControl('', {validators: [Validators.required, Validators.email, Validators.maxLength(100)], updateOn: 'change'});
  password = new FormControl('', {
    validators: [Validators.minLength(6), Validators.required, Validators.maxLength(20)],
    updateOn: 'change'
  });

  constructor(public dialogRef: MatDialogRef<LoginViewComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    super('LoginViewComponent');
  }

  getEmailErrorMessage(): string {
    return this.email.hasError('required') ? this.getLabel('EmailIsEmpty') :
      this.email.hasError('email') ? this.getLabel('NotAValidEmail') :
        this.email.hasError('maxlength') ? this.getLabel('EmailExcedes100') :
          '';
  }

  getPasswordErrorMessage(): string {
    return this.password.hasError('minlength') ? this.getLabel('PasswordLessThan6') :
      this.password.hasError('required') ? this.getLabel('PasswordIsEmpty') :
        this.password.hasError('maxlength') ? this.getLabel('PasswordExceedes20') :
          '';
  }

  onLoginClick(): void {
    console.log('Login');
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
