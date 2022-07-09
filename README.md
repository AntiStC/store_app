![opensource](https://camo.githubusercontent.com/97d4586afa582b2dcec2fa8ed7c84d02977a21c2dd1578ade6d48ed82296eb10/68747470733a2f2f6261646765732e66726170736f66742e636f6d2f6f732f76312f6f70656e2d736f757263652e7376673f763d313033)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg)
# Warehouse Management API
## Overview
Simple CRUD API application example.
## Getting started
1. Clone project: `https://github.com/AntiStC/store_app.git`

## Developing
### Build with
 - Java
 - Java Servlet API
 - JDBC
 - PostgreSQL
### Prerequisites
### SettingUp Dev
### Building
### Deploying
## Versioning
## Configuration
## API request example 
### Create User 
```
{
  "login": "login_placeholder",
  "password": "password_placeholder",
  "user_details": {
    "first_name": "first_name_placeholder",
    "last_name": "last_name_placeholder",
    "passport_no": "1234",
    "address": "address_placeholder"
  },
  "cargo_list": [
    {
      "name": "name_placeholder_1",
      "description": "description_placeholder_1",
      "cargo_type": "ROUND",
      "cargo_state": "NOT_READY",
      "weight": 11.2,
      "volume": 0.5,
      "created_at": "1986-04-08 12:30",
      "modified_at": "1986-04-08 12:30"
    },
    {
      "name": "name_placeholder_2",
      "description": "description_placeholder_2",
      "cargo_type": "ROUND",
      "cargo_state": "READY",
      "weight": 0.5,
      "volume": 0.1,
      "created_at": "1986-04-08 12:32",
      "modified_at": "1986-04-08 12:32"
    }
  ]
}
```
## Tests
`todo: add examples`
## Database
## Licensing
