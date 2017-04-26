package ingenieria;

public class Tablero {
	
	Casilla[][] casillas;
	
	
	public Tablero(int dim){
		
		casillas = new Casilla[dim][dim];
		
		inicializarTab(dim);
		
	}
	
	public void inicializarTab(int dim){
		
		boolean color = false;
		
		for(int i=0;i<dim;i++){
			for(int j=0;j<dim;j++){
				casillas[i][j] = new Casilla(i,j,color);
				color = !color;
			}
		}
	}

}
