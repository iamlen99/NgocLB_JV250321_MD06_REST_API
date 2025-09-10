package ra.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.model.entity.Member;
import ra.edu.repository.MemberRepository;
import ra.edu.service.MemberService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Member not found by id : " + id));
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        Member existingMember = getMemberById(id);
        existingMember.setFullName(member.getFullName());
        existingMember.setEmail(member.getEmail());
        existingMember.setPhone(member.getPhone());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new NoSuchElementException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
