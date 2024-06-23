package online.ondemandtutor.be;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import online.ondemandtutor.be.validation.Review;
import online.ondemandtutor.be.validation.ReviewService;

public class viewTest {
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        reviewService = new ReviewService();
    }

    @Test
    public void testViewReview() {
        Review review = reviewService.viewReview(1);
        assertNotNull(review, "Review should not be null");
        assertEquals(1, review.getId(), "Review ID should be 1");
        assertEquals("Great Service!", review.getContent(), "Review content should be 'Great Service!'");
        assertEquals("a", review.getAuthor(), "Review author should be a");
    }

    @Test
    public void testViewNonExistentReview() {
        Review review = reviewService.viewReview(999);
        assertNull(review, "Review should be null for non-existent review ID");
    }

    @Test
    public void testViewReview2() {
        Review review = reviewService.viewReview(2);
        assertNotNull(review, "Review should not be null");
        assertEquals(2, review.getId(), "Review ID should be 2");
        assertEquals("Not bad.", review.getContent(), "Review content should be 'Not bad.'");
        assertEquals("b", review.getAuthor(), "Review author should be b");
    }

    @Test
    public void testViewReview3() {
        Review review = reviewService.viewReview(2);
        assertNotNull(review, "Review should not be null");
        assertEquals(2, review.getId(), "Review ID should be 2");
        assertEquals("Not bad.", review.getContent(), "Review content should be 'Not bad.'");
        assertNotEquals("a", review.getAuthor(), "Review author should be b");
    }

    @Test
    public void testViewReview4() {
        Review review = reviewService.viewReview(1);
        assertNotNull(review, "Review should not be null");
        assertEquals(1, review.getId(), "Review ID should be 1");
        assertNotEquals("Bad Service!", review.getContent(), "Review content should be 'Great Service!'");
        assertEquals("a", review.getAuthor(), "Review author should be a");
    }
}
