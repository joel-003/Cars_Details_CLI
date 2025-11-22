package com.github.joel003.service;

import com.github.joel003.dao.CarDAO;
import com.github.joel003.entity.CarDetail;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CarService {
    private final CarDAO carDAO;
    public CarService() {
        this.carDAO = new CarDAO();
    }

    public List<CarDetail> viewCars() {
        try {
            return carDAO.viewCars();
        }catch (SQLException e){
            System.out.println("SQL Exception (View Cars) : "+e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean insertCars(CarDetail carDetail) {
        try {
            return carDAO.insertCars(carDetail);
        }catch (SQLException e){
            System.out.println("SQL Exception (Insert Cars) : "+e.getMessage());
            return false;
        }
    }

    public boolean updateCars(CarDetail carDetail, int choice, int id) {
        try {
            return carDAO.updateCars(carDetail,choice,id);
        }catch (SQLException e){
            System.out.println("SQL Exception (Update Cars) : "+e.getMessage());
            return false;
        }
    }

    public boolean deleteCars(int id) {
        try{
            return carDAO.deleteCars(id);
        }catch (SQLException e){
            System.out.println("SQL Exception (Delete Cars) : "+e.getMessage());
            return false;
        }
    }

    public List<CarDetail> searchCars(CarDetail carDetail, int choice) {
        try{
            return carDAO.searchCars(carDetail,choice);
        }catch (SQLException e){
            System.out.println("SQL Exception (Search Cars) : "+e.getMessage());
            return Collections.emptyList();
        }

    }
}
