package online.ondemandtutor.be.service;

import online.ondemandtutor.be.entity.Account;
import online.ondemandtutor.be.entity.Category;
import online.ondemandtutor.be.entity.Subject;
import online.ondemandtutor.be.entity.TutorSchedule;
import online.ondemandtutor.be.model.SubjectRequest;
import online.ondemandtutor.be.repository.CategoryRepository;
import online.ondemandtutor.be.repository.SubjectRepository;
import online.ondemandtutor.be.repository.TutorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    private TutorScheduleRepository tutorScheduleRepository;

    //CRUD: read all
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    //CRUD: read by name
    public List<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }

    //CRUD: create
    public Subject createSubject(SubjectRequest subjectRequest) {
        Subject subject = new Subject();

        //tìm ID nhận vào từ SubjectRequest
        Category category = categoryRepository.findById(subjectRequest.getCategoryId());
        List<TutorSchedule> schedule = tutorScheduleRepository.findAllBySubjectId(subjectRequest.getScheduleId());

        subject.setName(subjectRequest.getName());
//        subject.setGrade(subjectRequest.getGrade());
        subject.setCategory(category);

        subject.setTutorSchedules(schedule);

        Subject newSubject = subjectRepository.save(subject);
        return newSubject;
    }

    //CRUD: update
    public Subject updateSubject(SubjectRequest subjectRequest, long id) {
        Subject subject = subjectRepository.findById(id);
        Category category = categoryRepository.findById(subjectRequest.getCategoryId());
        List<TutorSchedule> schedule = tutorScheduleRepository.findAllBySubjectId(subjectRequest.getScheduleId());

        subject.setName(subjectRequest.getName());
//        subject.setGrade(subjectRequest.getGrade());
        subject.setCategory(category);

        subject.setTutorSchedules(schedule);

        Subject newSubject = subjectRepository.save(subject);
        return newSubject;
    }

    //CRUD: delete
    public Subject deleteSubject(long id) {
        Subject subject = subjectRepository.findById(id);
        if (subject != null) {
            subject.setDeleted(true);
            Subject newSubject = subjectRepository.save(subject);
            return newSubject;
        }
        else {
            return null;
        }
    }
}
