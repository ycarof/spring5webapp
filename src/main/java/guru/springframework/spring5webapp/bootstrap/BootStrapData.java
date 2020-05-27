package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        System.out.println("Started in Bootstrap");

        Publisher atalantePublisher = new Publisher("Librairie L'Atalante", "15, rue des Vieilles-Douves", "Nantes", "France", "44000");
        publisherRepository.save(atalantePublisher);
        Publisher ltJLPublisher = new Publisher("Editions LT Jacques Lanore", "5, allée de la 2e D.B.", "Paris", "France", "75015");
        publisherRepository.save(ltJLPublisher);

        Author terryPAuthor = new Author("Terry", "Pratchett");
        authorRepository.save(terryPAuthor);

        Book laddm1Book = new Book("La huitième couleur", "978-2-84172-689-9");
        bookRepository.save(laddm1Book);

        terryPAuthor.getBooks().add(laddm1Book);
        laddm1Book.getAuthors().add(terryPAuthor);
        laddm1Book.setPublisher(atalantePublisher);
        atalantePublisher.getBooks().add(laddm1Book);
        authorRepository.save(terryPAuthor);
        bookRepository.save(laddm1Book);
        publisherRepository.save(atalantePublisher);

        Book laddm2Book = new Book("Le huitième sortilège", "978-2-84172-690-5");
        terryPAuthor.getBooks().add(laddm2Book);
        laddm2Book.getAuthors().add(terryPAuthor);
        laddm2Book.setPublisher(atalantePublisher);
        atalantePublisher.getBooks().add(laddm2Book);
        authorRepository.save(terryPAuthor);
        bookRepository.save(laddm2Book);
        publisherRepository.save(atalantePublisher);

        Author bdAuthor = new Author("Bernard", "Deschamps");
        Author jcdAuthor = new Author("Jean-Claude", "Deschaintre");
        Book lldpBook = new Book("Le livre du pâtissier", "978-2-86268-417-8");
        bdAuthor.getBooks().add(lldpBook);
        jcdAuthor.getBooks().add(lldpBook);
        lldpBook.getAuthors().add(bdAuthor);
        lldpBook.getAuthors().add(jcdAuthor);
        lldpBook.setPublisher(ltJLPublisher);
        ltJLPublisher.getBooks().add(lldpBook);

        authorRepository.save(bdAuthor);
        authorRepository.save(jcdAuthor);
        bookRepository.save(lldpBook);
        publisherRepository.save(ltJLPublisher);

        System.out.println("Number of saved publisher : " + publisherRepository.count());
        System.out.println("Number of saved author : " + authorRepository.count());
        System.out.println("Number of saved book : " + bookRepository.count());
        System.out.println("Number of saved book(s) for Atalante publisher : " + atalantePublisher.getBooks().size());
        System.out.println("Number of saved book(s) for LT publisher : " + ltJLPublisher.getBooks().size());

    }
}
