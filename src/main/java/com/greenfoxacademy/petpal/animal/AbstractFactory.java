package com.greenfoxacademy.petpal.animal;

public interface AbstractFactory<T, U extends Enum<U>> {

  public T create(U type);

}
