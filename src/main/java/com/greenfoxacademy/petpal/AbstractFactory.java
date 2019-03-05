package com.greenfoxacademy.petpal;

public interface AbstractFactory<T, U extends Enum<U>> {

  public T create(U type);

}
