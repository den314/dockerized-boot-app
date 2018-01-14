package pl.desz.sample.keycloak;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.keycloak.OAuth2Constants.PASSWORD;

@Service
public class KeycloakService {

    private Keycloak keycloak;
    private RealmResource realm;

    @PostConstruct
    private void init() {
        keycloak = KeycloakBuilder.builder()
                    .serverUrl("http://192.168.99.100:18080/auth")
                    .grantType(PASSWORD)
                    .realm("master")
                    .clientId("admin-cli")
                    .username("admin")
                    .password("admin")
                    .build();

        realm = keycloak.realm("sample");
    }

    public void listUsers() {
        UserRepresentation user = realm.users().list().get(0);
    }

    public void doWhatever() {
        // do some cool stuff
    }
}
