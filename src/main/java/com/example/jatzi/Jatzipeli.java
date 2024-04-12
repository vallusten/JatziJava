package com.example.jatzi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Jatzipeli extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    ArrayList[] tulokset;
    ArrayList nopat;
    String polku = "huippupisteet.dat";
    ArrayList<Integer> tilanne1 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0));
    ArrayList<Integer> tilanne2 = new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0));
    ArrayList<Integer> tilanne3 = new ArrayList<Integer>(Arrays.asList(0, 0));
    ArrayList<Integer> tilanne4 = new ArrayList<Integer>(Arrays.asList(0));
    Image yksin = new Image("yksi.png");
    ImageView yksinview = new ImageView(yksin);
    Image kaksin = new Image("kaksi.png");
    ImageView kaksinview = new ImageView(kaksin);
    Image kolmen = new Image("kolme.png");
    ImageView kolmenview = new ImageView(kolmen);
    Image neljan = new Image("neljä.png");
    ImageView neljanview = new ImageView(neljan);
    Image viisin = new Image("viisi.png");
    ImageView viisinview = new ImageView(viisin);
    Image kuusin = new Image("kuusi.png");
    ImageView kuusinview = new ImageView(kuusin);
    int heittolaskuri = 0;
    private ObjectInputStream luettavaTiedosto = null;
    int huippupisteet = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            luettavaTiedosto = new ObjectInputStream(new FileInputStream(polku));
            if (luettavaTiedosto != null) {
                Object objekti = null;
                try{
                    while((objekti = luettavaTiedosto.readObject()) != null) {
                        Peli peli  =(Peli) objekti;
                        huippupisteet = peli.getHuippupisteet();
                    }
                } catch (ClassNotFoundException CNFE) {

                }
            }
            luettavaTiedosto.close();
        } catch(FileNotFoundException e) {

        } catch (IOException IOex) {

        }
        //
        Image logo = new Image("Jatzilogo.png");
        ImageView logoview = new ImageView(logo);
        logoview.setX(260);
        logoview.setY(95);
        //
        Image tausta = new Image("tausta.png");
        ImageView taustaview = new ImageView(tausta);
        //
        Image tausta2 = new Image("tulostausta.png");
        ImageView tausta2view = new ImageView(tausta2);
        //
        Image upnappi = new Image("uusipeli.png");
        ImageView upnappiview = new ImageView(upnappi);

        //
        Image spnappi = new Image("suljepeli.png");
        ImageView spnappiview = new ImageView(spnappi);
        //
        Image tnappi = new Image("takaisin.png");
        ImageView tnappiview = new ImageView(tnappi);
        //
        Image heitta = new Image("heitänappi.png");
        ImageView heitaview = new ImageView(heitta);
        //
        Button takaisin = new Button();
        takaisin.setLayoutX(615);
        takaisin.setLayoutY(535);
        takaisin.setGraphic(tnappiview);
        takaisin.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );                                                // Chat-gpt auttoi poistamaan reunukset napista

        //

        Pane nopitus = new Pane();

        nopitus.getChildren().add(tausta2view);



        //

        Scene mainpeli = new Scene(nopitus);

        //


        Button uusi_peli = new Button();
        uusi_peli.setLayoutX(215);
        uusi_peli.setLayoutY(260);
        uusi_peli.setGraphic(upnappiview);
        uusi_peli.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );                                                // Chat-gpt auttoi poistamaan reunukset napista
        uusi_peli.setOnAction(event -> {
            primaryStage.setScene(mainpeli);
        });

        //
        Button sulje_peli = new Button();
        sulje_peli.setLayoutX(215);
        sulje_peli.setLayoutY(350);
        sulje_peli.setGraphic(spnappiview);
        sulje_peli.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        sulje_peli.setOnAction(event -> {
            primaryStage.close();
        });
        //

        Label tilasto1 = new Label(Arrays.toString(tilanne1.toArray()));
        tilasto1.setWrapText(true);
        tilasto1.setMaxWidth(20);

        Label tilasto2 = new Label(Arrays.toString(tilanne2.toArray()));
        tilasto2.setWrapText(true);
        tilasto2.setMaxWidth(10);
        Label bonus = new Label("0");
        Label tilasto3 = new Label("0, 0");
        tilasto3.setWrapText(true);
        tilasto3.setMaxWidth(10);
        Label tilasto4 = new Label("0");
        tilasto4.setWrapText(true);
        tilasto4.setMaxWidth(10);
        //
        pelintoiminta toiminta = new pelintoiminta();
        //

        VBox tilastotVBox = new VBox();
        tilastotVBox.getChildren().addAll(tilasto1, tilasto3, tilasto2, tilasto4);
        tilastotVBox.setLayoutX(300);
        tilastotVBox.setLayoutY(100);
        //
        HBox nopatHBox = new HBox();
        nopatHBox.setLayoutX(425);
        nopatHBox.setLayoutY(245);
        nopatHBox.getChildren().addAll(yksinview, kaksinview, kolmenview, neljanview, viisinview);
        //
        Button heita = new Button();
        heita.setLayoutX(390);
        heita.setLayoutY(295);
        heita.setGraphic(heitaview);
        heita.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0;"
        );
        /**
         * Nopan heitto
         */
        heita.setOnAction(event -> {
            if (heittolaskuri <= 13){
                tulokset = toiminta.heitto();
                nopat = tulokset[0];
                tilanne1 = tulokset[1];
                tilanne2 = tulokset[2];
                tilanne3 = tulokset[3];
                tilanne4 = tulokset[4];
                heittolaskuri += 1;
                if (heittolaskuri == 13){
                    if(tilanne4.get(0) > huippupisteet) {
                        Peli ppeli = new Peli(tilanne4.get(0), "nimi", new Date());
                        try {
                            ObjectOutputStream tallennettavaTiedosto = new ObjectOutputStream(new FileOutputStream(polku));
                            tallennettavaTiedosto.writeObject(ppeli);
                            tallennettavaTiedosto.close();
                        } catch (FileNotFoundException fileNotFound) {
                            System.out.println("Tiedostoa ei löytynyt, luodaan uusi tiedosto");

                        } catch (IOException ioex) {
                            System.out.println(ioex);
                        }
                    }
                }
            }
            int i = 0;
            for (Object child : nopatHBox.getChildren()) {
                ImageView imgView = (ImageView) child;
                int noppa = (int) nopat.get(i);
                switch(noppa){
                    case 1:
                        imgView.setImage(yksin);
                        break;
                    case 2:
                        imgView.setImage(kaksin);
                        break;
                    case 3:
                        imgView.setImage(kolmen);
                        break;
                    case 4:
                        imgView.setImage(neljan);
                        break;
                    case 5:
                        imgView.setImage(viisin);
                        break;
                    case 6:
                        imgView.setImage(kuusin);
                        break;
                }
                i++;
            }
            tilasto1.setText(Arrays.toString(tilanne1.toArray()));
            tilasto2.setText(Arrays.toString(tilanne2.toArray()));
            tilasto4.setText(Arrays.toString(tilanne4.toArray()));
            tilasto3.setText(Arrays.toString(tilanne3.toArray()));
        });
        //
        Label ennatys = new Label("Ennätys:" + huippupisteet );

        Pane root = new Pane();

        root.getChildren().add(taustaview);
        root.getChildren().add(logoview);
        root.getChildren().add(sulje_peli);
        root.getChildren().add(uusi_peli);
        root.getChildren().add(ennatys);


        //
        Scene valikko = new Scene(root);
        //
        takaisin.setOnAction(event -> {
            primaryStage.setScene(valikko);
        });
        nopitus.getChildren().add(takaisin);
        nopitus.getChildren().add(heita);
        nopitus.getChildren().add(nopatHBox);
        nopitus.getChildren().add(tilastotVBox);

        //
        primaryStage.setScene(valikko);
        primaryStage.setTitle("Jatzi");
        primaryStage.setWidth(800);
        primaryStage.setHeight(635);
        primaryStage.show();

        //




    }
}
