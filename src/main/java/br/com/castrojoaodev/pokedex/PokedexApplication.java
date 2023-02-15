package br.com.castrojoaodev.pokedex;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@RestController
@SpringBootApplication
public class PokedexApplication {

    @GetMapping("/tipo")
    public String listaTiposPokemon() throws IOException {

        final JsonNode jsonNode = new ObjectMapper().readTree(new URL("https://pokeapi.co/api/v2/type/"));

        ArrayList<String> tipos = new ArrayList<>();
        for (JsonNode tipo : jsonNode.get("results")) {
            tipos.add(String.valueOf(tipo.get("name")));
        }
        return String.valueOf(tipos);
    }

    @GetMapping("/tipo/{tipo}/{quantidade}")
    public String listaPokemon(@PathVariable String tipo, @PathVariable int quantidade) throws IOException {

        final JsonNode jsonNode = new ObjectMapper().readTree(new URL("https://pokeapi.co/api/v2/type/" + tipo));

        int limite = 0;
        ArrayList<String> pokemon = new ArrayList<>();
        for (JsonNode nome : jsonNode.get("pokemon")) {
            if (limite < quantidade) {
                pokemon.add(String.valueOf(nome.get("pokemon").get("name")));
            } else {
                break;
            }
            limite++;
        }
        return String.valueOf(pokemon);
    }

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

}
