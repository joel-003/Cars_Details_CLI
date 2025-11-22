package com.github.joel003.dao;

import com.github.joel003.entity.CarDetail;
import com.github.joel003.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {


    private final Connection con = DBConnection.getConnection();


    public List<CarDetail> viewCars()throws SQLException {
        List<CarDetail> carDetails = new ArrayList<>();

        String query ="select * from carslist ORDER BY c_id ASC";
        try(PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CarDetail carDetail = new CarDetail();

                carDetail.setC_id(rs.getInt(1));
                carDetail.setC_brand(rs.getString(2));
                carDetail.setC_model(rs.getString(3));
                carDetail.setC_color(rs.getString(4));
                carDetail.setC_type(rs.getString(5));
                carDetail.setC_price(rs.getInt(6));

                carDetails.add(carDetail);
            }
        }
        return carDetails;
    }

    public boolean insertCars(CarDetail carDetail) throws SQLException {

        String query =" INSERT into carslist values(?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, carDetail.getC_id());
            ps.setString(2, carDetail.getC_brand());
            ps.setString(3, carDetail.getC_model());
            ps.setString(4, carDetail.getC_color());
            ps.setString(5, carDetail.getC_type());
            ps.setInt(6, carDetail.getC_price());

            int res = ps.executeUpdate();

            return (res > 0);
        }
    }

    public boolean updateCars(CarDetail carDetail, int choice, int id) throws SQLException {

        String query = switch(choice) {
            case 1 -> "UPDATE carslist SET c_brand = ? WHERE c_id = ?";
            case 2 -> "UPDATE carslist SET c_model = ? WHERE c_id = ?";
            case 3 -> "UPDATE carslist SET c_color = ? WHERE c_id = ?";
            case 4 -> "UPDATE carslist SET c_type = ? WHERE c_id = ?";
            case 5 -> "UPDATE carslist SET c_price = ? WHERE c_id = ?";
            default ->throw new IllegalStateException("Invalid update choice");
        };

        try (PreparedStatement ps = con.prepareStatement(query)){

            switch (choice) {
                case 1 -> ps.setString(1, carDetail.getC_brand());
                case 2 -> ps.setString(1, carDetail.getC_model());
                case 3 -> ps.setString(1, carDetail.getC_color());
                case 4 -> ps.setString(1, carDetail.getC_type());
                case 5 -> ps.setInt(1, carDetail.getC_price());
            }
            ps.setInt(2, id);

            int res = ps.executeUpdate();
            return (res > 0);
        }
    }

    public boolean deleteCars(int id) throws SQLException {

        String query ="DELETE from carslist WHERE c_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)){
            ps.setInt(1, id);

            int res = ps.executeUpdate();
            return (res > 0);
        }
    }

    public List<CarDetail> searchCars(CarDetail carDetail, int choice) throws SQLException {

        List<CarDetail> list = new ArrayList<>();

        String query = switch (choice) {
            case 1 -> "SELECT * FROM carslist WHERE c_id = ?";
            case 2 -> "SELECT * FROM carslist WHERE LOWER(c_brand) LIKE LOWER(?)";
            case 3 -> "SELECT * FROM carslist WHERE LOWER(c_model) LIKE LOWER(?) ";
            case 4 -> "SELECT * FROM carslist WHERE LOWER(c_color) LIKE LOWER(?) ";
            case 5 -> "SELECT * FROM carslist WHERE LOWER(c_type) LIKE LOWER(?) ";
            case 6 -> "SELECT * FROM carslist WHERE c_price = ?";
            default -> throw new IllegalStateException("Invalid search choice");
        };

        try (PreparedStatement ps = con.prepareStatement(query)) {

            switch (choice) {
                case 1 -> ps.setInt(1, carDetail.getC_id());
                case 2 -> ps.setString(1, "%" + carDetail.getC_brand() + "%");
                case 3 -> ps.setString(1, "%" + carDetail.getC_model() + "%");
                case 4 -> ps.setString(1, "%" + carDetail.getC_color() + "%");
                case 5 -> ps.setString(1, "%" + carDetail.getC_type() + "%");
                case 6 -> ps.setInt(1, carDetail.getC_price());
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                CarDetail carDetail1 = new CarDetail();

                carDetail1.setC_id(rs.getInt(1));
                carDetail1.setC_brand(rs.getString(2));
                carDetail1.setC_model(rs.getString(3));
                carDetail1.setC_color(rs.getString(4));
                carDetail1.setC_type(rs.getString(5));
                carDetail1.setC_price(rs.getInt(6));

                list.add(carDetail1);
            }
        }
        return list;
    }
}
