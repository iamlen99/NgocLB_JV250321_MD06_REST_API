package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ra.edu.model.entity.Service;
import ra.edu.repository.ServiceRepository;
import ra.edu.service.ServiceFunction;

@org.springframework.stereotype.Service
public class ServiceFunctionImpl implements ServiceFunction {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Page<Service> getAllServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return serviceRepository.findAll(pageable);
    }

    @Override
    public Page<Service> getAllServices(int page, int size, String search, Long animalId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return serviceRepository.findAllByAnimal_IdAndNameContaining(animalId, search, pageable);
    }

    @Override
    public Page<Service> getAllServices(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return serviceRepository.findAllByNameContaining( search, pageable);
    }

    @Override
    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public boolean deleteServiceById(Long id) {
        if (serviceRepository.existsById(id)) {
            serviceRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
