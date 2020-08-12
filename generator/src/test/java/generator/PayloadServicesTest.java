package generator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PayloadServicesTest {
    @Test
    void jsonTemplateForClientPayloadsShouldBeRead() {
        // give
        PayloadServices services = new PayloadServices();
        int expectedSize = 1827; // there is no reason to change json template
        // when
        String contents = services.getClientPayloadsTemplate();
        // then
        assertThat(contents, is(notNullValue()));
        assertThat(contents, hasLength(expectedSize));
    }
}