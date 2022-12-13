package Negocio;
/**
 *
 * @author RHR+
 */
import Negocio.Bitwise;

public class Conjuntob {

    Bitwise C[];
    int cant;

    public Conjuntob(int cant) {
        this.cant = cant;
        int cbw = cant / 32;
        if (cant % 32 != 0) {
            cbw++;
        }
        C = new Bitwise[cbw];
        for (int i = 0; i < cbw; i++) {
            C[i] = new Bitwise();
        }
    }

    public void Insertar(int ele) {// ele = 35
        if (ele <= cant) {
            int bit = Nbit(ele); // -> 3
            int bitw = Nbitw(ele);// -> 1 
            C[bitw].set1(bit);
        }
    }

    public void ELiminar(int ele) {// ele = 35
        if (ele <= cant) {
            int bit = Nbit(ele); // -> 3
            int bitw = Nbitw(ele);// -> 1 
            C[bitw].set0(bit);
        }
    }

    public boolean Pertenece(int ele) {
        int bit = Nbit(ele); // -> 3
        int bitw = Nbitw(ele);// -> 1 
        return (C[bitw].getbit(bit) == 1);
    }

    public void Union(Conjuntob A, Conjuntob B) {
        for (int i = 1; i <= A.cant; i++) {
            if (A.Pertenece(i)) {
                Insertar(i);
            }
        }
        for (int j = 1; j <= B.cant; j++) {
            if (B.Pertenece(j)) {
                Insertar(j);
            }
        }
    }

    public void Interseccion(Conjuntob A, Conjuntob B) {
        //int NEle;
        if (A.cant < B.cant) {
            for (int i = 1; (i <= A.cant); i++) {
                if (A.Pertenece(i) && B.Pertenece(i)) {
                    Insertar(i);
                }
            }
        } else {
            for (int i = 1; (i <= B.cant); i++) {
                if (A.Pertenece(i) && B.Pertenece(i)) {
                    Insertar(i);
                }
            }
        }
    } 
    private int Nbit(int ele) {
        return (((ele-1)%32)+1);
    }

    private int Nbitw(int ele) {
        return ((ele-1)/32);
    }

    @Override
    public String toString() {
    String S= "C={";
    for (int i=1;i<=cant;i++){
        if(Pertenece(i)){
            S=S+i+", ";
        }
    }
    S=S+"}";
    return S;
    }

}
