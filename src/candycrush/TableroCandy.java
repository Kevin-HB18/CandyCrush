
package candycrush;

public class TableroCandy extends Candies {   
    private String matrix[][] = new String[9][9];
    
    public void llenarTab(){
       Candies dulce = new Candies();
       int dado=0,dado2,cont=0; 
        for(int i=0;i<9;i++){ 
           for(int j=0;j<9;j++){        
               dado2=dado;
               dado=(int) Math.floor(Math.random()*5+1);     
               if(dado==dado2)
                   cont++;
               else
                   cont=0;
               if(cont==2){
                   while(dado==dado2){
                       dado=(int) Math.floor(Math.random()*5+1);     
                   }
                   cont=0;
               }                   
               switch (dado) {
                   case 1:
                       matrix[i][j]=dulce.getSun();
                       break;
                   case 2:
                       matrix[i][j]=dulce.getPaz();
                       break;
                   case 3:
                       matrix[i][j]=dulce.getRad();
                       break;
                   case 4:
                       matrix[i][j]=dulce.getStar();
                       break;
                   default:
                       matrix[i][j]=dulce.getYinyang();
                       break;
               }
           }
       }
               
    }
    public void showTab(){
        for(int i=0;i<9;i++){
            System.out.print("|");
            for(int j=0;j<9;j++){
                String value = String.format("%1s", matrix[i][j]);
                System.out.print(" "+value+" ");
            }              
            System.out.println("|");
        }   
        System.out.println("");
    }
    
    public void movePosition(int x1,int y1, int x2, int y2){
        String aux=matrix[x1][y1];
        matrix[x1][y1]=matrix[x2][y2];
        matrix[x2][y2]=aux;
    }
}
