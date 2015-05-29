package de.otto.edison.togglz;

import de.otto.edison.testsupport.applicationdriver.SpringTestBase;
import de.otto.edison.togglz.configuration.TogglzConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.togglz.core.manager.TogglzConfig;

import static de.otto.edison.testsupport.dsl.Then.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.hamcrest.core.Is.is;

public class DefaultTogglzConfigTest extends SpringTestBase {

    private TogglzConfig togglzConfig;

    @BeforeMethod
    public void setUp() throws Exception {
        togglzConfig = applicationContext().getBean(TogglzConfig.class);

    }

    @Test
    public void shouldCreateTogglzConfigBySpring() {
        assertThat(togglzConfig, is(not(nullValue())));
        assertThat(togglzConfig.getFeatureClass(), typeCompatibleWith(TogglzConfiguration.Features.class));
        assertThat(togglzConfig.getStateRepository(),is(not(nullValue())));
        assertThat(togglzConfig.getUserProvider(),is(not(nullValue())));
    }

    @Test
    public void shouldProvideToggleStateWhichIsActiveByDefaultInTests() {
        assertThat(TogglzConfiguration.Features.TEST.isActive(),is(true));
    }
}