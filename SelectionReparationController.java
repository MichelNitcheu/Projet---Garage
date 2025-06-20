//package com.example.garage.utils;
//
//import com.example.garage.models.Mecano;
//import com.example.garage.models.RendezVous;
//import com.example.garage.service.RendezVousService;
//import javafx.collections.FXCollections;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//
//import java.util.List;
//
//public class SelectionReparationController {
//    @FXML private TableView<RendezVous> reparationTable;
//    @FXML private TableColumn<RendezVous, String> vehiculeColumn;
//    @FXML private TableColumn<RendezVous, String> panneColumn;
//    @FXML private TableColumn<RendezVous, String> dateColumn;
//    @FXML private Button btnSelect;
//
//    private Mecano mecano;
//    private RendezVousService rendezVousService;
//
//    public void setMecano(Mecano mecano) {
//        this.mecano = mecano;
//        loadReparations();
//    }
//
//    public void setRendezVousService(RendezVousService service) {
//        this.rendezVousService = service;
//    }
//
//    @FXML
//    public void initialize() {
//        // Configuration des colonnes
//        vehiculeColumn.setCellValueFactory(cellData ->
//                cellData.getValue().getVehicule().marqueProperty().concat(" ")
//                        .concat(cellData.getValue().getVehicule().modeleProperty()));
//
//        panneColumn.setCellValueFactory(cellData ->
//                cellData.getValue().getPanne().nomProperty());
//
//        dateColumn.setCellValueFactory(cellData ->
//                cellData.getValue().dateHeureProperty().asString("dd/MM/yyyy HH:mm"));
//    }
//
//    private void loadReparations() {
//        List<RendezVous> reparations = rendezVousService.getRendezVousByStatut("planifie");
//        reparationTable.setItems(FXCollections.observableArrayList(reparations));
//    }
//
//    @FXML
//    private void handleSelect() {
//        RendezVous selected = reparationTable.getSelectionModel().getSelectedItem();
//        if (selected != null) {
//            selected.setStatut("en_cours");
//            selected.setMecano(mecano);
//            rendezVousService.updateRendezVous(selected);
//            showSuccess("Réparation sélectionnée", "Vous avez pris en charge cette réparation");
//        } else {
//            showError("Erreur", "Veuillez sélectionner une réparation");
//        }
//    }
//}