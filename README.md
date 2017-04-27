# CodeTeddies_BibTex

## Tästä tulee Ohjelmistotuotanto Kevät 2017 miniprojekti

* [Speksi] (https://github.com/mluukkai/ohtu2017/wiki/miniprojekti-speksi) 
* [Backlogs] (https://docs.google.com/spreadsheets/d/1SRH5Wl2S941oJ2RSSWVe4ZDMs6_j_8OO5ymFnmnr7v8/)
---
[![Build Status](https://travis-ci.org/StarkasteBamse/CodeTeddies_BibTex.svg?branch=master)](https://travis-ci.org/StarkasteBamse/CodeTeddies_BibTex) 
 
[![Coverage Status](https://coveralls.io/repos/github/StarkasteBamse/CodeTeddies_BibTex/badge.svg?branch=master)](https://coveralls.io/github/StarkasteBamse/CodeTeddies_BibTex?branch=master)

## Ohje ohjelman suorittamiseen


1. Lataa .jar-tiedosto seuraavasta osoitteesta:
   https://github.com/StarkasteBamse/CodeTeddies_BibTex/releases/tag/sprintti3
2. Linuxissa komento jar avaa paketin, ks. alla.) Sen kummempaa asennusta ei tarvita, voit ajaa jar-tiedoston suoraan. Esimerkiksi Windowsissa sinun tarvitsee vain suorittaa komento
  
  java.exe -jar miniprojekti-all.jar

hakemistossa, johon tallensit titokone.jar-paketin. Linuxissa sinun
tulee vastaavasti ajaa komento terminaalissa:

  java -jar miniprojekti-all.jar

## MongoDb-ohje (Devaajille)
### Miniprojekti käyttää MongoDB dokumentti-tietokantaa viitteiden säilömiseen.
* MongoDb on integroitu gradlen kanssa. Se täytyy käynnistää ennen varsinaisen ohjelman ajoa localhostin tarjoamana TCP-palveluna (ip-osoite on 127.0.0.1 ja portti: 27017).

__Huom!__ Tietokanta on konffattu tällä hetkellä build-kansioon, joten ```gradle clean```-käsky pyyhkii myös tietokannan joka kerta tyhjäksi.

1. __Ennen ohjelman ajoa__ suorita komento: ```gradle startMongoDb``` tai vaihtoehtoisesti ```./gradlew startMongoDb```
   * MongoDB luo ```build/db```-kansioon uuden tietokannan, tietokannan suorituksen aikana kirjoittamat logit löytyvät ```build```-kansiosta ```embedded-mongo.log```-tiedostosta
2. __Suorittamisen jälkeen__ tietokanta suljetaan: ```gradle stopMongoDb``` tai vaihtoehtoisesti ```./gradlew stopMongoDb```-käskyllä

### Testaus/Buildaus:
Testaus onnistuu kätevästi käskyllä: 
* ```gradle startManagedMongoDb test```, jolloin MongoDb käynnistetään ennen testejä ja suljetaan automaattisesti testien jälkeen. 

Tietokanta on lisäksi nyt aluksi ainakin konffattu niin, että se käyttää testauksessa samaa varsinaista tietokannan instanssia kuin normaalistikin, mutta luo sinne testattaessa erillisen "test"-nimisen collectionin, joka poistetaan testien lopussa. Normaalisuorituksessa käytetään "references"-nimistä collectionia, joka säilyy tietokannassa testien ajamisesta huolimatta. 
