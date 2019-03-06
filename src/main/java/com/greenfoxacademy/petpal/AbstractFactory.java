package com.greenfoxacademy.petpal;

public interface AbstractFactory<T, U extends Enum> {

  public T create(U type);

}
