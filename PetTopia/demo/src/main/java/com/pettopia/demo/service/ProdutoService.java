package com.pettopia.demo.service;

import java.util.List;

import com.pettopia.demo.entity.Produto;

public interface ProdutoService {
    List<Produto> getAll();

    void save(Produto produto);

    void delete(long id);
}
