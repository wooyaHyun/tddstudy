package com.example.tddstudy.service.Membership;

import com.example.tddstudy.domain.Membership.MembershipRepository;
import com.example.tddstudy.domain.Membership.MembershipType;
import com.example.tddstudy.exception.MembershipErrorResult;
import com.example.tddstudy.exception.MembershipException;
import com.example.tddstudy.web.dto.MembershipDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public List<MembershipDetailResponseDto> getMembershipList (final String userId){
        final List<Map<String, Object>> membershipList = membershipRepository.findAllByUserId(userId);
        return membershipList.stream()
                .map(v -> MembershipDetailResponseDto.builder()
                        .id((Long)v.get("ID"))
                        .userId((String)v.get("USER_ID"))
                        .membershipType((MembershipType) v.get("MEMBERSHIP_NAME"))
                        .point((int)v.getOrDefault("POINT", 0))
                        .build())
                .collect(Collectors.toList());
    }

    public MembershipDetailResponseDto getMembership(final Long membershipId, final String userId){
        final Optional<Map<String, Object>> optionalMembership = Optional.ofNullable(membershipRepository.findById(membershipId));
        final Map<String, Object> membership = optionalMembership.orElseThrow(() -> new MembershipException(MembershipErrorResult.MEMBERSHIP_NOT_FOUND));
        //final Map<String, Object> membership = membershipRepository.findById(membershipId);

        if(!membership.get("USER_ID").equals(userId)){
            throw new MembershipException(MembershipErrorResult.NOT_MEMBERSHIP_OWNER);
        }
        return MembershipDetailResponseDto.builder()
                .id((Long)membership.get("ID"))
                .membershipType((MembershipType) membership.get("MEMBERSHIP_NAME"))
                .point((int)membership.get("POINT"))
                .build();
    }
}
