package br.com.castrojoaodev.pokedex.controllers;

import br.com.castrojoaodev.pokedex.services.BuscaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BuscaController {

    BuscaService buscaService = new BuscaService();

    @GetMapping("/tipo")
    public String listaTiposPokemon() throws IOException {
        return buscaService.buscaTiposPokemon();
    }

    @GetMapping("/tipo/{tipo}/{quantidade}")
    public String listaPokemon(@PathVariable String tipo, @PathVariable int quantidade) throws IOException {
        return buscaService.buscaPokemon(tipo, quantidade);
    }

}
