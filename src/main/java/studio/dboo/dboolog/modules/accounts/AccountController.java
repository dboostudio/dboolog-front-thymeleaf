package studio.dboo.dboolog.modules.accounts;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import studio.dboo.dboolog.modules.accounts.entity.Account;
import studio.dboo.dboolog.modules.annotation.RestControllerLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
@Api(tags = {"계정 CRUD"})
public class AccountController {

    private final AccountService accountService;

    @RestControllerLogger
    @GetMapping
    @ApiOperation(value = "getAccount", notes = "본인 계정 조회")
    public ResponseEntity<Account> getAccount(@CurrentAccount Account account){
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccount(account.getUserId()));
    }

    @Validated
    @RestControllerLogger
    @PostMapping
    @ApiOperation(value = "createAccount", notes = "계정 생성")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account, HttpServletRequest request) throws URISyntaxException {
        Account savedAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).location(new URI(request.getRequestURI() + "/" + savedAccount.getId())).body(savedAccount);
    }

    @RestControllerLogger
    @PutMapping
    @ApiOperation(value = "updateAccount", notes = "계정 정보 갱신")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account account){
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.updateAccount(account));
    }

    @RestControllerLogger
    @DeleteMapping
    @ApiOperation(value = "deleteAccount", notes = "계정 삭제")
    public ResponseEntity<String> deleteAccount(@CurrentAccount Account account){
        accountService.deleteAccount(account);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RestControllerLogger
    @PostMapping("/login")
    @ApiOperation(value = "login", notes = "로그인")
    public ResponseEntity<?> login(@RequestBody Account account){
        accountService.login(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @RestControllerLogger
    @GetMapping("/logout")
    @ApiOperation(value = "logout", notes = "로그아웃")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response, Account account){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
