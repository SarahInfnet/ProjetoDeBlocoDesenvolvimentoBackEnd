package wheels;

public class Payment {
    //set up the member variables
    private Customer customer = null;
    private int paymentId = 0;

    private static int paymentCount = 001;

    public Payment(Customer cust){
        //set the member variables

        customer = cust;
        paymentId = paymentCount++;
    }

    public void calculateTotalPayment(Hire hire){
        //call the private method
        issueReceipt(hire);
    }

    private void issueReceipt(Hire hire){
        //print out all the relevant details
        String cust = hire.getCustomer().getName();
        String pcode = hire.getCustomer().getPostcode();
        System.out.println("Printing out receipt for '" + cust + "' ......");
        System.out.println("In postcode: " + pcode + "\n");

        System.out.println("Hiring bike number '" + hire.getBike().getBikeNumber() + "' for " + hire.getNumberOfDays() + " days" + "\n");
        hire.getBike().calculateCost(hire.getNumberOfDays());
    }
}
