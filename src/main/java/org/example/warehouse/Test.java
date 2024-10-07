package org.example.warehouse;

public class Test {

    public static void main(String[] args) {

        Warehouse wh1 = Warehouse.createWarehouse("hej");
        Warehouse wh2 = Warehouse.createWarehouse("hej");
        Warehouse wh3 = Warehouse.createWarehouse();
        boolean isSame = wh1.equals(wh2);
        boolean isEmpty = wh1.equals(wh3);

        System.out.println(isSame);
        System.out.println(isEmpty);
        System.out.println(isEmpty);


    }

    private static boolean isEmpty(Object o){
        if (o instanceof String) {

        }
    }
}
