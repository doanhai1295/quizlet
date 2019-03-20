package com.quizlet.core.app.service;

import com.quizlet.core.app.dto.TestDTO;
import com.quizlet.core.app.entity.TestEntity;
import com.quizlet.core.app.repository.TestRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository mainRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    public List<TestDTO> findAll() throws Exception {

        List<TestEntity> categories = mainRepository.findAll();     
        return categories.stream().map(category -> convertToDto(category)).collect(Collectors.toList());

    }
    
    private TestDTO convertToDto(TestEntity entity) {
        TestDTO testDTO = modelMapper.map(entity, TestDTO.class);
        return testDTO;
    }
    

}
