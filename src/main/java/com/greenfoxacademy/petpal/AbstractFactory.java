package com.greenfoxacademy.petpal;

public interface AbstractFactory<T, U extends Enum> {

  T create(U type);

}
