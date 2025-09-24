package ra.edu.service;

import org.springframework.data.domain.Page;
import ra.edu.model.entity.Mentor;
import ra.edu.model.request.MentorRequest;
import ra.edu.model.response.MentorDetailsResponse;
import ra.edu.model.response.MentorResponse;

public interface MentorService {
    Page<MentorResponse> getAllMentors(int page, int size);

    Page<MentorDetailsResponse> getAllMentorsDetails(int page, int size);

    MentorDetailsResponse findDetailsById(Long id);

    Mentor findById(Long id);

    Mentor createMentor(MentorRequest mentorRequest);

    Mentor updateMentor(Long mentorId, MentorRequest mentorRequest);
}
