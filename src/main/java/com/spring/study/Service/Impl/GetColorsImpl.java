package com.spring.study.Service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.spring.study.Entity.Colors;
import com.spring.study.Repository.ColorRepository;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Factory.ColorResponseFactoryImpl;
import com.spring.study.Response.ColorsResponse;
import com.spring.study.Service.GetColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetColorsImpl implements GetColors {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorsResponse colorsResponse;

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
}
