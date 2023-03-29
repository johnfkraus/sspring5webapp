package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

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
        Author eric = new Author("Eric", "Evans");
        Publisher genPub = new Publisher("Generic Books Publishing", "321 Maple Street", "Anytown", "New Jersey", "12115");
        Book ddd = new Book("Domain Driven Design", "12365488", genPub);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(genPub);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development Without EJB", "13865488", genPub);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        Author paul = new Author("Paul", "Meehl");
        Publisher echoPoint = new Publisher("Echo Point Books & Media", "123 Oak Street", "East Palestine", "Ohio", "12345");
        Book clin = new Book("Clinical Versus Statistical Prediction", "13865899", echoPoint);

        paul.getBooks().add(clin);
        clin.getAuthors().add(paul);
        clin.setPublisher(echoPoint);
        publisherRepository.save(echoPoint);
        authorRepository.save(paul);
        bookRepository.save(clin);

        System.out.println(">>>>>> Started in bootstrap");
        System.out.println(">>>>>> Number of books = " + bookRepository.count());
        assert(bookRepository.count() == 3);
//        Iterable<Book> books = bookRepository.findAll();
//        for (Book book : books) {
//            System.out.println(book);
//        }
    }
}
