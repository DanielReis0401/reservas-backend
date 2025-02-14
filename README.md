# Reservas API

## Prerequisites

- Java 17
- Maven
- Docker

## Getting Started

### Clone the Repository

```sh
git clone <repository-url>
cd <repository-directory>
```

### Build the Application

```sh
mvn clean install
```

### Start the Database

1. Ensure Docker is running.
2. Run the following script to start the MySQL database:

```sh
./startDb.sh
```

### Run the com.reservasapi.Application

```sh
./startApplication.sh
```

The application will start on `http://localhost:8080`.

## API Endpoints

- `POST /reservations` - Create a new reservation
- `GET /reservations/{id}` - Get a reservation by ID
- `PUT /reservations/{id}` - Update a reservation by ID
- `DELETE /reservations/{id}` - Delete a reservation by ID
- `GET /reservations` - List all reservations
- `GET /reservations/client/{customerName}` - Find reservations by customer name

## Stopping the Database

To stop the MySQL container, run:

```sh
docker compose -f mysql.yaml down
```

## License

This project is licensed under the MIT License.