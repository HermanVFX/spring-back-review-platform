package com.hermanvfx.springbackreviewplatform;

import com.github.javafaker.Faker;
import com.hermanvfx.springbackreviewplatform.entity.Commentary;
import com.hermanvfx.springbackreviewplatform.entity.Company;
import com.hermanvfx.springbackreviewplatform.entity.Role;
import com.hermanvfx.springbackreviewplatform.entity.User;
import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.repository.CommentaryRepository;
import com.hermanvfx.springbackreviewplatform.repository.CompanyRepository;
import com.hermanvfx.springbackreviewplatform.repository.RoleRepository;
import com.hermanvfx.springbackreviewplatform.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    private final CommentaryRepository commentaryRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Faker faker = new Faker();

        Role userRole = new Role();
        Role adminRole = new Role();

        userRole.setCreate(LocalDate.now());
        userRole.setRole("USER_ROLE");
        roleRepository.save(userRole);
        log.info(" -- Role :" + userRole + " was added");

        adminRole.setCreate(LocalDate.now());
        adminRole.setRole("ADMIN_ROLE");
        roleRepository.save(adminRole);
        log.info(" -- Role :" + adminRole + " was added");

        User userNoAdmin = new User();
        userNoAdmin.setFirstName(faker.name().firstName());
        userNoAdmin.setSpecialities(Speciality.FRONTEND);
        userNoAdmin.setLastName(faker.name().lastName());
        userNoAdmin.setEmail("user@mail.ru");
        userNoAdmin.setCreate(LocalDate.now());
        userNoAdmin.setPassword(encoder.encode("user"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRole("USER_ROLE")
                .orElseThrow(() -> new NotFoundException("Role: [USER_ROLE] does not found")));
        userNoAdmin.setRoles(roles);
        log.info(" -- User :" + userNoAdmin.getFirstName() + " " + userNoAdmin.getLastName() + " was added with role: " + userNoAdmin.getRoles());
        userRepository.save(userNoAdmin);

    }
}
