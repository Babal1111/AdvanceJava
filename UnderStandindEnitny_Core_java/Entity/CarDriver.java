




public  class CarDriver {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the option you want to choose: ");
        byte choice = sc.nextByte();
        Car car = new Car();
        Engine engine;

        switch (choice){
            case 1:
                engine = new PetrolEngine();
                break;
            case 2:
                engine = new DieselEngine();
                break;
        }
        //feild injection
        car.engine = engine;
        car.engine.run();

        //setter injection
        car.setEngine(engine);
        car.getEngine().run();
        System.out.println(car.getEngine().getClass());

        //constructor injection

        Car car = new Car(engine);
        car.getEngine().run();
        System.out.println(car.getEngine().getClass());


    }

}