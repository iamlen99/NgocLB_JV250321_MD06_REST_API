package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.History;
import ra.edu.repository.HistoryRepository;
import ra.edu.service.HistoryService;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public Page<History> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return historyRepository.findAll(pageable);
    }

    @Override
    public History save(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History findById(Long id) {
        return historyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        if (historyRepository.existsById(id)) {
            historyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
