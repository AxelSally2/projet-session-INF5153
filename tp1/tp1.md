% INF5153: Groupe 40 - Projet (Partie 1)
  Echecs
% Audrey EUGENE -- EUGA21589707
  Jean-Michel POIRIER -- POIJ26089200
% 12 octobre 2017

# Diagramme de cas d'utilisation

Ce diagramme présente les cas d'utilisation que peut effectuer chaque acteur (le joueur et le serveur) pour que le système de jeux d'échecs soit fonctionnel.
Le joueur doit être en mesure de lancer ses différents types de partie, de sauvegarder et charger une partie contre un joueur artificiel, de visualiser les parties une fois terminées
De plus, en cas de record de temps, il doit entrer ses informations.
Le serveur est celui qui va mettre en liaison deux joueur qui veulent démarrer une partie contre un humain.

![Diagramme de cas d'utilisation](useCaseDiagram.png)


# Diagramme de classe du système (modèle du domaine)

Ce diagramme présente les objets du domaine du jeu.
Un joueur lance une partie (contre un autre humain ou contre une IA).
En cas d'une partie avec une IA, le système dispose d'un document XML pour les sauvegardes et d'une mémoire des meilleurs temps selon le niveau de difficulté.

![Diagramme de classe du système](ContextDiagram.png)


# Diagrammes de séquence

Les diagrammes suivants modélisent les différentes fonctionnalités du système au travers des échanges des objets dans le temps.
Pour chacune des situations qui vont suivre, le diagramme associé présentera les messages échangés par l'utilisateur et le système de jeu.

## Choisir un adversaire

Ce cas illustre le fait de chercher un adversaire humain.

![Diagramme de séquence](DS1.png)

## Choisir un niveau de difficulté

Dans le cas où on choisit d'affrontrer une IA.

![Diagramme de séquence](DS2.png)

## Jouer un coup

Le joueur joue la partie.

![Diagramme de séquence](DS3.png)

## Sauvegarder une partie

Le joueur souhaite arrêter la partie pour l'instant et voudrait pouvoir la reprendre par la suite.

![Diagramme de séquence](DS4.png)

## Charger une partie

L'utilisateur veut reprendre une partie là où il a arrêté.

![Diagramme de séquence](DS5.png)

## Enregistrer un nouveau temps

Le joueur a eu un nouveau record de temps dans un niveau de difficulté face à une IA.

![Diagramme de séquence](DS6.png)

## Consulter les meilleurs temps

Le joueur veut voir les meilleurs temps qu'il a effectué.

![Diagramme de séquence](DS7.png)

## Visualiser une partie

À la fin d'une partie, l'utilisateur veut revoir la partie dans son intégralité.

![Diagramme de séquence](DS8.png)


# Diagramme de package

Le diagramme suivant établit les packages du systèmes et leurs liens. Le package présentation est celui qui traitera de "l'ouverture" de l'application, présentant le choix des types de parties et de consulter les records de temps de l'utilisateur.

![Diagramme de package](diagrammePackage.png)

# Diagramme de composants

Ce diagramme présente les composants du système et leur communication. L'application est donc lié l'entité de partie d'échecs et liée par une interface au service de recherche d'adversaire pour les parties contre un humain.

![Diagramme de composants](diagrammeComposants.png)


# Diagramme de déploiement

Le diagramme de déploiement ci-contre illustre la disposition physique des composants vu précédemment.
On ne sait pas sur quelle machine l'application sera déployée donc nous n'avons pas mis de noeud pour l'application.

![Diagramme de déploiement](diagrammeDeploiement.png)
