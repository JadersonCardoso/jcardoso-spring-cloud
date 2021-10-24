package br.com.jcardoso.repository;

import br.com.jcardoso.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
