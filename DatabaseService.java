package com.example.garage.service;

import java.sql.Connection;

public interface DatabaseService {
    Connection getConnection();
    void closeConnection();
}
