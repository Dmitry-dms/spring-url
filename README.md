
# Basic implementation of the URL shortener service

The server is based on the Spring framework with Redis storage.



## Usage

Send a GET request with the "url" parameter to get the shortened URL.
```bash
  http://localhost:8080/?url=https://google.com/
```

To use a shortened link, send a GET request with a shortened URL path.

```bash
  http://localhost:8080/Mw=00bw
```
