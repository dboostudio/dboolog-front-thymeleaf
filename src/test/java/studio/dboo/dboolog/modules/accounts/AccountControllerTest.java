package studio.dboo.dboolog.modules.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import studio.dboo.dboolog.modules.accounts.entity.Account;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountService accountService;
    @Autowired AccountRepository accountRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired ObjectMapper objectMapper;

    @BeforeEach
    public void createTestAccounts(){
        for(int i = 0; i<50; i++){
            createUser(
                    "test"+i,
                    "1234",
                    "test"+i+"@test.com"
            );
        }
    }
//
//    @DisplayName("??????????????? ?????????")
//    @Test
//    public void testEnvironment(){
//        System.out.println("??????!");
//    }
//
//    @DisplayName("????????????_??????")
//    @Test
//    public void getAccount_success() throws Exception {
//        // TODO - ???????????? ????????? ?????? ????????? ???????????? ???????????? ????????? ??????
//        String userId = "test1";
//        mockMvc.perform(get("/api/account").param("userId", userId))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("????????????_??????")
//    @Test
//    public void getAccount_fail() throws Exception {
//        String userId = "@!#$%%%";
//        mockMvc.perform(get("/api/account").param("userId", userId))
//                .andDo(print())
//                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
//    }
//
//    @DisplayName("????????????_??????")
//    @Test
//    public void createAccount_success() throws Exception {
//        Account account = Account.builder()
//                .userId("test")
//                .password("1234")
//                .build();
//        mockMvc.perform(post("/api/account")
//                .content("{\"userId\":\"dboo.studio\"" +
//                        ",\"email\":\"dboo.studio@gmail.com\"" +
//                        ",\"password\":\"1234\",\"groups\":[]}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isOk());
//        Optional<Account> byUserId = accountRepository.findByUserId(account.getUserId());
//    }
//
//    @DisplayName("????????????_??????(????????????)")
//    @Test
//    public void createAccount_fail() throws Exception {
//        Account account  = Account.builder()
//                .userId("test")
//                .password("1234")
//                .build();
//        String param = objectMapper.writeValueAsString(account);
//        mockMvc.perform(post("/api/account")
//                .content(param)
//                .contentType(MediaType.APPLICATION_JSON)
//                .with(csrf()))
//                .andDo(print())
//                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
//    }
//
//    @DisplayName("????????????_??????")
//    @Test
//    public void formLogin_success() throws Exception {
//        String password = "1234";
//        Account account = Account.builder()
//                .userId("test")
//                .password(password)
//                .build();
//
//        accountService.createAccount(account);
//
//        mockMvc.perform(formLogin().user(account.getUserId()).password(password))
//                .andDo(print())
//                .andExpect(authenticated());
//    }
//
//    @DisplayName("????????????_??????_??????????????????")
//    @Test
//    public void formLogin_fail_user_not_found() throws Exception {
//        String password = "1234";
//        Account account = Account.builder()
//                .userId("test")
//                .password(password)
//                .build();
//
//        accountService.createAccount(account);
//
//        mockMvc.perform(formLogin().user(account.getUserId() + "___").password(password))
//                .andDo(print())
//                .andExpect(unauthenticated());
//    }
//
//    @DisplayName("????????????_??????_?????????????????????")
//    @Test
//    public void formLogin_fail_wrong_password() throws Exception {
//        String password = "1234";
//        Account account = Account.builder()
//                .userId("test")
//                .password(password)
//                .build();
//
//        accountService.createAccount(account);
//
//        mockMvc.perform(formLogin().user(account.getUserId()).password(password + "1"))
//                .andDo(print())
//                .andExpect(unauthenticated());
//    }
//
//    @DisplayName("?????????_??????")
//    @Test
//    public void login_success() throws Exception {
//        Account account = Account.builder()
//                .userId("dboo")
//                .password("1234")
//                .role("USER")
//                .build();
//
//        accountService.createAccount(account);
//
//        mockMvc.perform(post("/api/account/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userId\":\"dboo\",\"password\":\"1234\"}")
//                .accept(MediaType.APPLICATION_JSON)
//                .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @DisplayName("?????????_??????_??????????????????")
//    @Test
//    public void login_fail_user_not_found() throws Exception {
//        Account account = Account.builder()
//                .userId("test")
//                .password("1234")
//                .role("USER")
//                .build();
//
//        accountService.createAccount(account);
//
//        mockMvc.perform(post("/api/account/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userId\":\"test1\",\"password\":\"1234\"}")
//                .with(csrf()))
//                .andDo(print())
//                .andExpect(status().isOk());;
//    }
//
//    @DisplayName("?????????_??????_?????????????????????")
//    @Test
//    public void login_fail_wrong_password() throws Exception {
//        String userId = "test";
//        String password = "123";
//        String email = "test@gmail.com";
//        this.createUser(userId, password, email);
//
//        mockMvc.perform(formLogin().user(userId).password(password+"0"))
//                .andDo(print())
//                .andExpect(unauthenticated());
//    }

    private Account createUser(String userId, String password, String email) {
        Account account = Account.builder()
                .userId(userId)
                .password(password)
                .role("USER")
                .build();
        accountService.createAccount(account);
        return account;
    }
}