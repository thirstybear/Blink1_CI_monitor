Feature: Status matches build

  Scenario: Blink(1) light shows build status
    Given a Jenkins server at localhost:8899/job/test
    And the build is clean
    When the Blink server is queried
    Then the light is green

    Given a Jenkins server at localhost:8888/job/test
    And the build is broken
    When the Blink server is queried
    Then the light is red