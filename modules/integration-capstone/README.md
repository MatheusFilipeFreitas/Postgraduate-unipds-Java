# Integration Capstone

Full integration exercise combining HTTP, sockets, Gson, and audit history.

## Key concepts

- Embedded HttpServer
- HttpClient and raw Socket I/O
- Gson JSON export
- WeakHashMap audit history

## Run

```bash
cd modules/integration-capstone
mvn exec:java -Dexec.mainClass=com.mathffreitas.integration.Main
```

## Notes

Additional entry points: GenerateJSONData, HttpServerMain, HttpClientMain, ServerWithSocket, ClientWithSocket.
