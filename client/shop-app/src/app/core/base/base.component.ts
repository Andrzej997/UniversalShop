import {
  OnInit, OnChanges, OnDestroy, SimpleChanges, DoCheck,
  AfterContentInit, AfterContentChecked, AfterViewInit, AfterViewChecked, HostListener
} from '@angular/core';
import {
  APPLICATION_CONTEXT_CHANGED_EVENT, CLIENT_CONTEXT_CHANGED_EVENT, EDIT_FLAG_CHANGED, EDIT_FLAG_CHANGED_EVENT,
  VALUE_CHANGED_EVENT
} from '../events/base-events';
import {LocaleManager} from '../../locale/locale.manager';

export abstract class BaseComponent implements OnInit, OnChanges, OnDestroy, DoCheck,
  AfterContentInit, AfterContentChecked, AfterViewInit, AfterViewChecked {

  private name = 'BaseComponent';
  private _editFlag = false;

  constructor(name: string) {
    this.name = name;
  }

  public ngOnInit(): void {
  }

  @HostListener(`${CLIENT_CONTEXT_CHANGED_EVENT}`, ['$event'])
  protected onClientContextChanged(evt: Event): void {
  };

  @HostListener(`${APPLICATION_CONTEXT_CHANGED_EVENT}`, ['$event'])
  protected onApplicationContextChanged(evt: Event): void {
  };

  @HostListener(`${VALUE_CHANGED_EVENT}`, ['$event'])
  protected onValueChanged(evt: Event): void {
    this._editFlag = true;
    window.dispatchEvent(new Event(EDIT_FLAG_CHANGED, {bubbles: true}));
  }

  @HostListener(`${EDIT_FLAG_CHANGED_EVENT}`, ['$event'])
  protected onEditFlagChanged(evt: Event): void {
  };

  public getLabel(key: string): string {
    return LocaleManager.getValue(key);
  }

  public ngOnChanges(changes: SimpleChanges): void {
  }

  public ngOnDestroy(): void {
  }

  public ngDoCheck(): void {
  }

  public ngAfterContentInit(): void {
  }

  public ngAfterContentChecked(): void {
  }

  public ngAfterViewInit(): void {
  }

  public ngAfterViewChecked(): void {
  }

  set editFlag(value: boolean) {
    this._editFlag = value;
  }

  get editFlag(): boolean {
    return this._editFlag;
  }
}
