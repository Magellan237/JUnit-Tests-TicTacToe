package Praktikum;

public class TicTacToe {

private TicTacToeUI ui;
	
	final static int ENTSCHIEDEN = 1;
	final static int UNENTSCHIEDEN = 2;
	final static int LAEUFT = 3;
	final static int ABGEBROCHEN = 4;
	
	private int anzahlZuege = 0;

	private char[] spielerZeichen = new char[2];
	private char[][] spielbrett = new char [3][3];
	
	private int zugAnzahl=0;
	
	public TicTacToe(TicTacToeUI ui){

		this.ui = ui;
		
		spielerZeichen[0] = 'X';
		spielerZeichen[1] = 'O';
		
		resetSpiel();
	}
	
	/**
	 * Setzt das Spielfeld wieder in den Ursprungszustand.
	 */
	void resetSpiel(){
		
		anzahlZuege = 0;
		for(char zeile[]: spielbrett )
		{
			for (int i = 0; i < zeile.length; i++) {
				zeile[i] = ' ';				
			}
		}
	}
	
	/**
	 * Zuf�llige Ermittlung wer das Spiel beginnt.
	 * @return Array-Index des Spielers, der beginnt
	 */
	static int getStartSpieler(){
		
		return (int) Math.round( Math.random());
	}
	
	/**
	 * Bestimmt den Spieler, der als n�chstes am Zug ist.
	 * @param aktuellerSpieler Spieler, der aktuell am Zug ist
	 * @return Array-Index des n�chsten Spielers
	 */
	int getNaechstenSpieler(int aktuellerSpieler){
		
		return (aktuellerSpieler + 1) % 2;
	}
	
	/**
	 * Testet, ob das adressierte Feld im Array vorkommt und ob es frei ist.
	 * @param zeile Zeile des Zuges
	 * @param spalte Spalte des Zuges
	 * @return true, falls es vorkommt und frei ist - falls es nicht vorkommt oder belegt ist
	 */
	boolean isGueltig(int zeile, int spalte){
		
		if(spalte>2||spalte<0
				||zeile>2||zeile<0
				||spielbrett[zeile] [spalte] != ' '){

			return false;
		}
		else{

			return true;
		}
	}
	
	/**
	 * Ist das Spiel unentschieden?
	 * @return true, falls unentschieden
	 */
	boolean isUnentschieden(){
		
		boolean unentschieden = true;
		
		for (char[] zeile:spielbrett) {
			for(char spielfeld:zeile){
		
				if(spielfeld == ' ')
					unentschieden = false;
			}
		}
		
		return unentschieden;
	}
	
	/**
	 * Bestimmt den aktuellen Spielzustand
	 * @return
	 * LAEUFT: Weitere Spielz�ge m�glich
	 * UNENTSCHIEDEN: Spiel ohne Sieger beendet
	 * ENTSCHIEDEN: Es gibt einen Gewinner
	 */
	int getSpielzustand(){
		
		int status = LAEUFT;
		
		if (getGewinner() != ' '){
			
			return ENTSCHIEDEN;
		}
		
		if (isUnentschieden()){
			
			return UNENTSCHIEDEN;
		}
		
		return status;
	}
	
	/**
	 * F�hrt f�r den �bergebenen Spieler einen Spielzug durch.
	 * @param aktuellerSpieler  Spieler, der aktuell am Zug ist
	 * @return Spielzustand
	 */
	int spielzug(int aktuellerSpieler){

		int aktuelleZeile = 3;
		int aktuelleSpalte= 3;
		do {

			String zugEingabe = ui.getZugeingabe(spielbrett, spielerZeichen[aktuellerSpieler]);

			if (zugEingabe.equalsIgnoreCase("a")){ 
				
				return  ABGEBROCHEN;
				}

			try{
				aktuelleZeile = Integer.parseInt(zugEingabe)/10;
				aktuelleSpalte= Integer.parseInt(zugEingabe)%10;
			}
			catch (NumberFormatException nfe){}
			
			
		} while (!isGueltig(aktuelleZeile, aktuelleSpalte));
		
		spielbrett[aktuelleZeile][aktuelleSpalte] = spielerZeichen[aktuellerSpieler];

		anzahlZuege++;
		return getSpielzustand();
	}
	
	/**
	 * F�hrt ein komplettes Spiel durch.
	 * @return Spielzustand
	 */
	public int spiel(int startSpieler) {
				
		resetSpiel();

		int spielzustand = LAEUFT;
				
		int aktuellerSpieler = startSpieler; 

		do {
			
			spielzustand = spielzug(aktuellerSpieler);
					
			aktuellerSpieler = getNaechstenSpieler(aktuellerSpieler); 
					
		
		} while (spielzustand == LAEUFT);
				
		return spielzustand;
	}
	
	/**
	 * Pr�ft ob es einen Gewinner gibt und wer es ist.
	 * @return Zeichen des Gewinners oder Leerzeichen
	 */
	public char getGewinner(){
		
		char gewinner = ' ';
		
		for(char spieler: spielerZeichen ){
			
			for(int i=0; i<3; i++){
						
				if ((spielbrett[i][0] + spielbrett[i][1] + 
						spielbrett[i][2] == 3*spieler) ||
						(spielbrett[0][i] + spielbrett[1] [i] + 
								spielbrett[2][i] == 3*spieler)||
								(spielbrett[0][0] + spielbrett[1][1] + spielbrett[2][2] == 
									3*spieler) 
				 				||(spielbrett[2][0] + spielbrett[1][1] + spielbrett[0][2] == 
										3*spieler)
						) {
				
					gewinner = spieler;
					break;
				}
			}
		
		}
		
		return gewinner;
	}
	
	/**
	 * Gibt das Spielbrett zur�ck
	 * @return Spielbrett
	 */
	public char[][] getSpielbrett() {
		return spielbrett;
	}
	
	int getZugAnzahl() {
		return zugAnzahl;
	}

	/**
	 * Setzt das Spielbrett auf die �bergebenen Werte.
	 * @param spielbrett
	 */
	public void setSpielbrett(char[][] spielbrett) {
		this.spielbrett = spielbrett;
	}
	
	public int getAnzahlZuege(){
		return anzahlZuege;
	}
	
}
