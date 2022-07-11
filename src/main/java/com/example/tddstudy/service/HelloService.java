package com.example.tddstudy.service;


import com.example.tddstudy.domain.HelloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final HelloRepository helloRepository;

    @Transactional
    public String HellloNow(){
        return helloRepository.helloGetNow();
    }
}
