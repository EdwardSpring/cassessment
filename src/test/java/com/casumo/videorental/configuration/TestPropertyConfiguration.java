package com.casumo.videorental.configuration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration
@TestPropertySource(locations = { "classpath:test.properties" })
public class TestPropertyConfiguration {

}
