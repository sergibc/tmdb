package com.sergibc.tmdb.clean.domain.bean;

import lombok.Data;

/**
 * TODO: Add your comments
 */
@Data
public class ErrorResponseBo {

    private String errorCode;

    private String errorDescription;
}