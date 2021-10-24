package br.com.jcardoso.controller;

import br.com.jcardoso.model.Book;
import br.com.jcardoso.proxy.CambioProxy;
import br.com.jcardoso.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,
                         @PathVariable("currency") String cureency) {

        var book = repository.getById(id);
        if (book == null) throw new RuntimeException("Book not found");

        var cambio = proxy.getCambio(book.getPrice(), "USD", cureency);

        var port = environment.getProperty("local.server.port");
        book.setEnvironment("Book Port: " + port + " Cambio Port: " + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        return book;
    }

//    @GetMapping(value = "/{id}/{currency}")
//    public Book findBook(@PathVariable("id") Long id,
//                         @PathVariable("currency") String cureency) {
//
//        var book = repository.getById(id);
//        if (book == null) throw new RuntimeException("Book not found");
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("amount", book.getPrice().toString());
//        params.put("from", "USD");
//        params.put("to", cureency);
//
//        var response =  new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
//                Cambio.class, params);
//
//        var cambio = response.getBody();
//
//        var port = environment.getProperty("local.server.port");
//        book.setEnvironment(port);
//        book.setPrice(cambio.getConvertedValue());
//        return book;
//    }



}
