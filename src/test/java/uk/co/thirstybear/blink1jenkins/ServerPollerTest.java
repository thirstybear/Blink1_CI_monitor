package uk.co.thirstybear.blink1jenkins;

import org.junit.Test;
import uk.co.thirstybear.blink1jenkins.blink1.Blink1Worker;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsView;
import uk.co.thirstybear.blink1jenkins.jenkins.JenkinsViewException;

import static org.mockito.Mockito.*;
import static uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState.FAIL;
import static uk.co.thirstybear.blink1jenkins.jenkins.JenkinsState.PASS;

public class ServerPollerTest {
    @Test
    public void turnsBlinkRedWhenBuildFails() throws Exception {
        JenkinsView mockJenkinsView = mock(JenkinsView.class);
        when(mockJenkinsView.status()).thenReturn(FAIL);

        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);

        new ServerPoller(mockJenkinsView, mockBlink1Worker).run();

        verify(mockBlink1Worker).buildFailed();
        verifyNoMoreInteractions(mockBlink1Worker);
    }

    @Test
    public void turnsBlinkGreenWhenBuildPasses() throws Exception {
        JenkinsView mockJenkinsView = mock(JenkinsView.class);
        when(mockJenkinsView.status()).thenReturn(PASS);

        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);

        new ServerPoller(mockJenkinsView, mockBlink1Worker).run();

        verify(mockBlink1Worker).buildPassed();
        verifyNoMoreInteractions(mockBlink1Worker);
    }

    @Test
    public void flashesBlinkContinuouslyWhenUrlInvalid() {
        JenkinsView mockJenkinsView = mock(JenkinsView.class);
        when(mockJenkinsView.status()).thenThrow(new JenkinsViewException(null));

        Blink1Worker mockBlink1Worker = mock(Blink1Worker.class);

        new ServerPoller(mockJenkinsView, mockBlink1Worker).run();

        verify(mockBlink1Worker).oops();
        verifyNoMoreInteractions(mockBlink1Worker);
    }
}