package com.github.j5ik2o.spetstore.domain.support.support

import org.specs2.mutable.Specification
import com.github.j5ik2o.spetstore.infrastructure.identifier.IdentifierService

class RepositoryOnMemorySpec extends Specification {

  val identifierService = IdentifierService()

  case class PersonRepositoryOnMemory(entities: Map[PersonId, Person] = Map.empty)
    extends RepositoryOnMemory[PersonId, Person](entities) with PersonRepository {

    type This = PersonRepository

    protected def createInstance(entities: Map[PersonId, Person]): This =
      new PersonRepositoryOnMemory(entities)

  }

  "repository" should {
    implicit val ctx = EntityIOContextOnMemory
    "store a entity" in {
      val personId = PersonId(identifierService.generate)
      val person = Person(personId, "Junichi", "Kato", None)
      PersonRepositoryOnMemory().
        store(person) must beSuccessfulTry.like {
        case (PersonRepositoryOnMemory(entities), _) =>
          entities.contains(personId) must beTrue
      }
    }

    "contains a entity" in {
      val personId = PersonId(identifierService.generate)
      val person = Person(personId, "Junichi", "Kato", None)
      val entities = Map(personId -> person)
      entities.contains(personId) must beTrue
      PersonRepositoryOnMemory(entities).
        existById(personId) must beSuccessfulTry.like {
        case result =>
          result must beTrue
      }
    }

    "get a entity" in {
      val personId = PersonId(identifierService.generate)
      val person = Person(personId, "Junichi", "Kato", None)
      PersonRepositoryOnMemory(Map(personId -> person)).
        resolveById(personId) must beSuccessfulTry.like {
        case entity =>
          entity must_== person
      }
    }

    "delete a entity" in {
      val personId = PersonId(identifierService.generate)
      val person = Person(personId, "Junichi", "Kato", None)
      PersonRepositoryOnMemory(Map(personId -> person)).
        deleteById(personId) must beSuccessfulTry.like {
        case (PersonRepositoryOnMemory(entities), _) =>
          entities.contains(personId) must beFalse
      }

    }

  }

}
