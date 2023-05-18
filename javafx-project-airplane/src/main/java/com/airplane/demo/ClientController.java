package com.airplane.demo;

import com.airplane.demo.entities.Vol;
import com.airplane.demo.entities.escale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private TableColumn<Vol,String> ColAA;

    @FXML
    private TableColumn<Vol,String> ColAd;

    @FXML
    private TableColumn<Vol, String> ColDA;

    @FXML
    private TableColumn<Vol, String> ColDD;

    @FXML
    private TableColumn<Vol,String> ColHA;

    @FXML
    private TableColumn<Vol,String> ColHD;





    @FXML
    private TableView<Vol> TablVol;

    @FXML
    private TableColumn<Vol,Integer> colId;

    @FXML
    private TableColumn<Vol,String> colEtat;

    //escale table
    @FXML
    private TableColumn<escale,String> HAriiver;

    @FXML
    private TableColumn<escale,String> HEscale;

    @FXML
    private TableView<escale> tabEscale;

    @FXML
    private TableColumn<escale,String> AEscale;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(cellData -> cellData.getValue().idVolProperty().asObject());
        ColAd.setCellValueFactory(cellData -> cellData.getValue().aeroportArriveeProperty());
        ColAA.setCellValueFactory(cellData -> cellData.getValue().aerropDepartProperty());
        ColHA.setCellValueFactory(cellData -> cellData.getValue().dateArriveeProperty());
        ColHD.setCellValueFactory(cellData -> cellData.getValue().dateDepartProperty());
        ColDA.setCellValueFactory(cellData -> cellData.getValue().heureArriveeProperty());
        ColDD.setCellValueFactory(cellData -> cellData.getValue().heureDepartProperty());
        colEtat.setCellValueFactory(cellData -> cellData.getValue().etatProperty());
        TablVol.setItems(getVols());
    }
    private ObservableList<Vol> getVols() {
        ObservableList<Vol> vols = FXCollections.observableArrayList();

        Connection con = Connexion.getConnexionn();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `vol`");
            while (resultSet.next()) {
                int id = resultSet.getInt("idVol");
                String aeroportArriver = resultSet.getString("aeroportArrivee");
                String aeroportDepart = resultSet.getString("aerropDepart");
                String dateArriver = resultSet.getString("dateArrivee");
                String dateDepart = resultSet.getString("dateDepart");
                String heureArriver = resultSet.getString("heureArrivee");
                String heureDepart = resultSet.getString("heureDepart");
                String etat = resultSet.getString("etat");


                Vol vol = new Vol(id, aeroportArriver, aeroportDepart, dateArriver, dateDepart, heureArriver, heureDepart, etat);
                vols.add(vol);

            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return vols;
    }
    public void btnReserver(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reservation effectuée avec succès");
        alert.showAndWait();
    }

    public void ListerLesEscales(ActionEvent evnet)
    {
        HAriiver.setCellValueFactory(cellData -> cellData.getValue().heurArriverProperty());
        HEscale.setCellValueFactory(cellData -> cellData.getValue().heureDepartProperty());
        AEscale.setCellValueFactory(cellData -> cellData.getValue().nomAeroportProperty());
        tabEscale.setItems(getEscale());
    }
    private ObservableList<escale> getEscale() {
        ObservableList<escale> escales = FXCollections.observableArrayList();
        int idVol = TablVol.getSelectionModel().getSelectedItem().getIdVol();
        System.out.println(idVol);

        Connection con = Connexion.getConnexionn();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM escale where idVol = "+idVol+"");
            while (resultSet.next()) {
                int id = resultSet.getInt("idEscale");
                String nomAeroport = resultSet.getString("nomAeroport");
                String heureArriver = resultSet.getString("heureDepart");
                String heureDepart = resultSet.getString("heureArrivee");
                escale escale = new escale(nomAeroport, heureArriver, heureDepart, idVol);
                escales.add(escale);


            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return escales;
    }


}