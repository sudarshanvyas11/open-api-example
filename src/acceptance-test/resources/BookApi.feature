@txn
Feature: Getting book details

  Scenario: No books in repository returns not found status
    Given There are no books available
    When Book is requested for ID 1
    Then Response is 'Not found'

  Scenario: There are books in repository, it will return not found status if no book found for specified ID
    Given There are books available
    When Book is requested for ID 2
    Then Response is 'Not found'