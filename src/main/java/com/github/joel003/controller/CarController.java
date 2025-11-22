package com.github.joel003.controller;

import com.github.joel003.entity.CarDetail;
import com.github.joel003.service.CarService;
import com.github.joel003.util.ConsoleHelper;
import com.github.joel003.util.InputValidation;

import java.util.List;
import java.util.Scanner;

public class CarController {
    private final CarService carService;
    public CarController() {
        this.carService = new CarService();
    }

    public void userPage(String res, Scanner sc) {

        boolean flag ;
        do{
            ConsoleHelper.clearScreen(5);
            System.out.printf("-------------> WELCOME TO CARS %s <-------------\n",res);
            System.out.println("1.View Cars\n2.Insert Car\n3.Update Car\n4.Delete Car\n5.Search Car \n6.LogOut");

            int choice = InputValidation.choiceValidate(1,6,sc);
            flag = true;

            switch(choice){
                case 1 -> viewCars();
                case 2 -> insertCars(sc);
                case 3 -> updateCars(sc);
                case 4 -> deleteCars(sc);
                case 5 -> searchCars(sc);
                case 6 -> {flag = false;System.out.println("********** LOGGED OUT **********");}
            }

        }while(flag);
    }

    private void viewCars() {
        ConsoleHelper.clearScreen(5);

        List<CarDetail> carDetails = carService.viewCars();

        if (carDetails.isEmpty()) {
            System.out.println(" No Cars found.");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s %-15s %-15s \n","CarID","Brand","Model","Color","Type","Price");
        System.out.println("---------------------------------------------------------------------------------------");
        for (CarDetail carDetail : carDetails) {
            System.out.printf("%-8s %-15s %-15s %-15s %-15s %-15s \n",carDetail.getC_id(),carDetail.getC_brand(),carDetail.getC_model(),carDetail.getC_color(),carDetail.getC_type(),carDetail.getC_price());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }

    private void insertCars(Scanner sc) {

        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> INSERT CARS <-------------");
        System.out.print("Enter Car ID :");
        int id = sc.nextInt();sc.nextLine();
        System.out.print("Enter the Car Brand : ");
        String brand = sc.nextLine();
        System.out.print("Enter the Car model : ");
        String model = sc.nextLine();
        System.out.print("Enter the Car Color : ");
        String color = sc.nextLine();
        System.out.print("Enter the Car Type : ");
        String type = sc.nextLine();
        System.out.print("Enter the Car price : ");
        int price = sc.nextInt();sc.nextLine();

        CarDetail carDetail = new CarDetail();
        carDetail.setC_id(id);
        carDetail.setC_brand(brand);
        carDetail.setC_model(model);
        carDetail.setC_color(color);
        carDetail.setC_type(type);
        carDetail.setC_price(price);

        boolean success = carService.insertCars(carDetail);
        System.out.println("-------------------------------------------------");
        System.out.println(success ? "Car Inserted successfully" : "Failed to Insert Car.");
        System.out.println("-------------------------------------------------");
    }

    private void updateCars(Scanner sc) {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> UPDATE CARS <------------");
        System.out.println("1.Update Brand\n2.Update Model\n3.Update Color\n4.Update Type\n5.Update Price");
        int choice = InputValidation.choiceValidate(1,5,sc);

        System.out.print("Enter the Car ID :");
        int id =Integer.parseInt(sc.nextLine());
        CarDetail carDetail = new CarDetail();

        switch(choice) {
            case 1 -> {
                System.out.print("Enter Car Brand :");
                String brand = sc.nextLine();
                carDetail.setC_brand(brand);
            }
            case 2 -> {
                System.out.print("Enter Car Model :");
                String model = sc.nextLine();
                carDetail.setC_model(model);
            }
            case 3 -> {
                System.out.print("Enter Car Color :");
                String color = sc.nextLine();
                carDetail.setC_color(color);
            }
            case 4 -> {
                System.out.print("Enter Car Type :");
                String type = sc.nextLine();
                carDetail.setC_type(type);
            }
            case 5 -> {
                System.out.print("Enter Car Price :");
                int price = Integer.parseInt(sc.nextLine());
                carDetail.setC_price(price);
            }
        }

        boolean success = carService.updateCars(carDetail,choice,id);
        System.out.println("-------------------------------------------------");
        System.out.println(success ? "Car Updated successfully" : "Failed to Update Car.");
        System.out.println("-------------------------------------------------");


    }

    private void deleteCars(Scanner sc) {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> DELETE CARS <------------");
        System.out.print("Enter the Car id to delete Record :");
        int id = sc.nextInt();sc.nextLine();
        boolean success = carService.deleteCars(id);
        System.out.println("-------------------------------------------------");
        System.out.println(success ? "Car Deleted successfully" : "Failed to Delete Car.");
        System.out.println("-------------------------------------------------");
    }

    private void searchCars(Scanner sc) {
        ConsoleHelper.clearScreen(5);
        System.out.println("-------------> SEARCH CARS <------------");
        System.out.print("1.Search by id \n2.Search by Brand \n3.Search by Model \n4.Search by Color \n5.Search by Type \n6.Search by Price \n");
        int choice = InputValidation.choiceValidate(1,6,sc);

        CarDetail carDetail = new CarDetail();

        switch(choice) {
            case 1 -> {
                System.out.print("Enter the Car ID :");
                int id =sc.nextInt();sc.nextLine();
                carDetail.setC_id(id);
            }
            case 2 -> {
                System.out.print("Enter Car Brand :");
                String brand = sc.nextLine();
                carDetail.setC_brand(brand);
            }
            case 3 -> {
                System.out.print("Enter Car Model :");
                String model = sc.nextLine();
                carDetail.setC_model(model);
            }
            case 4 -> {
                System.out.print("Enter Car Color :");
                String color = sc.nextLine();
                carDetail.setC_color(color);
            }
            case 5 -> {
                System.out.print("Enter Car Type :");
                String type = sc.nextLine();
                carDetail.setC_type(type);
            }
            case 6 -> {
                System.out.print("Enter Car Price :");
                int price = sc.nextInt();sc.nextLine();
                carDetail.setC_price(price);
            }
        }

        List<CarDetail> carDetails = carService.searchCars(carDetail,choice);

        if (carDetails.isEmpty()) {
            System.out.println(" No Cars found.");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s %-15s %-15s \n","CarID","Brand","Model","Color","Type","Price");
        System.out.println("---------------------------------------------------------------------------------------");
        for (CarDetail carDetail1 : carDetails) {
            System.out.printf("%-8s %-15s %-15s %-15s %-15s %-15s \n",carDetail1.getC_id(),carDetail1.getC_brand(),carDetail1.getC_model(),carDetail1.getC_color(),carDetail1.getC_type(),carDetail1.getC_price());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }


}
