package ra.edu.service;

import ra.edu.model.entity.Bus;

import java.util.List;
import java.util.Optional;

public interface BusService {
    List<Bus> findAll();
    Optional<Bus> findById(Long id);
    Optional<Bus> findByLicensePlate(String licensePlate);
    Bus save(Bus bus);
    boolean delete(Long id);
}
