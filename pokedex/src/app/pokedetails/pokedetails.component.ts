import { Component, Input } from '@angular/core';
import { PokeDetails } from '../pokemon';
import { NgFor } from '@angular/common';
import { PokeShareInfoService } from '../poke-share-info.service';

@Component({
  selector: 'app-pokedetails',
  standalone: true,
  imports: [NgFor],
  templateUrl: './pokedetails.component.html',
  styleUrl: './pokedetails.component.css',
  providers: [PokeShareInfoService]
})
export class PokedetailsComponent {
  @Input('details')
  detail: PokeDetails | undefined

  constructor(private pokeShareService: PokeShareInfoService) {
    this.pokeShareService.getObservable().subscribe(e => console.log('e' + e))
  }

  ngOnInit(): void {
    console.log(this.pokeShareService.getObservable());
  }
}
