package com.example.garage.service;

import com.example.garage.models.RendezVous;
import com.example.garage.models.Client;
import com.example.garage.models.Mecano;
import java.util.List;

public interface RendezVousService {
    void creerRendezVous(RendezVous rendezVous);
    void annulerRendezVous(int id);
    List<RendezVous> getRendezVousByClient(Client client);
    List<RendezVous> getRendezVousByMecano(Mecano mecano);
}