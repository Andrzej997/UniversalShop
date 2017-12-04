import {Directive, ElementRef, EventEmitter, HostListener, Input, OnChanges, Output, Renderer2, SimpleChanges} from '@angular/core';
import {LocaleManager} from '../../../locale/locale.manager';
import {LOCALE_CHANGED_EVENT} from '../../events/base-events';

@Directive({
  selector: '[locale]'
})
export class LocaleChangeDirective implements OnChanges {
  @Input('locale') _key: string;
  @Output('localeValueChange') localeValueChange = new EventEmitter<string>()
  private _value: string;

  constructor(private renderer: Renderer2, private el: ElementRef) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['_key']) {
      this.setLocalisedText();
    }
  }

  private setLocalisedText(): void {
    if (this._key !== null && this._key !== '') {
      this.el.nativeElement.innerText = this.getValueFromLocale(this._key);
      this._value = this.el.nativeElement.innerText;
      this.localeValueChange.emit(this._value);
    }
  }

  public getValueFromLocale(key: string): string {
    return LocaleManager.getValue(key);
  }

  @HostListener(`${LOCALE_CHANGED_EVENT}`, ['$event'])
  public onLocaleChanged(): void {
    this.setLocalisedText();
  }

}
