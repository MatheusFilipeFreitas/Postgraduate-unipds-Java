# OOP Patterns

Design patterns applied to a menu/product domain with file I/O.

## Key concepts

- Factory pattern (ReaderFactory)
- Builder pattern (ProductBuilder)
- Mapper pattern
- CSV and JSON readers

## Run

```bash
cd modules/oop-patterns
mvn exec:java -Dexec.mainClass=com.mathffreitas.patterns.Main
```

## Notes

Prompts for a file path; use classpath resources like `itens-cardapio.csv`.
