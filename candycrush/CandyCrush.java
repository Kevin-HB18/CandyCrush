package candycrush;

public class CandyCrush {

	private Jugador jugador;
	private Candy [] candies;
	//private int [] valorPuntos;
	private String [][] tablero;
	
	
	// Constructor
	
	public CandyCrush(String nombreJugador) {

		jugador = new Jugador(nombreJugador, 5, 50);

		candies = new Candy [5];
		candies[0] = new Candy("☢", "Verde");
		candies[1] = new Candy("\u272A", "Azul");
		candies[2] = new Candy("\u2742", "Rojo");
		candies[3] = new Candy("\u262F", "Amarillo");
		candies[4] = new Candy("\u262E", "Negro");

		//valorPuntos = new int [] {50, 100, 200};

		generarTablero();
	}
	
	// Funciones

	/* Generar un tablero aleatorio */
	public void generarTablero() {
		int aux = 0; 
		int dado=0;
		this.tablero = new String [9][9];
		for(int i=0;i<tablero.length;i++){ 
	           for(int j=0;j<tablero[0].length;j++){
	        	    aux = (int) (Math.random()*4); 
	               dado=(int) Math.floor(Math.random()*4);
	               switch (dado) {
	                   case 1:
	                       tablero[i][j]=candies[0].getDulce();
	                       break;
	                   case 2:
	                	   tablero[i][j]=candies[1].getDulce();
	                       break;
	                   case 3:
	                	   tablero[i][j]=candies[2].getDulce();
	                       break;
	                   case 4:
	                	   tablero[i][j]=candies[3].getDulce();
	                       break;
	                   default:
	                	   tablero[i][j]=candies[4].getDulce();
	                       break;
	               }
				while(tablero[0][j]==tablero[1][j] ||tablero[2][j]==tablero[1][j]){
					aux = (int) (Math.random()*4+1); 
                    tablero[1][j]=this.candies[aux].getDulce();
					
	           		}
	            while(tablero[3][j]==tablero[4][j] || tablero[5][j]==tablero[4][j]){
	            	aux = (int) (Math.random()*4+1); 
                    tablero[4][j]=this.candies[aux].getDulce();
	            	
	           		}
	            while(tablero[6][j]==tablero[7][j] || tablero[8][j]==tablero[7][j]){
	            	aux = (int) (Math.random()*4+1); 
                    tablero[7][j]=this.candies[aux].getDulce();
	            	
		           	}
		       while(tablero[i][0]==tablero[i][1] || tablero[i][2]==tablero[i][1]){
		    	   aux = (int) (Math.random()*4+1); 
                   tablero[i][1]=this.candies[aux].getDulce();
		        	
	    			}
		       while(tablero[i][3]==tablero[i][4] || tablero[i][5]==tablero[i][4]){
		    	   aux = (int) (Math.random()*4+1); 
                   tablero[i][4]=this.candies[aux].getDulce();
		        	
	    			}
		       while(tablero[i][6]==tablero[i][7] || tablero[i][8]==tablero[i][7]){
		    	   aux = (int) (Math.random()*4+1); 
                   tablero[i][7]=this.candies[aux].getDulce();
		        	
	    			}
			}
		}
	}
	
	/* Validar rango de posiciones ingresadas */
    boolean validarRangoMovimiento(int iOrigen, int jOrigen, int iDestino, int jDestino) {
		return (iOrigen >= 0 && iOrigen <= 8 && jOrigen >= 0 && jOrigen <= 8 && iDestino >= 0 && iDestino <= 8 && jDestino >= 0 && jDestino <= 8);
	}

    /* Realizar un movimiento*/
	public boolean mover(int iOrigen, int jOrigen, int iDestino, int jDestino) {
		//String aux2 = tablero[iOrigen][jOrigen];
		//String aux3 = tablero[iDestino][jDestino];
		if (validarRangoMovimiento(iOrigen, jOrigen, iDestino, jDestino)) {
			if(iDestino==iOrigen||jDestino==jOrigen) {
				if(iDestino == iOrigen-1 || iDestino == iOrigen+1 ||jDestino == jOrigen-1 ||jDestino == jOrigen+1) {
					String aux = tablero[iDestino][jDestino];
					String aux2 = tablero[iOrigen][jOrigen];
					String aux3 = tablero[iDestino][jDestino];
					tablero[iDestino][jDestino] = tablero[iOrigen][jOrigen];
					tablero[iOrigen][jOrigen] = aux;
					if(puntos( iDestino, jDestino)) {
						moverAbajo();
						llenarVacios();
						verificarTab();
						jugador.lessMovimientos();
						return true;
					}else
					tablero[iDestino][jDestino] = aux3;
					tablero[iOrigen][jOrigen] = aux2;
					jugador.lessMovimientos();
					return false;
			}
			}
			}
		jugador.lessMovimientos();
		return false;
	}
	
	//elimina y suma puntos
	public boolean puntos( int iDestino, int jDestino) {
		 String aux= " ";
		 		//verificar si es centro 
		 		//jugada por abajo(+2) y arriba(-2)
		    	if(iDestino>=2 && iDestino<=6) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-2][jDestino]= aux;
	                    tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+2][jDestino]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                    } 
		    		
		    	}
		    	
		    		//jugada por abajo(+2) y arriba(-1)
		    	 if(iDestino<=6 && iDestino>=1) {
		    			if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+2][jDestino]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    	
		    	}
		    		//jugada por abajo(+1) y arriba(-2)
		    	 if(iDestino<=7 && iDestino>=2) {
		    			if(tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino])&& tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino-2][jDestino]= aux;
	                    tablero[iDestino+1][jDestino]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    
		    	}
		    	//jugada por abajo(+1) y arriba(-1)
		    	 if(iDestino>=1 && iDestino<=7) {
		    			if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
	                    tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino+1][jDestino]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    
		    	}
		    
		    	//verificar si es centro 
		    	//jugada por derecha(+2) e izquierda(-2)
		    	if(jDestino>=2 && jDestino<=6) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-2]= aux;
	                    tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                    } 
		    		
		    	}
		    	//jugada por derecha(+2) e izquierda(-1)
		    	 if(jDestino<=6 && jDestino>=1) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    	
		    	}
		    	//jugada por derecha(+1) e izquierda(-2)
		    	 if(jDestino<=7 && jDestino>=2) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1])&& tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino-2]= aux;
	                    tablero[iDestino][jDestino+1]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    		
		    	}
		    	//jugada por derecha(+1) e izquierda(-1)
		    	 if(jDestino>=1 && jDestino<=7) {
		    			if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) ){
		    			tablero[iDestino][jDestino]= aux;
	                    tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino+1]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    	}
		 //verificacion a la derecha
		 	if(jDestino<=3) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+3]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+4]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+5])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
	                    tablero[iDestino][jDestino+3]= aux;
	                    tablero[iDestino][jDestino+4]= aux;
	                    tablero[iDestino][jDestino+5]= aux;
		    			jugador.addPuntos(400);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 	 if(jDestino<=4) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+3]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+4])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
	                    tablero[iDestino][jDestino+3]= aux;
	                    tablero[iDestino][jDestino+4]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 	 if(jDestino<=5) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+3]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
	                    tablero[iDestino][jDestino+3]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 	
		 	 if(jDestino<=6) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino+2]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino+1]= aux;
	                    tablero[iDestino][jDestino+2]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    		
		    	} 
		 	
		    	
		    	
		    	
		    	//verificacion a la izquierda
		 		if(jDestino<=8 && jDestino>=5) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-3]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-4]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-5])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino-2]= aux;
	                    tablero[iDestino][jDestino-3]= aux;
	                    tablero[iDestino][jDestino-4]= aux;
	                    tablero[iDestino][jDestino-5]= aux;
		    			jugador.addPuntos(400);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(jDestino<=8 && jDestino>=4) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-3]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-4])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino-2]= aux;
	                    tablero[iDestino][jDestino-3]= aux;
	                    tablero[iDestino][jDestino-4]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(jDestino<=8 && jDestino>=3) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-3]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino-2]= aux;
	                    tablero[iDestino][jDestino-3]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    		
		    	}
		    
		 		 if(jDestino<=8 && jDestino>=2) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-1]) && tablero[iDestino][jDestino].equals(tablero[iDestino][jDestino-2]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino][jDestino-1]= aux;
	                    tablero[iDestino][jDestino-2]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    		
		    	}
		    	
		    	
		  	
		    	//verificacion para abajo
		 		if(iDestino<=3) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+3][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+4][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+5][jDestino])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+2][jDestino]= aux;
	                    tablero[iDestino+3][jDestino]= aux;
	                    tablero[iDestino+4][jDestino]= aux;
	                    tablero[iDestino+5][jDestino]= aux;
		    			jugador.addPuntos(400);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(iDestino<=4) {
		 				if(tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+3][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+4][jDestino])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+2][jDestino]= aux;
	                    tablero[iDestino+3][jDestino]= aux;
	                    tablero[iDestino+4][jDestino]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                   	} 
		    		
		    	}
		 		 if(iDestino<=5) {
		 				if(tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+3][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+2][jDestino]= aux;
	                    tablero[iDestino+3][jDestino]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(iDestino<=6) {
		 				if(tablero[iDestino][jDestino].equals(tablero[iDestino+1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino+2][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino+1][jDestino]= aux;
	                    tablero[iDestino+1][jDestino]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    		
		    	}
	
		    	
		    	//verificacion para arriba
		 		if(iDestino<=8 && iDestino>=5) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-3][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-4][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-5][jDestino])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino-2][jDestino]= aux;
	                    tablero[iDestino-3][jDestino]= aux;
	                    tablero[iDestino-4][jDestino]= aux;
	                    tablero[iDestino-5][jDestino]= aux;
		    			jugador.addPuntos(400);
	                    return true;
	                    
	                    } 
		    		
		    	}	
		 		 if(iDestino<=8 && iDestino>=4) {
		 				if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-3][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-4][jDestino])){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino-2][jDestino]= aux;
	                    tablero[iDestino-3][jDestino]= aux;
	                    tablero[iDestino-4][jDestino]= aux;
		    			jugador.addPuntos(200);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(iDestino<=8 && iDestino>=3) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-3][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino-2][jDestino]= aux;
	                    tablero[iDestino-3][jDestino]= aux;
		    			jugador.addPuntos(100);
	                    return true;
	                    
	                    } 
		    		
		    	}
		 		 if(iDestino<=8 && iDestino>=2) {
		    		if(tablero[iDestino][jDestino].equals(tablero[iDestino-1][jDestino]) && tablero[iDestino][jDestino].equals(tablero[iDestino-2][jDestino]) ){
		    			tablero[iDestino][jDestino]= aux;
		    			tablero[iDestino-1][jDestino]= aux;
	                    tablero[iDestino-2][jDestino]= aux;
		    			jugador.addPuntos(50);
	                    return true;
	                    
	                    } 
		    		
		    	}
		
	    return false;
		
	
}
	 // poner piezas abajo despues de eliminarlas
    public void moverAbajo(){
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[0].length;j++){
                while(i!=8 && this.tablero[i+1][j]==" " && this.tablero[i][j]!=" "){
                    this.tablero[i+1][j]=this.tablero[i][j];
                    this.tablero[i][j]=" ";
                    i=0;
                    j=0;
                }
            }
        }            
    }    
    //verificar tablero
    public void verificarTab() {
    	for(int i=0; i< tablero.length; i++) {
    		for(int j=0; j<tablero[0].length;j++) {
    			puntos(i,j);
    			moverAbajo();
    			llenarVacios();
    		}
    	}
    	
    }
    	
    	
    
    // despues de mover todo abajo, esto llena los vacios que queden 
    public void llenarVacios(){
        for(int i=0;i<tablero.length;i++){
            for(int j=0;j<tablero[0].length;j++){
                if(this.tablero[i][j]==" "){                        
                    int aux = (int) (Math.random()*4+1); 
                    int dado=(int) Math.floor(Math.random()*4+1);
                     switch (dado) {
                   case 1:
                       tablero[i][j]=candies[0].getDulce();
                       break;
                   case 2:
                	   tablero[i][j]=candies[1].getDulce();
                       break;
                   case 3:
                	   tablero[i][j]=candies[2].getDulce();
                       break;
                   case 4:
                	   tablero[i][j]=candies[3].getDulce();
                       break;
                   default:
                	   tablero[i][j]=candies[4].getDulce();
                       break;
               }
                     
                     this.tablero[i][j]=this.candies[aux].getDulce();
                     
                }
                	
                }
                
            }
        }
        
    
	
	/* Validar si seguir o no el juego*/
	public boolean seguirJuego () {
		return jugador.getVidas() > 0;
	}
	
	/* Imprimir resultado del juego*/
	public void resultado() {
		System.out.println("\n................RESULTADOS.................");
		System.out.println("Jugador: " + jugador.getNombre());
		System.out.println("Puntaje: " + jugador.getPuntos());
		System.out.println("Vidas: " + jugador.getVidas());
		System.out.println("Movimientos restantes: " + jugador.getMovimientos());
		System.out.println("...........................................\n");
		
	}

	/* Imprimir tablero y estado del juego*/
	public void printTablero() {
		System.out.println("\n...........................................\n");
		System.out.println("     0    1   2   3    4   5   6   7    8");
		System.out.println("   ----------------------------------------");
		for (int i = 0; i < tablero.length; i++) {
			System.out.print(" " + i + " | ");
			for (int j = 0; j < tablero[0].length; j++) {
				System.out.print(tablero[i][j] + " | ");
			}
			System.out.println();
			System.out.println("   ----------------------------------------");
		}
		System.out.println("\nJugador: " + jugador.getNombre());
		System.out.println("Puntaje: " + jugador.getPuntos());
		System.out.println("Vidas: " + jugador.getVidas());
		System.out.println("Movimientos restantes: " + jugador.getMovimientos());
		System.out.println("...........................................\n");
	}
}
