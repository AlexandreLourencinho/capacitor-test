export interface CategoryPlugin {
  echo(options: {value: string}): Promise<{value: string}>;
}
