package com.uk.ms.lcs.feign;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class MyMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public MyMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
                 mediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED); //Add text/html type support
        setSupportedMediaTypes(mediaTypes);
    }
}