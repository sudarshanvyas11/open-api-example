@txn
Feature: Getting book details

  Background:
    Given An Address
      | First Line | Post Code | City      | Country  |
      | Eternal    | UNDEAD    | Damnation | Outworld |
    And An Author
      | First Name | Last Name |
      | Liu        | Kang      |
    And A Publisher
      | Name        | Email               | Website |
      | NetherRealm | neatherrealm@mk.com | mk.com  |

  Scenario: No books in repository returns not found status
    Given There are no books available
    When Book is requested for ID 1
    Then Response is Not Found

  Scenario: There are books in repository, it will return not found status if no book found for specified ID
    Given There are books available
      | Title                            | Genre   |
      | Defeating Shao Kahn - My memoirs | ROMANCE |
    When Book is requested for ID 223567567
    Then Response is Not Found