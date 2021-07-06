package Tools;

import Classes.*;
import Enums.Parameters;
import Enums.Sizes;

import java.util.ArrayList;
import static Tools.Math_Tools.*;

public class Sel {
    private Sel(){};

    //Muestra las Ks
    public static void showKs(ArrayList<Matrix> Ks){
        for (int i = 0; i < Ks.size() ; i++) {
            System.out.println("K del elemento"+(i+1));
            Ks.get(i).Show();
            System.out.println("**********************************");
        }
    }


    //Muestra ls bs
    public static void showbs(ArrayList<Vector> bs){
        for (int i = 0; i < bs.size() ; i++) {
            System.out.println("b del elemento"+(i+1));
            bs.get(i).Show();
            System.out.println("**********************************");
        }
    }

    //Crea el elemento local
    public static float calculateLocalD(int ind, Mesh m){
        float D,a,b,c,d,e,f,g,h,i;

        Element el = m.getElement(ind);

        Node n1 = m.getNode(el.getNode1()-1);
        Node n2 = m.getNode(el.getNode2()-1);
        Node n3 = m.getNode(el.getNode3()-1);
        Node n4 = m.getNode(el.getNode4()-1);

        a=n2.getX()-n1.getX();
        b=n2.getY()-n1.getY();
        c=n2.getZ()-n1.getZ();
        d=n3.getX()-n1.getX();
        e=n3.getY()-n1.getY();
        f=n3.getZ()-n1.getZ();
        g=n4.getX()-n1.getX();
        h=n4.getY()-n1.getY();
        i=n4.getZ()-n1.getZ();
 //Se calcula el determinante de esta matriz utilizando
    //la Regla de Sarrus.
        D = a*e*i+d*h*c+g*b*f-g*e*c-a*h*f-d*b*i;

        return D;
    }


//Caluclando el volumen 
public static float  calculateLocalVolume(int ind, Mesh m){
         float V,a,b,c,d,e,f,g,h,i;

        Element el = m.getElement(ind);

        Node n1 = m.getNode(el.getNode1()-1);
        Node n2 = m.getNode(el.getNode2()-1);
        Node n3 = m.getNode(el.getNode3()-1);
        Node n4 = m.getNode(el.getNode4()-1);

        a=n2.getX()-n1.getX();
        b=n2.getY()-n1.getY();
        c=n2.getZ()-n1.getZ();
        d=n3.getX()-n1.getX();
        e=n3.getY()-n1.getY();
        f=n3.getZ()-n1.getZ();
        g=n4.getX()-n1.getX();
        h=n4.getY()-n1.getY();
        i=n4.getZ()-n1.getZ();
 //Se calcula el determinante de esta matriz utilizando
    //la Regla de Sarrus.
        V = (1/6)*(a*e*i+d*h*c+g*b*f-g*e*c-a*h*f-d*b*i);

        return V;

}

    /*Calcula la magnitud de un vector.
    public static float calculateMagnitude(float v1, float v2){
        return (float) Math.sqrt(Math.pow(v1,2)+Math.pow(v2,2));
    }

    public static float calculateLocalArea(int i, Mesh m){
        //Formula de Herón
        float A,s,a,b,c;
        Element e = m.getElement(i);
        Node n1 = m.getNode(e.getNode1()-1);
        Node n2 = m.getNode(e.getNode2()-1);
        Node n3 = m.getNode(e.getNode3()-1);

        a = calculateMagnitude(n2.getX()-n1.getX(),n2.getY()-n1.getY());
        b = calculateMagnitude(n3.getX()-n2.getX(),n3.getY()-n2.getY());
        c = calculateMagnitude(n3.getX()-n1.getX(),n3.getY()-n1.getY());
        s = (a+b+c)/2.0f;

        A = (float) Math.sqrt(s*(s-a)*(s-b)*(s-c));

        return A;
    }
*/

 public static float ab_ij(float ai, float aj, float a1, float bi, float bj, float b1){

     return (ai - a1)*(bj - b1) - (aj - a1)*(bi - b1);
 }

    //Calcula la la matriz local A
    public static void calculateLocalA(int i,Matrix A, Mesh m){
        Element e = m.getElement(i);
        Node n1 = m.getNode(e.getNode1()-1);
        Node n2 = m.getNode(e.getNode2()-1);
        Node n3 = m.getNode(e.getNode3()-1);
        Node n4 = m.getNode(e.getNode4()-1);

        A.get(0).set(0,ab_ij(n3.getY(),n4.getY(),n1.getY(),n3.getZ(),n4.getZ(),n1.getZ()));
        A.get(0).set(1,ab_ij(n4.getY(),n2.getY(),n1.getY(),n4.getZ(),n2.getZ(),n1.getZ()));
        A.get(0).set(2,ab_ij(n2.getY(),n3.getY(),n1.getY(),n2.getZ(),n3.getZ(),n1.getZ()));
        A.get(1).set(0,ab_ij(n4.getX(),n3.getX(),n1.getX(),n4.getZ(),n3.getZ(),n1.getZ()));
        A.get(1).set(1,ab_ij(n2.getX(),n4.getX(),n1.getX(),n2.getZ(),n4.getZ(),n1.getZ()));
        A.get(1).set(2,ab_ij(n3.getX(),n2.getX(),n1.getX(),n3.getZ(),n2.getZ(),n1.getZ()));
        A.get(2).set(0,ab_ij(n3.getX(),n4.getX(),n1.getX(),n3.getY(),n4.getY(),n1.getY()));
        A.get(2).set(1,ab_ij(n4.getX(),n2.getX(),n1.getX(),n4.getY(),n2.getY(),n1.getY()));
        A.get(2).set(2,ab_ij(n2.getX(),n3.getX(),n1.getX(),n2.getY(),n3.getY(),n1.getY()));
    }

    //Calcula y llena la matriz B
    private static void calculateB(Matrix B){
        B.get(0).set(0, -1f);
        B.get(0).set(1, 1f);
        B.get(0).set(2, 0f);
        B.get(0).set(3, 0f);
        B.get(1).set(0, -1f);
        B.get(1).set(1, 1f);
        B.get(1).set(2, 0f);
        B.get(1).set(3, 0f);
        B.get(2).set(0, -1f);
        B.get(2).set(1, 1f);
        B.get(2).set(2, 0f);
        B.get(2).set(3, 0f);
    }

    //Metodo que cera una matriz local K y lo alamcena en m
    private static Matrix createLocalK(int element,Mesh m){
        // K = (k*Ae/D^2)Bt*At*A*B := K_3x3
        float D,Ve,k = m.getParameter(Parameters.THERMAL_CONDUCTIVITY.ordinal());
        Matrix K=new Matrix(),A=new Matrix(),B=new Matrix(),Bt=new Matrix(),At=new Matrix();

        D = calculateLocalD(element,m);
        Ve = calculateLocalVolume(element,m);

        zeroes(A,2);
        zeroes(B,2,3);
        calculateLocalA(element,A,m);
        calculateB(B);
        transpose(A,At);
        transpose(B,Bt);

        productRealMatrix(k*Ve/(D*D),productMatrixMatrix(Bt,productMatrixMatrix(At,productMatrixMatrix(A,B,3,3,4),3,3,4),4,3,4),K);

        return K;
    }

    public static float calculateLocalJ(int ind, Mesh m){
        float J,a,b,c,d,e,f,g,h,i;

        Element el = m.getElement(ind);
        Node n1 = m.getNode(el.getNode1()-1);
        Node n2 = m.getNode(el.getNode2()-1);
        Node n3 = m.getNode(el.getNode3()-1);
        Node n4 = m.getNode(el.getNode4()-1);


        a=n2.getX()-n1.getX();
        b=n3.getX()-n1.getX();
        c=n4.getY()-n1.getY();
        d=n2.getY()-n1.getY();
        e=n3.getY()-n1.getY();
        f=n4.getZ()-n1.getZ();
        g=n2.getX()-n1.getX();
        h=n3.getY()-n1.getY();
        i=n4.getZ()-n1.getZ();

    J = a*e*i+d*h*c+g*b*f-g*e*c-a*h*f-d*b*i;

        return J;
    }

    //Metodo que crea el elemento local b y lo almacena en m
    public static Vector createLocalb(int element,Mesh m){
        Vector b = new Vector();

        float Q = m.getParameter(Parameters.HEAT_SOURCE.ordinal()), J, b_i;
        J = calculateLocalJ(element,m);

        b_i = Q * J / 24;
        b.add(b_i);
        b.add(b_i);
        b.add(b_i);
        b.add(b_i);

        

        return b;
    }

    //Esta funcion crea los sitemas locales (K y b) y almacena los datos en sus respectivas listas
    public static void crearSistemasLocales(Mesh m, ArrayList<Matrix> localKs, ArrayList<Vector> localbs){
        for(int i = 0; i<m.getSize(Sizes.ELEMENTS.ordinal()); i++){
            localKs.add(createLocalK(i,m));
            localbs.add(createLocalb(i,m));
        }
    }

    //Esta funcion realiza el ensamblaje de la matriz K global, recibe el elemento actual, la matriz K local
    //y la matriz K global en la cual se realizara el ensamblaje
    public static void assemblyK(Element e, Matrix localK, Matrix K){
        int index1 = e.getNode1() - 1;
        int index2 = e.getNode2() - 1;
        int index3 = e.getNode3() - 1;
        int index4 = e.getNode3() - 1;


        K.get(index1).set(index1, K.get(index1).get(index1) + localK.get(0).get(0));
        K.get(index1).set(index2, K.get(index1).get(index2) + localK.get(0).get(1));
        K.get(index1).set(index3, K.get(index1).get(index3) + localK.get(0).get(2));
        K.get(index1).set(index4, K.get(index1).get(index4) + localK.get(0).get(3));
        K.get(index2).set(index1, K.get(index2).get(index1) + localK.get(1).get(0));
        K.get(index2).set(index2, K.get(index2).get(index2) + localK.get(1).get(1));
        K.get(index2).set(index3, K.get(index2).get(index3) + localK.get(1).get(2));
        K.get(index2).set(index4, K.get(index2).get(index4) + localK.get(1).get(3));
        K.get(index3).set(index1, K.get(index3).get(index1) + localK.get(2).get(0));
        K.get(index3).set(index2, K.get(index3).get(index2) + localK.get(2).get(1));
        K.get(index3).set(index3, K.get(index3).get(index3) + localK.get(2).get(2));
        K.get(index3).set(index4, K.get(index3).get(index4) + localK.get(2).get(3));
        K.get(index4).set(index1, K.get(index4).get(index1) + localK.get(3).get(0));
        K.get(index4).set(index2, K.get(index4).get(index2) + localK.get(3).get(1));
        K.get(index4).set(index3, K.get(index4).get(index3) + localK.get(3).get(2));
        K.get(index4).set(index4, K.get(index4).get(index4) + localK.get(3).get(3));
   
    }

    //Esta funcion realiza el ensamblaje del arreglo b global, recibe el elemento actual, el arreglo b local
    //y el arreglo b glocal en el cual se realizara el ensablaje
    public static void assemblyb(Element e, Vector localb, Vector b){
        int index1 = e.getNode1() - 1;
        int index2 = e.getNode2() - 1;
        int index3 = e.getNode3() - 1;
        int index4 = e.getNode4() - 1;


        b.set(index1, b.get(index1) + localb.get(0));
        b.set(index2, b.get(index2) + localb.get(1));
        b.set(index3, b.get(index3) + localb.get(2));
        b.set(index4, b.get(index4) + localb.get(3));

    }

    //Se realiza el ensamblaje de los sistemas locales K y B utilizando las funciones assemblyK y assemblyb
    public static void ensamblaje(Mesh m, ArrayList<Matrix> localKs, ArrayList<Vector> localbs, Matrix K,Vector b){
        for(int i=0; i<m.getSize(Sizes.ELEMENTS.ordinal()); i++){
            Element e = m.getElement(i);
            assemblyK(e,localKs.get(i),K);
            assemblyb(e,localbs.get(i),b);
        }
    }

    //Funcion que aplica la condicion de neumann al vector b
    public static void applyNeumann(Mesh m,Vector b){
        for(int i=0;i <m.getSize(Sizes.NEUMANN.ordinal()); i++){
            Condition c = m.getCondition(i,Sizes.NEUMANN);
            b.set(c.getNode1()-1, b.get(c.getNode1()-1) + c.getValue());
        }
    }

    //Funcion que aplica la condicion de dirichlet al sistema de ecuaciones
    public static void applyDirichlet(Mesh m,Matrix K,Vector b){
        for(int i=0; i<m.getSize(Sizes.DIRICHLET.ordinal()); i++){

            Condition c = m.getCondition(i,Sizes.DIRICHLET);
            int index = c.getNode1()-1;

            K.remove(index);
            b.remove(index);

            for(int row=0; row < K.size(); row++){
                float cell = K.get(row).get(index);
                K.get(row).remove(index);
                b.set(row, b.get(row) + (-1*c.getValue()) * cell);
            }
        }
    }

    //Funcion que calcula el resultado del SEL
    public static void calculate(Matrix K, Vector b, Vector T){
        System.out.println("Iniciando calculo de respuesta...");
        Matrix Kinv = new Matrix();
        System.out.println("Calculo de inversa...");
        inverseMatrix(K, Kinv);
        System.out.println("Calculo de respuesta...");
        productMatrixVector(Kinv, b, T);
    }

}
