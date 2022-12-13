
package Negocio;

import javax.swing.JOptionPane;

/**
 *
 * @author RHR
 */

public class Bitwise {
  
    int x;
    public Bitwise(){
        x=0;
    }
    public void set1(int pos){
        if(pos>0){
        int mask =1;
        mask=mask<<(pos-1);
        x=x|mask;
        }         
    }
    public void set0(int pos){
        if (pos>0){
            int mask=1;
            mask=mask<<(pos-1);
            mask=~mask;
            x=x&mask;
            }
    }
    public int getbit(int pos){
        int mask=1;
        mask=mask<<(pos-1);
        mask=mask&x;
        mask=mask>>>(pos-1);
        return (mask);
    } 

    @Override
    public String toString() {
        String S="X=";
        for (int i =32;i>=1;i--){
            S=S+getbit(i)+" ";
             }
        return (S);
    }
  public static void main(String[] args) {
      int pos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la posicion que quiere encender ala vector Bitswise"));
        
      Bitwise A=new Bitwise();
       A.set1(pos);
        System.out.println(A.toString());
      }
      
    
}
