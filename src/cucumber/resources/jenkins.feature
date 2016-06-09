Feature: Status matches build

  Scenario: Blink(1) light shows build status
    Given a Jenkins job at localhost:8899/job/test
    When the build is clean
    Then the light is green

    Given a Jenkins view at localhost:8899/view/test
    When the build is clean
    Then the light is green

    Given a Jenkins job at localhost:8888/job/test
    When the build is broken
    Then the light is red

    Given a Jenkins view at localhost:8899/view/test
    When the build is broken
    Then the light is red

    Given a Jenkins job at unknownhost:8080/myjob/tst
    Then the light flashes red