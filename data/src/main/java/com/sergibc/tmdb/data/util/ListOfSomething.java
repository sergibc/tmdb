package com.sergibc.tmdb.data.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Utils class used to serialize and deserialize generic lists of objects.
 * See <a href="http://stackoverflow.com/questions/14139437/java-type-generic-as-argument-for-gson">link</a>
 * for more details
 */
public class ListOfSomething <K> implements ParameterizedType {

    private Class<?> wrapped;

    public ListOfSomething(Class<K> wrapped) {
        this.wrapped = wrapped;
    }

    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }

}