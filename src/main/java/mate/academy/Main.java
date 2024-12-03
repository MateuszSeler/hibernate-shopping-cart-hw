package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.CinemaHall;
import mate.academy.model.Movie;
import mate.academy.model.MovieSession;
import mate.academy.model.User;
import mate.academy.security.AuthenticationService;
import mate.academy.service.CinemaHallService;
import mate.academy.service.MovieService;
import mate.academy.service.MovieSessionService;
import mate.academy.service.ShoppingCartService;

public class Main {
    private static final Injector injector = Injector.getInstance("mate.academy");

    public static void main(String[] args) {

        // 0. INJECTING
        final var cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        final var movieService
                = (MovieService) injector.getInstance(MovieService.class);
        final var movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        final var authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        final var shoppingCartService
                = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

        // 1. CREATING
        // Movies
        Movie movie1 = new Movie();
        movie1.setTitle("The Godfather");
        movie1.setDescription("Cinema classics");

        Movie movie2 = new Movie();
        movie2.setTitle("Up");
        movie2.setDescription("Touching");

        Movie movie3 = new Movie();
        movie3.setTitle("Harold Crick");
        movie3.setDescription("Original");

        // CinemaHalls
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(100);
        cinemaHall1.setDescription("Small Hall");

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(200);
        cinemaHall2.setDescription("Medium Hall");

        CinemaHall cinemaHall3 = new CinemaHall();
        cinemaHall3.setCapacity(300);
        cinemaHall3.setDescription("Large Hall");

        //MovieSessions
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall1);
        movieSession1.setShowTime(2024, 12,1,10, 15);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie2);
        movieSession2.setCinemaHall(cinemaHall2);
        movieSession2.setShowTime(2024, 12,2,10, 15);

        MovieSession movieSession3 = new MovieSession();
        movieSession3.setMovie(movie3);
        movieSession3.setCinemaHall(cinemaHall3);
        movieSession3.setShowTime(2024, 12,2,23, 15);

        // 2. ADDING
        // Movies
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.add(movie3);

        // CinemaHalls
        cinemaHallService.add(cinemaHall1);
        cinemaHallService.add(cinemaHall2);
        cinemaHallService.add(cinemaHall3);

        //MovieSessions
        movieSessionService.add(movieSession1);
        movieSessionService.add(movieSession2);
        movieSessionService.add(movieSession3);

        // User
        authenticationService.register("jan@gmail.com", "haslojana");
        User user1 = authenticationService.login("jan@gmail.com", "haslojana");
        authenticationService.register("tomasz@gmail.com", "haslotomasza");
        User user2 = authenticationService.login("tomasz@gmail.com", "haslotomasza");
        authenticationService.register("piotr@gmail.com", "haslopiotra");
        User user3 = authenticationService.login("piotr@gmail.com", "haslopiotra");

        // ShoppingCart
        shoppingCartService.registerNewShoppingCart(user1);
        shoppingCartService.registerNewShoppingCart(user2);
        shoppingCartService.registerNewShoppingCart(user3);

        // Ticket
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession2, user2);
        shoppingCartService.addSession(movieSession3, user1);
        shoppingCartService.addSession(movieSession3, user2);
        shoppingCartService.addSession(movieSession3, user3);

        // 3. GETTING
        System.out.println(shoppingCartService.getByUser(user1));
        System.out.println(shoppingCartService.getByUser(user2));
        System.out.println(shoppingCartService.getByUser(user3));
    }
}
