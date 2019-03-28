package com.greenfoxacademy.petpal;

public interface Factory<T, U extends Enum> {

  public T create(U type);

}
