package com.phillinux94.webradio;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;


public class Controller {

    Media media;
    MediaPlayer player = null;

    @FXML
    private Button btnMusique;

    @FXML
    private Button btnInformation;

    @FXML
    private Button btnGeneraliste;

    @FXML
    private TitledPane paneMusique;

    @FXML
    private TitledPane paneInformation;

    @FXML
    private TitledPane paneGeneraliste;

    @FXML
    private Label time;

    @FXML
    private Label date;


    @FXML
    private void setMusique(){

        paneMusique.setVisible(true);
        paneInformation.setVisible(false);
        paneGeneraliste.setVisible(false);

    }

    @FXML
    private void setInformation(){

        paneMusique.setVisible(false);
        paneInformation.setVisible(true);
        paneGeneraliste.setVisible(false);

    }

    @FXML
    private void setGeneraliste(){

        paneMusique.setVisible(false);
        paneInformation.setVisible(false);
        paneGeneraliste.setVisible(true);

    }

    @FXML
    private void pause(){

        try {

            this.player.pause();

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }


    @FXML
    private void playRadioFg(){

        playRadio("http://radiofg.impek.com/fg");

    }

    @FXML
    private void playRtl2(){

        playRadio("http://streaming.radio.rtl2.fr/rtl2-1-44-128");

    }

    @FXML
    private void playSkyrock(){

        playRadio("http://www.skyrock.fm/stream.php/tunein16_128mp3.mp3");

    }

    @FXML
    private void playNrj(){

        playRadio("http://cdn.nrjaudio.fm/audio1/fr/30001/mp3_128.mp3?origine=fluxradios");

    }

    @FXML
    private void playRfm(){

        playRadio("https://ais-live.cloud-services.paris:8443/rfm.mp3");

    }

    @FXML
    private void playFranceInfo(){

        playRadio("http://direct.franceinfo.fr/live/franceinfo-midfi.mp3");

    }

    @FXML
    private void playBfmBusiness(){

        playRadio("http://chai5she.cdn.dvmr.fr/bfmbusiness");

    }

    @FXML
    private void playRtl(){

        playRadio("http://streaming.radio.rtl.fr/rtl-1-44-128");

    }

    @FXML
    private void playFranceInter(){

        playRadio("http://direct.franceinter.fr/live/franceinter-midfi.mp3");

    }

    @FXML
    private void playEurope1(){

        playRadio("http://ais-live.cloud-services.paris:8000/europe1.mp3");

    }

    private void playRadio(String urlRadio) {

        try {

            media = new Media(urlRadio);

            if (this.player != null){

                this.player.stop();

            }

            this.player = new MediaPlayer(media);

            this.player.setVolume(2.0);
            this.player.play();

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void Stop() {

        try {

            this.player.stop();

        }
        catch (Exception e){

            System.out.println(e.getMessage());

        }

    }

    @FXML
    public void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();

            String jour = String.valueOf(currentDate.getDayOfMonth());
            String mois = String.valueOf(currentDate.getMonthValue());
            String annee = String.valueOf(currentDate.getYear());

            jour = String.join("", Collections.nCopies(2 - jour.length(), "0")) + jour;
            mois = String.join("", Collections.nCopies(2 - mois.length(), "0")) + mois;

            date.setText(jour + "/" + mois + "/" + annee);
            String heures = String.valueOf(currentTime.getHour());
            String minutes = String.valueOf(currentTime.getMinute());
            String secondes = String.valueOf(currentTime.getSecond());

            heures = String.join("", Collections.nCopies(2 - heures.length(), "0")) + heures;
            minutes = String.join("", Collections.nCopies(2 - minutes.length(), "0")) + minutes;
            secondes = String.join("", Collections.nCopies(2 - secondes.length(), "0")) + secondes;

            time.setText(heures + ":" + minutes + ":" + secondes);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}

