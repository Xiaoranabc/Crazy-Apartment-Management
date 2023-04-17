package com.example.apartmentManagement.service;
import com.example.apartmentManagement.modal.Maintenance;
import com.example.apartmentManagement.modal.Room;
import com.example.apartmentManagement.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    public void saveMaintenance(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }
    public List<Maintenance> showAllMaintenance() {
        List<Maintenance> maintenances = new ArrayList<>();
        for(Maintenance maintenance :maintenanceRepository.findAll()) {
            if(maintenance!=null) {
                maintenances.add(maintenance);
            }
        }
        return maintenances;
    }

    public void deleteById(Integer id){
        Maintenance maintenance = maintenanceRepository.findById(id);
        maintenanceRepository.delete(maintenance);
    }
}
