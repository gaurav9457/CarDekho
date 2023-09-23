package com.jspiders.car_dekho_hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.car_dekho_hibernate.dto.CarDTO;



public class CarDAO {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static EntityTransaction entityTransaction;

    private static void openConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("car");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    private static void closeConnection() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityTransaction != null) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
        }
    }

    public void saveCar(CarDTO car) {
        openConnection();
        entityTransaction.begin();
        
        entityManager.persist(car);
        
        entityTransaction.commit();
        closeConnection();
    }

    public CarDTO getCarById(int carId) {
        openConnection();
        CarDTO car = entityManager.find(CarDTO.class, carId);
        closeConnection();
        return car;
    }

    public void updateCar(CarDTO car) {
        openConnection();
        entityTransaction.begin();
        
        entityManager.merge(car);
        
        entityTransaction.commit();
        closeConnection();
    }

    public void deleteCar(int carId) {
        openConnection();
        entityTransaction.begin();
        
        CarDTO car = entityManager.find(CarDTO.class, carId);
        if (car != null) {
            entityManager.remove(car);
        }
        
        entityTransaction.commit();
        closeConnection();
    }
}
