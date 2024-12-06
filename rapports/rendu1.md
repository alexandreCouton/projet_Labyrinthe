#  Rapport rendu 1
____________________

## Introduction
Dans le cadre de notre projet de développement du jeu labyrinthe, 
nous avons dû faire différents choix de conception qui seront expliqués dans ce rapport. Bien évidemment tout au long de ce projet, nous utiliserons la méthode Agile à l'aide de Kanban inclus dans Git.
____
## Conception

### Choix generaux

Ce projet utilise une conception type MVC (Model View Controller) pour séparer les différentes parties du code. 
Le modèle contient les classes qui définissent les objets du jeu (player, gameBoard, etc). 
La vue contient les classes qui gèrent l'affichage du jeu. 
Le contrôleur contient les classes qui gèrent les interactions entre les objets du modèle et la vue.

### Choix techniques

Dans notre conception, nous avons choisi une classe Plateau qui sera l'élément central de notre jeu. 
Cette classe contient les informations sur le gameBoard de jeu (taille, cases, etc) et les méthodes pour le manipuler. 
Le gameBoard contient aussi les joueurs qui ont chacun un pion. 
Les pions sont des objets de la classe Pion qui contiennent les informations sur la position du pion sur le gameBoard.

Nous avons décidé de créer une classe Position pour gérer l'ensemble des positions des objets du jeu,
ce qui rend plus simple la gestion des positions des objets du jeu.

Le gameBoard contient aussi des tiles qui sont des objets de la classe Tuile qui contiennent les informations sur les cases du gameBoard. 
Les tiles peuvent être d'angle, en T ou droite. Chaque type de tiles contient son image associée. 
Et initialise son hashmap de la façon qui lui est propre. 
La hashmap contient une direction et un booléen, ce qui permet de connaître les différentes ouvertures actuelles d'une tiles.
La classe TuileFactory permet de créer des tiles de chaque type de façon simple et rapide.

Les objectifs enfin contiennent les informations sur les objectifs du jeu. Ils sont des objets de la classe Objectif.

Enfin, nous avons une enumération TuileOuverture qui contient des directions (Haut, Bas, Gauche, Droite) 
et qui permet d'augementer la lisibilité du code et de facilité la relecture.

Dans cette conception, le gameBoard est l'élément central car il permet d'initialiser le gameBoard et donc le jeu en plaçant
les tiles mais aussi les joueurs et les objectifs. 
Dans cette optique, il permet aussi de faire la liaison entre le contrôleur et les différentes classes ce qui limite les
dépendances entre les classes externes au modèle et le reste.

Pour le gameBoard nou avons fais le choix d'un tableau de tiles comme suit :
```java
m_lstTuilesPlateau = new Tuile[7][7];
/*[y],[x]
[[1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7],
[ 1,2,3,4,5,6,7]]*/
```

### Interactions entre les classes

Le contrôleur GameController joue un rôle central dans la gestion des interactions entre le modèle et la vue. 
Il maintient une liste d'observateurs (PlateauObserver) qui sont notifiés des changements d'état du jeu, comme le déplacement des tiles ou des joueurs. 
Le contrôleur utilise également le modèle pour initialiser la partie, placer les tiles, et gérer les tours des joueurs.



### Gestion des événements

Les événements dans le jeu, tels que le déplacement d'un player ou la capture d'un objective, sont gérés par le contrôleur. 
Par exemple, lorsqu'un player se déplace, le contrôleur appelle la méthode deplacer de la classe Joueur, qui met à jour la position du pion du player. 
Ensuite, le contrôleur notifie les observateurs de ce changement, ce qui permet à la vue de mettre à jour l'affichage en conséquence.

___
## Conclusion

En conclusion, notre conception MVC pour le jeu labyrinthe permet une séparation claire des responsabilités entre les différentes parties du code.
Cela facilite la maintenance et l'évolution future du projet.
Les choix techniques que nous avons faits, tels que l'utilisation de classes spécifiques pour gérer les positions et les tiles, rendent le code plus modulaire et réutilisable.
