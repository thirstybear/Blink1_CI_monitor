package uk.co.thirstybear.blink1service.blink1.actions;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class StreamSlurperTest {

    private static final int ENDOFSTREAM = -1;

    @Test
    public void consumesStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("1234567890".getBytes());
        final StreamSlurper streamSlurper = new StreamSlurper(inputStream);
        streamSlurper.run();
        assertThat(inputStream.read(), equalTo(ENDOFSTREAM));
    }

    @Test
    public void passesStreamValuesToListeners() {
        InputStream inputStream = new ByteArrayInputStream("123\n456\n789\n0".getBytes());

        StreamListenerStub listener = new StreamListenerStub();
        final StreamSlurper streamSlurper = new StreamSlurper(inputStream, listener);
        streamSlurper.run();

        assertThat(listener.hasReceivedValues(), contains("123", "456", "789", "0"));
    }

    private class StreamListenerStub implements StreamListener {
        private List<String> receivedValues = new ArrayList<>();

        @Override
        public void notify(String value) {
            receivedValues.add(value);
        }

        List<String> hasReceivedValues() {
            return receivedValues;
        }
    }
}