
import dao.CategoryDAO;
import models.Category;
import models.GeneratedValues;
import models.LetterTemplate;
import models.user.RoleEnum;
import models.user.StatusEnum;
import models.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.*;
import utils.RandomGenerator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.GeneratedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        UserService userService = new UserService();
        CategoryService categoryService = new CategoryService();
        CategoryDAO categoryDAO = new CategoryDAO();
        GeneratedValueService generatedValueService = new GeneratedValueService();
        Category category = new Category();
        User user;
        GeneratedValues generatedValue = new GeneratedValues();




//        category.setTitle("Category title 4");
//        category.setDescription("category description 4 keywords");
//        categoryService.saveCategory(category);

//        System.out.println(categoryDAO.findByTitle("Category title").getDescription().toString());

//        List<Category> categories = categoryService.findCategoryByKeyword("key");
//        for (Category resultCategory : categories) {
//            System.out.println(resultCategory.getTitle() + " " + resultCategory.getDescription());
//        }




//        generatedValue = generatedValueService.createGeneratedValue(10,true,true);
//        user = new User ();
//        user.setEmail("kardinaldon@yandex.ua");
//        user.setPassword("123456");
//        user.setRole(RoleEnum.USER);
//        user.setStatusEnum(StatusEnum.NOT_ACTIVE);
//        userService.createUser(user);

//            try {
//                if(generatedValueService.findGeneratedValuesByValue("rmOueNlSNb") != null) {
//                    user = userService.findUserByEmail("kardinaldon@yandex.ua");
//                    user.setStatusEnum(StatusEnum.NOT_ACTIVE);
//                    userService.updateUser(user);
//                } else {
//                    log.warn("Попытка верификации по неправильной ссылке");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }



        try {
            user = userService.findUserByEmail("kardinaldon@yandex.ua");
            userService.deleteUser(user);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        GeneratedValueService generatedValueService = new GeneratedValueService();
//        GeneratedValues generatedValue = generatedValueService.createGeneratedValue(10,true,true);
//
//        try {
//            System.out.println(generatedValueService.findGeneratedValuesByValue(generatedValue.getGeneratedValue()));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        LetterTemplate letterTemplate = new LetterTemplate();
//        letterTemplate.setLetterHeader("Подтвердите регистрацию");
//        letterTemplate.setLetterText("Перейдите по ссылке для завершения регистрации");
//        LetterService letterService = new LetterService();
//        letterService.createLetter(letterTemplate );

//        RegisterService registerService = new RegisterService();
//        registerService.startRegistration("123@mail.ru","123456789");

//        try {
//            TimeUnit.SECONDS.sleep(10);
//            System.out.println("Waked up");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Runnable task = () -> {
//            try {
//                int secToWait = 1000 * 60;
//                Thread.currentThread().sleep(secToWait);
//                System.out.println("Waked up");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();


//        Thread currentThread = Thread.currentThread();
//        ThreadGroup threadGroup = currentThread.getThreadGroup();
//        System.out.println("Thread: " + currentThread.getName());
//        System.out.println("Thread Group: " + threadGroup.getName());
//        System.out.println("Parent Group: " + threadGroup.getParent().getName());


//        GeneratedValueService generatedValueService = new GeneratedValueService();
//        GeneratedValues generatedValues = new GeneratedValues();
//        generatedValues.setGeneratedValue((new RandomGenerator().getAlphanumericRandomValue(20,true,true)));
//        generatedValueService.createGeneratedValue(generatedValues);
//        System.out.println(generatedValues.getGeneratedValueId());


//        System.out.println(new Properties().getProperty("mail.smtp.password"));

//        String to = "kardinaldon@yandex.ua";         // sender email
//        String from = "kardinal021188@mail.ru";       // receiver email
//        String host = "smtp.yandex.ru";            // mail server host
//
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
//        Session session = Session.getDefaultInstance(properties); // default session

//        UserService userService = new UserService();

//        //Тестово создать пользователя
//        UserService userService = new UserService();
//        User user = new User ();
//        user.setEmail("test");
//        user.setPassword("test");
////        user.setRole(RoleEnum.ADMIN);
//        userService.createUser(user);
//        log.info(user.getEmail() + " создан");

//        try {
//            System.out.println(userService.findUserByEmail("test2").size());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//
//        //Тестово создать категорию
//        CategoryService categoryService = new CategoryService();
//        Category category = new Category("Some T4535676414982044g16s73525210gg33 rrr", "rsg1055149636s3g244502817627544fr");
//        categoryService.saveCategory(category);
//
//        //Тестово содать статью
//        ArticleService articleService = new ArticleService();
//        Article article =new Article();
//        article.setTitle("Article Tit375854414160582139ss3420g6425637ggsdf");
//        article.setSmallDescription("Small Arti1140cl73e sDes6c5224071g59852543r66i4433bcvb");
//        article.setDescription("Article Descri101646p73t2256si8524410g5957on443gb");
//        article.setDateOfCreation(LocalDateTime.now());
//        article.setCategory(category);
//        article.setUser(user);
//        articleService.saveArticle(article);
//
//
//        //1.
//        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//
//        //2.
//        SecurityManager securityManager = factory.getInstance();
//
//        //3.
//        SecurityUtils.setSecurityManager(securityManager);
//
//        System.exit(0);
//
//        // get the currently executing user:
//        Subject currentUser = SecurityUtils.getSubject();
//
//        // Do some stuff with a Session (no need for a web or EJB container!!!)
//        Session session = currentUser.getSession();
//        session.setAttribute("someKey", "aValue");
//        String value = (String) session.getAttribute("someKey");
//        if (value.equals("aValue")) {
//            log.info("Retrieved the correct value! [" + value + "]");
//        }
//
//        // let's login the current user so we can check against roles and permissions:
//        if (!currentUser.isAuthenticated()) {
//            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
//            token.setRememberMe(true);
//            try {
//                currentUser.login(token);
//            } catch (UnknownAccountException uae) {
//                log.info("There is no user with username of " + token.getPrincipal());
//            } catch (IncorrectCredentialsException ice) {
//                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
//            } catch (LockedAccountException lae) {
//                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
//                        "Please contact your administrator to unlock it.");
//            }
//            // ... catch more exceptions here (maybe custom ones specific to your application?
//            catch (AuthenticationException ae) {
//                //unexpected condition?  error?
//            }
//        }
//
//        //say who they are:
//        //print their identifying principal (in this case, a username):
//        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
//
//        //test a role:
//        if (currentUser.hasRole("schwartz")) {
//            log.info("May the Schwartz be with you!");
//        } else {
//            log.info("Hello, mere mortal.");
//        }
//
//        //test a typed permission (not instance-level)
//        if (currentUser.isPermitted("lightsaber:wield")) {
//            log.info("You may use a lightsaber ring.  Use it wisely.");
//        } else {
//            log.info("Sorry, lightsaber rings are for schwartz masters only.");
//        }
//
//        //a (very powerful) Instance Level permission:
//        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
//            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
//                    "Here are the keys - have fun!");
//        } else {
//            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
//        }
//
//        //all done - log out!
//        currentUser.logout();
//
//        System.exit(0);

        //          Подключаем стороннего провайдера
//        Security.addProvider(new BouncyCastleProvider());
//
//            Provider[] providers = Security.getProviders();
//            for (Provider p : providers) {
//                System.out.println(p.getName());
//            }
//
//        try {
//            MessageDigest digester = MessageDigest.getInstance("SHA-512");
//            byte[] input = "Secret string".getBytes();
//            byte[] digest = digester.digest(input);
//            byte[] salt = new byte[16];
//            SecureRandom.getInstanceStrong().nextBytes(salt);
//            digester.update(salt);
//            System.out.println(DatatypeConverter.printHexBinary(digest));
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException(e);
//        }




        log.info("exit");
    }
}
