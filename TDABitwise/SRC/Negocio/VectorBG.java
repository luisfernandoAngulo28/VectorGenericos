package Negocio;

/**
 *
 * @author RHR
 */
public class VectorBG {

    int V[];//vector de bits
    int cbit; //cantidad de bit
    int dim; //dimension o cantidad de elementos del vector bitwise generico
    int nro;
    int maxval;

    public VectorBG(int Nelementos, int cantbit) {
        nro = 0;
        int x = ((Nelementos * cantbit) / 32);
        if (x % 32 != 0) {
            x++;
        }
        V = new int[x];
        cbit = cantbit;
        dim = Nelementos;
        maxval = (int) (Math.pow(2, cbit) - 1);
    }

    private int elevado(int b, int exp) {
        int res;
        if (exp == 0) {
            res = 1;
        } else {
            res = 1;
            for (int i = 1; i <= exp; i++) {
                res = res * b;
            }
        }
        return res;
    }

    private int Nbit(int pos) {
        return ((((pos - 1) * cbit)) % 32) + 1;
    }

    private int NEnter(int pos) {
        return ((((pos - 1) * cbit) / 32));
    }

    public void Insertar(int dato) {
        Insertar(dato, nro);
        nro++;
    }

    public void Insertar(int dato, int pos) {
        if ((pos <= dim) && (dato <= maxval)) {
            int x = Nbit(pos);
            int y = NEnter(pos);
            if (x + cbit <= 32) {
                dato = dato << x - 1;
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 << x - 1;
                mask1 = ~mask1;
                V[y] = V[y] & mask1;
                V[y] = V[y] | dato;
            } else {
                int dato1 = dato;
                dato = dato << x - 1;
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 << x - 1;
                mask1 = ~mask1;
                V[y] = V[y] & mask1;
                V[y] = V[y] | dato;
                dato1 = dato1 >>> 32 - x + 1;
                mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 >>> 32 - x + 1;
                mask1 = ~mask1;
                V[y + 1] = V[y + 1] & mask1;
                V[y + 1] = V[y + 1] | dato1;
            }
        } else {
            System.out.print("Error la capacidad fue sobrepasada aumente elementos");
        }
    }

    public int Sacar(int pos) {
        int aux = 0;
        if (pos <= dim) {
            int x = Nbit(pos);
            int y = NEnter(pos);
            if (x + cbit <= 32) {
                aux = V[y];
                aux = aux >>> x - 1;
                int mask1 = elevado(2, cbit) - 1;
                aux = aux & mask1;
            } else {
                int aux2 = V[y];
                aux2 = aux2 >>> x - 1;
                aux = V[y + 1];
                int mask1 = elevado(2, cbit) - 1;
                mask1 = mask1 >>> 32 - x + 1;
                aux = aux & mask1;
                aux = aux << 32 - x + 1;
                aux = aux | aux2;
            }
        }

        return aux;
    }

    public void Eliminar(int pos) {
        if (pos <= dim) {
            VectorBG Aux = new VectorBG(dim, cbit);
            int i = 1;
            int j = 0;
            while (i <= this.dim) {
                int x = Sacar(i);
                if (pos != i) {
                    j++;
                    Aux.Insertar(x, j);
                }
                i++;
            }
            Aux.dim--;
            this.V = Aux.V;
            this.dim = Aux.dim;
        } else {
            System.out.println("No se encuentra la pos para eliminar");
        }

    }

    public void Intercambiar(int pos1, int pos2) {
        int aux = Sacar(pos1);
        int aux2 = Sacar(pos2);
        Insertar(aux, pos2);
        Insertar(aux2, pos1);
    }

    public void Ordenar() {
        for (int i = 1; i < dim; i++) {
            for (int j = i + 1; j <= dim; j++) {
                if (Sacar(i) > Sacar(j)) {
                    Intercambiar(i, j);
                }
            }
        }
    }

    @Override
    public String toString() {
        String S = "V=[";
        for (int i = 1; i <= dim; i++) {
            S = S + Sacar(i) + " , ";
        }
        S = S.substring(0, S.length() - 2);
        S = S + "]";
        return S;
    }

}
