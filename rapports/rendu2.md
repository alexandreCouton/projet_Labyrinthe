# Rapport rendu 2

---

## Introduction

Dans le cadre de notre projet de développement du jeu **Labyrinthe**,  
nous allons expliquer, dans ce deuxième rendu, les différents changements de conception apportés à notre projet.
Pour le partage de code, nous avons utilisé le partage de code d'intellij por travailler simultanément.
---

## Conception

### Choix généraux

Ce projet utilise une conception de type **MVC** (*Model-View-Controller*) pour séparer les différentes parties du code.  
Nous avons suivi les choix de conception planifiés précédemment, un choix qui s'est avéré judicieux et réfléchi pour le développement du jeu.

---

### Choix techniques

Dans l'ensemble, notre conception de base reste inchangée, mais nous avons introduit une classe `Game` pour centraliser les informations liées au jeu.

- La classe `Game` contient les informations principales (joueurs, plateau, etc.) et les méthodes permettant de les manipuler indirectement.
- Elle inclut un objet de la classe `gameBoard`, qui contient les données du plateau de jeu comme la taille, les cases, etc., ainsi que les méthodes nécessaires pour les manipuler.
- La classe `Game` gère également les joueurs, chacun possédant un pion et des objectifs.

Ce deuxième rendu s'est particulièrement concentré sur la séparation des responsabilités entre les différentes classes afin de limiter les dépendances, ce qui rend le code plus modulaire et réutilisable.  
Par exemple, seule la classe `GameBoard` connaît la taille du plateau, et seules les classes responsables des positions réalisent les opérations associées.

Nous avons également décomposé les méthodes volumineuses en plusieurs méthodes plus petites et spécifiques.  
Cette démarche améliore la lisibilité et la maintenabilité du code, tout en simplifiant le débogage et en réduisant la complexité de chaque méthode.

Les changements majeurs concernent la **vue**, qui a été entièrement implémentée.  
Elle comprend plusieurs classes, chacune gérant un aspect précis de l'interface graphique. 
Par exemple, la classe `GameDisplay` réunit tous les éléments de l'interface graphique et les affiche.

---

### Interactions entre les classes

Le `GameController` a également été retravaillé pour se concentrer uniquement sur les interactions entre le joueur et la vue.

---

### Gestion des événements

Nous avons ajouté plusieurs **Observers** pour permettre à un plus grand nombre de classes d'être observées de manière simple et efficace.

---

## Conclusion

En conclusion, nos choix de conception ont été mûrement réfléchis et ont permis de rendre le code plus modulaire et réutilisable.  
Grâce à ces choix initiaux, nous avons pu implémenter la vue et les interactions entre les classes sans rencontrer de grandes difficultés.  
Nous avons ainsi pu progresser rapidement sur le projet et fournir un code propre, fonctionnel et évolutif.
