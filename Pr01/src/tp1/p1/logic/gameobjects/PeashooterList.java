package tp1.p1.logic.gameobjects;

public class PeashooterList {
 private int contador;
 private Peashooter[] peashooters;
 
 
 
 public PeashooterList(int contador) {
	 this.contador = contador;
	 this.peashooters = new Peashooter[contador];
 }


public void addPeashooter( int col, int row) {
	Peashooter peashooter = new Peashooter(col, row);
	 peashooters[contador] = peashooter;              
	 contador++;
}


public boolean isPositionEmpty(int col, int row) {
boolean ocupado = false;
int i = 0;
while (i < contador && ocupado == false) {
	if (!peashooters[i].libre(col, row)) {
		ocupado = true;
	}
	i++;
}
return ocupado;
}


public int update() {
	
	 
	 return 0;
}

}

