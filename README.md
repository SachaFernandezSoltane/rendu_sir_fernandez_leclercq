# Rendu SIR TP 2 à 7 - Sacha Fernandez-Soltane et Paul Leclercq

* Prérequis : Avoir JAVA_HOME et MAVEN_HOME dans les variables d'environnements
* Avoir phpmyadmin en local et changer les identifiants pour se connecter à la base de données si besoin dans le fichier 
persistence.xml
---
## TP 2 à 5 : de JPA à Servelet

### Démarrer le projet ####

Pour démarrer le projet, faire la commande : 

```
cd /jpa 
```
puis dans le pom exécuter la commande : 

```
mvn clean:install
```
Ensuite lancer le serveur, puis lancer la classe situé dans "/jpa/src/main/java/jpa/JpaTest.java", cette classe devrait 
créer puis insérér des informations en base de données.

### Avancé du projet ###

**Q1-2)**
Dans un premier temps, nous avons créé des classes assez simplistes, étant donné qu'il y avait beaucoup de travaux pratiques à réaliser. Il n'était pas judicieux pour nous de perdre trop de temps sur celui-ci.

Pour ce faire, il faut utiliser les annotations @Entity pour définir les entités.

**Q3-4)**
Cela nous a permis de découvrir comment fonctionne le JPA et de mettre en place un début d'architecture. 
Ensuite, nous avons fait évoluer ce projet en ajoutant des DAO pour chaque entité. Nous l'avons ensuite connecté à notre base de données phpMyAdmin en local. 
Nous avons décidé de ne pas faire d'héritage au sein de nos classes pour le moment, car nous n'étions pas certains de comment nous allions gérer les types (ticket, user...). 
Plutôt que de perdre beaucoup de temps, nous avons préféré garder cette idée de côté et l'implémenter par la suite si le besoin se faisait sentir.
**Q5-6)**
Concernant le problème du N+1, nous le connaissions déjà et celui-ci a déjà été abordé en cours. Nous l'avons rapidement étudié sans pour autant approfondir le sujet.

Une fois la partie allant de 2 à 4 terminée, nous avons implémenté la partie 5 dans le même TP, à savoir les servlets. Le but, une fois de plus, était de prendre en main les servlets. 
Cette partie ne fera pas partie du TP final, car il n'était pas utile pour nous d'y consacrer trop de temps. 
Nous avons donc décidé de rester sur l'idée de faire simple et de continuer sur le chemin de la découverte.

**Partie 1 : Servlet**
**Q1-4)**
Nous avons ajouté les dépendances dans le pom.xml, et ajouté le code manquant, en rectifiant les petites erreurs qui traînaient.

**Q5)**
Nous avons ainsi implémenté deux pages : une page contenant un formulaire pour insérer un nouvel utilisateur dans la base de données, et une deuxième permettant de visualiser les utilisateurs présents dans la base de données.

Le formulaire qui permet d'insérer fonctionne assez simplement : on récupère les informations du formulaire lorsque l'utilisateur clique sur 'Envoyer', on crée une entité et ensuite on l'insère grâce au DAO.

Pour le deuxième, c'est encore plus simple : on instancie un DAO et on exécute la méthode findAll() du DAO.

---

## TP 7 : Pokédex en angular

### Démarrer le projet ####

Pour démarrer le projet, faire la commande :

```
cd /pokedex
```
puis dans le pom exécuter la commande :

```
npm install
```
et enfin :
```
ng serve
```
Ensuite il ne vous restera qu'à vous rendre sur l'url : "localhost:4200"

### Avancé du projet ###

Le projet a été réalisé à 100%, nous n'avons rien loupé, nous tenons à remonter que les vidéos sont très
utiles bien qu'elles nécessitent une mise à jour, en 5 ans, Angular a bien évolué et certains
modules n'existent plus. En dehors de ca, les explications sont claires et les vidéos de bonnes qualités, 
c'est un peu difficile au début de résoudre les problèmes de version, mais une fois ceux-ci passés tout marche 
assez bien rapidement.

Je pense que quasiment tout ce qui est dans ce TP sera utile pour la suite, néanmoins il pourrait être
judicieux de faire découvrir ngrx/store pour éviter de faire trop d'appels à l'API par exemple.

**Q1-Q7)** Nous avons commencé par initialisé le projet, nous avons créé les composants nécessaires, ajouter la classe 
Pokémon pour définir les pokémons, je ne détaillerais pas cette partie là, je mentionnerais juste qu'il faut importer le
composant pour l'inclure dans un autre composant (ne pas utiliser l'annotation @NgModule qui n'existe plus)

**Q7-9)** Pour la question du pipe, j'ai eu plusieurs problèmes, par exemple pour utiliser le pipe il faut l'importer dans le 
composant, sauf que pour l'importer il faut qu'il soit en standalone:true et ca c'est pas forcément écrit ni déjà fait par défaut.

```typescript
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
```
**Q9-14)** Ensuite, nous avons rajouté l'accès à l'API de pokeAPI en créant un service, puis tout avons "bind" le 
retour de la route de l'API avec une interface pour faciliter le traitement des données et l'affichage de celle-ci. 

Pour gagner du temps on a utilisé des outils pour obtenir directement les interfaces grâce au json que retourne la route.

Pour terminer nous avons fait un service qui permet de faire communiquer les composants que nous avons créé, pour se faire 
on fait comme ceci :

```java
public stringVar = new Subject<number>();
  constructor() { }

  getObservable(): Subject<number> {
    return this.stringVar;
  }

  setObservable(newStringVar: number) {
    this.stringVar.next(newStringVar);
  }
```
