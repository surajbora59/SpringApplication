package com.spring.study.Factory;

import com.spring.study.Response.ColorsResponse;

public class ColorResponseFactoryImpl implements ColorsResponseFactory{

    @Override
    public ColorsResponse colorsResponse(Integer id, String colorType) {
        return new ColorsResponse();
    }
}
