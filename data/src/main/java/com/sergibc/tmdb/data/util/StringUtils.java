package com.sergibc.tmdb.data.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Util class to work with strings
 */
public class StringUtils {

    private static Gson gson = new GsonBuilder().create();

    /**
     * This method is used to serialize an object using Gson annotations
     *
     * @param object    the object to be serialized
     * @param classType the class of the object
     * @param <T>       the type of the object
     *
     * @return the object serialized as a JSON string
     */
    public static <T> String serialize(T object, Class<T> classType) {
        return gson.toJson(object, classType);
    }

    /**
     * This method is used to serialize a list of objects using Gson annotations
     *
     * @param list      the list of objects to be serialized
     * @param classType the class of the objects
     * @param <T>       the type of the objects
     *
     * @return the list serialized as a JSON string
     */
    public static <T> String serialize(List<T> list, Class<T> classType) {
        return gson.toJson(list, new ListOfSomething<T>(classType));
    }

    /**
     * This method is used to deserialize a JSON string using Gson annotations
     *
     * @param serializedString the serialized string
     * @param classType        the class of the object
     * @param <T>              the return type
     *
     * @return the deserialized object
     */
    public static <T> T deserializeObject(String serializedString, Class<T> classType) {
        return gson.fromJson(serializedString, classType);
    }

    /**
     * This method is used to deserialize a JSON string using Gson annotations
     *
     * @param serializedString the serialized string
     * @param classType        the class of the objects
     * @param <T>              the return type
     *
     * @return the deserialized list of objects
     */
    public static <T> List<T> deserializeList(String serializedString, Class<T> classType) {
        return gson.fromJson(serializedString, new ListOfSomething<T>(classType));
    }

    /**
     * This method is used to get a new instance of a serializable object
     */
    public static <T> T copy(T object, Class<T> classType) {
        String temp = StringUtils.serialize(object, classType);
        return StringUtils.deserializeObject(temp, classType);
    }
}