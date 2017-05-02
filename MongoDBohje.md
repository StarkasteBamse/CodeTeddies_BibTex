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

### JAR-tiedosto, joka sisältää MongoDb-riippuvuudet
MongoDB:n saa käännettyä jar:iin mukaan käskyllä `gradlew shadowJar`. 