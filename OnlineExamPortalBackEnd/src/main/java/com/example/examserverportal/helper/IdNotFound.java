package com.example.examserverportal.helper;

public class IdNotFound extends RuntimeException{
    String resourceName;
    String fieldName;
    String fieldValue;

    public IdNotFound(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
}
 public  IdNotFound(String msg){
       super(msg);
 }

}
