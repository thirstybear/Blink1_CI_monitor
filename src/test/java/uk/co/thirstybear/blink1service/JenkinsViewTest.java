package uk.co.thirstybear.blink1service;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static uk.co.thirstybear.blink1service.JenkinsState.FAIL;
import static uk.co.thirstybear.blink1service.JenkinsState.PASS;

public class JenkinsViewTest {
    @Test
    public void whenViewFailingReturnsFail() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);
        when(mockUrlReader.get()).thenReturn(new JenkinsViewResponse().getResponse("red"));

        final JenkinsView jenkinsView = new JenkinsView(mockUrlReader);

        assertThat(jenkinsView.status(), is(FAIL));
    }

    @Test
    public void whenViewPassingReturnsPass() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);
        when(mockUrlReader.get()).thenReturn(new JenkinsViewResponse().getResponse("blue"));

        final JenkinsView jenkinsView = new JenkinsView(mockUrlReader);

        assertThat(jenkinsView.status(), is(PASS));
    }


    @Test
    public void whenSingleJobFailingReturnsFail() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);
        when(mockUrlReader.get()).thenReturn(new JenkinsSingleJobResponse().getResponse("red"));

        final JenkinsView jenkinsView = new JenkinsView(mockUrlReader);

        assertThat(jenkinsView.status(), is(FAIL));
    }

    @Test
    public void whenSingleJobPassingReturnsPass() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);
        when(mockUrlReader.get()).thenReturn(new JenkinsSingleJobResponse().getResponse("blue"));

        final JenkinsView jenkinsView = new JenkinsView(mockUrlReader);

        assertThat(jenkinsView.status(), is(PASS));
    }


    @Test(expected = JenkinsViewException.class)
    public void throwsExceptionWhenProblemEncountered() throws IOException {
        UrlReader mockUrlReader = mock(UrlReader.class);
        when(mockUrlReader.get()).thenReturn("make the code blow up!");

        final JenkinsView jenkinsView = new JenkinsView(mockUrlReader);

        jenkinsView.status();
    }
}