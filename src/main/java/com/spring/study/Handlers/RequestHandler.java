package com.spring.study.Handlers;

import com.spring.study.Request.ColorsRequest;
import io.micrometer.common.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public ColorsRequest getColorsRequest(String colorType, Integer colorId) {
        if (colorId != null) {
            logger.info("colorId is {}", colorId);
        }
        if (!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}", colorType);
        } else {
            colorType = "";
            logger.error("colorType is null");
        }
        return ColorsRequest.builder().colorType(colorType).id(colorId).build();
    }

    public ColorsRequest addColorsRequest(String colorType) {
        if (!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}", colorType);
        } else {
            colorType = "";
            logger.error("colorType is null");
        }
        return ColorsRequest.builder().colorType(colorType).build();
    }
}
