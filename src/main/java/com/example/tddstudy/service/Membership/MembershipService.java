package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import com.example.tddstudy.web.dto.MemebershipDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<MemebershipDetailResponseDto> getMembershipList (final String userId){
        final List<Map<String, Object>> membershipList = membershipRepository.findAllByUserId(userId);
        return membershipList.stream()
                .map(v -> MemebershipDetailResponseDto.builder()
                        .id((Long)v.get("ID"))
                        .userId((String)v.get("USER_ID"))
                        .membershipType((MembershipType) v.get("MEMBERSHIP_NAME"))
                        .build())
                .collect(Collectors.toList());
    }
}
