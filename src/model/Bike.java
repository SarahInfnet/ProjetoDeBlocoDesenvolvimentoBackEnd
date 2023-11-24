package model;

public class Bike {
    //create teh bike
    protected static Bike[] bikeList = new Bike[5];
    //set up member variables
    protected int deposit = 0;
    protected String description = "";
    protected int rate = 0;
    protected int bikeNumber = 0;

    /*this block is run when the class is loaded and sets up our bike store.
     * it arbitrarily populates the attributes: deposit, rates and bikeNumber */
    static{
        bikeList[0] = new Bike("Bicicleta Aro 29 Ksw 21 Marchas Aluminio Cambio Shimano Freio a Disco", 50, 5,1);
        bikeList[1] = new Bike("Bicicleta Aro 17 Ksw 21 Marchas Aluminio Cambio Shimano Freio a Disco", 43, 3,2);
        bikeList[2] = new Bike("Bicicleta 5 Marchas Cambio Shimano Freio a Disco", 70, 6,3);
        bikeList[3] = new Bike("Bicicleta Aro 29 Ksw 21 Marchas Aluminio Cambio Shimano Freio a Disco", 23, 6,4);
        bikeList[4] = new Bike("Bicicleta Aro 29 Ksw 21 Marchas Aluminio Cambio Shimano Freio a Disco", 76, 3,5);
    }

    public Bike(String desc, int dep, int rat, int num){
        //set the member variables
        deposit = dep;
        rate = rat;
        bikeNumber = num;
        description = desc;
    }

    public int getDeposit(){
        return deposit;
    }

    public String getDescription(){
        return description;
    }

    public static Bike[] getBikeList(){
        return bikeList;
    }

    public int getRate(){
        return rate;
    }

    public int getBikeNumber(){
        return bikeNumber;
    }

    public static Bike findBikeByNumber(int bikeNum){
        int numberOfBikes = bikeList.length;

        //iterate over the list of bikes
        for(int i = 0; i < numberOfBikes; i++){
            //if we find the bike with the correct number...
            if(bikeList[i].getBikeNumber() == bikeNum){
                //tell user that we've found it
                System.out.println("Bike with number '" + bikeNum + "' found " + "\n");
                //and return it to the UI
                return bikeList[i];
            }
        }
        //if we dont find the bike, tell the user and return nothing
        System.out.println("Bike with number '" + bikeNum + "' not found" + "\n");
        return null;
    }

    public void showDetails(){
        //print out all the details
        System.out.println(toString());
    }

    public String toString(){
        String details = "Detalhes da bicicleta #'" + bikeNumber + "'";
        details = details + "\n" + "Deposito: " + deposit;
        details = details + "\n" + "Taxa diaria: " + rate;
        return details;
    }


    public int calculateCost(int numberOfDays){
        //work out the cost
        int cost = deposit + (rate*numberOfDays);
        System.out.println("Cost would be £ " + cost + "\n");
        return cost;
    }
}
