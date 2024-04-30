//package com.example.demo.ManagedBeans;
//
//import com.example.demo.entities.User;
//import com.example.demo.services.AuthService;
//import jakarta.faces.context.ExternalContext;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.context.Flash;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//class AuthTest {
//
//    @InjectMocks
//    private Auth auth;
//
//    @Mock
//    private AuthService authService;
//
////    @Mock
////    private ExternalContext externalContext;
////    @Mock
////    private Flash flash;
//
////    @Mock
////    private FacesContext context;
//
//    private User user;
//
////    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        user = new User(1L ,"NameTest" , "TestEmail@gmail.com" , "password221");
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
////    @Test
//    void register() {
//    }
//
////    @Test
//    void login() {
//        FacesContext context = ContextMocker.mockFacesContext();
//        Flash flash = mock(Flash.class);
//        ExternalContext ext = mock(ExternalContext.class);
//        when(context.getExternalContext()).thenReturn(ext);
//        when(ext.getFlash()).thenReturn(flash);
//
//        // Mock authService behavior
//        authService = mock(authService.getClass());
//        when(authService.login("NameTest", "TestEmail@gmail.com")).thenReturn(user);
//
//        // Call the method under test
//        Auth auth = new Auth();
//        String page = auth.login();
//
//        // Assert the outcome
//        assertEquals("home", page);
//    }
//}