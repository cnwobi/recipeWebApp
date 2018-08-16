package com.chukanwobi.recipeapp.exceptions;

public class ImageNotFoundException extends NullPointerException {
    public ImageNotFoundException(){
        super();
    }
    public ImageNotFoundException(String message){
        super(message);
    }

}
