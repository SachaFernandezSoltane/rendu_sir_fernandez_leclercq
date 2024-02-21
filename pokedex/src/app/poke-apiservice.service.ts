import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PokeDetails, PokeServiceRes } from './pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokeAPIServiceService {

  // Constantes pour les liens API
  private readonly baseUrl: string = 'https://pokeapi.co/api/v2/pokemon/';

  constructor(private http: HttpClient) { }

  getPokemon(): Observable<PokeServiceRes> {
    return this.http.get<PokeServiceRes>(this.baseUrl);
  }

  getPokemonInfos(id: number): Observable<PokeDetails> {
    const pokemonUrl: string = this.baseUrl + id + '/';
    return this.http.get<PokeDetails>(pokemonUrl);
  }
}
