package com.spring.study.Handlers;

import com.spring.study.Request.AddColorRequest;
import com.spring.study.Request.ColorsRequest;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@Configuration
public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    public ColorsRequest getColorsRequest(String colorType, Integer colorId) {
        if (colorId != null) {
            logger.info("colorId is {}", colorId);
            if(colorId<0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id cannot be zero/-ve");
            }
        }
        if (!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}", colorType);
        } else {
            colorType = "";
            logger.error("colorType is null");
        }
        return ColorsRequest.builder().colorType(colorType).id(colorId).build();
    }

    public AddColorRequest addColorsRequest(String colorType) {
        if (!StringUtils.isEmpty(colorType)) {
            logger.info("colorType is {}", colorType);
        } else {
            logger.error("colorType is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "colorType is null");
        }
        return AddColorRequest.builder().colorType(colorType).build();
    }

    public ColorsRequest updateColorRequest(ColorsRequest colorsRequest){
        if(colorsRequest.getId() != null && !StringUtils.isEmpty(colorsRequest.getColorType())) {
            logger.info("colorId is {}", colorsRequest.getId());
            if (!isNumeric(Integer.parseInt(colorsRequest.getColorType()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The input is not a number");
            }
            if (isNumeric(colorsRequest.getId())) {
                return ColorsRequest.builder().
                                    id(colorsRequest.getId()).
                                    colorType(colorsRequest.getColorType()).
                                    build();
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id is not a number");
            }
        }
        else{
            logger.error("colorType is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "colorType/id is null");
        }
    }

    public Integer deleteColorRequest(Integer id){
        if(id != null) {
            logger.info("colorId is {}", id);
            if (id>0) {
                return id;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id cannot be zero/-ve");
            }
        }
        else{
            logger.error("colorType is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is null");
        }
    }
}
