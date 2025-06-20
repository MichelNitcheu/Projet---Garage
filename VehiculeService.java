package com.example.garage.service;

import com.example.garage.models.Vehicule;
import com.example.garage.models.Client;
import java.util.List;

public interface VehiculeService {
    void ajouterVehicule(Vehicule vehicule);
    List<Vehicule> getVehiculesByClient(Client client);
    Vehicule getVehiculeById(int id);
    void supprimerVehicule(int id);
}