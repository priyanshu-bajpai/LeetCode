import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class TopRatedMovies {
    public static void main(String[] args) {
        Movie m1 = new Movie("MI-1-9", 9, new ArrayList<>());
        Movie m4 = new Movie("MI-4-10", 10, new ArrayList<>());
        Movie m2 = new Movie("MI-2-8", 8, new ArrayList<>());

        ArrayList a5 = new ArrayList();
        ArrayList a3 = new ArrayList();
        Movie m3 = new Movie("MI-3-7", 7, a3);

        a5.add(m1);
        a3.add(m4);
        a5.add(m3);
        a5.add(m2);
        Movie m5 = new Movie("MI-5", 6, a5);
        System.out.println(getTopRatedMovies(m5,5));
    }


    static ArrayList<Movie> getTopRatedMovies(Movie movie, int n) {

        PriorityQueue<Movie> heap = new PriorityQueue<>(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.rating - o1.rating;
            }
        });

        Stack<Movie> st = new Stack<>();
        st.push(movie);

        HashSet movieSet = new HashSet();

        while(!st.empty()) {

            Movie curMovie = st.pop();
            heap.add(curMovie);
            movieSet.add(curMovie);
            for (Movie similar : curMovie.similar) {
                if (!movieSet.contains(similar)) {
                    st.push(similar);
                }
            }
        }

        int i = 0;
        ArrayList <Movie> topRatedMovies = new ArrayList<>();
        while (!heap.isEmpty()){
            topRatedMovies.add(heap.peek());
            heap.remove();
            i++;
            if (i == n)
                break;
        }

        return topRatedMovies;
    }
}
