# Spring Modulith Sample - Architecture Modulaire en Kotlin

Exemple d'application utilisant **Spring Modulith** pour démontrer l'implémentation d'un **monolithe modulaire** avec Kotlin. Ce projet illustre les spécificités et bonnes pratiques de Spring Modulith dans un contexte médical/hospitalier.

## 🏗️ Architecture Spring Modulith

### Modules Métier
L'application est organisée en **4 modules Spring Modulith** :

- **`core`** - Utilitaires partagés, configuration, exceptions communes
- **`hospital`** - Gestion des hôpitaux, médecins et traitements  
- **`doctor`** - Gestion des docteurs, spécialités et qualifications (dépend de hospital)
- **`patient`** - Gestion des patients, dossiers médicaux et médicaments (écoute les événements hospital)

### Principes Démontrés

#### 1. **Modules par Package** 
```kotlin
@ApplicationModule(displayName = "hospital")
class ModuleMetadata  // dans hospital/ModuleMetadata.kt
```
Chaque package principal (`com.ggardet.hospital`, `com.ggardet.patient`, etc.) constitue un module Spring Modulith avec ses propres responsabilités.

#### 2. **Faible Couplage entre Modules**
- **Architecture Hexagonale** par module : `internal/` (domaine, entités, repositories) vs `spi/` (DTOs, interfaces publiques)
- **Interfaces nommées** pour contrôler l'exposition :
```kotlin
@NamedInterface("core-exception-spi")  
open class InternalNotFoundException(message: String) : RuntimeException(message)
```

#### 3. **Communication Événementielle Asynchrone**
```kotlin
@ApplicationModuleListener
fun onRemovedHospitalEvent(event: HospitalDeletedEvent) {
    // Traitement asynchrone entre modules
}
```
Les modules communiquent via des **événements Spring Application Events**, permettant un découplage temporel.

#### 4. **Tests de Conformité Modulaire**
```kotlin
@Test
fun shouldBeCompliant() {
    ApplicationModules.of(SpringModulithApplication::class.java).verify()
}
```
Les tests vérifient automatiquement le respect des **frontières modulaires** et des règles de dépendances.

#### 5. **Documentation Automatique**
```kotlin
@Test  
fun writeDocumentationSnippets() {
    Documenter(modules, "doc")
        .writeModuleCanvases()
        .writeModulesAsPlantUml()
        .writeIndividualModulesAsPlantUml()
}
```
Génération automatique de :
- **Diagrammes PlantUML** des modules et leurs relations
- **Module Canvas** pour chaque module (dans `doc/`)

## 🔧 Spécificités Techniques

### Kotlin + Spring Modulith
- **Kotlin 2.1.0** (dernière version) avec Spring Boot 3.3.1
- **Classes `ModuleMetadata.kt`** remplaçant les `package-info.java`
- **Architecture hexagonale** préservée en Kotlin
- **Mapping manuel** (MapStruct remplacé par des `@Component` Spring)

### Base de Données Multi-Schema
- **PostgreSQL** avec schémas dédiés par module (`doctors`, `hospitals`, `patients`, `services`)
- **Flyway per-module** : migrations dans `src/main/resources/db/migration/{module}/`
- **JPA/Hibernate** avec entités Kotlin

### Gestion des Événements
- **Publication d'événements** via `ApplicationEventPublisher`
- **Traitement asynchrone** avec `@ApplicationModuleListener` 
- **Monitoring des événements** avec `EventPublications`

## 🚀 Utilisation

### Prérequis
- **Java 21**
- **Docker** (pour PostgreSQL)
- **Maven 3.9+**

### Commandes

```bash
# Compilation
mvn clean compile

# Tests (incluant tests de modularité)
mvn test

# Test spécifique de conformité modulaire  
mvn test -Dtest=ModularityTests

# Démarrage de l'application
mvn spring-boot:run

# Génération de documentation
mvn test -Dtest=ModularityTests#writeDocumentationSnippets
```

### Docker
Les conteneurs PostgreSQL démarrent **automatiquement** via `spring-boot-docker-compose`.

## 📊 Documentation Générée

Après exécution des tests, consulter :
- `doc/components.puml` - Vue d'ensemble des modules
- `doc/module-{nom}.puml` - Diagrammes par module  
- `doc/module-{nom}.adoc` - Canvas détaillé par module

## 🎯 Points Clés Démontrés

1. **Modularité sans Microservices** - Monolithe structuré avec frontières claires
2. **Évolutivité** - Modules peuvent être extraits en microservices si besoin
3. **Communication Découplée** - Événements asynchrones entre modules  
4. **Validation Architecturale** - Tests automatisés de conformité
5. **Documentation Vivante** - Diagrammes générés automatiquement
6. **Kotlin First** - Démonstration complète en Kotlin moderne

Ce projet illustre parfaitement comment **Spring Modulith** permet de structurer une application complexe tout en maintenant la simplicité opérationnelle d'un monolithe.