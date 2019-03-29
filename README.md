# beerApplication

## Auteur 

Alice Duffourt

## Présentation

Projet ayant pour but de créer une application java faisant appel à une API Rest.
Cette application affiche plus de 2 000 bières du monde entier issuent d'une API créée spécialement pour le projet.

## Prérequis

* Installation d'Android Studio
* Récupérer la branche develop
* https://github.com/LudoCarlu/Pokemon.git

## Consignes respectées :

* Deux écrans : Un écran avec une liste et un écran avec un détail de l’item.
* Appel WebService à une API Rest.
* Affichage d'une liste dans un RecyclerView
* Stockage des données en cache.
* Architecture 
* Fonctions supplémentaires :
  * Barre de recherched
  * Tri des bières de A à Z, de Z à A et du taux d'alcool par volume 
  * Redirection vers le site de la brasserie de la bière sélictionnée
  
## Fonctionalités :

### Premier écran

* Splash affichant le logo de l'application ainsi qu'un rond de chargement pendant que les données de l'API se mettent a jour dans l'application

<img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/splashscreen.jpg" width="281.25" height="500">

### Ecran d'accueil

* Ecran affichant la liste des bières avec leur taux d'alcool par volume 
* affiche aussi un spinner tri et un espace de recherche 

<img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/1st%20screen.jpg" width="281.25" height="500">   <img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/sorthl.jpg" width="281.25" height="500">

<img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/research.jpg" width="281.25" height="500">   <img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/researchbis.jpg" width="281.25" height="500">

### Ecran du détail de la bière

* Ecran affichant les différentes informations sur la bière : 
  * taux d'alcool par volume
  * description
  * style
  * nom de la brasserie
  * adresse
  * téléphone
  * site web
* il est aussi possible de visité le site de la brasserie en cliquant sur le lien du site web

<img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/item.jpg" width="281.25" height="500">   <img src="https://github.com/aliceduffourt/beerApplication/blob/master/Image%20app/website.jpg" width="281.25" height="500">
