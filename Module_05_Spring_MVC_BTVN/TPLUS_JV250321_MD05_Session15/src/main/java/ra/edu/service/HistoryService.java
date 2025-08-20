package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.History;

import java.util.List;

public interface HistoryService {
    Page<History> findAll(int page, int size);

    History save(History history);
    History findById(Long id);
    boolean deleteById(Long id);
}
