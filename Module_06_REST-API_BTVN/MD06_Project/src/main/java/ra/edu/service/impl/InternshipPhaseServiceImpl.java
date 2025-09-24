package ra.edu.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.edu.controller_advice.custom_exception.ResourceConflictException;
import ra.edu.model.entity.InternshipPhase;
import ra.edu.model.request.InternshipPhaseRequest;
import ra.edu.repository.InternshipPhaseRepository;
import ra.edu.service.InternshipPhaseService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InternshipPhaseServiceImpl implements InternshipPhaseService {
    private final InternshipPhaseRepository internshipPhaseRepository;

    @Override
    public Page<InternshipPhase> getAllInternshipPhase(int page, int size) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, size);
        return internshipPhaseRepository.findAll(pageable);
    }

    @Override
    public InternshipPhase findById(Long id) {
        return internshipPhaseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giai đoạn thực tập có id = " + id));
    }

    @Override
    public InternshipPhase createInternshipPhase(InternshipPhaseRequest internshipPhaseRequest) {
        if(internshipPhaseRepository.existsByPhaseName(internshipPhaseRequest.getPhaseName())) {
            throw new ResourceConflictException("Tên giai đoạn thực tập đã tồn tại!");
        }

        InternshipPhase internshipPhase = InternshipPhase.builder()
                .phaseName(internshipPhaseRequest.getPhaseName())
                .startDate(internshipPhaseRequest.getStartDate())
                .endDate(internshipPhaseRequest.getEndDate())
                .description(internshipPhaseRequest.getDescription())
                .createdAt(LocalDateTime.now().withNano(0))
                .updatedAt(LocalDateTime.now().withNano(0))
                .build();
        return internshipPhaseRepository.save(internshipPhase);
    }

    @Override
    public InternshipPhase updateInternshipPhase(Long id, InternshipPhaseRequest internshipPhaseRequest) {
        InternshipPhase existingInternshipPhase = findById(id);
        if (!existingInternshipPhase.getPhaseName().equals(internshipPhaseRequest.getPhaseName())
        && internshipPhaseRepository.existsByPhaseName(internshipPhaseRequest.getPhaseName())) {
            throw new ResourceConflictException("Tên giai đoạn thực tập đã tồn tại!");
        }
        existingInternshipPhase.setPhaseName(internshipPhaseRequest.getPhaseName());
        existingInternshipPhase.setStartDate(internshipPhaseRequest.getStartDate());
        existingInternshipPhase.setEndDate(internshipPhaseRequest.getEndDate());
        existingInternshipPhase.setDescription(internshipPhaseRequest.getDescription());
        existingInternshipPhase.setUpdatedAt(LocalDateTime.now().withNano(0));
        return internshipPhaseRepository.save(existingInternshipPhase);
    }

    @Override
    public void deleteInternshipPhase(Long id) {
        InternshipPhase internshipPhase = findById(id);
        internshipPhaseRepository.delete(internshipPhase);
    }
}
