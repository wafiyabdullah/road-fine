class Bike extends Vehicle {

    double totalPayment;

    Bike() {
        totalPayment = 0.0;
    }

    Bike(double speed, double speedLimit) {
        super(speed, speedLimit);
    }

    //calculate the fine price
    double getTotalPayment() {

        double fine = 0;
        double overSpeed = getSpeed() - getSpeedLimit();

        if (overSpeed <= 0) {
            return fine;
        } 
        else if (overSpeed > 0 && overSpeed <= 10) {
            fine = 20;
        } 
        else if (overSpeed > 10 && overSpeed <= 20) {
            fine = 50;
        } 
        else if (overSpeed > 20 && overSpeed <= 30) {
            fine = 100;
        } 
        else {
            fine = 200;
        }

        return fine;  
    }

    public String toString(){
        return super.toString()+
        "total payment "+totalPayment;
    }
}
