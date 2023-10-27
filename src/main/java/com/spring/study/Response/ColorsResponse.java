package com.spring.study.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Component
//@Scope("prototype")
public class ColorsResponse {
    private Integer id;
    private String colorType;
}
