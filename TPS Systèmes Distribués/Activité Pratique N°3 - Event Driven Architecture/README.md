# TP3 : Event Driven Architecture

## Objectifs

- Créer une application qui permet de gérer des comptes respectant les patterns CQRS et Event Sourcing avec les Framework AXON et Spring Boot :
- Part 1 : https://www.youtube.com/watch?v=fqfg3sNIDDk

- Part 2 : https://www.youtube.com/watch?v=0MG8akH6cfU

- POC : https://www.youtube.com/watch?v=npP2GLYLW8c

## Partie 1 :

### 1. Créer un projet Spring Boot avec les dépendances suivantes :

- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Lombok
- MySQL Driver
- Axon Server Connector

### Architecture de l'application :

- `com.ettounani.pattern` :
- `commands` : contient les commandes
  - `aggregates` : contient les agrégats
  - `controllers` : contient les contrôleurs
- `query` : contient les requêtes
- `comman` : contient les elements communs
  - `commands` : contient les commandes
  - `events` : contient les événements
  - `dtos` : contient les dtos
  - `enums` : contient les enums
  - `query` : contient les requêtes

### 2. Créer `baseCommand` avec les attributs suivants :

```java
public abstract class BaseCommand<T> {
    @TargetAggregateIdentifier
    @Getter
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
```

### 2. Créer `CreateAccountCommand` et `CreditAccountCommand` et `DebitAccountCommand` :

```java
public class CreateAccountCommand extends BaseCommand<String> {

    @Getter
    private double initialBalance;
    @Getter
    private String currency;

    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }

}
```

```java
public class CreditAccountCommand extends BaseCommand<String> {

    private double amount;
    private String currency;

    public CreditAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
```

```java
public class DebitAccountCommand extends BaseCommand<String> {

    private double amount;
    private String currency;

    public DebitAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }

}
```
