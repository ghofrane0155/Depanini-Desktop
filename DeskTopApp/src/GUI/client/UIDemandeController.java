/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.client;

import Entity.Contrats;
import Entity.Recrutements;
import Entity.Pdf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import services.RecrutementService;
import Utils.MyDB;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.transform.Scale;
import services.ContratsService;

/**
 * FXML Controller class
 *
 * @author yasmine
 */
public class UIDemandeController implements Initializable {

  

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private BarChart<?, ?> barChart;
   
    @FXML
    private TableView<Contrats> tableC;
    private TableColumn<Contrats, Integer> cin;
    @FXML
    private TableColumn<Contrats, String> dateC;
    @FXML
    private TableColumn<Contrats, String> cible;
    @FXML
    private TableColumn<Contrats, String> descriptionC;
    @FXML
    private TextField rechercher;
    @FXML
    private ImageView trash1;
    @FXML
    private ImageView buttonmodifier1;
    @FXML
    private ImageView buttonsearch1;
    @FXML
    private ComboBox<String> ExporterListe;
    private TableColumn<Contrats, Integer> id_offre;
    @FXML
    private TextField id;
    @FXML
    private ImageView imageview9;

    public UIDemandeController() {
        connect = MyDB.getInstance().getConx();
    }

    @FXML
    private ImageView sound;

    /**
     * Initializes the controller class.
     */
    Image image = new Image(getClass().getResourceAsStream("/GUI/images/voice-note.PNG"));
    Image image1 = new Image(getClass().getResourceAsStream("/GUI/images/add.PNG"));
    Image image2 = new Image(getClass().getResourceAsStream("/GUI/images/search.PNG"));
    Image image5 = new Image(getClass().getResourceAsStream("/GUI/images/search.PNG"));

    Image image3 = new Image(getClass().getResourceAsStream("/GUI/images/trash.PNG"));
    Image image6 = new Image(getClass().getResourceAsStream("/GUI/images/trash.PNG"));

    Image image4 = new Image(getClass().getResourceAsStream("/GUI/images/modifie.PNG"));
    Image image7 = new Image(getClass().getResourceAsStream("/GUI/images/modifie.PNG"));
    Image image9 = new Image(getClass().getResourceAsStream("/GUI/images/client.PNG"));


    @FXML
    private TextField budget;
    @FXML
    private DatePicker date;
    @FXML
    private TextField descripton;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private ImageView add;
    @FXML
    private Label Controle;
    @FXML
    private TextField nom;
    @FXML
    private Label label5;
    @FXML
    private TextField filter;
    @FXML
    private TableView<Recrutements> Table;
    @FXML
    private TableColumn<Recrutements, String> nomcol;
    @FXML
    private TableColumn<Recrutements, Float> budgetcol;
    @FXML
    private TableColumn<Recrutements, String> datecol;
    @FXML
    private TableColumn<Recrutements, String> descriptioncol;

    public Recrutements d;
    public List<Recrutements> demande;
    private RecrutementService de = new RecrutementService();

    public Contrats c;
    public List<Contrats> contrat;
    private ContratsService Cs = new ContratsService();

    @FXML
    private ImageView buttonsearch;

    private Set<String> possibleWordSet = new HashSet<>();
    private AutoCompletionBinding<String> autocompletionBinding;
    private String filePath = "src/GUI/client/words.txt";
    @FXML
    private ImageView trash;
    @FXML
    private ImageView buttonmodifier;
    @FXML
    private Text text;
    @FXML
    private Label calcul;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sound.setImage(image);
        add.setImage(image1);
        buttonsearch.setImage(image2);
        buttonsearch1.setImage(image5);

        trash.setImage(image3);
        trash1.setImage(image6);

        buttonmodifier.setImage(image4);
        buttonmodifier1.setImage(image7);
         imageview9.setImage(image9);


        ObservableList<Recrutements> demandelist = FXCollections.observableArrayList();

        nomcol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("nom"));
        budgetcol.setCellValueFactory(new PropertyValueFactory<Recrutements, Float>("salaire"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("description"));
        datecol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("date"));

        try {
            demande = de.afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        demandelist.addAll(demande);
        Table.setItems(demandelist);

        readWordsFromFile();
        autocompletionBinding = TextFields.bindAutoCompletion(filter, possibleWordSet);
        filter.setOnKeyReleased(event -> {
            if (!event.getCode().isArrowKey()) {
                learnWord(filter.getText());
            }
        });

    

        ObservableList<Contrats> ContratsList = FXCollections.observableArrayList();

        //id_offre.setCellValueFactory(new PropertyValueFactory<Contrats, Integer>("id_contart"));
        dateC.setCellValueFactory(new PropertyValueFactory<Contrats, String>("date"));
        cible.setCellValueFactory(new PropertyValueFactory<Contrats, String>("Etat"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Contrats, String>("conditions"));
        try {
            contrat = Cs.afficher();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ContratsList.addAll(contrat);
        tableC.setItems(ContratsList);

        calcul();
        chart();

        ObservableList<String> list1 = FXCollections.observableArrayList("PDF", "Imprimer");
        ExporterListe.setItems(list1);

    }

    @FXML
    private void soundon(MouseEvent event) {
        String soundFilePath = "/GUI/images/Bienvenue.wav";
        AudioClip audioClip = new AudioClip(getClass().getResource(soundFilePath).toString());
        audioClip.play();
    }

    @FXML
    private void ControleNumbers(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) { //numbers regular expression
            event.consume();
            id.setStyle("-fx-border-color: red");
            Controle.setText("Veuillez entrer que des nombres");
        } else {
            id.setStyle("-fx-border-color: green");
            Controle.setText("");
        }
    }

    @FXML
    private void ControleBudget(KeyEvent event) {
        if (event.getCharacter().matches("[a-zA-Z]+")) { //numbers regular expression
            event.consume();
            budget.setStyle("-fx-border-color: red");
            Controle.setText("Veuillez entrer que des nombres");
        } else {
            budget.setStyle("-fx-border-color: green");
            Controle.setText("");
        }
    }

    @FXML
    private void ControleString(KeyEvent event) {

    }

    @FXML
    private void ajouterD(MouseEvent event) {
        //controle de saisie
        if (nom.getText().length() == 0) {
            nom.setStyle("-fx-border-color: red");
            label5.setText("Ce champ est requis");
            event.consume();
        } else {
            nom.setStyle(null);
            label5.setText("");
        }

        if (id.getText().length() == 0) {
            id.setStyle("-fx-border-color: red");
            label1.setText("Ce champ est requis");
            event.consume();
        } else {
            id.setStyle(null);
            label1.setText("");
        }
        if (budget.getText().length() == 0) {
            budget.setStyle("-fx-border-color: red");
            label2.setText("Ce champ est requis");
            event.consume();
        } else {
            budget.setStyle(null);
            label2.setText("");
        }
        if (descripton.getText().length() == 0) {
            descripton.setStyle("-fx-border-color: red");
            label4.setText("Ce champ est requis");
            event.consume();
        } else {
            descripton.setStyle(null);
            label4.setText("");
        }
        if (date.getValue() == null) {
            date.setStyle("-fx-border-color: red");
            label3.setText("Ce champ est requis");
            event.consume();
        } else {
            date.setStyle(null);
            label3.setText("");
        }
        try {
            RecrutementService sp = new RecrutementService();
            Recrutements p = new Recrutements(Integer.parseInt(id.getText()), Float.parseFloat(budget.getText()), descripton.getText(), date.getValue().toString(), nom.getText());

            sp.ajouter(p);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("recrutement ajoutee avec succes");
            alert.show();
            System.out.println("recrutement ajoutee");
        } catch (SQLException ex) {
            ex.getMessage();
        }
        calcul();
        Updatechart();
    }

    @FXML
    private void recherche(MouseEvent event) {
        String searchString = filter.getText();

        ObservableList<Recrutements> demandelist = FXCollections.observableArrayList();

        id_offre.setCellValueFactory(new PropertyValueFactory<Contrats, Integer>("IDContrat"));
        nomcol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("nom"));
        budgetcol.setCellValueFactory(new PropertyValueFactory<Recrutements, Float>("salaire"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("description"));
        datecol.setCellValueFactory(new PropertyValueFactory<Recrutements, String>("date"));

        try {
            demande = de.Recherche(searchString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        demandelist.addAll(demande);
        Table.setItems(demandelist);
    }

    private void learnWord(String text) {
        possibleWordSet.add(text);
        autocompletionBinding.dispose();
        autocompletionBinding = TextFields.bindAutoCompletion(filter, possibleWordSet);
        writeWordsToFile();

    }

    private void readWordsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                possibleWordSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeWordsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String word : possibleWordSet) {
                bw.write(word);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void supprimer(MouseEvent event) throws Exception {
        RecrutementService Sf = new RecrutementService();
        List<Recrutements> listeFr;
        if (Table.getSelectionModel().getSelectedItem() != null) {
            int Matricule = Table.getSelectionModel().getSelectedItem().getRef();
            Sf.supprimer(Matricule);
            Table.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Demande supprimee avec succes");
            alert.show();
        }
        calcul();
        Updatechart();
    }

    @FXML
    private void modifier(MouseEvent event) throws SQLException {
        Recrutements New = Table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/client/UIModifier.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        UIModifierController fnc = loader.getController();
        fnc.setTextFields(New);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }

    private void calcul() {
        String query = "SELECT COUNT(DISTINCT idClient) AS totalClients FROM recrutements";
        try {
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                int totalClients = rs.getInt("totalClients");
                calcul.setText(String.valueOf(totalClients));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chart() {
        String chartSql = "SELECT date, COUNT(idClient) FROM recrutements GROUP BY date ";
        connect = MyDB.getInstance().getConx();
        try {
            XYChart.Series chartData = new XYChart.Series();
            prepare = connect.prepareStatement(chartSql);
            result = prepare.executeQuery();

            while (result.next()) {
                chartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            barChart.getData().add(chartData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Updatechart() {
        String chartSql = "SELECT date, COUNT(idClient) FROM recrutements GROUP BY date ";
        connect = MyDB.getInstance().getConx();
        try {
            barChart.getData().clear(); // clear existing data
            prepare = connect.prepareStatement(chartSql);
            result = prepare.executeQuery();
            XYChart.Series chartData = new XYChart.Series();

            while (result.next()) {
                chartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));

            }
            barChart.getData().add(chartData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @FXML
    private void rechercherC(MouseEvent event) {
        String searchString = rechercher.getText();
        ObservableList<Contrats> ContratsList = FXCollections.observableArrayList();

        //id_offre.setCellValueFactory(new PropertyValueFactory<Contrats, Integer>("id_contart"));
        dateC.setCellValueFactory(new PropertyValueFactory<Contrats, String>("date"));
        cible.setCellValueFactory(new PropertyValueFactory<Contrats, String>("Etat"));
        descriptionC.setCellValueFactory(new PropertyValueFactory<Contrats, String>("conditions"));

        try {
            contrat = Cs.Recherche(searchString);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ContratsList.addAll(contrat);
        tableC.setItems(ContratsList);

    }

    @FXML
    private void supprimerC(MouseEvent event) throws SQLException {
        ContratsService Sf = new ContratsService();
        List<Contrats> listeC;
        if (tableC.getSelectionModel().getSelectedItem() != null) {
            int Matricule = tableC.getSelectionModel().getSelectedItem().getIDContrat();
            Sf.supprimer(Matricule);
            tableC.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("contrat supprimee avec succes");
            alert.show();
        }
    }

    @FXML
    private void modifierC(MouseEvent event) {
        Contrats New = tableC.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/client/UIModifierContrat.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        UIModifierContratController fnc = loader.getController();
        fnc.setTextFields(New);
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void ExporterListe(ActionEvent event) throws IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, SQLException {
        String N = (String) ExporterListe.getValue();

        if (N == "PDF") {

            ExporterListe.setValue("Exporter");
            Contrats f = tableC.getSelectionModel().getSelectedItem();
            Pdf pd = new Pdf();
            try {
                pd.GeneratePdf("" + f.getConditions() + "", f, f.getIDContrat());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PDF");
                alert.setHeaderText(null);
                alert.setContentText("!!!PDF exported!!!");
                alert.showAndWait();
                System.out.println("impression done");
            } catch (Exception ex) {
                Logger.getLogger(ContratsService.class.getName()).log(Level.SEVERE, null, ex);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("!!!Selectioner une formation!!!");
                alert.showAndWait();
            }
        }
        if (N == "Imprimer") {
            ExporterListe.setValue("Exporter");
            printNode(tableC);
        }
    }

    private void PDF(MouseEvent event) {
        Contrats f = tableC.getSelectionModel().getSelectedItem();

        Pdf pd = new Pdf();
        try {
            pd.GeneratePdf("MesInformations", f, f.getIDContrat());
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ContratsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        javafx.print.PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);

    }

    private void ImprimerAction(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(tableC);
    }

}
