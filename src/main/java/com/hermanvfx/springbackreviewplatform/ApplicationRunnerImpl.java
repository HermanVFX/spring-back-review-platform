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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CommentaryRepository commentaryRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Faker faker = new Faker();

        var userAdminMock = User.builder()
                .firstName("Райан")
                .lastName("Гослинг")
                .avatar("https://cdn.kanobu.ru/c/9d4136be5e203d48b7aeb2a11a805056/270x200/cdn.kanobu.ru/articles/pics/tmp/images/2023/1/27/46743968-e2f1-496a-bb49-08b8110d68ac.jpg")
                .password(encoder.encode("admin"))
                .email("admin")
                .id(UUID.fromString("56c55332-d701-11ed-afa1-0242ac120002"))
                .create(LocalDate.now())
                .role(Role.ADMIN).build();
        userRepository.save(userAdminMock);

        for (int i = 0; i < 20; i++) {
            User userNoAdmin = new User();
            userNoAdmin.setFirstName(faker.name().firstName());
            userNoAdmin.setSpecialities(Speciality.FRONTEND);
            userNoAdmin.setLastName(faker.name().lastName());
            userNoAdmin.setEmail(faker.name().username() + "@mail.ru");
            userNoAdmin.setCreate(LocalDate.now());
            userNoAdmin.setPassword(encoder.encode(faker.crypto().sha512()));
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
            user2.setPassword(encoder.encode(faker.crypto().sha512()));
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
            review.setTime(OffsetDateTime.now());
            review.setStudent(user);
            review.setReviewer(user2);
            review.setSpeciality(Speciality.FRONTEND);
            review.setLink(faker.avatar().image());
            review.setStatus(StatusReview.TOBE);
            review.setCreate(LocalDate.now());
            log.info(" -- Review :" + review.getTheme() + " for user " + user.getFirstName() + " was added");
            reviewRepository.save(review);

        }
    }
}
