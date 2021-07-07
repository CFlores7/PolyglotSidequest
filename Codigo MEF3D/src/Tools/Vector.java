package Tools;

import java.util.ArrayList;

//Aqui se declaran el tipo vector como si fuese un typedef
public class Vector extends ArrayList<Float> {
    public Vector(){};

    //Constructor que servira para inicializar en 0 un vector recien instanciado
    public Vector(int size, float defaultElement){
        for (int i = 0; i < size; i++) {
            this.add(defaultElement);
        }
    }

    //Funcion que imprime el vector, se utiliza el bucle para poder asignar el numero de digitos decimales
    public void Show(){
        System.out.print("[\t");
        for (int i = 0; i < this.size(); i++) {
            System.out.print(String.format("%.3f", this.get(i))+"\t");
        }
        System.out.println("]");
    }
}
