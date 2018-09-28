# Devoir Bataille Navale
(Louis Blasselle, Benoît Manhes, Axel Raffatin)

## Packages et classes :
Nous avons 4 packages qui contiennent chacun plusieurs classes :
  - Controller :
    - Main : démarre le jeu
    - Game : contient la boucle de jeu
  - Model :
    - Bateau : classe mère pour tous les bateaux
    - 5 sous classe de bateau (Torpilleur, Contre Torpilleur, Sous Marin, Croiseur, Porte Avion)
    - Joueur : classe de base pour les joueurs, contient les méthodes d'action du joueur (tirer, déplacer)
  - View
    - Affichage : contient une méthode pour afficher la grille et une pour afficher la liste des bateaux en vie
  - Exception
    - ExceptionChoixBateau : permet de ne pouvoir sélectionner que des bateaux existants et encore en vie
    - ExceptionMouvement : permet de sélectionner seulement un mouvement possible du bateau
    - ExceptionPlacement : permet de placer les bateaux à seulement à des positions réalisables
    - ExceptionTir : permet d'éviter les tirs hors champs de tir et hors de la grille
  
## Déroulement du jeu :
  - Saisie du pseudo des deux joueurs
  - Placement de tous les bateaux pour le premier joueur puis pour le second
  - Esuite, chaque joueur joue à tour de rôle, chaque tour consiste à :
    - sélectionner un bateau puis tirer
    - si le tir touche un bateau adverse, le tour se finie et le bateau adverse perd 1 point de vie ou est détruit si il n'a plus de vie
    - si le tir échoue, on choisit un bateau afin de pouvoir le déplacer deux fois en vertical ou horizontal
  - La partie se finie lorsque un des deux joueurs n'a plus de bateau en jeu
  - Pour finir, on demande si les joueurs veulent effectuer une nouvelle partie
  
## Remarques :
  - Les bateaux ne peuvent pas effectuer de rotation
