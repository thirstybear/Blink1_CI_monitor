package uk.co.thirstybear.blink1service;

import org.junit.Test;
import uk.co.thirstybear.blink1service.blink1.Blink1Worker;
import uk.co.thirstybear.blink1service.jenkins.JenkinsView;

import static org.mockito.Mockito.*;
import static uk.co.thirstybear.blink1service.jenkins.JenkinsState.FAIL;
import static uk.co.thirstybear.blink1service.jenkins.JenkinsState.PASS;

public class ServerPollerTest {
    @Test
    public void whenBuildFailsTurnBlinkRed() throws Exception {
        JenkinsView mockJenkinsView = mock(JenkinsView.class);
        when(mockJenkinsView.status()).thenReturn(FAIL);

        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);

        new ServerPoller(mockJenkinsView, mockBlink1Worker).run();

        verify(mockBlink1Worker).buildFailed();
        verify(mockBlink1Worker, times(0)).buildPassed();
    }

    @Test
    public void whenBuildPassesTurnBlinkGreen() throws Exception {
        JenkinsView mockJenkinsView = mock(JenkinsView.class);
        when(mockJenkinsView.status()).thenReturn(PASS);

        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);

        new ServerPoller(mockJenkinsView, mockBlink1Worker).run();

        verify(mockBlink1Worker, times(0)).buildFailed();
        verify(mockBlink1Worker).buildPassed();
    }
}