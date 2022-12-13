
package Presentacion;
/**
 *
 * @author RHR
 */


import Negocio.Bitwise;
//import Negocio.Carta;
import Negocio.Conjuntob;
import Negocio.Vector7b;
import Negocio.VectorBitGen;
public class TDABitwise {


    public static void main(String[] args) {
        Conjuntob A = new Conjuntob(10);
        Conjuntob B = new Conjuntob(10);
        A.Insertar(15);
        A.Insertar(10);
        A.Insertar(4);
        A.Insertar(7);
        B.Insertar(5);
        B.Insertar(5);
        B.Insertar(5);
        B.Insertar(13);
        B.Insertar(15);
        B.Insertar(7);

        System.out.println("A" + A);
      }
        
    }
    

