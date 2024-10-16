# API

## Auth

```bash
curl -X POST -i \
  --header "Content-Type: application/json" \
  --data '{"username":"mario1","password":"mario1p"}' \
  http://localhost:8080/auth/create-token
```

```bash
curl -X GET -i \
  --header "Content-Type: application/json" \
  --header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzI4OTUyNDU0LCJleHAiOjE3Mjg5NTQyNTR9.zvzXiRk9cp-cvGMPNOOILY9qyakwKqMJYnE7Nyu35U0" \
  http://localhost:8080/auth/protected
```

