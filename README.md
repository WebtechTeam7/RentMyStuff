RentMyStuff
===========
![Logo] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/public/images/RentMyStuff_Logo.png)

HTWG Konstanz: Web-Technologien Projekt 
Teammitglieder: 
	- Ramona Barth 286752 
	- Dennis Klein 286082
	- Jan Gaideczka 286572

Einleitung
==========

Jeder kennt es vermutlich. Man zieht in eine neue Stadt um dort zu studieren oder eben aus anderen Gründen. Doch spätestens beim Aufbau der Möbel stößt man auf die ersten Probleme. Bohrmaschine, Schraubenzieher, irgendetwas fehlt immer. Da wäre es doch sinnvoll eine Plattform zu haben, auf der man das benötigte Werkzeug suchen kann und es von jemandem der es Anbietet ausleihen kann. Genau das soll unser "RentMyStuff" -  Portal ermöglichen. 

Anforderungen
=============

Im folgenden Absatz werden unsere funktionalen Anforderungen sowie unsere nicht funktionalen Anforderungen an das Portal erläutert. Diese beschränken sich zunächst auf für uns relevante Punkte. Die Anforderungen wurden im Verlauf des Projektes weiter angepasst.

Funktionale Anforderungen
-------------------------

####1. Registrierung

 Der Nutzer muss sich einmalig am System registrieren um Angebote und Gesuche einsehen zu können beziehungsweise um selbst Angebote und Gesuche erstellen zu können. Der Nutzer verwendet dafür seine E-Mail-Adresse sowie ein Passwort.  Die Werte werden in der Datenbank gespeichert. 

####2. Profil pflegen OPTIONAL

Der Nutzer kann nach erfolgreicher Registrierung sein Profil pflegen. Unter anderem mit seiner Telefonnummer, E-Mail und ggf. auch mit seiner Adresse. 
 
####3. Gesuch vs. Angebot

 In dem Portal werden nach erfolgreicher Anmeldung auf der Startseite selbst erstellte Angebote und Gesuche angezeigt. In der Navigation kann ausgewählt werden zwischen "Angebote" und "Gesuche". Man gelangt bei dem Klick auf eines der Navigationselemente auf die jeweilige Seite, auf der alle Angebote sowie Gesuche von allen Usern angezeigt werden. Diese können nach verschiedenen Kategorien gefiltert werden. Ebenfalls können Angebote oder Gesuche erstellt werden.
 
####4. Kategorisierung
 
 Der Nutzer kann in der Eingabemaske zwischen verschiedenen Kategorien auswählen. Dies dient der einfacheren Verwaltung der Daten. Ebenso kann in der Suche nur eine Kategorie angegeben werden. Wir haben uns auf sechs Kategorien beschränken, diese lauten wie folgt: Werkzeuge, Elektronik, Fahrzeuge, Gartengeräte, Dienstleistungen sowie Sonstiges.

Nicht funktionale Anforderungen
-------------------------------

####1. Darstellung der Webseite
-Die Webseite soll in einem Responsive Design umgesetzt sein.
-Die Webseite soll intuitiv für den User bedienbar sein.

####2. Git
-Die Versionskontrolle soll via GitHub realisiert werden. 

 
Mockup-Screens
==============

####1 Eigene Einträge

![Eigene Einträge] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/2_Eigene_Eintraege_Startseite.PNG)

####2 Angebote

![Angebote] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/3.1_Angebote.PNG)

####3 Gesuche

![Gesuche] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/4.1_Gesuche.PNG)


UseCase-Diagramme
==============
 
Der Nutzer kann sich am System registrieren. Nachdem die Daten in der Datenbank gespeichert wurden kann er sich am System anmelden.
 
 ![Anmeldung] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Anmeldung.PNG)
 
 Der Nutzer kann einen Artikel als Angebot oder ein Gesuch in das System einstellen. Dieses Angebot umfasst neben der Kategorie, eine Beschreibung sowie eine Adresse.
 
 ![Verleihen] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Ausleihen.PNG)
 
 Nutzer können die eingestellten Angebote und Gesuche anderer Nutzer einsehen, nach Kategorien filtern und mit dem Eigentümer von angebotenen Leihgaben via Mail Kontakt aufnehmen. 
 
 ![Ausleihen] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Ausleihen.PNG)
 
Klassendiagramm
==============
![Klassendiagramm] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Klassendiagramm/rentmystuff_klassendiagramm.JPG)
 
Verwendete Technologien
==============

####HTML

Verwendet wurde die aktuelle Version HTML5 von HTML (Hypertext Markup Language).
HTML wird dazu verwendet, um zu beschreiben, wie eine Seite im Browser ausgegeben werden soll.
In unserem Projekt ist dies der Fall. HTML wird verwendet um die Inhalte der Seite strukturiert darzustellen.
 
####CSS

Mit der neuen Version CSS3 (Cascading Style Sheets) wurde das Design des Portals entworfen. 
CSS ist für die Darstellung und Formatierung von Webseiten zuständig. 
Eingesetzt wird es zusammen mit HTML und XML und wird ständig weiterentwickelt um neue Design-Möglichkeiten zu bekommen.

####Responsive Webdesign mit Bootstrap

Bootstrap ist ein CSS-Framework mit HTML, CSS und JS, mit dem responsive Webdesign realisiert werden kann.
Mit Responsive Webdesign wird ermöglicht,dass sich der strukturelle Aufbau einer Webseite der Bildschirmauflösung des mobilen Endgeräts anpasst.

 ![Responsive Webdesign] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/Responsive_RmS.PNG)

####Javascript und jQuery

Javascript ist eine Skriptsprache, die es ermöglicht, objektorientierte Anwendungen in Internetseiten zu Implementieren. 
Es wird in unserem Portal verwendet, um Kategorien mit Ajax auswählen zu können. 
Ebenfalls wird es für die Validierung von Formularen verwendet und um Adressen für die Google maps API auszulesen.
Durch jQuery wird das Scripting für Webseiten vereinfacht. Es glättet die Unterschiede zwischen verschiedenen Browsern und füllt viele Lücken von Javascript.

**Google Maps API**
 
 ![Javascript] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/Javascript.PNG)
 

**Validierung von Kategorien - Der Submit-Button kann erst geklickt werden, wenn eine Kategorie ausgewählt wurde**

 ![jQuery] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/jQuery.PNG)
 

####Java und PlayFramework

Unterstützt vom Play Framework wurde das komplette Backend unseres Portals erstellt. Beispielsweise wurde die Anmeldung mit Java realisiert,
sowie die Hash-Verschlüsselung des Passworts oder das Session handling. Ebenso wurden Datenbankzugriffe um neue Gesuche oder Angebote zu erstellen, in Java geschrieben.
Play ist ein Web-Applikation Framework. Es wurde in Java und Scala geschrieben und verfolgt die  Model-View-Controller Architektur. Es bietet einen Web-Server, 
der im Standardumfang mitgeliefert wird und übernimmt viele Routineaufgaben.

**Java Beispiel Anmeldung**

 ![Java] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/jQuery.PNG)
 
**PlayFramework - Model-View-Controller Architektur**

  ![MVC] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/Play-Framework_MVC.PNG)
  
 https://s3.amazonaws.com/nettuts/613_mvc/diagram.jpg

####SQL Database

In der SQL Datenbank werden die User, die Adressen, für den Standort, an dem Werkzeuge, Fahrzeuge, Gartengeräte etc. abgeholt werden können,
sowie die Anzeigen (Angebote, Gesuche) gespeichert.

####Websockets und JSON

Mit Websockets ist es möglich, dass der Server Daten direkt an den Client senden kann und umgekehrt. Dies nennt man auch eine "bidirektionale Verbindung" zwischen Server und Client.
WebSockets fragen also Daten von einem Server ab, nachdem die Website aufgerufen wurde. Die Verbindung zum Server bleibt dabei bestehen. 
WebSocket-Anfragen werden in Javascript programmiert. 
Unsere Idee der Websocket Umsetzung war folgende:
Im Portal Rent My Stuff wird der Client sofort über Änderungen informiert, wenn ein neues Angebot oder ein neues Gesuch erstellt wird.
Ist ein anderer Nutzer auf der Gesuch bzw. Angebotsseite dann wird die Website per Ajax nachgeladen. Leider konnten wir unsere Idee nicht umsetzen. Weitere Informationen dazu befinden sich im Kapitel "Probleme".
JavaScript Object Notation (JSON) ist ein Datenformat das zum Datenaustausch zwischen Anwendungen genutzt wird.

####Heroku

Heroku, ist eine mehrsprachige Cloud-Anwendungsplattform und ermöglicht es Entwicklern zu implementieren, zu skalieren und ihre Anwendungen zu verwalten. 
Es werden verschiedene Programmiersprachen, wie Ruby on Rails, Node.js, Python und Java unterstützt. Mit Heroku vereinfacht es die Arbeit beim Entwickeln, 
indem man sich beispielsweise nicht um einen Server kümmern muss, da dieser von Heroku bereitgestellt wird.

 ![Heroku] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/Heroku.PNG)
 
http://www.codecheese.com/wp-content/uploads/heroku-logo.png

Zusätzliche Features
==============

####Google Maps:
Wir entschieden uns dafür auf unserem Portal Google Maps einzubinden. Dies wird genutzt um dem User den Standort der Leihgabe zu zeigen. Dies erhöht die Usability für unsere User, denn wenn dieser ein interessantes Angebot entdeckt, kann er die Adresse des Angebots einsehen. Dies spart Zeit, denn so muss der User nicht dem Anbieter eine Email schreiben um die Adresse zu erhalten. 

![GoogleMaps] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/googlemaps.PNG)


Probleme
==============

Während der Projektphase kam es zu zwei größeren Problemen in der Entwicklung.
Eines der Probleme trat bei der Datenbank auf. Da wir zu Beginn mit dem Problem kämpften, dass wir keine Angebote anlegen konnten, weil die Datenbank-File gesperrt wurde. Nachdem wir 
unsere Model-Klasse als ein Singelton implementierten und somit 
garantierten, dass es nur eine Verbindung zur Datenbank besteht, 
dachten wir das Problem sei gelöst. Doch das Problem stieß uns in 
unregelmäßigen Abständen wieder auf. Dieser Bugfix beanspruchte eine 
ganze Weile, da das Problem für uns nie klar verständlich und ebenso wenig reproduzierbar war. Schlussendlich haben wir es bis zum jetzigen Zeitpunkt gefixt.
Das nächste Problem war der Zeitdruck. So reichte es für uns nicht mehr die Websockets nach unseren Vorstellungen umzusetzen. Da wir dabei auf mehr Probleme als gedacht stießen sind die Websockets zum Abgabe Zeitpunkt nicht funktionsfähig. 
Um die Systemfunktionalität nicht negativ zu beeinflussen haben wir die Websockets auskommentiert. So ist die Plattform ohne Einschränkungen nutzbar.

Was haben wir gelernt?/Fazit
==============
 
Angefangen von der Portal-Idee, über die Anforderungen bis hin zur Umsetzung der Technolgien bereicherte das RentMyStuff Portal unseren Wissenhorizont sehr. Wir lernten die Anforderungen in Arbeitspakete aufzugliedern, diese im Team aufzuteilen und anschließen umzusetzen. Äußerst ansprechend war dabei, dass wir dieses Projekt von der Idee bis zum Deployment begleiten durften. Dabei erlernten wir eine Menge an Technolgien und bekamen einen Einblick, in dem was alles hinter der Erstellung eines Portals steckt. Bereits aus dem Studium erlernte Kenntnisse konnten erfoglreich umgesetzt und weiterentwickelt werden. Das Projekt RentmyStuff war für alle Teammitglieder ein voller Erfolg.
 



 
