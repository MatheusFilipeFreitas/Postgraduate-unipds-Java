# Serialization

Java object serialization to binary files.

## Key concepts

- Serializable interface
- ObjectOutputStream / ObjectInputStream
- Pix domain object

## Run

```bash
cd modules/serialization
mvn exec:java -Dexec.mainClass=com.mathffreitas.serialization.Main
```

## Notes

Writes and reads `target/pix.ser`.
