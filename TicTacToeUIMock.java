package Praktikum;

public class TicTacToeUIMock implements TicTacToeUI{

		private int zug = 0;
		private String zugfolge[];
		
		
		@Override
		public String getZugeingabe(char[][] spielbrett, char aktuellerSpieler) {			
			return zugfolge[zug++];
		}
			
		public void setZugfolge(String[] zugfolge)
		{
			
			//this.zugfolge = new String[zugfolge.length];
			//for(int j=0;j<zugfolge.length;j++)
				//this.zugfolge[j] = zugfolge[j];
			
			
			this.zugfolge = zugfolge;
			zug = 0;
		}
}

