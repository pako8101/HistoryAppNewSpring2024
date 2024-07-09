package com.example.historyappnew.service;

import HistoryAppGradleSecurity.model.entity.UserEnt;
import HistoryAppGradleSecurity.model.entity.UserRoleEnt;
import HistoryAppGradleSecurity.model.enums.UserRoleEnum;
import HistoryAppGradleSecurity.repository.UserRepository;
import HistoryAppGradleSecurity.service.ApplicationUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private ApplicationUserDetailsService serviceToTest;
@Mock
    private UserRepository userRepositorytoMock;

    @BeforeEach
    void setUp() {
        serviceToTest = new ApplicationUserDetailsService(
                userRepositorytoMock
        );
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("pako")
        );
    }

    @Test
    void testUserFoundException() {
        // Arrange
        UserEnt testUserEntity = createTestUser();
        when(userRepositorytoMock.findUserEntByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));

        // Act
        UserDetails userDetails =
                serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        // Assert
        assertNotNull(userDetails);
        assertEquals(
                testUserEntity.getUsername(),
                userDetails.getUsername(),
                "Username is not mapped.");

        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN),
                "The user is not admin");
        assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER),
                "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEnt createTestUser() {
        return new UserEnt()
                .setFullName("FullName")
                .setEmail("pako@softuni.bg")
                .setUsername("pako")
                .setPassword("pako")
                .setRoles(List.of(
                        new UserRoleEnt().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEnt().setRole(UserRoleEnum.USER)
                ));
    }
}
