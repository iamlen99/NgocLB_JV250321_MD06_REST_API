package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Mentor;
import ra.edu.model.request.MentorRequest;

public interface MentorService {
    Page<Mentor> getAllMentor(int page, int size);

    Mentor findById(Long id);

    Mentor createMentor(MentorRequest mentorRequest);

    Mentor updateMentor(MentorRequest mentorRequest);
}
