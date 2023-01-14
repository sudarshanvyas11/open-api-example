@txn
Feature: Getting book details by Genre

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
    When Book is requested for Genre ROMANCE
    Then Response is Not Found

  Scenario: There are books in repository, it will return not found status if no book found for specified ID
    Given There are books available
      | Title                               | Genre  |
      | Defeating Shao Kahn - My memoirs    | ACTION |
      | Dating Kitana - Mistakes of my life | ACTION |
    When Book is requested for Genre ROMANCE
    Then Response is Not Found

  Scenario: There are books in repository, it will return the book if found for ID
    Given There are books available
      | Title                               | Genre  |
      | Defeating Shao Kahn - My memoirs    | ACTION |
      | Dating Kitana - Mistakes of my life | ACTION |
    When Book is requested for Genre ACTION
    Then Response is OK and books found
      | Title                               | Genre  | Author First Name | Author Last Name | Publisher Name | Publisher Email     | Publisher Website | Publisher Address - First Line | Publisher Address - Postcode | Publisher Address - City | Publisher Address - Country |
      | Defeating Shao Kahn - My memoirs    | ACTION | Liu               | Kang             | NetherRealm    | neatherrealm@mk.com | mk.com            | Eternal                        | UNDEAD                       | Damnation                | Outworld                    |
      | Dating Kitana - Mistakes of my life | ACTION | Liu               | Kang             | NetherRealm    | neatherrealm@mk.com | mk.com            | Eternal                        | UNDEAD                       | Damnation                | Outworld                    |