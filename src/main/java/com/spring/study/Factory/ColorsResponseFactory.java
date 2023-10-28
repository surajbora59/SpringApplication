package com.spring.study.Factory;

import com.spring.study.Response.ColorsResponse;

public interface ColorsResponseFactory {
    ColorsResponse colorsResponse(Integer id, String colorType);
}
