import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PokeDetails, Pokemon } from '../pokemon';
import { NgFor,NgIf } from '@angular/common';
import { FilterPokemonPipe } from '../filter-pokemon--pipe.pipe';
import { PokeAPIServiceService } from '../poke-apiservice.service';
import { PokedetailsComponent } from '../pokedetails/pokedetails.component';
import { PokeShareInfoService } from '../poke-share-info.service';
@Component({
  selector: 'app-my-component',
  standalone: true,
  imports: [FormsModule, NgFor,NgIf, FilterPokemonPipe,PokedetailsComponent],
  templateUrl: './my-component.component.html',
  styleUrl: './my-component.component.css',
  providers : [PokeAPIServiceService,PokeShareInfoService],
})

export class MyComponentComponent {
  pokes: Pokemon[] = []
  selectedPokeId: number | undefined;
  searchPokeId = "";
  selectedPokemon : PokeDetails | undefined;

  constructor(private pokeService: PokeAPIServiceService,private pokeShareService: PokeShareInfoService) {
  }

  ngOnInit(): void {
    this.pokeService.getPokemon().subscribe((data) => {
      data.results.forEach((e: { name: string }, index: number) => {
        this.pokes.push(new Pokemon(index+1,e.name,data.next)); // Correction : utilisez 'index + 1' pour obtenir l'ID correct
      });
    });
  }


  findSelectedPokemon(): Pokemon | undefined {
    const selectedPokemon = this.pokes.find(poke => poke.id == this.selectedPokeId);
    return selectedPokemon;
  }

  go() {
      if(this.selectedPokeId){
            this.pokeShareService.setObservable(this.selectedPokeId);

        this.pokeService.getPokemonInfos(this.selectedPokeId).subscribe((data) => {
            this.selectedPokemon = data
        })
      }
  }

}
