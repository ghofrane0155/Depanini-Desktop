/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.reclamation;

import Entity.Reclamation;
import Entity.Statut;
import Entity.Type;
import Entity.admin;
import Entity.user;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.AdminService;
import services.FeedbackService;
import services.ReclamationService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLReclamationAdminController implements Initializable {

    @FXML
    private TableView<Reclamation> table_reclamation;
    @FXML
    private TableColumn<Reclamation, String> nom_user_col;
    @FXML
    private TableColumn titre_reclamatio_col;
    @FXML
    private TableColumn etat_reclamation_col;

    @FXML
    private TableColumn<Reclamation, Date> marck_col;
    @FXML
    private TableColumn action_col;

    public Reclamation r = null;
    public List<Reclamation> reclamations = new ArrayList<>();
    ;
    private ReclamationService rs = new ReclamationService();
    @FXML
    private TextField search;
    private ObservableList<Reclamation> dataList = FXCollections.observableArrayList();
    private FilteredList<Reclamation> filteredList = new FilteredList<>(dataList);
    @FXML
    private ImageView image_re;

    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private ImageView tfimg;
    @FXML
    private Label tfnom1;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout1;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
 /*********************************************************/   
    public user u;
    public admin a;
    public List<user> users;
    private UserService us=new UserService();
    private AdminService as=new AdminService();
 /***************************************/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         a = as.getAdminByLogin("admin");
        String path = a.getPhoto_admin();
        Image img = new Image(getClass().getResourceAsStream(path));
        tfimg.setImage(img);
        tfnom1.setText(a.getNom_admin() + " " + a.getPrenom_admin());
        FeedbackService fs = new FeedbackService();
        
        dataList = aficherreclamation();
        // table_reclamation.setItems(dataList);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Reclamation> filteredList = new FilteredList<>(dataList, p -> true);
            filteredList.setPredicate(rec -> {
                if (newValue == null || newValue.isEmpty()) {
                    aficherreclamation();
                    return true;
                }
                String lowerCaseSearchText = newValue.toLowerCase();
                return rec.getNomUtilisateur().toLowerCase().contains(lowerCaseSearchText);
            });
            aficherreclamation();
            table_reclamation.setItems(filteredList);

        });

    }

    public ObservableList<Reclamation> aficherreclamation() {

        ObservableList<Reclamation> list_rec = FXCollections.observableArrayList();
        image_re.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aficherreclamation();
            }
        });
        nom_user_col.setCellValueFactory(new PropertyValueFactory<>("nomUtilisateur"));
        marck_col.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date_reclamation"));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory1 = (param) -> {

            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Reclamation r = (Reclamation) getTableRow().getItem();
                        String etat = r.getStatut().getLabel();
                        if (etat == "En_attente") {
                            HBox hbox = new HBox();
                            ImageView imageView = new ImageView(new Image("GUI/images/icons8-spinner.gif"));
                            imageView.setFitWidth(25);
                            imageView.setFitHeight(25);
                            Label label = new Label("En attente");
                            hbox.getChildren().addAll(imageView, label);
                            hbox.setSpacing(5);
                            hbox.setStyle("-fx-alignment: CENTER;");
                            setGraphic(hbox);
                            setText(null);

                        } else if (etat == "Resolu") {
                            HBox hbox = new HBox();
                            ImageView imageView = new ImageView(new Image("GUI/images/icons8-verifie.gif"));
                            imageView.setFitWidth(25);
                            imageView.setFitHeight(25);
                            Label label = new Label("Résolut");
                            hbox.getChildren().addAll(imageView, label);
                            hbox.setSpacing(5);
                            hbox.setStyle("-fx-alignment: CENTER;");
                            setGraphic(hbox);
                            setText(null);
                        } else {
                            HBox hbox = new HBox();
                            ImageView imageView = new ImageView(new Image("GUI/images/icons8-envoyer.gif"));
                            imageView.setFitWidth(25);
                            imageView.setFitHeight(25);
                            Label label = new Label("Ouvert");
                            hbox.getChildren().addAll(imageView, label);
                            hbox.setSpacing(5);
                            hbox.setStyle("-fx-alignment: CENTER;");
                            setGraphic(hbox);
                            setText(null);
                        }

                        setText(null);

                    }
                }
            };
            return cell;
        };

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory2 = (param) -> {

            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Reclamation r = (Reclamation) getTableRow().getItem();
                        Label l = new Label(r.getType().getLabel());
                        setGraphic(l);
                        setText(null);
                    }
                }
            };
            return cell;

        };

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory = (param) -> {

            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView infoIcon = new FontAwesomeIconView(FontAwesomeIcon.INFO_CIRCLE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:25px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:25px;"
                                + "-fx-fill:#00E676;"
                        );
                        infoIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:25px;"
                                + "-fx-fill:#3a70d6;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            Reclamation r = table_reclamation.getSelectionModel().getSelectedItem();

                            try {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette ligne ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    rs.supprimer(r.getId_reclamation());
                                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                                    info.setTitle("Information");
                                    info.setHeaderText(null);
                                    info.setContentText("La ligne a été supprimée avec succès.");
                                    Optional<ButtonType> result2 = info.showAndWait();
                                    if (result.get() == ButtonType.OK) {
                                        aficherreclamation();
                                    } else {
                                        //nt
                                    }
                                } else {
                                    System.out.println("suppression annuler");
                                }
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            // Récupérer l'élément sélectionné
                            Reclamation r = table_reclamation.getSelectionModel().getSelectedItem();

                            // Ouvrir une nouvelle fenêtre pour éditer les données de l'élément sélectionné
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/reclamation/FXMLUpdateReclamation.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);

                                // Passer les données de l'élément sélectionné à la nouvelle fenêtre
                                FXMLUpdateReclamationController controller = loader.getController();
                                controller.setMyData(r);

                                // Afficher la nouvelle fenêtre
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        infoIcon.setOnMouseClicked((MouseEvent event) -> {
                            // Récupérer l'élément sélectionné
                            Reclamation r = table_reclamation.getSelectionModel().getSelectedItem();

                            // Ouvrir une nouvelle fenêtre pour éditer les données de l'élément sélectionné
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/reclamation/FXMLReclamationDetails.fxml"));
                                Parent root = loader.load();
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);

                                // Passer les données de l'élément sélectionné à la nouvelle fenêtre
                                FXMLReclamationDetailsController controller = loader.getController();
                                controller.setMyData(r);

                                // Afficher la nouvelle fenêtre
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon, infoIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(infoIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
        action_col.setCellFactory(cellFactory);
        etat_reclamation_col.setCellFactory(cellFactory1);
        titre_reclamatio_col.setCellFactory(cellFactory2);

        try {
            reclamations = rs.afficherAcevJointure();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        list_rec.addAll(reclamations);

        table_reclamation.setItems(list_rec);
        table_reclamation.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (TableColumn col : table_reclamation.getColumns()) {
            col.setStyle("-fx-alignment: CENTER;");
        }

        return list_rec;

    }


    @FXML
    private void users_clicked(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Admin/TableView.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    
    }

 

 @FXML
    private void feedback(ActionEvent event) throws IOException {
                Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/feedback/FXMLFeedbackAdmin.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();
    }
    

     @FXML
    private void EventPage(ActionEvent event) throws IOException {
         root =FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


     @FXML
    private void Reclamation_view(ActionEvent event) throws IOException {
           
        root = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamationAdmin.fxml"));          
       
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.getIcons().add(new Image("/GUI/images/logo.png"));
        stage.setTitle("Reclamation Admin Page");   
        stage.setScene(scene);
        stage.show();
    }

  @FXML
    private void signOut_clicked(ActionEvent event) throws IOException, SQLException {
        root =FXMLLoader.load(getClass().getResource("/GUI/User/Login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
