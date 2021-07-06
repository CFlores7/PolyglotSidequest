package Tools;

import java.util.ArrayList;
public class Matrix extends ArrayList<Vector> {
    //Constructor vacio
    public Matrix(){};

    //Util para instanciar un objeto de tipo matriz lleno de ceros
    public Matrix(int numRows, int numCols, float defaultElement){
        for (int i = 0; i < numRows; i++) {
            this.add(new Vector(numCols,defaultElement));
        }
    }

    //Funcion que imprime la matriz
    public void Show(){
        for (int i = 0; i < this.size(); i++) {
            this.get(i).Show();
        }
    }
}
