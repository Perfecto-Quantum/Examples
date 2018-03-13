@clock
Feature: Step Interface Example Feature
  #Sample Test Scenario Description

  @worldClock
  Scenario: Set World Clock
    Given I start the Clock Application
    And I click the world clock tab
    When I add the location "Paris"
    Then I must have a clock for "Paris"
    And I delete the city "Paris"
