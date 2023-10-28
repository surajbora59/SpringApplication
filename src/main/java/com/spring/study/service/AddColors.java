package com.spring.study.service;

import com.spring.study.Request.AddColorRequest;
import com.spring.study.Response.GenericResponse;

public interface AddColors {
    GenericResponse addColors(AddColorRequest addColorRequest);
}
