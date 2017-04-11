package MainGeocod;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by 1234 on 4/8/2017.
 */
public class MainController  {

    GeocodingProcess geocodingProcess;
    String area, lat, lng;
    In in;
    Out out;

    @FXML
    Label customer, region, address;


    public MainController() throws IOException {
        in=new In("C:\\Users\\1234\\Downloads\\in.xls");
        out = new Out("C:\\Users\\1234\\Downloads\\out.xls");
    }



    public void start_process(ActionEvent actionEvent) throws Exception {



        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке

            {


                System.out.println("Привет из побочного потока!");

                try {
                customer.setText("Hellow from other thread");


                    System.out.println("This is a number a lines: " + in.getNumberOfLines());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        myThready.start();




        Thread myThready1 = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке

            {
                System.out.println("Привет из побочного потока!");





        for(int j=1; j<7; j++) {
            String[] str = new String[0];
            try {
                str = in.getInputLine(j);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("This is a number a lines: " + in.getNumberOfLines());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("First line: " + str[0]);

            try {
                geocodingProcess = new GeocodingProcess(str[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            area = geocodingProcess.getArea();
    lat = geocodingProcess.getLat();
    lng = geocodingProcess.getLng();

    try {
        out.fileCreation();
    } catch (Exception e) {
        e.printStackTrace();
    }
            try {
                out.setOutPutData(j, str, area, lat, lng);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println();
    System.out.println("This is responce from Controller: " + area);
    System.out.println("This is responce from Lat: " + lat);
    System.out.println("This is responce from Lng: " + lng);

  //  customer.setText(str[0]);
 //   address.setText(str[1]);
 //   region.setText(area);
}

            }
        });
        myThready1.start();

    }


    public void stop_action(ActionEvent actionEvent) {

    }

}
