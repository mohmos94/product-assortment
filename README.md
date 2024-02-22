# Produktassortiment System

Dette prosjektet er utviklet for å hjelpe en liten oppstart som ønsker å konkurrere med IKEA. De trenger et enkelt system som holder styr på produktassortiment, kunder og solgte varer. Siden de ønsker å konkurrere med IKEA, vil de selge møbler, tekstiler og pølser.

## Forutsetninger

- Alt som selges hos oppstarten har en pris og en beskrivelse.
- Alle varer i kategorien Møbler har en varenr. og en vekt (utover pris og beskrivelse).
- Alle varer i kategorien Tekstiler har en varenr. og en farge (utover pris og beskrivelse).
- Pølser har en smak (utover pris og beskrivelse), men ingen varenr.
- Kunder kan ha medlemskap i oppstarten. Medlemmer får rabatt på en varekategorier.

## Eksempler:

- En kunde kjøper et teppe. Han er ikke medlem, så han får ingen rabatt.
- En annen kunde kjøper et teppe. Hun er medlem med rabatt. Derfor får hun rabatt på teppe.

## REST-API

Applikasjonen eksponerer REST-endepunkter for følgende funksjonalitet:

- **Opprett ordre**: Lar deg opprette en ny ordre.
- **Hent ordre**: Gir deg informasjon om en spesifikk ordre, inkludert produkter og eventuelle rabatter.
- **Hent produkt**: Gir deg informasjon om en spesifikk produkt.
- **Hent kunde**: Gir deg informasjon om en spesifikk kunde.


## Teknologier og verktøy

Dette prosjektet bruker følgende teknologier og verktøy:

- **Språk**: Java
- **Rammeverk**: Spring Boot
- **Database**: Postgres
- **Dokumentasjon**: Swagger
- **Byggeverktøy**: Maven

## Kjøring av prosjektet lokalt med docker

For å kjøre prosjektet lokalt med docker, følg disse trinnene:

1. Klone dette git-repositoriet til din lokale maskin ved å kjøre følgende kommando i terminalen:
2. Kjør mvn clean install
2. Start opp docker image med  docker-compose up 
3. Søk på http://localhost:8080/swagger-ui/index.html


## Kjøring av prosjektet lokalt med postgreSQL

For å kjøre prosjektet lokalt med docker, følg disse trinnene:

1. Klone dette git-repositoriet til din lokale maskin ved å kjøre følgende kommando i terminalen:
2. Lag database med navn product_assortment
2. start applikasjonen lokalt 
3. Søk på http://localhost:8080/swagger-ui/index.html

   ```bash
   git clone https://github.com/mohmos94/product-assortment.git
