package com.spring.study.Resources;

import java.util.List;

import com.spring.study.Handlers.RequestHandler;
import com.spring.study.Request.AddColorRequest;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.ColorsResponse;
import com.spring.study.Response.GenericResponse;
import com.spring.study.Service.Impl.ColorsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/colors")
@Validated
public class ColorsController {
    @Autowired
    private RequestHandler requestHandler;
    @Autowired
    private ColorsServiceImpl colorsServiceImpl;
    @GetMapping(value = "/getColors", produces = "application/json")
    public ResponseEntity<GenericResponse> getColors(@RequestParam(name = "colorType",required = false) String colorType,
                                                     @RequestParam(name = "id",required = false) Integer colorId) {
        ColorsRequest request = requestHandler.getColorsRequest(colorType, colorId);
        GenericResponse genericResponse = null;
        if(StringUtils.hasText(request.getColorType())) {
            List<ColorsResponse> response = colorsServiceImpl.getColors(request);
            genericResponse = GenericResponse.builder().data(response).build();
        }
        else{
            ColorsResponse response = colorsServiceImpl.getColor(request.getId());
            genericResponse = GenericResponse.builder().data(response).build();
        }
        return ResponseEntity.ok().body(genericResponse);
    }

    @PostMapping(value = "/addColor", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GenericResponse> addColors(@RequestBody AddColorRequest addColorRequest) {
        AddColorRequest request = requestHandler.addColorsRequest(addColorRequest.getColorType());
        GenericResponse response =  colorsServiceImpl.addColors(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/updateColor", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GenericResponse> updateColors(@RequestBody ColorsRequest colorsRequest){
        ColorsRequest request = requestHandler.updateColorRequest(colorsRequest);
        GenericResponse response = colorsServiceImpl.updateColor(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/deleteColor", produces = "application/json")
    public ResponseEntity<GenericResponse> deleteColors(@RequestParam(name = "id",required = true) Integer colorId){
        Integer id = requestHandler.deleteColorRequest(colorId);
        GenericResponse response = colorsServiceImpl.deleteColor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
