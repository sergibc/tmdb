package com.sergibc.tmdb.domain.mapper;

/**
 * Interface that specifies the method to map data to model and vice versa
 */
public interface Mapper <M, D> {

    D modelToData(M model);

    M dataToModel(D data);
}

