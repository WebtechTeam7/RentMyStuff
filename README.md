RentMyStuff
===========
![Logo] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/public/images/RentMyStuff_Logo.png)

HTWG Konstanz: Web-Technologien Projekt 
Teammitglieder: Ramona Barth, Dennis Klein und Jan Gaideczka

Einleitung
==========

Jeder kennt es vermutlich. Man zieht in eine neue Stadt um dort zu studieren oder eben aus anderen Gründen. Doch spätestens beim Aufbau der Möbel stößt man auf die ersten Probleme. Bohrmaschine, Schraubenzieher irgendetwas fehlt immer. Da wäre es doch sinnvoll eine Plattform zu haben, auf der man das benötigte Werkzeug suchen kann und es von jemandem der es Anbietet ausleihen kann. Genau das soll unser "RentMyStuff" -  Portal ermöglichen. 

Anforderungen
=============

Im folgenden Absatz werden unsere funktionalen Anforderungen sowie unsere nicht funktionalen Anforderungen an das Portal erläutert. Diese beschränken sich zunächst auf für uns relevante Punkte. Die Anforderungen können im Verlauf des Projektes weiter angepasst werden.

Funktionale Anforderungen
-------------------------

**1. Registrierung**
	 Der Nutzer muss sich einmalig am System registrieren um die Angebote einsehen zu können beziehungsweise um selbst Angebote erstellen zu können. Der Nutzer wählt dabei einen Usernamen sowie sein Passwort. Die Werte werden in der Datenbank gespeichert. 
	 
 **2. Profil pflegen OPTIONAL**
 Der Nutzer kann nach erfolgreicher Registrierung sein Profil pflegen. Unter anderem mit seiner Telefonnummer, E-Mail und ggf. auch mit seiner Adresse. 
 
**3. Gesuch vs. Angebot**
 In dem Portal kann man nach erfolgreicher Anmeldung auf der Startseite zwischen "Angebote" und "Gesuch aufgeben" unterscheiden. Per default werden eigens angelegte Angebote sowie Gesuche gelistet. Wird "Angebote" gewählt, erscheint eine neue Seite mit allen Angebote. Diese können mit verschiedenen Filtern beschränkt werden. Wird "Gesuche" gewählt, so öffnet sich eine Eingabemaske, die es dem Nutzer ermöglicht seinen Artikel bzw. seine Dienstleistung online zu stellen. 
 
 **4. Kategorisierung**
 Der Nutzer kann in der Eingabemaske zwischen verschiedenen Kategorien auswählen. Dies dient der einfacheren Verwaltung der Daten. Ebenso kann in der Suche nur eine Kategorie angegeben werden. Zu beginn werden wir uns auf 6 Kategorien beschränken, diese lauten wie folgt: Werkzeuge, Elektronik, Kraftfahrzeug, Gartengeräte, Dienstleistungen sowie Sonstiges.

nicht funktionale Anforderungen
-------------------------------

 **1. Technologien**
 Es werden alle verlangten Technologien genutzt. Weitere Infos folgen. 
 
Mockup-Screens
==============
**2 Eigene Einträge**
![Eigene Einträge] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/2_Eigene_Eintraege_Startseite.PNG)

**3.1 Angebote**
![Angebote] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/3.1_Angebote.PNG)

**4.1 Gesuche**
![Gesuche] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/mockups/4.1_Gesuche.PNG)


UseCase-Diagramme
==============
 
 Der Nutzer kann sich am System, also dem RentmyStuff Portal registrieren und nachdem die Daten in der Datenbank gespeichert wurden am System anmelden.
 
 ![Anmeldung] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Anmeldung.PNG)
 
 Der Nutzer kann einen Artikel als Angebot in das System einstellen. Dieses Angebot umfasst neben der Kategorie, eine Beschreibung sowie eine Adresse.
 
 ![Verleihen] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Ausleihen.PNG)
 
 Nutzer können die eingestellten Angebote anderer Nutzer einsehen, nach Kategorien filtern und mit dem Eigentümer via Mail Kontakt aufnehmen. Des Weiteren kann ein Nutzer ein Gesuch einstellen, welches wiederrum auch von anderen Nutzern eingesehen werden kann. 
 
 ![Ausleihen] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Use%20Case%20Diagramme/Ausleihen.PNG)
 
Klassendiagramm
==============

In folgender Grafik ist unser Klassendiagramm zu sehen. 

![Klassendiagramm] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/Klassendiagramm/rentmystuff_klassendiagramm.JPG)
 
Verwendete Technologien
==============

**HTML**

Verwendet wurde die aktuelle Version HTML5 von HTML (Hypertext Markup Language).
HTML wird dazu verwendet, um zu beschreiben, wie eine Seite im Browser ausgegeben werden soll.
In unserem Projekt ist dies ebenfalls der Fall. HTML wird verwendet um die Inhalte der Seite strukturiert darzustellen.
 
**CSS**

Mit der neuen Version CSS3 (Cascading Style Sheets) wurde das Design des Portals entworfen. 
CSS ist für die Darstellung und Formatierung von Webseiten zuständig. 
Eingesetzt wird es zusammen mit HTML und XML und wird ständig weiterentwickelt um neue Design-Möglichkeiten zu bekommen.

**Responsive Webdesign mit Bootstrap**

Bootstrap ist ein CSS-Framework mit HTML, CSS und JS, mit dem responsive Webdesign realisiert werden kann.
Mit Responsive Webdesign wird ermöglicht,dass sich der strukturelle Aufbau einer Webseite der Bildschirmauflösung des mobilen Endgeräts anpasst.

 ![Responsive Webdesign] (https://github.com/WebtechTeam7/RentMyStuff/blob/master/bilder/Responsive_RmS.JPG)

**Javascript und jQuery**

Javascript ist eine Skriptsprache, die es ermöglicht, objektorientierte Anwendungen in Internetseiten zu Implementieren. 
Es wird in unserem Portal verwendet, um Kategorien mit Ajax auswählen zu können. 
Ebenfalls wird es für die Validierung von Formularen verwendet und um Adressen für die Google maps API auszulesen.
Durch jQuery wird das Scripting für Webseiten vereinfacht. Es glättet die Unterschiede zwischen verschiedenen Browsern und füllt viele Lücken von Javascript.

**Java und PlayFramework**

Unterstützt vom Play Framework wurde das komplette Backend unseres Portals erstellt. Beispielsweise wurde die Anmeldung mit Java realisiert,
sowie die Hash-Verschlüsselung des Passworts oder das Session handling. Ebenso wurden Datenbankzugriffe um neue Gesuche oder Angebote zu erstellen, in Java geschrieben.
Play ist ein Web-Applikation Framework. Es wurde in Java und Scala geschrieben und verfolgt die  Model-View-Controller Sicht. Es bietet einen Web-Server, 
der im Standardumfang mitgeliefert wird und übernimmt viele Routineaufgaben.

**SQL Database**

In der SQL Datenbank werden die User, die Adressen, für den Standort, an dem Werkzeuge, Fahrzeuge, Gartengeräte etc. abgeholt werden können,
sowie die Anzeigen (Angebote, Gesuche) gespeichert.

**Websockets und JSON**

Mit Websockets ist es möglich, dass der Server Daten direkt an den Client senden kann und umgekehrt. Dies nennt man auch eine "bidirektionale Verbindung" zwichen Server und Client.
WebSockets fragen also Daten von einem Server ab, nachdem die Website aufgerufen wurde. 
WebSocket-Anfragen werden in Javascript programmiert. Die Verbindung zum Server bleibt dabei bestehen.
Im Portal Rent My Stuff wird der Client dann sofort über Änderungen informiert, wenn ein neues Angebot oder ein neues Gesuch erstellt wird.
Dieses wird dann bei anderen Nutzern sofort angezeigt.
JavaScript Object Notation (JSON) ist ein Datenformat das zum Datenaustausch zwischen Anwendungen genutzt wird.

**Heroku**

Heroku, ist eine mehrsprachige Cloud-Anwendungsplattform und ermöglicht es Entwicklern zu implementieren, zu skalieren und ihre Anwendungen zu verwalten. 
Es werden verschiedene Programmiersprachen, wie Ruby on Rails, Node.js, Python und Java unterstützt. Mit Heroku vereinfacht es die Arbeit beim Entwickeln, 
indem man sich beispielsweise nicht um einen Server kümmern muss, da dieser von Heroku bereitgestellt wird.


Probleme
==============

Während der der gesamten Projektphase gab es keine schwerwiegenden Probleme mit dem Projekt an sich und ebenso wenig gab es innerhalb des Teams Probleme. Unsere größten Probleme waren meistens Kleinigkeiten,
die wir in Griff bekamen. Eines der wenigen größeren Probleme trat bei der Datenbank auf. Da wir zu Beginn mit dem Problem kämpften, dass wir keine Angebote anlegen konnten, weil die Datenbank-File gesperrt wurde. Nachdem wir 
unsere Model-Klasse als ein Singelton implementierten und somit garantierten, dass es nur eine Verbindung zur Datenbank besteht, dachten wir das Problem sei gelöst. Doch das Problem stieß uns in unregelmäßigen Abständen wieder auf. Dieser Bugfix beanspruchte eine ganze Weile, da das Problem für uns 
nie klar verständlich und ebenso wenig reproduzierbar war. Schlussendlich haben wir es bis zum jetzigen Zeitpunkt gefixt. 
Ein weiteres kleines Problem war, dass man auf die verschiedenen Browser achten muss, so funktionieren teilweise funktionen im Google Chrome ohne Probleme, aber im Internet Explorer oder sogar Firefox machen sie Probleme. Dies betrifft vor allem Javascript. 
Wie in nahezu jedem Projekt war auch dieses mal die Zeit zum Ende hin sehr knapp. Das Ergebnis lässt sich trotz Termindruck und Prüfungsstress sehen. Wir konnten unsere Anforderungen alle umsetzen.

 
Was haben wir gelernt?/Fazit
==============
 
Angefangen von der Portal-Idee, über die Anforderungen bis hin zur Umsetzung der Technolgien bereicherte das RentMyStuff Portal unseren Wissenhorizont sehr. Wir lernten die Anforderungen in Arbeitspakete aufzugliedern, diese im Team aufzuteilen und anschließen umzusetzen. Äußerst ansprechend war dabei, dass wir dieses Projekt von der Idee bis zum Deployment begleiten durften. Dabei erlernten wir eine Menge an Technolgien und bekamen einen Einblick, in dem was alles hinter der Erstellung eines Portals steckt. Bereits aus dem Studium erlernte Kenntnisse konnten erfoglreich umgesetzt und weiterentwickelt werden. Das Projekt RentmyStuff war für alle Teammitglieder ein voller Erfolg.
 



 

