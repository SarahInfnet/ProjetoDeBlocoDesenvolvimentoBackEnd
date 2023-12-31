package wheels;

import model.Bike;
import model.Customer;
import model.Hire;
import model.Payment;

import java.util.Date;

public class IssueBikeUI_notUsed {
    //set up the member variables
    private Bike chosenBike = null;
    private Customer customer = null;
    private Payment payment = null;
    private Hire hire = null;
    private int numberOfDays = 0;

    public void showBikeDetails(int bikeNum){
        //find the bike by its number
        chosenBike = Bike.findBikeByNumber(bikeNum);
        if(chosenBike != null){
            chosenBike.showDetails();
        }
    }

    public void calculateCost(int numDays){
        //set the member variable so it can be used later
        numberOfDays = numDays;
        //then ask the bike for the cost
        chosenBike.calculateCost(numDays);
    }

    public void createCustomer(String name, String postcode, int telephone){
        //create a customer and associated hire and payment
        customer = new Customer(name, postcode, telephone);
        payment = new Payment(customer);
        hire = new Hire(new Date(), numberOfDays, chosenBike, customer);
    }

    public void calculateTotalPayment(){
        //get the total payment from the payment object
        payment.calculateTotalPayment(hire);
    }
}
