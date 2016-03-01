Feature: Status matches build

  Scenario: Blink(1) light shows build status
    Given a Jenkins job at localhost:8899/job/test
    And the build is clean
    When the Blink server is queried
    Then the light is green

    Given a Jenkins view at localhost:8899/view/test
    And the build is clean
    When the Blink server is queried
    Then the light is green

    Given a Jenkins job at localhost:8888/job/test
    And the build is broken
    When the Blink server is queried
    Then the light is red

    Given a Jenkins view at localhost:8899/view/test
    And the build is broken
    When the Blink server is queried
    Then the light is red

    Given a Jenkins job at unknownhost:8080/myjob/tst
    When the Blink server is queried
    Then the light flashes red