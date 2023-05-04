package com.hermanvfx.springbackreviewplatform;

import com.github.javafaker.Faker;
import com.hermanvfx.springbackreviewplatform.entity.*;
import com.hermanvfx.springbackreviewplatform.entity.enums.Role;
import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import com.hermanvfx.springbackreviewplatform.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final CommentaryRepository commentaryRepository;
    private final InterviewRepository interviewRepository;
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
//                .id(UUID.fromString("56c55332-d701-11ed-afa1-0242ac120002"))
                .create(OffsetDateTime.now())
                .role(Role.ADMIN).build();
        userRepository.save(userAdminMock);

        var userAdminMock2 = User.builder()
                .firstName("Артём")
                .lastName("Майоров")
                .avatar("https://sun9-80.userapi.com/s/v1/if1/q0n6zcy7y_KOUBqaeebG2tEldbEFsT8Q14Sl-JClhSUwtcOZqrFpEHb3XHu9LmrkxF7wacPs.jpg?size=200x200&quality=96&crop=82,82,656,656&ava=1")
                .password(encoder.encode("admin2"))
                .email("admin2")
                .id(UUID.fromString("56c55332-d701-11ed-afa1-0242ac120003"))
                .create(OffsetDateTime.now())
                .role(Role.ADMIN)
                .specialities(Speciality.FRONTEND)
                .build();
        userRepository.save(userAdminMock2);

        var userAdminMock3 = User.builder()
                .firstName("Дима")
                .lastName("Ленивый")
                .avatar("https://tl.rulate.ru/i/addition/Book/162004/1620043/main_608fe3e3d315f.jpg")
                .password(encoder.encode("admin4"))
                .email("admin4")
                .id(UUID.fromString("56c55332-d701-11ed-afa1-0242ac120004"))
                .create(OffsetDateTime.now())
                .role(Role.ADMIN)
                .specialities(Speciality.FRONTEND)
                .build();
        userRepository.save(userAdminMock3);

        var userAdminMock5 = User.builder()
                .firstName("Александр")
                .lastName("Красавцев")
                .avatar("https://i.ytimg.com/vi/p3aOgllm0ck/maxresdefault.jpg")
                .password(encoder.encode("nagibator228"))
                .email("nagibator3000")
                .id(UUID.fromString("16e4d516-e9d1-11ed-a05b-0242ac120003"))
                .create(OffsetDateTime.now())
                .role(Role.ADMIN)
                .specialities(Speciality.FRONTEND)
                .build();
        userRepository.save(userAdminMock5);

        var userAdminMock6 = User.builder()
                .firstName("Luche")
                .lastName("Cinema")
                .avatar("https://steamuserimages-a.akamaihd.net/ugc/856104172566104759/1369BFF9A3B63B57C9AB28E2C219F6692BD32D6C/")
                .password(encoder.encode("UOOvn34"))
                .email("shipoklyk")
                .id(UUID.fromString("16e4d516-e9d1-11ed-a05b-0242ac120003"))
                .create(OffsetDateTime.now())
                .role(Role.ADMIN)
                .specialities(Speciality.FRONTEND)
                .build();
        userRepository.save(userAdminMock5);

        for (int i = 0; i < 20; i++) {
            User userNoAdmin = new User();
            userNoAdmin.setFirstName(faker.name().firstName());
            userNoAdmin.setSpecialities(Speciality.FRONTEND);
            userNoAdmin.setLastName(faker.name().lastName());
            userNoAdmin.setEmail(faker.name().username() + "@mail.ru");
            userNoAdmin.setCreate(OffsetDateTime.now());
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
            user2.setCreate(OffsetDateTime.now());
            user2.setPassword(encoder.encode(faker.crypto().sha512()));
            user2.setRole(Role.USER);
            log.info(" -- User :" + user2.getFirstName() + " " + user2.getLastName()
                    + " was added with role: " + user2.getRole());
            userRepository.save(user2);

            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setSpecialities(Speciality.FRONTEND);
            user.setLastName(faker.name().lastName());
            user.setEmail(faker.name().username() + "@mail.ru");
            user.setCreate(OffsetDateTime.now());
            user.setPassword(faker.crypto().sha512());
            user.setRole(Role.USER);
            log.info(" -- User :" + user.getFirstName() + " " + user.getLastName()
                    + " was added with role: " + user.getRole());
            userRepository.save(user);

            Review review = new Review();
            review.setTheme("Theme");
            review.setTime(OffsetDateTime.now());
            review.setStudent(user);
            review.setReviewer(user2);
            review.setSpeciality(Speciality.FRONTEND);
            review.setLink(faker.avatar().image());
            review.setStatus(StatusReview.TOBE);
            review.setCreate(OffsetDateTime.now());
            log.info(" -- Review :" + review.getTheme() + " for user " + user.getFirstName() + " was added");
            reviewRepository.save(review);

            Review review2 = new Review();
            review2.setTheme("Theme");
            review2.setTime(OffsetDateTime.now());
            review2.setStudent(user);
            review2.setReviewer(user2);
            review2.setSpeciality(Speciality.FRONTEND);
            review2.setLink(faker.avatar().image());
            review2.setStatus(StatusReview.PASSED);
            review2.setCreate(OffsetDateTime.now());
            log.info(" -- Review :" + review2.getTheme() + " for user " + user.getFirstName() + " was added");
            reviewRepository.save(review2);

            Review review3 = new Review();
            review3.setTheme("Theme");
            review3.setTime(OffsetDateTime.now());
            review3.setStudent(user);
            review3.setReviewer(user2);
            review3.setSpeciality(Speciality.FRONTEND);
            review3.setLink(faker.avatar().image());
            review3.setStatus(StatusReview.CANCELED);
            review3.setCreate(OffsetDateTime.now());
            log.info(" -- Review :" + review3.getTheme() + " for user " + user.getFirstName() + " was added");
            reviewRepository.save(review3);

        }

        for (int i = 0; i < 5; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setJobLink("https://www.youtube.com/watch?v=_suZGUbIvvM&ab_channel=%2aDOCPRODUCTION");
            company.setRating(10.0);
            company.setCreate(OffsetDateTime.now());

            Commentary commentary = new Commentary();
            commentary.setText(faker.lorem().characters(24, 499));
            commentary.setLikes(150);
            commentary.setDislikes(23);
            commentary.setCreate(OffsetDateTime.now());
            commentary.setUser(userAdminMock);
            userAdminMock.setCommentaries(List.of(commentary));

            commentary.setCompany(company);
            company.setCommentaries(List.of(commentary));

            userRepository.save(userAdminMock);
            companyRepository.save(company);
            commentaryRepository.save(commentary);
            log.info(" -- Company : " + company.getName() + " was added");
        }

        Company companyMoc = new Company();
        companyMoc.setName(faker.company().name());
        companyMoc.setJobLink("https://www.youtube.com/watch?v=_suZGUbIvvM&ab_channel=%2aDOCPRODUCTION");
        companyMoc.setRating(10.0);
        companyMoc.setCreate(OffsetDateTime.now());
        companyRepository.save(companyMoc);
        log.info(" -- CompanyMoc : " + companyMoc.getName() + " was added");


        for (int i = 0; i < 10; i++) {
            Interview interview = new Interview();
            interview.setJobTitle(faker.job().title());
            interview.setJobLink("https://www.youtube.com/watch?v=_suZGUbIvvM&ab_channel=%2aDOCPRODUCTION");
            interview.setDescription(faker.lorem().word());
            interview.setMoney(300);
            interview.setVideoLink("https://www.youtube.com/watch?v=dQw4w9WgXcQ&feature=youtu.be&ab_channel=RickAstley");
            interview.setCompany(companyMoc);
            interview.setCreate(OffsetDateTime.now());
            interview.setStructure("Название компании");
            interview.setSubstructure("Название подразделения компании");
            interview.setDate(OffsetDateTime.now());

            companyMoc.setInterviews(List.of(interview));

            List<Commentary> commentaryList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Commentary commentary = new Commentary();
                commentary.setText(faker.lorem().characters(24, 499));
                commentary.setLikes(150);
                commentary.setDislikes(23);
                commentary.setCreate(OffsetDateTime.now());
                commentary.setUser(userAdminMock);
                userAdminMock.setCommentaries(List.of(commentary));
                commentary.setInterview(interview);
                interview.setCommentaries(List.of(commentary));
                commentaryList.add(commentary);
            }

            companyRepository.save(companyMoc);
            interview.setUser(userAdminMock);
            userAdminMock.setInterviews(List.of(interview));

            userRepository.save(userAdminMock);
            interviewRepository.save(interview);
            commentaryList.forEach(c -> {
                commentaryRepository.save(c);
                log.info(" -- Commentary : " + c.getUser().getFirstName() + " was added");
            });

            log.info(" -- Interview : " + interview.getJobTitle() + " was added");
        }
    }
}