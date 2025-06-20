package com.example.garage.service;

import com.example.garage.models.Panne;
import java.util.List;

public interface PanneService {
    List<Panne> getAllPannes();
    Panne getPanneById(int id);
    List<Panne> getPannesByNom(String nom);
}