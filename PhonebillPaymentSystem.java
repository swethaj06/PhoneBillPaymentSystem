import java.util.Scanner;

class Customer {
    private String name;
    private String phoneNumber;
    private double amountDue;
    private int callMinutes;

    // Constructor
    public Customer(String name, String phoneNumber, int callMinutes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.callMinutes = callMinutes;
        this.amountDue = calculateBill();
    }

    // Method to calculate bill based on call minutes and data used
    
    private double calculateBill() {
        double callRate = 0.5; // Charge per minute
        return (callMinutes * callRate);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
}

class PhoneBillPaymentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input customer details
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter Call Minutes Used: ");
        int callMinutes = scanner.nextInt();

        // Create Customer object and automatically calculate the bill
        Customer customer = new Customer(name, phoneNumber, callMinutes);

        // Display Customer Information and Bill Details
        System.out.println("\n----- Customer Bill Details -----");
        System.out.println("Name: " + customer.getName());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.println("Call Minutes Used: " + callMinutes + " mins");
        System.out.println("Amount Due: $" + customer.getAmountDue());

        // Bill payment option
        System.out.print("\nEnter payment amount: ");
        double payment = scanner.nextDouble();

        if (payment >= customer.getAmountDue()) {
            customer.setAmountDue(0);
            System.out.println("Payment successful! No outstanding amount.");
        } else {
            customer.setAmountDue(customer.getAmountDue() - payment);
            System.out.printf("Partial payment received. Remaining amount due: $%.2f\n", customer.getAmountDue());
        }

        // Final bill summary
        System.out.println("\n----- Final Bill Summary -----");
        System.out.println("Name: " + customer.getName());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.printf("Final Amount Due: $%.2f\n", customer.getAmountDue());

        scanner.close();
    }
}