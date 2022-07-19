package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public int addMembership(final String userId, final MembershipType membershipType, final int point){
        final Map<String, Object> result= membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if(result != null){
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        param.put("membershipName", membershipType);
        param.put("point", point);

        return membershipRepository.save(param);
    }
}
