package myApp.security.authenticationProvider;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class AuthenticationProviderUserPasswordTest {

    @Inject
    EmbeddedServer embeddedServer;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testAuthenticationSuccess() {
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("admin", "adm");
        HttpRequest<?> request = HttpRequest.POST("/login", usernamePasswordCredentials);
        HttpStatus status = client.toBlocking().exchange(request, Argument.STRING, Argument.STRING).status();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    void testAuthenticationFailure() {
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("noadmin", "adm");
        HttpRequest<?> request = HttpRequest.POST("/login", usernamePasswordCredentials);
        HttpStatus status = client.toBlocking().exchange(request, Argument.STRING, Argument.STRING).status();

        assertEquals(HttpStatus.UNAUTHORIZED, status);
    }
}