package com.spring.study.Handlers;

import com.spring.study.Request.ColorsRequest;
import io.micrometer.common.util.StringUtils;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public ColorsRequest getColorsRequest(String colorType, Integer colorId) {
        if(colorType == null && colorId == null) {
            logger.error("Both colorType and colorId are null");
        }
        if(!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}",colorType);
        }
        if(colorId != null) {
            logger.info("colorId is {}",colorId);
        }
        return ColorsRequest.builder().colorType(colorType).id(colorId).build();
    }

    public ColorsRequest addColorsRequest(String colorType) {
        if(colorType == null || colorType.isEmpty()) {
            colorType = Collections.emptyList().toString();
            logger.error("colorType is null");
        }
        if(!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}",colorType);
        }
        return ColorsRequest.builder().colorType(colorType).build();
    }
}
