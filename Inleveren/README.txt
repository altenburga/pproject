Wat is dit?
-----------
Dit bestand bevat informatie over het installeren en het starten van het spel Connect Four 3D.

Hoe installeer ik het spel?
---------------------------
1. Pak het ZIP bestand uit in een gewenste map.
2. Open Eclipse.
3. Selecteer File > Import in het hoofdmenu.
4. Selecteer General > Excisting Project into Workspace en klik op Next.
5. Kies voor Select root directory en klik op Browse om naar de map te gaan waarin het project staat opgeslagen.
6. Onder het kopje Projects, selecteer het project dat je wilt installeren.
7. Klik op Finish om het importeren te starten.

Hoe start ik het spel?
----------------------
De Client en de Server lopen op dezelfde computer:
1. Open de package projectOnline.server.
2. Klik met de rechter muisknop op Server.java.
3. Selecteer Run As > Run Configurations...
4. Ga naar Arguments.
5. Onder het kopje Program arguments vul je een een poortnummer in (bijvoorbeeld 1337).
6. Klik op Apply.
7. Klik op Run.
9. Klik met de rechter muisknop op Client.java.
10. Selecteer Run As > Run Configurations...
11. Ga naar Arguments.
12. Onder het kopje Program arguments vul je een localhost en een poortnummer in. (bijvoorbeeld 127.0.0.1 1337). Het poortnummer moet het zelfde nummer zijn als die van de server.
13. Klik op Apply.
14. Klik op Run.

De server loopt op een andere computer:
1. Open de package projectOnline.server.
2. Klik met de rechter muisknop op Client.java.
3. Selecteer Run As > Run Configurations...
4. Ga naar Arguments.
5. Onder het kopje Program arguments vul je het IP-adres van het netwerk waarmee de server verbonden is en een poortnummer. Het poortnummer moet het zelfde nummer zijn als die van de server.
6. Klik op Apply.
7. Klik op Run.
 