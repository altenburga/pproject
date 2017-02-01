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
ProjectOfline
----------------------
1. open de package project programmingproject.controller
2. open de class ThreeDRow.java
3. Klik op run. 
4. Vul de gevraagde input in.

ProjectOnline
----------------------
ComputerPlayer of HumanPlayer?

1. open de package ProjectOnline.server.
2. open de class GameHandler.
3. Verander in de constructor de players die worden aangemaakt naar HumanPlayer of Computerplayer naar wens. 
4. open de class client.
5. Mocht je spelen met twee Humanplayers verander de code van de run() methode naar:
	
public void run() {
		while (true) {
			try {
				String messageReceived = in.readLine();
				if (messageReceived.equals(Protocol.SERVER_MOVEREQUEST)) {
					Field choice = one.determineMove();
					this.sendMessage(Protocol.CLIENT_SETMOVE + " " + choice.getX() + " " + choice.getZ());
				} else if (!messageReceived.startsWith("[" + clientName + "]")) {
					print(messageReceived);
				}
			} catch (IOException | OutOfBoundsException e) {

			}
		}
	}

6. Mocht je willen spelen met 2 Computerplayers, verander de code van de run() methode naar:

	public void run() {
		while (true) {
			try {
				String messageReceived = in.readLine();
			 if (!messageReceived.startsWith("[" + clientName + "]")) {
					print(messageReceived);
				}
			} catch (IOException e) {

			}
		}
	}

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
 