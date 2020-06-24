
package candycrush;

import java.util.Scanner;



public class CandyCrush {
    
    public static void main(String[] args) {
       Scanner cin = new Scanner(System.in);
       TableroCandy tab = new TableroCandy();
       tab.llenarTab();
       tab.showTab();
      int x,y,h,k;
      x=cin.nextInt();
      y=cin.nextInt();
      h=cin.nextInt();
      k=cin.nextInt();
      tab.movePosition(x, y, h, k);
      tab.showTab();
    }
    
}
