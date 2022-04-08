#Prosjektrapport

###Rollefordeling:
Etter møtet vi hadde har vi fått en bedre fordeling av arbeidsoppgaver. Vi har fortsatt ganske frie roller, men er blitt bedre på å fordele arbeid. Dette har fungert bedre siden kommunikasjonsflyten også har blitt vesentlig forbedret.

###Prosjektmetodikk:
Vi bruker fortsatt scrum. Vi bryter ned arbeidet i små mål som skal fullføres før vi begynner på nye mål. Vi bruker tirsdagsmøtene som slutten av sprintene der vi kjører sprintgjennomgang og viser utført arbeid. Vi diskuterer og reflekterer over hva som har blitt gjort og hva vi bør gjøre til neste gang. I stedet for daglige møter har vi en kontinuerlig kommunikasjon på discord.

###Gruppedynamikk/kommunikasjon/samarbeid:
Her har vi sett stor fremgang og at flere i gruppen er mer aktive og til stedet. Gruppedynamikken er blitt bedre da vi har blitt enda bedre kjent med hverandre. Vi har også fått en større forståelse for hverandres styrker og svakheter, samt ferdigheter og egenskaper.

###Retrospektiv:

Vi møter nå problem med å lage unit test på grunn av tett sammenkoblet logikk i enkelte metoder. Det er på grunn av at vi ikke har laget en struktur med tydelig logikk i begynnelsen. Selv om det kan ha muligheter til å modifisere strukturen i løpet av prosjektet, er det en utfordring som vil være tidkrevende og omfattende.
Vi har blitt bedre på å lage issues og oppdatere project board.
Når det kommer til brukerhistorier så har dette vært en læringsprosess for oss.

###Forbedringspunkter:
Vi bør bli bedre på å strukturere både ved å lage logikken i enkelte metoder og ved å lære oss å bruke Junit 5 for testing koden.
Vi bør lage noen prosedyre på hvordan vi kan kjøre tester.
Vi bør ta hensyn til å lære oss Mockito som et mulig framework for testing.
Vi skal diskutere om vi skal bruke noen algoritmer som for eksempel A*, BFS, DFS, osv..for å innføre kanskje en kunstig intelligens problemløsning.
Endringen av størrelse på bilder / brikkene, og til opplysningen til spillet.
Lage mulighet for å spille multiplayer over internett.

###Møtereferat:
Se vedlagte dokumenter:
- Møtereferat 05:04:2022.docx
- Møtereferat 08:04:2022.docx
- Møtereferat 29:03:22.docx

##Deloppgave 2: krav

###Hva har vi gjort siden forrige gang?
Fikset på mappet slik at vi kan tegne inn enemies der vi vil.
Fikset buggen vi hadde når player falt utenfor mappet.
Lagt til figurer for players.
Lagt til lydeffekter ved interaksjoner og bevegelse.
Fikset helse fremvisning
Laget mulighet til å hoppe på motstander
Fikset hoppe funksjonalitet

###Hvordan har vi prioritert oppgaver fremover?
Lage flere automatiske tester er hovedmålet fremover.
Gjøre spillet mer brukervennlig (Visibility).
Mer feedback fra spillet: vise score når du dør.
Database for lagring av highscore.
Mulighet til dash når spiller trykker shift.
Nye blokktyper (blokk som spretter spiller, blokk som flytter deg opp/høyre/venstre, blokk som bare fiender kan kollidere med, blokk som endrer gravitasjon til å være oppned, blokk som gjøre at spilleren går fortere, blokk som gjøre at det er mer gravitasjon, blokk som gjør at spiller ikke blir påvirket av gravitasjon osv.)
Multiplayer over internett (client/server).

###Bugs som finnes i koden:
Hvis lyden på enheten din er deaktivert, vil dette føre til at spillet ikke kjører.
Det er ikke mulig å spille uten nett fordi bakgrunnen og andre bilder hentes direkte fra nettet. Dette fører til en runtime error og du kan ikke spille videre.
Fullskjerm medfører at når spiller laster inn ny skjerm, blir ikke alle elementene skalert på riktig måte.


##Deloppgave 3: Produkt og kode

###Dette har vi fikset siden sist mtp tilbakemeldinger fra kunden:
Vi fikk tilbakemeldinger fra kunden rett før fristen, så fikk ikke sett på dette før vi leverte. Håper på bedre kontakt med kunden videre:)

Selvom vi modifiserer koden, inkludert strukturen gradvis, finnes det fremdeles problem med SRP nå. Det skal vi gjøre best til å fikse til neste obligen. Siden SRP er nødvendig for en tydelig logikk i koden og hjelper mye til tests dekning.
####Manuelle tester:
Se dokumentet Manuelle Tester.docx