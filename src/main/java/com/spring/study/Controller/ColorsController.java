package com.spring.study.Controller;

import java.util.List;

import com.spring.study.Handlers.RequestHandler;
import com.spring.study.Request.ColorsRequest;
import com.spring.study.Response.ColorsResponse;
import com.spring.study.Response.GenericResponse;
import com.spring.study.service.Impl.GetColorsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    GetColorsImpl getColorsImpl;
    @GetMapping("/getColors")
    public ResponseEntity<GenericResponse> getColors(@RequestParam(name = "colorType",required = false) String colorType,
                                                     @RequestParam(name = "id",required = false) Integer colorId) {
        ColorsRequest request = requestHandler.getColorsRequest(colorType, colorId);
        List<ColorsResponse> response =  getColorsImpl.getColors(request);
        GenericResponse genericResponse = GenericResponse.builder().data(response).build();
        return ResponseEntity.ok().body(genericResponse);
    }
}
