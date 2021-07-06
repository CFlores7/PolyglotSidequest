package Tools;

import java.util.ArrayList;

//Tanto esta clase como la de Matrix, existen como alternativa a los typedef para evitar escrituras como
//ArrayList<ArrayList<float>>, volviendo el codigo mas legible
//Como aclaracion, se opto por arrayList en lugar de Vector ya que Vector es mas rapido y gasta menos memoria

public class Vector extends ArrayList<Float> {
    public Vector(){};

    //Constructor que servira para inicializar en 0 un vector recien instanciado
    public Vector(int size, float defaultElement){
        for (int i = 0; i < size; i++) {
            this.add(defaultElement);
        }
    }

    //Funcion que imprime el vector, se utiliza el bucle para poder asignar el numero de digitos decimales y
    //controlar el espacio entre cada numero, haciendo que la matriz y el vector sean mas faciles de leer
    public void Show(){
        System.out.print("[\t");
        for (int i = 0; i < this.size(); i++) {
            System.out.print(String.format("%.3f", this.get(i))+"\t");
        }
        System.out.println("]");
    }
}
