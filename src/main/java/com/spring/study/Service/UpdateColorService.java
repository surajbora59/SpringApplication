package com.spring.study.Service;

import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.GenericResponse;

public interface UpdateColorService {
    GenericResponse updateColor(ColorsRequest request);
    GenericResponse deleteColor(Integer id);
}
