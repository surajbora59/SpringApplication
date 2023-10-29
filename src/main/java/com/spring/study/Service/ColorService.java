package com.spring.study.Service;

import java.util.List;

import com.spring.study.Request.AddColorRequest;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.ColorsResponse;
import com.spring.study.Response.GenericResponse;

public interface ColorService {
    List<ColorsResponse> getColors(ColorsRequest request);
    ColorsResponse getColor(Integer id);
    GenericResponse updateColor(ColorsRequest request);
    GenericResponse deleteColor(Integer id);
    GenericResponse addColors(AddColorRequest addColorRequest);

}
