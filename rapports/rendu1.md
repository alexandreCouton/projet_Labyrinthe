#  Rapport rendu 1
____________________

## Introduction
Dans le cadre de notre projet de développement du jeu labyrinthe, 
nous avons dû faire différents choix de conception qui seront expliqués dans ce rapport.
____
## Conception

### choix generaux

Ce projet utilise une conception type MVC (Model View Controller) pour séparer les différentes parties du code. 
Le modèle contient les classes qui définissent les objets du jeu (joueur, plateau, etc). 
La vue contient les classes qui gèrent l'affichage du jeu. 
Le contrôleur contient les classes qui gèrent les interactions entre les objets du modèle et la vue.

### choix techniques

Dans notre conception, nous avons choisi une classe Plateau qui sera l'élément central de notre jeu. 
Cette classe contient les informations sur le plateau de jeu (taille, cases, etc) et les méthodes pour le manipuler. 
Le plateau contient aussi les joueurs qui ont chacun un pion. 
Les pions sont des objets de la classe Pion qui contiennent les informations sur la position du pion sur le plateau.

Nous avons décidé de créer une classe Position pour gérer l'ensemble des positions des objets du jeu,
ce qui rend plus simple la gestion des positions des objets du jeu.

Le plateau contient aussi des tuiles qui sont des objets de la classe Tuile qui contiennent les informations sur les cases du plateau. 
Les tuiles peuvent être d'angle, en T ou droite. Chaque type de tuile contient son image associée. 
Et initialise son hashmap de la façon qui lui est propre. 
La classe TuileFactory permet de créer des tuiles de chaque type de façon simple et rapide.

Les objectifs enfin contiennent les informations sur les objectifs du jeu. Ils sont des objets de la classe Objectif.

Enfin, nous avons une classe TuileOuverture qui contient des directions (Haut, Bas, Gauche, Droite) 
et qui permet de savoir si une tuile est ouverte dans une direction donnée mais qui pourra aussi être utilisée pour savoir si un joueur peut se déplacer dans une direction donnée.

Dans cette conception, le plateau est l'élément central car il permet d'initialiser le plateau et donc le jeu en plaçant
les tuiles mais aussi les joueurs et les objectifs. 
Dans cette optique, il permet aussi de faire la liaison entre le contrôleur et les différentes classes ce qui limite les
dépendances entre les classes externes au modèle et le reste.

Pour le plateau nou avons fais le choix d'un tableau de tuile comme suit :
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
Il maintient une liste d'observateurs (PlateauObserver) qui sont notifiés des changements d'état du jeu, comme le déplacement des tuiles ou des joueurs. 
Le contrôleur utilise également le modèle pour initialiser la partie, placer les tuiles, et gérer les tours des joueurs.



### Gestion des événements

Les événements dans le jeu, tels que le déplacement d'un joueur ou la capture d'un objectif, sont gérés par le contrôleur. 
Par exemple, lorsqu'un joueur se déplace, le contrôleur appelle la méthode deplacer de la classe Joueur, qui met à jour la position du pion du joueur. 
Ensuite, le contrôleur notifie les observateurs de ce changement, ce qui permet à la vue de mettre à jour l'affichage en conséquence.

___
## Conclusion

En conclusion, notre conception MVC pour le jeu labyrinthe permet une séparation claire des responsabilités entre les différentes parties du code.
Cela facilite la maintenance et l'évolution future du projet.
Les choix techniques que nous avons faits, tels que l'utilisation de classes spécifiques pour gérer les positions et les tuiles, rendent le code plus modulaire et réutilisable.
