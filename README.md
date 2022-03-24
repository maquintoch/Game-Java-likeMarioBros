# Sixth-antler
## Prosjekt Plattformspill

*"Super Mario ala Sixth-antler, samle pengene og kom deg igjennom nivåene uten å dø!"*

### Medlemmer i Sixth-antler:

- **Jonatan Valen** *(2.året Datateknologi)*
- **Marco Cardenas** *(3.året Informasjonsvitenskap)*
- **Yilin Yuan** *(2.året Datateknologi)*
- **Daniel Nyvoll** *(2.året Datateknologi)*
- **Tord Stang** *(3.året Informasjonsvitenskap)*
- **Lars Vestbo** *(3.året Data Science)*

Prosjektet og medlemmene har som mål og kunne presentere ett plattform-spill som er 2D og ved bruk av JavaFX.

Viktige krav og aspekter til spillet er:

1. Spillfigur som kan styres – gå til høyre/venstre, hoppe oppover
2. Todimensjonal verden: Plattform – horisontal flate spilleren kan stå eller gå på (inkludert «bakken»). Vegg – vertikal flate som spilleren ikke kan gå gjennom
   Spilleren beveger seg oppover ved å hoppe, og nedover ved å falle.
   
3. Fiender som beveger seg og er skadelige ved berøring
4. Spilleren kan samle poeng ved å plukke opp ting


### Game Setup with Maven
This project comes with a working Maven `pom.xml` file. You should be able to import it into Eclipse using *File → Import → Maven → Existing Maven Projects* (or *Check out Maven Projects from SCM* to do Git cloning as well). You can also build the project from the command line with `mvn clean compile` and test it with `mvn clean test`.

Pay attention to these folders:
* `src/main/java` – Java source files go here (as usual for Maven)
* `src/main/resources` – data files go here, for example in an `assets` sub-folder
* `src/test/java` – JUnit tests
* `target/classes` – compiled Java class files

You should probably edit the `pom.xml` and fill in details such as the project `name` and `artifactId`:


```xml

	< !-- FIXME - set group id -->
	<groupId>inf112.skeleton.app</groupId>
	< !-- FIXME - set artifact name -->
	<artifactId>javafx-app</artifactId>
	<version>1.0-SNAPSHOT</version>
	<packaging>jar</packaging>

	< !-- FIXME - set app name -->
	<name>mvn-app</name>
	< !-- FIXME change it to the project's website -->
	<url>http://www.example.com</url>
```


## Running
You can run the project from Eclipse/IntelliJ, or with Maven using `mvn javafx:run`. Change the main class by modifying the `main.class` setting in `pom.xml`:

```
		<main.class>inf112.skeleton.app.Main</main.class>
```

## Jar Files

If you run `mvn package` you get everything bundled up into a `.jar` file + a ‘fat’ Jar file where all the necessary dependencies have been added:

* `target/NAME-VERSION.jar` – your compiled project, packaged in a JAR file
* `target/NAME-VERSION-fat.jar` – your JAR file packaged with dependencies

Run Jar files with, for example, `java -jar target/javafx-app-1.0-SNAPSHOT-fat.jar`.

### Generelle krav:

* Java: Spillet skal skrives i Java, og dere vil få forslag til og tutorials for grafikkbibliotek.
* JUnit: For testing skal JUnit 5 brukes
* Byggesystem:  Dere kan velge mellom Maven og Gradle, men tips og sånt kommer til å være for Maven.
* Skjelett: Når dere skal begynne å programmere vil dere vil få et skjelettprosjekt dere kan bruke som utgangspunkt, men dere kan også sette opp prosjektet selv.
* Versjonskontroll: Bruk Git til versjonskontroll, og gjør aktiv bruk av kollaborativ funksjonalitet i GitLab.

