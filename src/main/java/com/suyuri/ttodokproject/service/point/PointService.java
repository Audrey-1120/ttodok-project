package com.suyuri.ttodokproject.service.point;

import com.suyuri.ttodokproject.entity.MemberEntity;
import com.suyuri.ttodokproject.entity.PointEntity;
import com.suyuri.ttodokproject.entity.WordEntity;
import com.suyuri.ttodokproject.repository.MemberRepository;
import com.suyuri.ttodokproject.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PointService {

    private final PointRepository pointRepository;

    private final MemberRepository memberRepository;

    public List<String> getAllProductNames() {
        List<PointEntity> pointEntityList = pointRepository.findAll();
        return pointEntityList.stream()
                .map(PointEntity::getProductName)
                .collect(Collectors.toList());
    }

    public List<Integer> getAllProductPoints() {
        List<PointEntity> pointEntityList = pointRepository.findAll();
        return pointEntityList.stream()
                .map(PointEntity::getProductPoint)
                .collect(Collectors.toList());
    }

    public List<String> getAllProductCodes() {
        List<PointEntity> pointEntityList = pointRepository.findAll();
        return pointEntityList.stream()
                .map(PointEntity::getProductCode)
                .collect(Collectors.toList());
    }


    public int getMemberPoint(String memberId) {
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(memberId);
        return optionalMember.map(MemberEntity::getPoint).orElse(0);
    }


    public List<PointEntity> getProductInfo(String productCode) {
        List<PointEntity> pointEntityList = pointRepository.findByProductCode(productCode);
        return pointEntityList;
    }
}