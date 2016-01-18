Feature: Status matches build

  Scenario: Good builds are green
    Given a Jenkins server
    When the build is clean
    Then the light is green

