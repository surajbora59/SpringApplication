package com.spring.study.Service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.spring.study.Entity.Colors;
import com.spring.study.Factory.ColorResponseFactoryImpl;
import com.spring.study.Repository.ColorRepository;
import com.spring.study.Request.AddColorRequest;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.ColorsResponse;
import com.spring.study.Response.GenericResponse;
import com.spring.study.Service.ColorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ColorsServiceImpl implements ColorService {
    private static final Logger logger = LoggerFactory.getLogger(ColorsServiceImpl.class);
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    ColorsResponse colorsResponse;
    @Override
    public List<ColorsResponse> getColors(ColorsRequest request) {
        if(request.getColorType()!=null || request.getId()!=null) {
            List<Colors> colorData = colorRepository.findByColorTypeIgnoreCase(request.getColorType());
            List<ColorsResponse> responseList = new ArrayList<>();
            colorData.forEach(color -> {
                colorsResponse = new ColorResponseFactoryImpl().colorsResponse(color.getId(), color.getColorType());
                colorsResponse.setColorType(color.getColorType());
                colorsResponse.setId(color.getId());
                responseList.add(colorsResponse);
            });
            return responseList;
        }
        return Collections.emptyList();
    }

    @Override
    public ColorsResponse getColor(Integer id) {
        Colors color = colorRepository.findById(id)
                                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Color not found with id: " + id));
        colorsResponse = new ColorResponseFactoryImpl().colorsResponse(color.getId(), color.getColorType());
        colorsResponse.setColorType(color.getColorType());
        colorsResponse.setId(color.getId());
        return colorsResponse;
    }

    @Override
    public GenericResponse updateColor(ColorsRequest request) {
        Colors existingColor = colorRepository.findById(request.getId())
                                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Color not found with id: " + request.getId()));
        existingColor.setColorType(request.getColorType());
        colorRepository.save(existingColor);
        return GenericResponse.builder().meta("Color updated successfully").build();
    }

    @Override
    public GenericResponse deleteColor(Integer id) {
        Colors existingColor = colorRepository.findById(id)
                                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Color not found with id: " + id));
        colorRepository.delete(existingColor);
        return GenericResponse.builder().meta("Color deleted successfully").build();
    }

    @Override
    public GenericResponse addColors(AddColorRequest addColorRequest) {
        try{
            Colors colors = new Colors();
            colors.setColorType(addColorRequest.getColorType());
            colorRepository.save(colors);
            return GenericResponse.builder().meta("Colors added successfully").build();
        }
        catch(Exception e){
            return GenericResponse.builder().meta("Error while adding colors").build();
        }
    }
}
