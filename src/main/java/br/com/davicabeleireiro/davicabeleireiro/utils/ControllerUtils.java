package br.com.davicabeleireiro.davicabeleireiro.utils;

import org.springframework.data.domain.Sort;

public class ControllerUtils {

    public static Sort.Direction getSortDirection(String direction){

        return "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
