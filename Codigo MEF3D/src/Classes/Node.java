package Classes;

public class Node extends Item{

    public Node(){};

    //Metodo que nos ayudara a crear las listas de nodos
    public static Node[] createNodes(int n){
        Node[] list = new Node[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Node();
        }
        return list;
    }

    @Override
    public void setValues(int a,float b,float c,float d,int e,int f,int g, int h, float i) {
        id = a;
        x = b;
        y = c;
        z = d;
    }
}
