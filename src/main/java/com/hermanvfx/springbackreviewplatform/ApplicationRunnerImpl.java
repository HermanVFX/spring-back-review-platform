package com.hermanvfx.springbackreviewplatform;

import com.github.javafaker.Faker;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.entity.User;
import com.hermanvfx.springbackreviewplatform.entity.enums.Role;
import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import com.hermanvfx.springbackreviewplatform.repository.CommentaryRepository;
import com.hermanvfx.springbackreviewplatform.repository.CompanyRepository;
import com.hermanvfx.springbackreviewplatform.repository.ReviewRepository;
import com.hermanvfx.springbackreviewplatform.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CommentaryRepository commentaryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Faker faker = new Faker();

//        Company company1 = new Company();
//        company1.setName(faker.company().name());
//        company1.setCreate(LocalDate.now());
//        company1.setRating(4.4);
//        companyRepository.save(company1);
//
//        Company company2 = new Company();
//        company2.setName(faker.company().name());
//        company2.setCreate(LocalDate.now());
//        company2.setRating(4.0);
//        companyRepository.save(company2);
//
//        Company company3 = new Company();
//        company3.setName(faker.company().name());
//        company3.setCreate(LocalDate.now());
//        company3.setRating(5.0);
//        companyRepository.save(company3);
//
//        Company company4 = new Company();
//        company4.setName(faker.company().name());
//        company4.setCreate(LocalDate.now());
//        company4.setRating(1.1);
//        companyRepository.save(company4);

        for (int i = 0; i < 20; i++) {
            User userNoAdmin = new User();
            userNoAdmin.setFirstName(faker.name().firstName());
            userNoAdmin.setSpecialities(Speciality.FRONTEND);
            userNoAdmin.setLastName(faker.name().lastName());
            userNoAdmin.setEmail(faker.name().username() + "@mail.ru");
            userNoAdmin.setCreate(LocalDate.now());
            userNoAdmin.setPassword(faker.crypto().sha512());
            userNoAdmin.setRole(Role.USER);
            log.info(" -- User :" + userNoAdmin.getFirstName() + " " + userNoAdmin.getLastName() + " was added with role: " + userNoAdmin.getRole());
            userRepository.save(userNoAdmin);
        }
        for (int i = 0; i < 20; i++) {
            User user2 = new User();
            user2.setFirstName(faker.name().firstName());
            user2.setSpecialities(Speciality.FRONTEND);
            user2.setLastName(faker.name().lastName());
            user2.setEmail(faker.name().username() + "@mail.ru");
            user2.setCreate(LocalDate.now());
            user2.setPassword(faker.crypto().sha512());
            user2.setRole(Role.USER);
            log.info(" -- User :" + user2.getFirstName() + " " + user2.getLastName() + " was added with role: " + user2.getRole());
            userRepository.save(user2);

            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setSpecialities(Speciality.FRONTEND);
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.name().username() + "@mail.ru");
            user.setCreate(LocalDate.now());
            user.setPassword(faker.crypto().sha512());
            user.setRole(Role.USER);
            log.info(" -- User :" + user.getFirstName() + " " + user.getLastName() + " was added with role: " + user.getRole());
            userRepository.save(user);

            Review review = new Review();
            review.setTheme("Theme");
            review.setTime(LocalDate.now());
            review.setStudent(user);
            review.setReviewer(user2);
            review.setSpeciality(Speciality.FRONTEND);
            review.setLink(faker.avatar().image());
            review.setStatus(StatusReview.TOBE);
            review.setCreate(LocalDate.now());
            log.info(" -- Review :" + review.getTheme() + " for user " + user.getFirstName() + " was added");
            reviewRepository.save(review);

        }


//        for (int i = 0; i < 10; i++) {
//            User userAdmin = new User();
//            userAdmin.setFirstName(faker.name().firstName());
//            userAdmin.setSpecialities(Speciality.FRONTEND);
//            userAdmin.setLastName(faker.name().lastName());
//            userAdmin.setEmail(faker.name().username() + "@mail.ru");
//            userAdmin.setCreate(LocalDate.now());
//            userAdmin.setPassword(faker.crypto().sha512());
//            Set<Role> rolesWithAdminRole = new HashSet<>();
//            rolesWithAdminRole.add(roleRepository.findByRole("USER_ROLE")
//                    .orElseThrow(() -> new NotFoundException("Role: [USER_ROLE] does not found")));
//            rolesWithAdminRole.add(roleRepository.findByRole("ADMIN_ROLE")
//                    .orElseThrow(() -> new NotFoundException("Role: [ADMIN_ROLE] does not found")));
//            userAdmin.setRoles(rolesWithAdminRole);
//            log.info(" -- User :" + userAdmin.getFirstName() + " " + userAdmin.getLastName() + " was added with role: " + userAdmin.getRoles());
//
//            userRepository.save(userAdmin);
//        }
//
//        UUID companyId = companyRepository.findAll().iterator().next().getId();
//        List<Commentary> commentaries = new ArrayList<>();
//
//        List<User> users = userRepository.findAll();
//        users.forEach(user -> user.setCommentaries(commentaries.add(getRandomCommentaryForCompany(companyId)));
//
//    }
//
//    private Commentary getRandomCommentaryForCompany(UUID companyId) {
//        Faker faker = new Faker();
//        Commentary commentary = new Commentary();
//        commentary.setCreate(LocalDate.now());
//        commentary.setCompany(companyRepository.findById(companyId).orElseThrow());
//        commentary.setText("Товарищи! укрепление и развитие структуры играет важную роль в формировании позиций, занимаемых участниками в отношении поставленных задач. С другой стороны рамки и место обучения кадров представляет собой интересный эксперимент проверки систем массового участия. Разнообразный и богатый опыт постоянный количественный рост и сфера нашей активности позволяет выполнять важные задания по разработке системы обучения кадров, соответствует насущным потребностям. Значимость этих проблем настолько очевидна, что новая модель организационной деятельности требуют определения и уточнения систем массового участия.");
//        commentary.setDislikes(10);
//        commentary.setLikes(10);
//        return commentary;
//    }
//    private List<Company> addCommentaryForCompany(User user) {
//
//    }
    }
}
