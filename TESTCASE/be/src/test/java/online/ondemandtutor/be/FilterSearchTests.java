package online.ondemandtutor.be;
import online.ondemandtutor.be.model.SubjectRequest;
import online.ondemandtutor.be.model.FilterSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class FilterSearchTests {
    private FilterSearch filter;
    private List<SubjectRequest> subjects;

    @BeforeEach
    public void setUp() {
        filter = new FilterSearch();
        subjects = Arrays.asList(
                createSubject(1, "Math", 5, 101, "High School"),
                createSubject(2, "Science", 5, 102, "High School"),
                createSubject(3, "Math", 6, 101, "Middle School"),
                createSubject(4, "History", 6, 103, "Middle School")
        );
    }

    private SubjectRequest createSubject(long id, String name, int grade, long categoryId, String educationLevel) {
        SubjectRequest subject = new SubjectRequest();
        subject.setId(id);
        subject.setName(name);
        subject.setGrade(grade);
        subject.setCategoryId(categoryId);
        subject.setEducationLevel(educationLevel);
        return subject;
    }

    @Test
    public void testFilterByName() {
        List<SubjectRequest> result = filter.filterByName(subjects, "Math");
        assertEquals(2, result.size(), "There should be 2 subjects with name 'Math'");
    }

    @Test
    public void testFilterByGrade() {
        List<SubjectRequest> result = filter.filterByGrade(subjects, 5);
        assertEquals(2, result.size(), "There should be 2 subjects with grade 5");
    }

    @Test
    public void testFilterByCategoryId() {
        List<SubjectRequest> result = filter.filterByCategoryId(subjects, 101);
        assertEquals(2, result.size(), "There should be 2 subjects with categoryId 101");
    }

    @Test
    public void testFilterByEducationLevel() {
        List<SubjectRequest> result = filter.filterByEducationLevel(subjects, "High School");
        assertEquals(2, result.size(), "There should be 2 subjects with education level 'High School'");
    }
}
