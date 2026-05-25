# File Reading

Comparing java.io, NIO, and Files.readAllLines performance.

## Key concepts

- BufferedReader vs FileChannel
- ByteBuffer reads
- Timing benchmarks

## Run

```bash
cd modules/file-reading
mvn exec:java -Dexec.mainClass=com.mathffreitas.filereading.Main
```

## Notes

Requires `target/benchmark.txt`. Run file-writing first, or it falls back to `../file-writing/target/benchmark.txt`.
