package com.spring.study.Service.Impl;

import com.spring.study.Entity.Colors;
import com.spring.study.Repository.ColorRepository;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.GenericResponse;
import com.spring.study.Service.UpdateColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateColorServiceImpl implements UpdateColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Override
    public GenericResponse updateColor(ColorsRequest request){
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
}
