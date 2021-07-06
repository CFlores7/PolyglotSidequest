package Classes;
//import java.util.ArrayList;
import Enums.*;
import static Enums.Sizes.*;

//Clase que guarda los componentes necesarios de la malla
public class Mesh {
    float parameters[] = new float[6];
    int sizes[] = new int [4];
    int indices_dirich[];
    Node node_list[];
    Element element_list[];
    Condition dirichlet_list[];
    Condition neumann_list[];

    //Constructor
    public Mesh(){
    };

    //Metodos

    //Funcion que almacena en la lista de parametros los  valres de k y Q
    public void setParameters(float k, float Q){
        parameters[Parameters.THERMAL_CONDUCTIVITY.ordinal()] = k;
        parameters[Parameters.HEAT_SOURCE.ordinal()] = Q;
    }

    //Se almacenan los datos correpondientes al numero de nodos, elementos de la malla y las condiciones de contorno
    public void setSizes(int nnodes, int neltos, int ndirich, int nneu){
        sizes[NODES.ordinal()]=nnodes;
        sizes[ELEMENTS.ordinal()]=neltos;
        sizes[DIRICHLET.ordinal()]=ndirich;
        sizes[NEUMANN.ordinal()]=nneu;
    }

    //Retorna el valor del arreglo sizes en el indice s
    public int getSize(int s){
        return sizes[s];
    }

    //Retorna el valor del arreglo parameters en el indice p
    public float getParameter(int p){
        return parameters[p];
    }

    //Crea inicializan los arreglos correspondientes a la lista de nodos, elementos, y condiciones de contorno
    public void createData(){
        /*En este punto todos los arreglos que guardan objetos tienen elementos nulos. Entonces hay que llenarlas con nuevas instancias,
          Para eso se creo un metodo estatico en las clases Node, Element y Condition que generan un arreglo lleno de objetos el cual es
          el que trabajaremos
        */
        node_list = Node.createNodes(sizes[NODES.ordinal()]);
        element_list = Element.createElements(sizes[ELEMENTS.ordinal()]);
        indices_dirich = new int[sizes[DIRICHLET.ordinal()]];
        dirichlet_list = Condition.createConditions(sizes[DIRICHLET.ordinal()]);
        neumann_list = Condition.createConditions(sizes[NEUMANN.ordinal()]);

    }

    //Metodo que retorna el arreglo indices_dirich
    public int[] getDirichletIndices() {
        return indices_dirich;
    }

    //Metodo que retorna el arreglo node_list
    public Node[] getNodes() {
        return node_list;
    }

    //Metodo que retorna el arreglo element_list
    public Element[] getElements() {
        return element_list;
    }

    //Metodo que retorna el arreglo dirichlet_list
    public Condition[] getDirichlet(){
        return dirichlet_list;
    }

    //Metodo que retorna el arreglo neumann_list
    public Condition[] getNeumann(){
        return neumann_list;
    }

    //Metodo que retorna el nodo almacenado en el indice i
    public Node getNode(int i){
        return node_list[i];
    }

    //Metodo que retorna el elemento en el indice i
    public Element getElement(int i){
        return element_list[i];
    }

    //Metodo que regresa la condicion de contorno, almacenado en el indice i dependiendo del tipo recibido
    public Condition getCondition(int i, Sizes type){
        if(type == Sizes.DIRICHLET) return dirichlet_list[i];
        else return neumann_list[i];
    }
}
