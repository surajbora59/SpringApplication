package com.spring.study.Response;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    Object meta;
    Object data;
}