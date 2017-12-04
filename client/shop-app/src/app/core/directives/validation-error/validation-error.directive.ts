import {AfterViewInit, Directive, ElementRef, Input, OnChanges, Renderer2, SimpleChanges} from '@angular/core';
import {ValidationChain} from '../../validation/validation.chain';
import {FormControl} from '@angular/forms';

@Directive({
  selector: '[validationError]',
})
export class ValidationErrorDirective implements AfterViewInit, OnChanges {

  @Input('formControl') formControl: FormControl;
  @Input('fieldName') fieldName: string;
  @Input('fieldValue') fieldValue: any;

  constructor(private renderer: Renderer2, private el: ElementRef) {
  }

  public ngAfterViewInit(): void {
    this.getErrorMessage();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['formControl'] != null || changes ['fieldName'] != null || changes['fieldValue'] != null) {
      this.getErrorMessage();
    }
  }

  private getErrorMessage(): void {
    if (this.formControl === null) {
      return;
    }
    const params: object = {fieldName: this.fieldName};
    const chain = ValidationChain.getInstance().chain;
    chain.params = params;
    this.el.nativeElement.innerText = chain.handleValidationErrors(this.formControl.errors);
  }

}
