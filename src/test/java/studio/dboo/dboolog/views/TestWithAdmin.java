package studio.dboo.dboolog.views;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Test
@WithMockUser(username = "dboolog@gmail.com", roles = "ADMIN")
public @interface TestWithAdmin {
}
