package online.ondemandtutor.be.validation;


import java.util.HashMap;
import java.util.Map;

public class ReviewService {
    private Map<Integer, Review> reviews = new HashMap<>();

    public ReviewService() {
        reviews.put(1, new Review(1, "Great Service!", "a"));
        reviews.put(2, new Review(2, "Not bad.", "b"));
    }

    public Review viewReview(int id) {
        return reviews.get(id);
    }
}

