package com.pettopia.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pettopia.demo.entity.Produto;
import com.pettopia.demo.service.CategoriaService;
import com.pettopia.demo.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ModelAndView index(){
        var listaProdutos = produtoService.getAll();
        return new ModelAndView("produto/index",
                    "listaProdutos",listaProdutos);
    }
    @GetMapping("/novo")
    public ModelAndView novo(){
        var produto = new Produto();
        var listaCategoria = categoriaService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("produto",produto);
        dados.put("ListaCategorias",listaCategoria);
        return new ModelAndView("produto/form", 
                                dados);
    }
    @PostMapping(params = "form")
    public ModelAndView save(Produto produto){

        produtoService.save(produto);
        return new ModelAndView("redirect:/produto");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") 
                            Produto produto){
        var listaCategoria = categoriaService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("produto",produto);
        dados.put("ListaCategorias",listaCategoria);
        return new ModelAndView("produto/form", 
                                dados);
    }
    @GetMapping("/remover/{id}")
    public ModelAndView remover (@PathVariable("id") long id){
        
        produtoService.delete(id);
        return new ModelAndView("redirect:/produto");
    }
}

