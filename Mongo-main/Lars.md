# K_Solo
## Projektrapport: MongoDB Facade & CRUD
### Deltagare: Kerim Kozo

### Projektbeskrivning
Jag har skapat en MongoDB Facade som kan hantera CRUD operationer. Programmet är baserat på OOP och har klasser som Main, Person, Employee, Customer & dbFacade.
Alla objekt som sparas och hanteras finns i databasen.

### Dependencies
Maven: org.mongodb:bson:4.9.1  
Maven: org.mongodb:mongodb-driver-sync:4.9.1

### Vem har gjort vad?
Jag arbetade ensam.

### Planering & Genomförande  
Jag planerade att göra en MongoDB Facade som skulle kunna hantera CRUD operationer. Jag började med att skapa klassen Main, Person, Employee & Customer
och började skapa objekt. All kod kördes till en början från Main. När jag fick igång en koppling mot mongoDB och lyckades lägga in värden skapade jag klassen dbFacade som skulle sköta alla CRUD operationer.
Därefter experimenterade jag och vidareutvecklade koden.

### Utmaningar & Exempel
Det som har utmanat mig i detta arbete var att tänka utanför boxen och göra programmet lite mer avancerat än 
det vi har lärt oss under kursen. Det var en otrolig utmaning att få programmet att fungera så som jag ville.
Jag brukade tidigare använda mig utav .equals men fick nu använda instanceof istället. Detta var något nytt för mig
och jag tog det som en utmaning att lära mig detta. Det var också väldigt intressant när jag insåg att jag kan jag kan hantera ett objekt som ännu inte är tillagt i min lista och
måste därför uppdatera min lista för att den ska läggas in. Samt att ifall jag bytte ut Customer till Employee men inte uppdaterade listan så fick jag
nullpointerexception.

## Slutsatser
### Vad gick bra/dåligt & vad hade du kunnat gjort annorlunda?
Jag lyckades åstadkomma med det jag ville och det var betydelsefullt för mig.
Det som gick sämre var att jag inte alltid förstod varför ett sätt fungerar men ett annat inte gör det.
Det enda jag kan komma på som jag hade kunnat göra annorlunda var att inte göra koden så avancerad eller
att göra koden mer avancerad. Jag har fler ideer men på grund av tidsbrist så har jag inte kunnat implementera dem.

### Lärdomar & möjligheter
Jag har fått insikt i hur man arbetar med MongoDB och det kommer jag alltid kunna ha med mig.
Nu är det upp till mig att vidare utforska och skriva mer avancerad kod.
Denna kunskap som jag har fått från kursen kommer jag ha nytta av när jag söker framtida tjänster som efterfrågar denna kunskap,
då vet jag vad rollen innebär och vad jag kan erbjuda.

