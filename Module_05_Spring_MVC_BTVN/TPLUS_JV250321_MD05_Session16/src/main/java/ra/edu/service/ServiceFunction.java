package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Animal;
import ra.edu.model.entity.Service;

public interface ServiceFunction {
    Page<Service> getAllServices(int page, int size);
    Page<Service> getAllServices(int page, int size, String search, Long animalId);
    Page<Service> getAllServices(int page, int size, String search);
    Service getServiceById(Long id);
    Service saveService(Service service);
    boolean deleteServiceById(Long id);
}
