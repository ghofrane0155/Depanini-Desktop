/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.userHome;

import Entity.Event;
import Entity.user;
import GUI.User.UserProfileController;
import GUI.participate.ItemsController;
import Utils.MyDB;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.EventService;
import services.FeedbackService;
import services.UserService;
import static services.UserService.search_user;
import services.metiersUser.SessionUser;
import static services.metiersUser.Verif.showAlert;

/**
 * FXML Controller class
 *
 * @author noure
 */
public class UserHomePageController implements Initializable {

    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;
    private Media mediaVideo;

    @FXML
    private Slider sliderTime;
    @FXML
    private HBox hboxControls;
    @FXML
    private Button buttonPPR;
    @FXML
    private HBox hboxVolume;
    @FXML
    private Label labelVolume;
    @FXML
    private Slider sliderVolume;
    @FXML
    private Label labelCurrentTime;
    @FXML
    private Label labelTotalTime;
    @FXML
    private Label labelSpeed;
    @FXML
    private Label labelFullScrene;
    /**
     * *************************************************************
     */
    private boolean atEndOfVid = false;
    private boolean isPlaying = true;
    private boolean isMuted = true;
    /**
     * **********************************************************
     */
    private ImageView ivPlay;
    private ImageView ivPause;
    private ImageView ivRestart;
    private ImageView ivVolume;
    private ImageView ivFullScreen;
    private ImageView ivMute;
    private ImageView ivExit;
    @FXML
    private VBox vboxParent;
    @FXML
    private Button Events;
    @FXML
    private Pane paneslide;
    @FXML
    private Button bar1;
    @FXML
    private Button bar2;
/*************************************************************************/
    Stage stage;
    Scene scene;
    Parent root;
    
    UserService us =new UserService();
    user utilisateur=null;
    private ListView<String> listView;
    
    @FXML
    private TextField search;
    @FXML
    private Button bar11;
    @FXML
    private Button bar22;
    @FXML
    private GridPane container;
    @FXML
    private ImageView tfimg;
    @FXML
    private Label tfnom_prenom;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView mail;
    private VBox pnItems;
    ArrayList<Event> Event = new ArrayList<>();
    @FXML
    private Button seemore;
    @FXML
    private Button reclamation;
    @FXML
    private Pane paneslide1;
    @FXML
    private Button bar12;
    @FXML
    private Text nombredejours;
Connection conx= MyDB.getInstance().getConx();;
    Statement stm;
//    public List<Event> Events;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
//        EventService es= new EventService();
//        try {
//            es.getevent(SessionUser.getLogin());
//            String strDate = formatter.format(date);
//            String d ="Il vous reste: "+CountDay(es.getevent(SessionUser.getLogin()).getDateDabEvent())+"jours pour participer a cet evenement";
//            nombredejours.setText(d);
//        } catch (ParseException ex) {
//            Logger.getLogger(UserHomePageController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserHomePageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
   
    
        
//        String path=SessionUser.getPhoto_user();
//        Image img=new Image(getClass().getResourceAsStream(path));
//        tfimg.setImage(img);
//        
//        tfnom_prenom.setText(SessionUser.getNom_user()+" "+SessionUser.getPrenom_user());
//        
        
    FeedbackService fs = new FeedbackService();

        ObservableList<String> userList = null;
        try {
            userList = fs.afficherNameAndStars();
        } catch (SQLException ex) {
            Logger.getLogger(UserHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(userList);
        listView = new ListView<>();

        listView.setItems(userList);

        listView.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user);
                }
            }
        });
        container.add(listView, 0, 0);       
        
        
/*************************************************************************************/
////////////////////////////////////les evenements//////////////////////////////////
//EventService serviceEvent = new EventService();
//        try {
//            Event.addAll(serviceEvent.getAll());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        // TODO
//       pnItems.getChildren().clear();
//       
//        
//        Node [] nodes = new  Node[15];
//        
//        
//        
//        for(int i = 0; i<Event.size() ; i++)
//        {
//             
//                try {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/GUI/participate/items.fxml"));
//                AnchorPane abc = fxmlLoader.load();
//                ItemsController itemcontroller = fxmlLoader.getController();
//                itemcontroller.getEvent(Event.get(i));
//                
//                
//                
//                
//                    pnItems.getChildren().add(abc);
//                } catch (IOException ex) {
//                    
//                }
//    }    
        /*****************************notification*****************************/
        paneslide1.setTranslateY(-280);
        bar12.setVisible(true);
        bar22.setVisible(false);
        
        
        /**
         * *****************************nav bar***********************
         */
        paneslide.setTranslateY(0);
        bar1.setVisible(true);
        bar2.setVisible(false);

        /**
         * ***************************************MediaAPI********************************************
         */
        final int IV_SIZE = 25;

        mediaVideo = new Media(new File("src/GUI/images/promo.mp4").toURI().toString());
        mpVideo = new MediaPlayer(mediaVideo);
        mvVideo.setMediaPlayer(mpVideo);

        Image imagePlay = new Image(new File("src/GUI/images/play-button-arrowhead.png").toURI().toString());
        ivPlay = new ImageView(imagePlay);
        ivPlay.setFitHeight(IV_SIZE);
        ivPlay.setFitWidth(IV_SIZE);

        Image imageStop = new Image(new File("src/GUI/images/video-pause-button.png").toURI().toString());
        ivPause = new ImageView(imageStop);
        ivPause.setFitHeight(IV_SIZE);
        ivPause.setFitWidth(IV_SIZE);

        Image imageRestart = new Image(new File("src/GUI/images/rewind.png").toURI().toString());
        ivRestart = new ImageView(imageRestart);
        ivRestart.setFitHeight(IV_SIZE);
        ivRestart.setFitWidth(IV_SIZE);

        Image imageVol = new Image(new File("src/GUI/images/volume-up-interface-symbol.png").toURI().toString());
        ivVolume = new ImageView(imageVol);
        ivVolume.setFitHeight(IV_SIZE);
        ivVolume.setFitWidth(IV_SIZE);

        Image imageFull = new Image(new File("src/GUI/images/fullscreen.png").toURI().toString());
        ivFullScreen = new ImageView(imageFull);
        ivFullScreen.setFitHeight(IV_SIZE);
        ivFullScreen.setFitWidth(IV_SIZE);

        Image imageMute = new Image(new File("src/GUI/images/mute.png").toURI().toString());
        ivMute = new ImageView(imageMute);
        ivMute.setFitHeight(IV_SIZE);
        ivMute.setFitWidth(IV_SIZE);

        Image imageExit = new Image(new File("src/GUI/images/Exit.png").toURI().toString());
        ivExit = new ImageView(imageExit);
        ivExit.setFitHeight(IV_SIZE);
        ivExit.setFitWidth(IV_SIZE);

        buttonPPR.setGraphic(ivPause);
        labelVolume.setGraphic(ivMute);
        labelSpeed.setText("1X");
        labelFullScrene.setGraphic(ivFullScreen);

        buttonPPR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button buttonPlay = (Button) actionEvent.getSource();
                if (atEndOfVid) {
                    sliderTime.setValue(0);
                    atEndOfVid = false;
                    isPlaying = false;
                }

                if (isPlaying) {
                    buttonPlay.setGraphic(ivPlay);
                    mpVideo.pause();
                    isPlaying = false;
                } else {
                    buttonPlay.setGraphic(ivPause);
                    mpVideo.play();
                    isPlaying = true;
                }
            }

        });

        hboxVolume.getChildren().remove(sliderVolume);

        mpVideo.volumeProperty().bindBidirectional(sliderVolume.valueProperty());

        bindCurrentTimeLabel();
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mpVideo.setVolume(sliderVolume.getValue());
                if (mpVideo.getVolume() != 0.0) {
                    labelVolume.setGraphic(ivVolume);
                    isMuted = false;

                } else {
                    labelVolume.setGraphic(ivMute);
                    isMuted = true;
                }
            }

        });
        labelSpeed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (labelSpeed.getText().equals("1X")) {
                    labelSpeed.setText("X2");
                    mpVideo.setRate(2.0);
                } else {
                    labelSpeed.setText("X1");
                    mpVideo.setRate(1.0);
                }
            }

        });

        labelVolume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!isMuted) {
                    labelVolume.setGraphic(ivVolume);
                    sliderVolume.setValue(0.2);
                    isMuted = false;
                } else {
                    labelVolume.setGraphic(ivMute);
                    sliderVolume.setValue(0.2);
                    isMuted = true;
                }
            }

        });

        labelVolume.setOnMouseDragEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (hboxVolume.lookup("sliderVolume") == null) {
                    hboxVolume.getChildren().add(sliderVolume);
                    sliderVolume.setValue(mpVideo.getVolume());
                }
            }
        });

        hboxVolume.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hboxVolume.getChildren().remove(sliderVolume);
            }
        });

        vboxParent.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene oldScene, Scene newScene) {
                if (oldScene == null && newScene != null) {
                    mvVideo.fitHeightProperty().bind(newScene.heightProperty().subtract(hboxControls.heightProperty().add(20)));
                }
            }

        });

        labelFullScrene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Label label = (Label) mouseEvent.getSource();
                Stage stage = (Stage) label.getScene().getWindow();
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    labelFullScrene.setGraphic(ivFullScreen);
                } else {
                    stage.setFullScreen(true);
                    labelFullScrene.setGraphic(ivExit);
                }
                stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.ESCAPE) {
                            labelFullScrene.setGraphic(ivFullScreen);
                        }
                    }

                });
            }

        });

        mpVideo.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldDuration, Duration newDuration) {
                sliderTime.setMax(newDuration.toSeconds());
                labelTotalTime.setText(getTime(newDuration));
            }

        });

        sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean wasChanging, Boolean isChanging) {
                if (!isChanging) {
                    mpVideo.seek(Duration.seconds(sliderTime.getValue()));
                }
            }

        });

        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double currentTime = mpVideo.getCurrentTime().toSeconds();
                if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
                    mpVideo.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
            }

        });
        /**
         * ***************n9admou fel vid*****************
         */
        mpVideo.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldTime, Duration newTime) {
                if (!sliderTime.isValueChanging()) {
                    sliderTime.setValue(newTime.toSeconds());
                }
                labelMatchEndVideo(labelCurrentTime.getText(), labelTotalTime.getText());
            }

        });
        /**
         * *****************reset everything **************
         */
        mpVideo.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                buttonPPR.setGraphic(ivRestart);
                atEndOfVid = true;
                if (!labelCurrentTime.textProperty().equals(labelTotalTime.textProperty())) {
                    labelCurrentTime.textProperty().unbind();
                    labelCurrentTime.setText(getTime(mpVideo.getTotalDuration()) + "/");
                }
            }

        });

    }

      /*******************************************************************************/
//    private int CountDay( Date d) throws ParseException{
//    
//          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//          Date localDates = new Date();
//        String dateString = sdf.format(d);
//          Date dateApres = sdf.parse(dateString);
//
//          
//         long diff = dateApres.getTime() - localDates.getTime() ;
//         float res = ((diff / (1000*60*60*24))+1);
//         System.out.println("Nombre de jours entre les deux dates est: "+res);        
//    return (int) res ;
//    
//    }
     
    /**********************************************************************************/
    public void bindCurrentTimeLabel() {
        labelCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mpVideo.getCurrentTime()) + "/";
            }
        }, mpVideo.currentTimeProperty()));
    }

    public String getTime(Duration time) {
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        long seconds = (long) time.toSeconds();

        if (seconds > 59) {
            seconds = seconds % 60;
        }
        if (minutes > 59) {
            minutes = minutes % 60;
        }
        if (hours > 59) {
            hours = hours % 60;
        }

        if (hours > 0) {
            return String.format("%d:%02d:%02d",
                    hours,
                    minutes,
                    seconds);
        } else {
            return String.format("%02d:%02d",
                    minutes,
                    seconds);
        }

    }

    public void labelMatchEndVideo(String LabelTime, String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {
            if (LabelTime.charAt(i) != labelTotalTime.charAt(i)) {
                atEndOfVid = false;
                if (isPlaying) {
                    buttonPPR.setGraphic(ivPlay);
                }
                break;
            } else {
                atEndOfVid = true;
                buttonPPR.setGraphic(ivRestart);
            }
        }
    }

    @FXML
    private void acc(ActionEvent event) {
    }

    @FXML
    private void EventsList(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/participate.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

    @FXML
    private void run2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();;
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(-240);
        slide.play();
        paneslide.setTranslateX(0);
        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(true);
            bar2.setVisible(false);
        });
    }

    @FXML
    private void run1(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();;
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide);
        slide.setToX(0);
        slide.play();
        paneslide.setTranslateX(-160);
        slide.setOnFinished((ActionEvent e) -> {
            bar1.setVisible(false);
            bar2.setVisible(true);
        });
    }
    /****************************************notification*******************************************************/
    
    
    
     @FXML
    private void notificationEvent22(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();;
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide1);
        slide.setToY(-280);
        slide.play();
        paneslide1.setTranslateY(0);
        slide.setOnFinished((ActionEvent e) -> {
            bar12.setVisible(true);
            bar22.setVisible(false);
        });
    }

    
    
    
    
    @FXML
    private void notificationEvent12(ActionEvent event) {
        
        TranslateTransition slide = new TranslateTransition();;
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(paneslide1);
        slide.setToY(0);
        slide.play();
        paneslide1.setTranslateY(-160);
        slide.setOnFinished((ActionEvent e) -> {
            bar12.setVisible(false);
            bar22.setVisible(true);
        });
    }
    
    
    
    
    
  
/**********************Ghof*******************************************************************************/
   @FXML
    private void search(MouseEvent event) throws IOException {

        if(search_user("nom_user",search.getText())!=null)
            utilisateur=search_user("nom_user",search.getText());
        else
            if(search_user("prenom_user",search.getText())!=null)
                utilisateur=search_user("prenom_user",search.getText());
            else
               if(search_user("login",search.getText())!=null)
                  utilisateur=search_user("login",search.getText());
               else{
               }
        
        if(utilisateur==null)
           showAlert(Alert.AlertType.ERROR, "Données erronés", "", "Utilisateur n'existe pas ");  
        else{

            FXMLLoader loader=new FXMLLoader(getClass().getResource("/GUI/User/userProfile.fxml"));
            root =loader.load();
            
            UserProfileController userprofile=loader.getController();
            userprofile.displayInfo(utilisateur); 
      
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene =new Scene(root);
                Stage stage = new Stage();
            stage.setScene(scene);   
            stage.show();  
        }
    }
/*****************Ghofrane***********************************************************************/
    @FXML
    private void prifile_clicked(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Profile.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void notif_clicked(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Notification.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void password_clicked(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Password.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void settings_clicked(ActionEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Settings.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();        
    }

    @FXML
    private void depanini_clicked(MouseEvent event) throws IOException {
        root =FXMLLoader.load(getClass().getResource("/GUI/userHome/userHomePage.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    
    @FXML
    private void fbLink(ActionEvent event) throws IOException {
        Desktop desk =Desktop.getDesktop();
        desk.browse(java.net.URI.create("https://www.facebook.com/profile.php?id=100090858939181"));
    }

    @FXML
    private void instaLink(ActionEvent event) throws IOException {
        Desktop desk =Desktop.getDesktop();
        desk.browse(java.net.URI.create("https://www.instagram.com/depanini2023"));        
    }

    @FXML
    private void mailLink(ActionEvent event) throws IOException {
        Desktop desk =Desktop.getDesktop();
        desk.browse(java.net.URI.create("mailto:depanini2023@gmail.com"));
        }


    @FXML
    private void evenement(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/participate/participate.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }
    
    
    
    
    
    

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
                Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamation.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }

   

/*****************************************************************************************************/
}
