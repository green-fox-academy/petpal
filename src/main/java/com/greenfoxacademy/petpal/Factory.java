package com.greenfoxacademy.petpal;

public interface Factory<T, U extends Enum> {

  T create(U type);

}
