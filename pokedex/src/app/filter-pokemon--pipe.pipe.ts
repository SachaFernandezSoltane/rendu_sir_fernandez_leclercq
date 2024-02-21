import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  standalone : true,
  name: 'filterPokemon'
})
export class FilterPokemonPipe implements PipeTransform {

  transform(value: any[], property: string = '', searchString?: string): any {
    if (typeof value !== 'undefined') {
      return value.filter((e) => {
        return e[property]?.toLowerCase().includes(searchString?.toLowerCase());
      });
    } else {
      return [];
    }
  }

}
