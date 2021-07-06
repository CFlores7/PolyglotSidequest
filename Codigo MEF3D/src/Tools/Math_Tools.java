package Tools;

public class Math_Tools {

    private Math_Tools(){};

    //Funciones sobrecargadas que llenan de 0 matrices y vectores respectivamente.
    public static void zeroes(Matrix M, int n){
        for (int i = 0; i < n; i++) {
            M.add(new Vector(n,0));
        }
    }

    public static void zeroes(Matrix M, int n, int m){
        for (int i = 0; i < n; i++) {
            M.add(new Vector(m,0));
        }
    }

    public static void zeroes(Vector v, int n){
        for (int i = 0; i < n; i++) {
            v.add(0f);
        }
    }

    //Funcion que copia la informacion de una matriz A a una matriz copy
    public static void copyMatrix(Matrix A, Matrix copy){
        zeroes(copy, A.size());
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).size(); j++) {
                copy.get(i).set(j, A.get(i).get(j));
            }
        }
    }

    public static float calculateMember(int i, int j, int r, Matrix A, Matrix B){
        float member = 0;
        for (int k = 0; k < r; k++) {
            member += A.get(i).get(k) * B.get(k).get(j);
        }
        return member;
    }

    //Metodo estatico que calcula el producto de dos matrices
    public static Matrix productMatrixMatrix(Matrix A, Matrix B, int n, int r, int m){
        //Instancia la matriz y la llena de ceros
        Matrix R = new Matrix(n,m,0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                R.get(i).set(j,calculateMember(i,j,r,A,B));
            }
        }
        return R;
    }

    //Metodo estatico que calcula el producto de una matriz A por un vector v
    public static void productMatrixVector(Matrix A, Vector v, Vector R){
        for (int i = 0; i < A.size(); i++) {
            float cell = 0;

            for (int j = 0; j < v.size(); j++) {
                cell += A.get(i).get(j) * v.get(j);
            }
            R.set(i,R.get(i) + cell);
        }
    }

    //Metodo estatico que calcula el producto de un numero real por una matriz
    public static void productRealMatrix(float real, Matrix M, Matrix R){
        zeroes(R,M.size());
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.get(0).size(); j++) {
                R.get(i).set(j, real * M.get(i).get(j));
            }
        }
    }

    //Metodo que calcula el menor de una matriz M
    public static void getMinor(Matrix M, int i, int j){
        M.remove(i);
        for (int k = 0; k < M.size(); k++) {
            M.get(k).remove(j);
        }
    }

    //Funcion que calcula el determinante de una matriz M
    public static float determinant(Matrix M){
        if(M.size() == 1) return M.get(0).get(0);
        else{
            float det = 0;
            for (int i = 0; i < M.get(0).size(); i++) {
                Matrix minor = new Matrix();
                copyMatrix(M, minor);
                getMinor(minor,0,i);
                det += Math.pow(-1,i)*M.get(0).get(i)*determinant(minor);
            }
            return det;
        }
    }

    //Metodo que calcula la matriz de cofactores dada una matriz M
    public static void cofactors(Matrix M, Matrix Cof){
        zeroes(Cof, M.size());
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.get(0).size(); j++) {
                Matrix minor = new Matrix();
                copyMatrix(M, minor);
                getMinor(minor, i, j);
                Cof.get(i).set(j, (float)Math.pow(-1, i+j)*determinant(minor));
            }
        }
    }

    //Metodo que calcula la matriz transpuesta dada una matriz M
    public static void transpose(Matrix M, Matrix T){
        zeroes(T, M.get(0).size(), M.size());
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.get(0).size(); j++) {
                T.get(j).set(i, M.get(i).get(j));
            }
        }
    }

    //Metodo que calcula la inversa de una matriz M utilizando el metodo de la adjunta.
    public static void inverseMatrix(Matrix M, Matrix Minv){
        System.out.println("Iniciando Calculo de inversa ....");
        Matrix Cof = new Matrix(), Adj = new Matrix();
        System.out.println("Calculo de determinante...");
        float det = determinant(M);
        if(det == 0) System.exit(1);
        System.out.println("Iniciando calculo de cofactores...");
        cofactors(M,Cof);
        System.out.println("Calculo de Adjunta...");
        transpose(Cof, Adj);
        System.out.println("Calculo de inversa...");
        productRealMatrix(1.0f/det,Adj,Minv);

    }

}