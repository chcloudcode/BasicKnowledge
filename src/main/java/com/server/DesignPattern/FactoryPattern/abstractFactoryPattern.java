package com.server.DesignPattern.FactoryPattern;

interface Animal{
    void run();
}
class Mouse implements Animal{
    @Override
    public void run() {
        System.out.println("Mouse is running ... ");
    }
}

class Rabbit implements Animal{
    @Override
    public void run() {
        System.out.println("Rabbit is running...");
    }
}

abstract class Factory{
    abstract Animal get(String type);
}

class AnimalFactory extends Factory{
    @Override
    Animal get(String type) {
       if(null==type||type.equals("")){
           return null;
       }
       if(type.equals("Mouse")){
           return new Mouse();
       }
       if(type.equals("Rabbit")){
           return new Rabbit();
       }
       return null;
    }
}

class FactoryCreator{
    public static Factory getFactory(String type){
        if(null==type||type.equals("")){
            return null;
        }
        if(type.equals("animal")){
            return new AnimalFactory();
        }
        return null;
    }
}

public class abstractFactoryPattern{
    public static void main(String[] args) {
        Factory factory = FactoryCreator.getFactory("animal");
        Animal Mouse = factory.get("Mouse");
        Mouse.run();
    }
}
