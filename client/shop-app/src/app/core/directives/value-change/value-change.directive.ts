import {Directive, ElementRef, HostListener, Input, Renderer2} from '@angular/core';
import {VALUE_CHANGED} from '../../events/base-events';

@Directive({
  selector: '[valueChange]'
})
export class ValueChangeDirective {

  @Input('valueChange') enabled = true;
  @Input('changeEnabled') changeEnabled = true;
  @Input('inputEnabled') inputEnabled = true;

  constructor(private renderer: Renderer2, private el: ElementRef) {
  };

  @HostListener('change', ['$event'])
  public onValueChange(evt: Event): void {
    if (this.changeEnabled) {
      this.onChange(evt);
    }
  }

  @HostListener('input', ['$event'])
  public onInput(evt: Event): void {
    if (this.inputEnabled) {
      this.onChange(evt);
    }
  }

  protected onChange(evt: Event): void {
    if (this.enabled) {
      window.dispatchEvent(new Event(VALUE_CHANGED, {bubbles: true}));
    }
    this.el.nativeElement.dispatchEvent(evt);
  }

}
