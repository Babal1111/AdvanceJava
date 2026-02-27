class Car {
    public Engine engine;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }


    // constrctor injection
    public  Car(){
        super();
    }
    public Car(Engine engine){
        super();
        this.engine = engine;
    }

}
