package com.alino.demoparkAPI.web.exception;

public class UsernameUniqueViolationException extends RuntimeException{

  public UsernameUniqueViolationException(String message){
    super(message);
  }

}
