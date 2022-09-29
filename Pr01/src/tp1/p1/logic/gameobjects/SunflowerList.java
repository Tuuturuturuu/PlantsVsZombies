package tp1.p1.logic.gameobjects;
import tp1.p1.logic.ZombiesManager;
public class SunflowerList {
 private int contador;
 private Sunflower[] sunflowers;
 
 
 
 public SunflowerList(int contador) {
	 this.contador = contador;
	 this.sunflowers = new Sunflower[contador];
 }
 
 
 public void addSunflower( int col, int row) {
		Sunflower sunflower = new Sunflower(col, row);
		 sunflowers[contador] = sunflower;              
		 contador++;
 }
 public void plantaMuerta(int pos) {
	 contador--;
	for(int i = pos; i < contador; i++) {
		sunflowers[i] = sunflowers[i + 1];
	}
 }
 public int buscarPlanta(int col, int row) {
	 boolean libre = true;
	int pos = 0;
	int i = 0;
	while (libre && pos < contador) {
		libre = sunflowers[i].libre(col, row);  // libre significa que no es esa planta
		i++;
	}
	pos = i - 1;
	 return pos;
 }


public boolean isPositionEmpty(int col, int row) {
	boolean ocupado = false;
	int i = 0;
	while (i < contador && ocupado == false) {
		if (!sunflowers[i].libre(col, row)) {
			ocupado = true;
		}
		i++;
	}
	return ocupado;
}
 
 public int update() {
	 int nGeneranSol = 0;
	 for(int i = 0; i < contador; i++) {
		 if(sunflowers[i].genera()) {
			 nGeneranSol++;
		 }
		
	 }
	 
	 return nGeneranSol;
 }
 
 
}
