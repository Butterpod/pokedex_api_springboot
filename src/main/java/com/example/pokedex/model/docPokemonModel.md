# Modèle `Pokemon`

Cette classe représente un Pokémon en base via **JPA/Hibernate**.

## Mapping JPA → Base de données
- `@Entity` + `@Table(name = "pokemon")` → table `pokemon`
- `@Id` + `@GeneratedValue(IDENTITY)` → clé primaire `id` auto-incrémentée
- Colonnes principales :
    - `name` (**NOT NULL**, **UNIQUE**) : nom du Pokémon
    - `type1` (**NOT NULL**) : type principal
    - `type2` (nullable) : type secondaire
    - `color` (**NOT NULL**) : couleur dominante
    - `hp`, `attack`, `defense`, `speed` : stats (nullable, entiers)

On retrouve les memes noms des données que dans notre BDD car c'est une represantation