package Negocio;
/**
 *
 * @author RHR
 */
public class Vector7b {

    int v[];
    int dim;//dimension del 
    // int cbit;

    public Vector7b(int cant) {
        int Nbit = cant * 7;// cantidad de bits a ocupar
        int NE = Nbit / 32;//cantidad de Enteros a ocupar
        if (Nbit % 32 != 0) {
            NE++;
        }
        v = new int[NE];
        dim = cant;
    }

    public void Insertar(int valor, int pos) {// pos es la posicion del vector donde se insertar el valor, las  posiciones desde 1
        if (pos <= dim) {
            int valor1 = valor;
            int mask = (int) (Math.pow(2, 7) - 1);
            int Nbit = CNbit(pos);
            int NE = CNEnt(pos);
            mask = mask << (Nbit - 1);
            mask = ~mask;
            v[NE] = v[NE] & mask;
            valor = valor << (Nbit - 1);
            v[NE] = v[NE] | valor;
            if ((Nbit-1 + 7) > 32) {/////falta, hemos perdido bits
                int bitf = Nbit - 1 + 7 - 32; //////calculamos los bits que faltan
                int mask1 = (int) (Math.pow(2, bitf) - 1);///// creamos una maskara para los bits faltantes 
                //////no es necesario mover dicha mascara, siempre estara al comienzo
                mask1 = ~mask1;//////mask para limpiar el espacio 
                v[NE + 1] = v[NE] & mask1;/////limpiando
                valor1 = valor1 >>> (7 - bitf);////// movemos a la derecha sin signo, para meter en el espacio correspondiente
                v[NE + 1] = v[NE + 1] | valor1;///metemos el valor que habiamos perdido :)
            }
        }
    }

    public int Sacar(int pos) {
        int mask = (int) (Math.pow(2, 7) - 1);//MASK con el mayor numero de bits posible 
        int Nbit = CNbit(pos);// ubicacion del bit 
        int NE = CNEnt(pos);
        mask = mask << (Nbit - 1);
        mask = mask & v[NE];
        mask = mask >>> (Nbit - 1);
        if ((Nbit-1+7)>32){
            int bitf=Nbit-1+7-32;//////calculamos los bits que faltan
            int mask1 = (int) (Math.pow(2, bitf) - 1);///// creamos una maskara para los bits faltantes 
                //////no es necesario mover dicha mascara, siempre estara al comienzo
            mask1=mask1&v[NE+1];
            mask1=mask1<<(7-bitf);
            mask=mask|mask1;
            }
    return (mask);
    }

    private int CNbit(int pos) {
        int Nbit = ((pos-1)*7 % 32)+1;
        return Nbit;
    }

    private int CNEnt(int pos) {
        int NEnt = ((pos-1)*7 / 32);
        return NEnt;
    }

    @Override
    public String toString() {
        String S= "V=[";
        for (int i=1;i<=dim;i++){
            S=S+Sacar(i)+ " , ";
            }
        S=S+"]";
        return S;
    }
    

    }

