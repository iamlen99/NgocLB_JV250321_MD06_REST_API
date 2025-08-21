package ra.edu.service;

import ra.edu.model.entity.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> findAll();
    Animal findById(Long id);
    Animal save(Animal animal);
    boolean deleteById(Long id);
}
