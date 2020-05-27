package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author terryP = new Author("Terry", "Pratchett");
        Book laddm1 = new Book("La huitième couleur", "978-2-84172-689-9");
        terryP.getBooks().add(laddm1);
        laddm1.getAuthors().add(terryP);

        Book laddm2 = new Book("Le huitième sortilège", "978-2-84172-690-5");
        terryP.getBooks().add(laddm2);
        laddm2.getAuthors().add(terryP);

        authorRepository.save(terryP);
        bookRepository.save(laddm1);
        bookRepository.save(laddm2);

        Author bd = new Author("Bernard", "Deschamps");
        Author jcd = new Author("Jean-Claude", "Deschaintre");
        Book lldp = new Book("Le livre du pâtissier", "978-2-86268-417-8");
        bd.getBooks().add(lldp);
        jcd.getBooks().add(lldp);
        lldp.getAuthors().add(bd);
        lldp.getAuthors().add(jcd);

        authorRepository.save(bd);
        authorRepository.save(jcd);
        bookRepository.save(lldp);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of saved book : " + bookRepository.count());

        Publisher atalante = new Publisher("Librairie L'Atalante", "15, rue des Vieilles-Douves", "Nantes", "France", "44000");
        publisherRepository.save(atalante);
        System.out.println("Number of saved publisher : " + publisherRepository.count());

    }
}
