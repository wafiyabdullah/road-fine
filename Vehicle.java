abstract class Vehicle {
    double speed;
    double speedLimit;

    Vehicle(){
        speed = 0.0;
        speedLimit = 0.0;
    }

    Vehicle(double speed, double speedLimit) {
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String toString(){
        return "speed "+speed+
        "speed limit "+speedLimit;
    }

    abstract double getTotalPayment();
}
