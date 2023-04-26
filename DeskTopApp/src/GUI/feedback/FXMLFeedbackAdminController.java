/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.feedback;

import Entity.user;
import java.net.URL;
import java.util.Date;
import Entity.Enum.*;
import Entity.admin;
import GUI.User.UserProfileController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.AdminService;
import services.FeedbackService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Mega-pc
 */
public class FXMLFeedbackAdminController implements Initializable {

    @FXML
    private GridPane container;

    private ListView<String> listView;
    @FXML
    private JFXButton show_user;

    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private ImageView tfimg1;
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
        tfimg1.setImage(img);
        tfnom1.setText(a.getNom_admin() + " " + a.getPrenom_admin());
        FeedbackService fs = new FeedbackService();

        ObservableList<String> userList = null;
        try {
            userList = fs.afficherNameAndStars();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

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
        show_user.setOnAction(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String subStr = "   Total :";
                int index = selectedItem.indexOf(subStr);
                if (index != -1) {
                    String email = selectedItem.substring(0, index).trim();

                    // user utilisateur = new user(index, subStr, result, result, result, date_nais_user, result, result, result, Sexe.femme, TypeR.Client, subStr)
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/User/userProfile.fxml"));
                    try {
                        root = loader.load();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    UserProfileController userprofile = loader.getController();
                    user utilisateur;
                    try {
                        utilisateur = fs.selectUser(email);

                        userprofile.displayInfo(utilisateur);
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();// Affiche "Hello world"
                }
                // listView.fireEvent(new ListView.EditEvent<String>(listView, ListView.editCommitEvent(), selectedItem));
            }
        });

    }

    @FXML
    private void Show_details_User(ActionEvent event) {

    }

    @FXML
    private void users_clicked(ActionEvent event) throws IOException {
        Parent afficherParent = FXMLLoader.load(getClass().getResource("/GUI/Admin/TableView.fxml"));
        Scene afficherScene = new Scene(afficherParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(afficherScene);
        window.show();

    }

    private void back(ActionEvent event) throws IOException {
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
        root = FXMLLoader.load(getClass().getResource("/GUI/event/welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Reclamation_view(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/GUI/reclamation/FXMLReclamationAdmin.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/GUI/images/logo.png"));
        stage.setTitle("Reclamation Admin Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void signOut_clicked(ActionEvent event) throws IOException, SQLException {
        root = FXMLLoader.load(getClass().getResource("/GUI/User/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
