package com.narola.spring;

import com.narola.spring.repository.StudentRepository;
import com.narola.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Validator bookValidator;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(bookValidator);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello-book")
    public ResponseEntity<String> book1() {
        return ResponseEntity.ok("Hello book");
    }

    @GetMapping("/{bookId}/comments/{commentId}")
    public ResponseEntity<String> getBook(@PathVariable("bookId") int sdsasd, @PathVariable String commentId) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.valueOf(sdsasd) + "=" + commentId);
    }

    @GetMapping("/getbookV1")
    public ResponseEntity<String> getBook2(HttpServletRequest request, @RequestParam("bookId") int bookId) {
        return ResponseEntity.ok(request.getParameter("bookId") + "=" + bookId);
    }

    @GetMapping(value = "/getbookV1", headers = "x-api-key")
    public ResponseEntity<String> getBook1(HttpServletRequest request, @RequestParam("bookId") int bookId,
                                           @RequestHeader Map<String, String> headMap) {
        return ResponseEntity.ok(request.getParameter("bookId") + "=" + bookId);
    }

    @PostMapping(value = "/book-data-bind")
    public Book addBook(@Validated @RequestBody Book book, BindingResult bindingResult) {
        if (book.getName().equals("oracle")) {
            throw new ResourceNotFoundException("Custome message");
        } else if (book.getName().equals("oracle1")) {
            throw new NullPointerException("Custome message");
        } else if (book.getName().equals("oracle2")) {
            throw new EmployeeProflePicNotFound("EmployeeProflePicNotFound");
        }
        return book;
    }

    @GetMapping("/addBookForm")
    public String addBookForm(Model bookModel) {
        bookModel.addAttribute("bookName", "ORACLE");
        return "AddBookForm";
    }

    @PostMapping(value = "/addbook")
    public ModelAndView addBookV1(@Validated Book book, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("BookView");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping("/jpa-persist")
    public ResponseEntity<String> jpaPersist(@RequestParam("emailId") String emailId) {
        EntityTransaction transaction = null;
        try {
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            User user = new User();
            user.setFirstName("JAVA");
            user.setLastName("8");
            user.setEmail(emailId);
            user.setCreatedOn(new Date());
            entityManager.persist(user);

            if (emailId.contains("ex")) {
                String str = null;
                str.trim();
            }

            transaction.commit();
            entityManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/jpa-persist1")
    @Transactional
    public ResponseEntity<String> jpaPersist1(@RequestParam("emailId") String emailId) {
        try {
//            User user = new User();
//            user.setFirstName("JAVA");
//            user.setLastName("8");
//            user.setEmail(emailId);
//            user.setCreatedOn(new Date());
//
//            Optional<User> optionalUser = userRepository.findById(2L);
//            if (optionalUser.isPresent()) {
//                System.out.println(optionalUser.get());
//            }
//
//            List<User> userList = (List<User>) userRepository.findAll();
//            System.out.println(userList.size());
//            System.out.println(userRepository.existsById(2L));
//
//            List<User> userList1 = userRepository.findByFirstName("JAVA");
//            System.out.println(userList1.size());
//
//            List<User> userList2 = userRepository.findByFirstNameAndLastName("JAVA", "8");
//            System.out.println(userList2.size());

//            entityManager.persist(user);


            Univercity univercity = new Univercity();
            univercity.setSubject("JAVA_STREAM");

            Student student = new Student();
            student.setFirstName("JAVA8.1");
            student.setUnivercity(univercity);

            studentRepository.save(student);

            List<Student> studentList = studentRepository.findByUnivercitySubject("JAVA_STREAM");
            System.out.println(studentList.size());

            if (emailId.contains("ex")) {
                String str = null;
                str.trim();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return ResponseEntity.ok().build();
    }

    @ModelAttribute
    public void modelAttribute1(Model model) {
        model.addAttribute("msg", "Welcome to Spring");
    }

    @ModelAttribute("tempBook")
    public Book modelAttribute1() {
        Book book = new Book();
        book.setName("TEMP1");
        book.setAuthor("TEMP2");
        return book;
    }
}
