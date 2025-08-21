package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Animal;
import ra.edu.repository.AnimalRepository;
import ra.edu.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public boolean deleteById(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
