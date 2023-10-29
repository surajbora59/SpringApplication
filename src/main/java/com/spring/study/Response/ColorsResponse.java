package com.spring.study.Response;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class ColorsResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String colorType;
}
