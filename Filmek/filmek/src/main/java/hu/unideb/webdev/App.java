package hu.unideb.webdev;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class App
        //implements CommandLineRunner
{
   // @Autowired
   // ApplicationContext context;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }

   // @Override
  //  public void run(String... args) throws  Exception{
     //   System.out.println("Hello World");

        //Film
       //  FilmDao dao = context.getBean(FilmDao.class);
        // dao.readAll().stream().forEach(System.out::println);
       //  System.out.println("Yuhu");


       //   List daoTest = new ArrayList<>();
       //  daoTest.add("Behind The Scenes");
        // dao.createFilm(new Film("Doom","ShooterFilm",2020,(byte)1,(byte)2,2.3,(short)100,3.4,Rating.PG,daoTest));
       //  System.out.println(dao);


      /*   Film dao2 = Film.builder()
                 .title("Doom4")
                 .description("Shooterfilm4")
                 .releaseYear(2014)
                 .language((byte)1)
                 .originalLanguage(null)
                 .rentalDuration((byte)2)
                 .rentalRate(2.7)
                 .length((short)10)
                 .replacementCost(1.4)
                 .rating(Rating.PG)
                 .specialFeatures("Behind The Scenes")
                 .build();

        Film dao3 = Film.builder()
                .title("Doom5")
                .description("Shooterfilm4")
                .releaseYear(2014)
                .language((byte)1)
                .originalLanguage(null)
                .rentalDuration((byte)2)
                .rentalRate(2.7)
                .length((short)10)
                .replacementCost(1.4)
                .rating(Rating.PG)
                .specialFeatures("Behind The Scenes")
                .build();


           dao.createFilm(dao2);
           dao.updateFirstMatch(dao2,dao3); */
          // dao.deleteFilm(dao2);
          // dao.deleteFilmById((short) 1007);
          // dao.deleteFilmById(dao2.getId());

        //Actor
        /* ActorDao daoTest = context.getBean(ActorDao.class);
         Actor daoCreate = Actor.builder()
                 .firstName("Steven")
                 .lastName("Hawking")
                 .build();
        Actor daoUpdate = Actor.builder()
                .firstName("Steven")
                .lastName("Hawking Junior")
                .build(); *

         daoTest.updateActor(daoCreate,daoUpdate);
        // dao.readAll().stream().forEach(System.out::println);
        // System.out.println("Yuhu"); */

        //Category
     //    CategoryDao dao = context.getBean(CategoryDao.class);
        // dao.readAll().stream().forEach(System.out::println);
        /*Category daoTest = Category.builder()
                .categoryId(18)
                .name("Macska")
                .build();
        Category daoUpdate = Category.builder()
                .categoryId(18)
                .name("Egér")
                .build();

       dao.updateCategory(daoTest,daoUpdate); */

        // System.out.println("Yuhu");

        //FilmActor
       //  FilmActorDao dao = context.getBean(FilmActorDao.class);
      //   dao.readAll().stream().forEach(System.out::println);
        // System.out.println("Yuhu");

        /* FilmActor daoCreate = FilmActor.builder()
                 .filmTitle("Doom5")
                 .actorName("Steven")
                 .build();
        FilmActor daoUpdate = FilmActor.builder()
                .filmTitle("Doom5")
                .actorName("REESE")
                .build();

         dao.updateFilmActor(daoCreate,daoUpdate); */
        //FilmCategory
       //  FilmCategoryDao dao = context.getBean(FilmCategoryDao.class);
         //dao.readAll().stream().forEach(System.out::println);
         //System.out.println("Yuhu");

       /*  FilmCategory daoCreate = FilmCategory.builder()
                 .filmTitle("Doom5")
                 .categoryName("Horror")
                 .build();
        FilmCategory daoCreate2 = FilmCategory.builder()
                .filmTitle("Doom5")
                .categoryName("Comedy")
                .build();
        dao.deleteFilmCategory(daoCreate); */
         //Language
     //  Language daoLanguage = Language.builder()
      //          .name("Russian")
       //         .build();
      //  Language daoUpdate = Language.builder()
       //         .name("Swedish")
      //          .build();
       // daoTest.createLanguage(daoLanguage);
       // daoTest.deleteLanguage(daoLanguage);
        //daoTest.updateLanguage(daoLanguage,daoUpdate);
         //dao.readAll().stream().forEach(System.out::println);

         //FilmDao dao = context.getBean(FilmDao.class);
        // dao.createFilm(film);
        // System.out.println(film);
       //  System.out.println("Yuhu22");
       //  System.out.println(Rating.PG13.getType());



    }
//}


