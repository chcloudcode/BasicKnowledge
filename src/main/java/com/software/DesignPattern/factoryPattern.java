package com.software.DesignPattern;

interface animal01 {
    void run();
}

class dog implements animal01{
    @Override
    public void run() {
        System.out.println("dog is running...");
    }
}

class cat implements  animal01{
    @Override
    public void run() {
        System.out.println("cat is running...");
    }
}

class animal01Factory01 {

    animal01  get(String type){
        if(null==type||type.equals("")){
            return null;
        }
        if(type.equals("dog")){
            return new dog();
        }
        if(type.equals("cat")){
            return new cat();
        }
        return null;
    }

}

public class factoryPattern {

    public static void main(String[] args) {
        animal01Factory01 factory = new animal01Factory01();
        animal01 dog = factory.get("dog");
        animal01 cat = factory.get("cat");
        dog.run();
        cat.run();
    }
}
