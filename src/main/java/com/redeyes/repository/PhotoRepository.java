package com.redeyes.repository;

import java.util.List;

public interface PhotoRepository {
    void add(byte[] photo);

    byte[] get(int id);

    int size();

    void init();

    List<Integer> getIds();
}
