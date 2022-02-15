# sixth-antler
# Obligatorisk oppgave 1: Prosjekt Plattformspill – oppstart

*«Kurt-Mario er i trøbbel igjen! Han er sent ute til eksamen, han har ikke lest nok – og hvor er egentlig eksamenslokalet? Hjelp Kurt-Mario å hoppe fra etasje til etasje på Høyteknologisenteret, plukke opp viktig kunnskap (og snacks!) på veien og nå frem til eksamen i tide. Men pass opp for de skumle professorene – vi har ikke tid til å høre om flere teoremer nå!»*

Dette semesterets programmeringsprosjekt er å lage et [plattformspill](https://en.wikipedia.org/wiki/Platform_game) – et 2D-spill der du beveger deg rundt ved å gå eller hoppe og samler ting mens du passer deg for fiender. [Her er et populært eksempel på sjangeren](https://supermarioplay.com/) som dere kan prøve – det er sikkert lurt å gjøre grundig «research» før dere setter i gang!

(Dere står fritt til å velge plott til spillet – Kurt-Mario er bare et eksempel.)

Her er viktige aspekter å ha med i spillet:

* Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover
* Todimensjonal verden:
   * Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»)
   * Vegg – vertikal flate som spilleren ikke kan gå gjennom
   * Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle
* Fiender som beveger seg og er skadelige ved berøring
* Spilleren kan samle poeng ved å plukke opp ting
* Utfordringen i spillet er gjerne en eller flere av: å bevege seg gjennom terrenget uten å falle utfor, å samle nok poeng, å bekjempe fiendene, å nå frem til og bekjempe en «big boss» 

Vanlige aspekter dere kan vurdere å ha med:

* Verden er bygget opp av blokker med fast størrelse
* Verden har plattformer eller stiger som man kan hoppe opp gjennom
* Verden er større enn skjermen og scroller horisontalt eller vertikalt
* Plattformer som beveger seg
* Spilleren kan drepe fiendene ved å hoppe på dem eller skyte dem
* «Power-ups» som gir spilleren spesielle krefter
* Skjulte gjenstander
* Akrobatikk

Velkjente eksempler er [Donkey Kong (1981)](https://en.wikipedia.org/wiki/Donkey_Kong), [Mario Bros. (1983)](https://en.wikipedia.org/wiki/Mario_Bros.), [Metroid (1986)](https://en.wikipedia.org/wiki/Metroid), [Bubble Bobble (1986)](https://en.wikipedia.org/wiki/Bubble_Bobble), [Castlevania (1986)](https://en.wikipedia.org/wiki/Castlevania), [Sonic the Hedgehog (1991)](https://en.wikipedia.org/wiki/Sonic_the_Hedgehog) – alle disse er fremdeles populære og tilgjengelige i nye utgaver.

Generelle krav:

* Java: Spillet skal skrives i Java, og dere vil få forslag til og tutorials for grafikkbibliotek.
* JUnit: For testing skal JUnit 5 brukes
* Byggesystem:  Dere kan velge mellom Maven og Gradle, men tips og sånt kommer til å være for Maven.
* Skjelett: Når dere skal begynne å programmere vil dere vil få et skjelettprosjekt dere kan bruke som utgangspunkt, men dere kan også sette opp prosjektet selv.
* Versjonskontroll: Bruk Git til versjonskontroll, og gjør aktiv bruk av kollaborativ funksjonalitet i GitLab.

