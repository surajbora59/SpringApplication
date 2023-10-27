package com.spring.study.service;

import java.util.List;

import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.ColorsResponse;

public interface GetColors {
    List<ColorsResponse> getColors(ColorsRequest request);
}
