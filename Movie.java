import java.util.ArrayList;

public class Movie {

    String title;
    int rating; //0 - 10
    ArrayList<Movie> similar;

    public Movie (String title, int rating, ArrayList<Movie> similarMovies) {
        this.rating = rating;
        this.title = title;
        this.similar = similarMovies;

    }

    @Override
    public String toString() {
        return title;
    }
}
