package com.example.garage.service;

import com.example.garage.models.Client;
import com.example.garage.models.Mecano;

public interface AuthService {
    Client authenticateClient(String email, String password);
    Mecano authenticateMecano(String email, String password);
}
