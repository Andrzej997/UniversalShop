export class StringUtils {

  public static substitute(str: string, ...params: any[]): string {
    if (str === null || str.length <= 0) {
      return null;
    }
    if (params === null || params.length <= 0) {
      return str;
    }
    for (const idx in params) {
      const value = params[idx];
      str = str.replace(`{${idx}}`, value);
    }
    return str;
  }
}
