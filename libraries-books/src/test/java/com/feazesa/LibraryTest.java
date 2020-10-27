package com.feazesa;

import com.feazesa.aggregate.Library;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;

class LibraryTest {

    private FixtureConfiguration<Library> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Library.class);
    }
}