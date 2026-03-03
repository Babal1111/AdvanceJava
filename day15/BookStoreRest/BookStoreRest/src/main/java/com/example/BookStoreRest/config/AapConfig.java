package com.example.BookStoreRest.config;

import ch.qos.logback.core.model.Model;
import com.example.BookStoreRest.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AapConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();

//        public BookDto createdBook(BookDto dto){
//
//        }
    }

}
