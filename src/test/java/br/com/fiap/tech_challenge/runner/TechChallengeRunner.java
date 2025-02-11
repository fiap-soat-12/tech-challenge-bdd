package br.com.fiap.tech_challenge.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature")
@ConfigurationParameter(key = "cucumber.glue", value = "br.com.fiap.tech_challenge.steps")
@ConfigurationParameter(key = "cucumber.filter.tags", value = "@TechChallenge")
public class TechChallengeRunner {
}
