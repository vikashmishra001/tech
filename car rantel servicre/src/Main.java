import  java.util.ArrayList;
import java.util.List;
import  java.util.Scanner;

class car{
    public static Object selectcar;
    private String carid;
    private String brand;
    private String model;
    private  double basepriseperday;

    private  boolean isAvailable;


public car( String carid, String brand, String model, double basepriseperday){
    this.carid = carid;
    this.brand = brand;
    this.model = model;
    this.basepriseperday = basepriseperday;
    this.isAvailable = true;
}
public  String getCarId(){
    return carid;
}
public  String getBrand(){
    return brand;
}
public  String getModel(){
    return  model;
}

public  double calculatePrice(int rentalDays){
    return basepriseperday*rentalDays;
}
public  boolean isAvailable(){
    return isAvailable;
}

public void rent(){
     isAvailable = false;
}
 public  void  returnCar(){
     isAvailable = true;
 }
 }
 class  customer{
     private final String name;
     public String cid;

    public customer(String cid,String name){
        this.cid = cid;
        this.name = name;
    }
    public String getCid(){
        return  cid;
    }
    public  String getName(){
        return name;
    }
 }

 class  rental{
    private  car car;
    private  customer customer;

    private  int days;

    public  rental( car car,customer customer,int days){
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
    public  car getCar(){
        return  car;
    }
    public customer getCustomer(){
        return  customer;
    }
    public  int getDays(){
        return days;
    }
 }

 class carRentalSystem{
    private  List<car> cars;
    private  List<customer> customers;
    private  List<rental> rentals;

    public carRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }
    public void addCar(car car){

        cars.add(car);}

     public void addcustomer(customer customer){
        customer.add (customer);
     }

     public void rentcar(car car, customer customer,int days){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new rental(car,customer,days));
        }
     }
     public void returncar(car car){
        car.returnCar();
        rental rentalToRemove = null;
        for (rental rental : rentals){
if (rental.getCar() == car){
    rentalToRemove = rental;
    break;
}
     }
        if (rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println("car return sucessfully");
        } else {
            System.out.println("the car is not returned");
        }
 }

 public void menu() {
     Scanner sc = new Scanner(System.in);

     while (true) {
         System.out.println("==== car rental system ====");
         System.out.println("1. rent a car");
         System.out.println("2. return a car");
         System.out.println("3. exit");
         System.out.println("enter your choice");
         int choice = sc.nextInt();
         sc.nextLine();// new line for customer

         if (choice == 1) {
             System.out.println("\n == rent a car== \n");
             System.out.println("enter your name");
             String customername = sc.nextLine();

             System.out.println("Avaliable car");
             for (car car : cars) {
                 if (car.isAvailable()) {
                     System.out.println(car.getCarId() + " - " + car.getBrand() + "  " + car.getModel());
                 }
             }
             System.out.println("\n enter the car id you wAnt to rent: ");
             String carId = sc.nextLine();

             System.out.println(" Enter the no of days ");
             int rentalDays = sc.nextInt();
             sc.nextLine();


             customer newCustomer = new customer(cid:"cus" + (customers.size() + 1), customername);
             addcustomer(newCustomer);
             car selectedcar = null;
             for (car car : cars) {
                 if (car.getCarId().equals(carId) && car.isAvailable()) {
                     selectedcar = car;
                     break;
                 }
             }
             if (selectedcar != null) {
                 double totalPrice = selectedcar.calculatePrice(rentalDays);
                 System.out.println("\n == rental info == \n");
                 System.out.println("customerid " + newCustomer.getCid());
                 System.out.println("customer name" + newCustomer.getName());
                 System.out.println("car:" + selectedcar.getBrand() + "  " + selectedcar.getModel());
                 System.out.println("rental days" + rentalDays);
                 System.out.println(" total price :=" + totalPrice);

                 System.out.println("\n confirm rental (y/n): ");
                 String confirm = sc.nextLine();
                 if (confirm.equalsIgnoreCase(anotherString:"y")){
                     rentcar(selectedcar, newCustomer, rentalDays);
                     System.out.println("\n rented sucessfully. ");
                 } else{
                     System.out.println("\n rental cancelled");
                 }

             } else {
                 System.out.println("\n invalid selection");
             }
         } else if (choice == 2) {
             System.out.println("\n return a car \n");
             System.out.println("enter the car id you want to retutn: ");
             String cariD = sc.nextLine();

             car carToreturn = null;
             for (car car : cars) {
                 if (car.getCarId().equals(cariD) && !car.isAvailable()) {
                     carToreturn = car;
                     break;
                 }
             }
             if (carToreturn != null) {
                 customer customer = null;
                 for (rental rental : rentals) {
                     if (rental.getCar() == carToreturn) {
                         customer = rental.getCustomer();
                         break;
                     }
                 }
                 if (customer != null) {
                     returncar(carToreturn);
                     System.out.println("car returned sucessfully by " + customer.getName());
                 } else {
                     System.out.println("car is no returned by customer or missing info");
                 }
             } else {
                 System.out.println("invalid car id");
             }

         } else if (choice == 3) {
             break;
         } else {
             System.out.println("invalid choice enter valid option");
         }
     }
 }
        public class Main {
            public static void main(String args[]) {
                carRentalSystem rentalSystem = new carRentalSystem();
                car car1 = new car(carid:"c001", brand:"toyota", model:"fortuner", basepriceperday:90.0);
                car car2 = new car(carid:"c002", brand:"tata", model:"safari", basepriceperday:100.0);
                car car3 = new car(carid:"c003", brand:"mahindra", model:"thar", basepriceperday:103.0);
                rentalSystem.addCar(car1);
                rentalSystem.addCar(car2);
                rentalSystem.addCar(car3);
            }
        }
