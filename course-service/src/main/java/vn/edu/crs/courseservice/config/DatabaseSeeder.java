package vn.edu.crs.courseservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.crs.courseservice.entity.Course;
import vn.edu.crs.courseservice.repository.CourseRepository;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DatabaseSeeder(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() < 12) {
            courseRepository.deleteAll();
            Course course1 = new Course(null, "Lap trinh Java co ban", 3, 40, 12);
            Course course2 = new Course(null, "Co so du lieu", 4, 35, 0);
            Course course3 = new Course(null, "Lap trinh Web nang cao", 3, 30, 15);
            Course course4 = new Course(null, "Cau truc du lieu va Giai thuat", 4, 50, 4);
            Course course5 = new Course(null, "Kien truc phan mem", 3, 40, 20);
            Course course6 = new Course(null, "An toan bao mat thong tin", 3, 30, 0);
            Course course7 = new Course(null, "Tri tue nhan tao", 4, 25, 5);
            Course course8 = new Course(null, "Phat trien ung dung di dong", 3, 35, 10);
            Course course9 = new Course(null, "Kiem thu phan mem", 3, 40, 18);
            Course course10 = new Course(null, "Mang may tinh", 3, 45, 2);
            Course course11 = new Course(null, "He dieu hanh", 3, 40, 1);
            Course course12 = new Course(null, "Phan tich va Thiet ke he thong", 3, 35, 7);
            courseRepository.saveAll(List.of(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10, course11, course12));
        }
    }
}
