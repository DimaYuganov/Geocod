package MainGeocod;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by 1234 on 4/8/2017.
 */
public class MainController  {

    GeocodingProcess geocodingProcess;
    public String area, lat, lng, name, locationAddress, city;
    In in;
    Out out;
    public Task task;
    public int totalNumbers;

    @FXML
    Label customer, region, address, fileLabel;
    @FXML
    ProgressBar progressBar;

    public MainController() throws IOException {
        // in=new In("C:\\Users\\1234\\Downloads\\in.xls");
        // out = new Out("C:\\Users\\1234\\Downloads\\out.xls");
    }



    public void start_process(ActionEvent actionEvent) throws Exception {



       task = new Task<Void>() {
            @Override public Void call() throws Exception {
            totalNumbers = in.getNumberOfLines();
                out.fileCreation();

                for (int j=0; j<=totalNumbers; j++) {
                   if (isCancelled()) {
                      break;
                  }
                    String[] inputString = new String[4];

                            inputString = in.getInputLine(j);

                            System.out.println("This is a number a lines: " + in.getNumberOfLines());
            name=inputString[0];
                    locationAddress=inputString[1];
                        System.out.println("First line: " + inputString[0]);

try {
    geocodingProcess = new GeocodingProcess(inputString[1]);

    area = geocodingProcess.getArea();
    lat = geocodingProcess.getLat();
    lng = geocodingProcess.getLng();
    city = geocodingProcess.getCity();


} catch (Exception e){
    System.out.println("geocoding problem");



}


                            out.setOutPutData(j,totalNumbers, inputString, area, lat, lng, city);

                        System.out.println();
                        System.out.println("This is responce from Controller: " + area);
                        System.out.println("This is responce from Lat: " + lat);
                        System.out.println("This is responce from Lng: " + lng);
                        System.out.println("This is responce from City: " + city);


                    updateProgress(j, totalNumbers);


                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            region.setText(area);
                            customer.setText(name);
                            address.setText(locationAddress);
                        }
                    });




                }
                return null;
            }


        };
      //  progressBar = new ProgressBar();
        progressBar.progressProperty().bind(task.progressProperty());



        new Thread(task).start();



    }


    public void stop_action(ActionEvent actionEvent) {
        task.cancel();
    }

    public void fileOpen(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        Stage stage = new Stage();

        File file = fileChooser.showOpenDialog(stage);

        String st1 = file.getPath();
        String st2 = file.getName();
        int st3 = st1.indexOf(st2);


        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(st3);

        fileLabel.setText(file.getPath());


         in=new In(file.getPath());
         out = new Out(file.getParent()+"\\out.xls");

    }
}
