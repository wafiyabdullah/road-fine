import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import java.io.*;

public class Interfaces extends Application {

    TextField vSpeed = new TextField();
    TextField spLimit = new TextField();
    TextField tPayment = new TextField();

    Button BtCalc = new Button("Calculate");
    Button print = new Button("Print Receipt");

    RadioButton rbCar = new RadioButton("Car");
    RadioButton rbBike = new RadioButton("Bike");

    GridPane gpane = new GridPane();
    String v;
    String VehicleType = null;

    @Override
    public void start(Stage primaryStage){

        ToggleGroup group = new ToggleGroup();

        //create ui
        gpane.setVgap(5); //vertical gap
        gpane.setHgap(5); //horizontal gap between labels

        gpane.add(new Label("Please choose your vehicle type "), 0,0);
        gpane.add(rbCar,1, 0);
        rbCar.setToggleGroup(group);
        gpane.add(rbBike,1, 1);
        rbBike.setToggleGroup(group);

        gpane.add(new Label("Vehicle Speed (km/h) : "), 0, 2);
        gpane.add(vSpeed, 1, 2);

        gpane.add(new Label("Vehicle Speed Limit (km/h) : "), 0, 3);
        gpane.add(spLimit, 1, 3);
        gpane.add(BtCalc, 1, 5);

        //ui properties
        gpane.setAlignment(Pos.CENTER);
        vSpeed.setAlignment(Pos.BASELINE_LEFT);
        spLimit.setAlignment(Pos.BASELINE_LEFT);
        tPayment.setAlignment(Pos.BASELINE_LEFT);
        tPayment.setEditable(false);
        GridPane.setHalignment(BtCalc, HPos.LEFT);

        //to calculate the total fine
        rbCar.setOnAction(e->{
                    if(rbCar.isSelected()) {
                        v = "car";
                        BtCalc.setOnAction(f->SubmitConfirmationButton()); //submit button
                    }
            });

        rbBike.setOnAction(e->{
                    if(rbBike.isSelected()) {
                        v = "bike";
                        BtCalc.setOnAction(f->SubmitConfirmationButton()); //submit button
                    }
            });

        //create scene and place it in the stage
        Scene scene = new Scene(gpane, 500, 500);
        primaryStage.setTitle("Road Fine System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //submit button
    public void SubmitConfirmationButton() {
        gpane.add(new Label("For confirmation, press confirm"), 1,8);
        Button submitButton = new Button("Confirm");
        gpane.add(submitButton, 1, 10);
        submitButton.setOnAction(e->Calculation());
    }

    //calculation fine 
    public void Calculation() {

        //casting the textfield data field into double
        double VehicleSpeed = Double.parseDouble(vSpeed.getText());
        double SpeedLimit = Double.parseDouble(spLimit.getText());      

        //selection for the calculation of either car or bike
        if(v.equals("car")) {
            VehicleType = "Car";
            if(VehicleSpeed >= SpeedLimit) {
                int cspeed = Integer.parseInt(vSpeed.getText());
                int limitSpeed = Integer.parseInt(spLimit.getText());
                Car c = new Car(cspeed, limitSpeed);
                tPayment.setText(String.format("RM%.2f", c.getTotalPayment()));
            }
            else {
                Button submitButton = new Button("Submit");
                gpane.add(submitButton, 1, 6);
                int cspeed = Integer.parseInt(vSpeed.getText());
                int limitSpeed = Integer.parseInt(spLimit.getText());
                Car c = new Car(cspeed, limitSpeed);
                tPayment.setText(String.format("RM%.2f", c.getTotalPayment()));
            }
        }

        if(v.equals("bike")) {
            VehicleType = "bike";
            if(VehicleSpeed >= SpeedLimit) {
                int bspeed = Integer.parseInt(vSpeed.getText());
                int limitSpeed1 = Integer.parseInt(spLimit.getText());
                Bike b = new Bike(bspeed, limitSpeed1);
                tPayment.setText(String.format("RM%.2f", b.getTotalPayment()));
            }
            else {
                int bspeed = Integer.parseInt(vSpeed.getText());
                int limitSpeed1 = Integer.parseInt(spLimit.getText());
                Bike b = new Bike(bspeed, limitSpeed1);
                tPayment.setText(String.format("RM%.2f", b.getTotalPayment()));
            }
        }

        //display the total payment for the fine
        gpane.add(new Label("Total Payment (fine): "), 0, 12);
        gpane.add(tPayment, 1, 12);

        gpane.add(print,0,13); //print button

        //button receipt action
        print.setOnAction(e->
                {
                    try
                    {
                        Receipt();
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
            });
    }

    //write receipt into FineReceipt.txt
    public void Receipt() throws Exception{
        FileWriter f = new FileWriter("FineReceipt");
        BufferedWriter b = new BufferedWriter(f);
        PrintWriter pw = new PrintWriter(b);

        pw.println("Your vehicle type  = "+VehicleType);
        pw.println("Your vehicle speed = "+vSpeed.getText());
        pw.println("Vehicle speedlimit = "+spLimit.getText());
        pw.println("Your total fine    = "+tPayment.getText());
        
        pw.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
