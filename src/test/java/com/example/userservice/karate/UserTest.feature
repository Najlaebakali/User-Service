Feature: User API Tests

  Background:
    * url 'http://localhost:8082/graphql'

  Scenario: Find All Users
    Given request { query: "{ findAllUsers { id nom prenom email role } }" }
    When method post
    Then status 200
    And match response.data.findAllUsers[*].id == '#notnull'
    And match response.data.findAllUsers[*].nom == '#string'
    And match response.data.findAllUsers[*].prenom == '#string'
    And match response.data.findAllUsers[*].email == '#string'
    And match response.data.findAllUsers[*].role == '#string'

  Scenario: Find User By ID
    Given request { query: "{ findUserById(id: 1) { id nom prenom email role } }" }
    When method post
    Then status 200
    And match response.data.findUserById.id == 1
    And match response.data.findUserById.nom == '#string'
    And match response.data.findUserById.prenom == '#string'
    And match response.data.findUserById.email == '#string'
    And match response.data.findUserById.role == '#string'

  Scenario: Save User
    Given request { query: "mutation { saveUser(user: { nom: \"John\" prenom: \"Doe\" email: \"john.doe@example.com\" password: \"password\" role: \"USER\" }) { id nom prenom email role } }" }
    When method post
    Then status 200
    And match response.data.saveUser.id == '#notnull'
    And match response.data.saveUser.nom == 'John'
    And match response.data.saveUser.prenom == 'Doe'
    And match response.data.saveUser.email == 'john.doe@example.com'
    And match response.data.saveUser.role == 'USER'

  Scenario: Update User
    Given request { query: "mutation { updateUser(id: 1, user: { nom: \"Jane\" prenom: \"Doe\" email: \"jane.doe@example.com\" password: \"newpassword\" role: \"USER\" }) { id nom prenom email role } }" }
    When method post
    Then status 200
    And match response.data.updateUser.id == 1
    And match response.data.updateUser.nom == 'Jane'
    And match response.data.updateUser.prenom == 'Doe'
    And match response.data.updateUser.email == 'jane.doe@example.com'
    And match response.data.updateUser.role == 'USER'

  Scenario: Delete User
    Given request { query: "mutation { deleteUser(id: 1) }" }
    When method post
    Then status 200
    And match response.data.deleteUser == true
