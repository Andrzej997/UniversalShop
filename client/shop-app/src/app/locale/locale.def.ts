export abstract class LocaleDef {
  private _name: string;
  private _values: any;

  protected constructor(name: string, values: any) {
    this._name = name;
    this._values = values;
  }

  getValue(key: string): string {
    return this._values[key];
  }

  public static getLocale(): LocaleDef {
    return null;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get values(): any {
    return this._values;
  }

  set values(value: any) {
    this._values = value;
  }
}
