Feature: Status matches build

  Scenario: Blink(1) light shows build status
    Given a Jenkins server
    When the build is clean
    Then the light is green

    Given a Jenkins server
    When the build is broken
    Then the light is red