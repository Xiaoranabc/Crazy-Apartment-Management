package com.example.apartmentManagement.repository;

import com.example.apartmentManagement.modal.Maintenance;
import com.example.apartmentManagement.service.MaintenanceService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends CrudRepository<Maintenance, Integer> {
    public Maintenance findById(Integer id);
}