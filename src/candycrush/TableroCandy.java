
package candycrush;

public class TableroCandy extends Candies {   
    private String matrix[][] = new String[9][9];
    
    public void llenarTab(){
       Candies dulce = new Candies();
       int dado; 
        for(int i=0;i<9;i++){ 
           for(int j=0;j<9;j++){
               dado=(int) Math.floor(Math.random()*5+1);
               switch (dado) {
                   case 1:
                       matrix[i][j]=dulce.getHazard();
                       break;
                   case 2:
                       matrix[i][j]=dulce.getPaz();
                       break;
                   case 3:
                       matrix[i][j]=dulce.getRad();
                       break;
                   case 4:
                       matrix[i][j]=dulce.getSkull();
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
            for(int j=0;j<9;j++)
              System.out.print(matrix[i][j]+" ");
            System.out.println("");
        }   
        System.out.println("");
    }
    
    public void movePosition(int x1,int y1, int x2, int y2){
        String aux=matrix[x1][y1];
        matrix[x1][y1]=matrix[x2][y2];
        matrix[x2][y2]=aux;
    }
}
