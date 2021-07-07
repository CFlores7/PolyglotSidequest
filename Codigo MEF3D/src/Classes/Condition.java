package Classes;

public class Condition extends Item{
    public Condition() {
    }

    //Metodo que nos ayudara a crear las listas de condiciones
    public static Condition[] createConditions(int n){
        Condition[] list = new Condition[n];
        for (int i = 0; i < n; i++) {
            list[i] = new Condition();
        }
        return list;
    }

    //Metodo para asignacion de valores
    @Override
    public void setValues(int a,float b,float c,float d,int e,int f,int g, int h, float i) {
        node1 = e;
        value = i;
    }
}

