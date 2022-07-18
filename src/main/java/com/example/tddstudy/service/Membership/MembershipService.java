package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.Membership;
import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Membership addMembership(final String userId, final MembershipType membershipType, final int point){
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);
        if(result != null){
            throw new MembershipException(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        return null;
    }
}
