# BookShelf

Mobil- és webes szoftverek tárgyhoz tartozó nagyházi.  

## Bemutatás
Sokan vannak, akik szeretnek olvasni viszont nem könnyű számontartani a könyveket. Ez
szülte az alkalmazásötletet, ahol ezeket fogjuk tudni managelni. Több felhasználó is fel tudja
vezetni az általa már olvasott könyveket, illetve azokat is, amikre még nem jutott ideje, de
tervben van.

## Főbb funkciók
Az alkalmazás megnyitása után lesz lehetőségünk belépni a saját felhasználónk profiljába.
Ezt a felhasználóhoz rendelt NFC tag beolvasásával tehetjük meg. Amennyiben új taget
olvastunk be akkor létrehozunk egy új felhasználót.

Lehetőségünk lesz 2 listát számontartani. Az egyikbe fognak kerülni az adott felhasználó
által már elolvasott könyvek a hozzá tartozó információkkal. (pl: olvasás dátuma, könyv
szöveges értékelése illetve pontozása)

Másik listába pedig azon könyvek kerülnek, amiket még nem olvasott a felhasználó, viszont
felvezette, hogy szeretné kiolvasni. Amennyiben ez megtörtént, akkor majd át lehet helyezni
az olvasott könyvek közé.

Nézetek, amik kelleni fognak:

- Profile: a felhasználó adatai fognak megjelenni. Pl: név, eddig olvasott könyvek
száma, mikor regisztrált.
- ReadedList: Lista a felhasználó által már olvasott könyvekről.
- WishList: A még nem olvasott, de tervezett könyvek.
- Book: Egy adott könyv részleteit tárolja. Pl: cím, szerző, címke (milyen műfajba
tartozik), mikor lett elolvasva, értékelés

A könyvek listáinak megjelenítéséért ReciclerView lesz a felelős. A kód modularitása végett
pedig 2 külön Fragmentből fog állni az olvasott könyv adatlapja. Az egyik csak a könyv alap
adatait fogja tárolni (ezek jelnnek meg a még nem olvasottaknál). A másik pedig a kiolvasott
könyveknél megjelenítendő plusz információkat.

Minden adat (felhasználók, könyveik, listáik) a telefon perzisztens memóriájában lesznek
tárolva és innen fognak betöltődni, amikor megnyitjuk az alkalmazást.
