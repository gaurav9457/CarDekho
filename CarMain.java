package com.jspiders.car_dekho_hibernate.main;

import java.util.Scanner;

import com.jspiders.car_dekho_hibernate.dao.CarDAO;
import com.jspiders.car_dekho_hibernate.dto.CarDTO;


public class CarMain {
    public static void main(String[] args) {
        CarDAO carDAO = new CarDAO();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("Choose a task:");
            System.out.println("1. Save Car");
            System.out.println("2. Get Car by ID");
            System.out.println("3. Update Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Exit");

            int task = scanner.nextInt();

            switch (task) {
                case 1:
                CarDTO newCar = new CarDTO();
                System.out.print("Enter Car ID: ");
                newCar.setCarId(scanner.nextInt());
                System.out.print("Enter Car Name: ");
                newCar.setCarName(scanner.next());
                System.out.print("Enter Car Brand: ");
                newCar.setCarBrand(scanner.next());
                System.out.print("Enter Fuel Type: ");
                newCar.setFuelType(scanner.next());
                System.out.print("Enter Price: ");
                newCar.setPrice(scanner.nextInt());

                carDAO.saveCar(newCar);
                System.out.println("Car saved successfully.");
                break;

            case 2:
                System.out.print("Enter Car ID to retrieve: ");
                int carIdToGet = scanner.nextInt();
                CarDTO retrievedCar = carDAO.getCarById(carIdToGet);
                if (retrievedCar != null) {
                    System.out.println("Retrieved Car: " + retrievedCar);
                } else {
                    System.out.println("Car not found.");
                }
                break;

            case 3:
                System.out.print("Enter Car ID to update: ");
                int carIdToUpdate = scanner.nextInt();
                CarDTO carToUpdate = carDAO.getCarById(carIdToUpdate);
                if (carToUpdate != null) {
                    System.out.print("Enter Car Name: ");
                    carToUpdate.setCarName(scanner.next());
                    System.out.print("Enter Car Brand: ");
                    carToUpdate.setCarBrand(scanner.next());
                    System.out.print("Enter Fuel Type: ");
                    carToUpdate.setFuelType(scanner.next());
                    System.out.print("Enter Price: ");
                    carToUpdate.setPrice(scanner.nextInt());
                    
                    carDAO.updateCar(carToUpdate);
                    System.out.println("Car updated successfully.");
                } else {
                    System.out.println("Car not found.");
                }
                break;

            case 4:
                System.out.print("Enter Car ID to delete: ");
                int carIdToDelete = scanner.nextInt();
                carDAO.deleteCar(carIdToDelete);
                System.out.println("Car deleted successfully.");
                break;

                case 5:
                    System.out.println("Exiting...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid task number.");
                    break;
            }
        }

        scanner.close();
    }
}
