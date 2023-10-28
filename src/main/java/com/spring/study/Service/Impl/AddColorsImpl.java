package com.spring.study.Service.Impl;

import com.spring.study.Entity.Colors;
import com.spring.study.Repository.ColorRepository;
import com.spring.study.Request.AddColorRequest;
import com.spring.study.Response.GenericResponse;
import com.spring.study.Service.AddColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddColorsImpl implements AddColors {

    private static final Logger logger = LoggerFactory.getLogger(AddColorsImpl.class);
    @Autowired
    private ColorRepository colorRepository;

    public GenericResponse addColors(AddColorRequest addColorRequest) {
        try{
            Colors colors = new Colors();
            colors.setColorType(addColorRequest.getColorType());
            colorRepository.save(colors);
            logger.info("Colors added successfully -> {}",addColorRequest.getColorType());
            return GenericResponse.builder().meta("Colors added successfully").build();
        }
        catch(Exception e){
            logger.error("Error while adding colors -> {}", e.fillInStackTrace());
            return GenericResponse.builder().meta("Error while adding colors").build();
        }
    }
}
