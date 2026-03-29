# 📚 Application de Gestion des Notes Académiques

> Mini-projet réalisé dans le cadre du programme **Génie Informatique Embarquée**  
> École Supérieure de Technologie — Université Mohammed Premier, Oujda  
> Année universitaire : **2023 / 2024**

---

## 👥 Réalisé par

| Nom | Filière |
|-----|---------|
| Safae El ATTAR | Génie Informatique Embarquée |
| Hafsa EL JAROUDI | Génie Informatique Embarquée |
| Jihane BOURAS | Génie Informatique Embarquée |

**Encadrant universitaire :** M. Mohcine Kodad

---

## 📌 Description du projet

Cette application de gestion des notes académiques a été développée pour offrir aux étudiants une **visibilité transparente sur leurs performances scolaires**, tout en permettant aux enseignants de gérer efficacement les résultats académiques.

Elle place la **simplicité d'utilisation**, la **convivialité** et la **sécurité des données** au cœur de ses priorités, et favorise une communication fluide entre enseignants et étudiants.

---

## 🎯 Objectifs

- Fournir aux étudiants une interface intuitive pour consulter leurs notes
- Permettre aux enseignants de saisir, modifier et supprimer les résultats
- Instaurer un climat de confiance grâce à la transparence des données
- Faciliter le suivi académique par matière, semestre et secteur
- Encourager un dialogue constructif autour des résultats académiques

---

## 👤 Acteurs et rôles

### 🧑‍🏫 Enseignant
- Se connecte via une authentification sécurisée
- Saisit, modifie et supprime les notes des étudiants
- Gère les profils étudiants (ajout, mise à jour)
- Suit les performances académiques en temps réel

### 🎓 Étudiant
- Se connecte via une authentification sécurisée
- Consulte ses notes par cours/matière
- Accède rapidement à son bilan académique

---

## ✨ Fonctionnalités clés

- 🔐 **Système de connexion sécurisé** : authentification distincte pour enseignants et étudiants
- ✏️ **Saisie des notes** : interface ergonomique avec validation instantanée
- 📊 **Consultation des notes** : tableau de bord clair par cours et par étudiant
- 🗃️ **Gestion complète des données** : ajout, modification, suppression, chargement
- 🔒 **Sécurité** : requêtes préparées (PreparedStatements) pour prévenir les injections SQL

---

## 🏗️ Architecture technique

| Élément | Technologie |
|--------|-------------|
| Langage principal | Java |
| Interface graphique | JavaFX |
| Base de données | MySQL |
| Connectivité BDD | JDBC |
| IDE | Eclipse |
| Administration BDD | PhpMyAdmin |

### Schéma des classes principales

```
Main
 ├── TeacherPage         → Authentification enseignant
 ├── StudentPage         → Authentification étudiant
 ├── AcceuilItecPage     → Tableau de bord enseignant (CRUD notes)
 ├── AcceuilStdPage      → Tableau de bord étudiant (consultation)
 ├── StudentData         → Modèle de données étudiant
 └── DatabaseConnection  → Gestion de la connexion MySQL
```

---

## 🧩 Conception UML

Le projet a été modélisé avec le langage **UML** via StarUML :

- **Diagramme de cas d'utilisation** : interactions entre Professeur, Étudiant et le système (Login, gérer les notes, consulter)
- **Diagramme de classes** : structure statique des classes, attributs, méthodes et relations

---

## 🖼️ Aperçu de l'interface

| Écran | Description |
|-------|-------------|
| Page principale | Choix du rôle : Étudiant ou Enseignant |
| Teacher Login | Authentification de l'enseignant (email + mot de passe) |
| Saisie des notes | Formulaire de gestion des notes avec tableau récapitulatif |
| Student Login | Authentification de l'étudiant |
| Consultation notes | Affichage des cours et notes associées |

---

## 🚀 Lancer le projet

### Prérequis

- Java JDK 17+
- JavaFX SDK 21
- MySQL Server
- Eclipse IDE (ou IntelliJ)
- Pilote JDBC MySQL (`mysql-connector-java`)

### Étapes

```bash
# 1. Cloner le dépôt
git clone https://github.com/votre-username/gestion-notes-javafx.git

# 2. Importer le projet dans Eclipse
# File > Import > Existing Projects into Workspace

# 3. Configurer la base de données
# - Installer XAMPP et démarrer Apache + MySQL
# - Ouvrir PhpMyAdmin : http://localhost/phpmyadmin
# - Créer une nouvelle base de données (ex: "databaseapp")
# - Cliquer sur "Importer" et sélectionner le fichier : database/databaseapp.sql
# - Mettre à jour les identifiants dans DatabaseConnection.java

# 4. Ajouter JavaFX au Build Path
# Project > Properties > Java Build Path > Add External JARs

# 5. Lancer la classe Main.java
```

### Configuration de la connexion (DatabaseConnection.java)

```java
private static final String URL = "jdbc:mysql://localhost:3306/votre_base";
private static final String USER = "root";
private static final String PASSWORD = "votre_mot_de_passe";
```

---

## 📁 Structure du projet

```
gestion-notes-javafx/
├── src/
│   ├── Main.java
│   ├── TeacherPage.java
│   ├── StudentPage.java
│   ├── AcceuilItecPage.java
│   ├── AcceuilStdPage.java
│   ├── StudentData.java
│   └── DatabaseConnection.java
├── database/
│   └── databaseapp.sql
├── rapport/
│   └── rapport_gestion_notes.pdf
└── README.md
```

---

## 🔄 Rétrospective

**Points forts :**
- Interface utilisateur conviviale et fluide avec JavaFX
- Bonne cohésion d'équipe et atteinte des jalons dans les délais
- Sécurisation des accès et des requêtes SQL

**Défis rencontrés :**
- Gestion de la base de données et intégration de fonctionnalités avancées
- Nécessité d'une communication transparente et d'une planification rigoureuse

---

## 📚 Références

- [Eclipse IDE](https://www.eclipse.org/)
- [Documentation Java](https://docs.oracle.com/en/java/)
- [JavaFX 21 Documentation](https://openjfx.io/)
- [StarUML](https://staruml.io)
- PhpMyAdmin — Administration base de données MySQL

---

## 📄 Licence

Projet académique — École Supérieure de Technologie, Université Mohammed Premier Oujda.  
Usage éducatif uniquement.

