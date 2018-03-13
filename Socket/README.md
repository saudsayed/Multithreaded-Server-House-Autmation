# Multi-threaded (e.g. file-based) Web Server
A multi-threaded (e.g. file-based) `web server` with thread-pooling implemented in Java.

## Request Flow
1. Server waits for a request.
2. Server receives a request at `port :8080`
3. Server creates a Socket (Runnable) for this request.
4. ClientController parses the request into a `HttpRequest`.
5. ClientController parses the request and extract the FileName from the `HttpRequest`.
6. ClientController reads file content from the `/root` folder from the server and writes it as a response.
7. ClientController closes the streams.
8. ClientController closes the Socket.

## Execution
1. Compile all the files in `Socket` folder.
2. Run the `Server.class` file, which will start the server and open the `Socket` for the Client
3. From the browser type the URL `localhost:8080/fileName` or `127.0.0.1/fileName`. 
