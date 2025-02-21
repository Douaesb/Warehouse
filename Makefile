APP_NAME=warehouse-app
DB_NAME=warehouse-db
DOCKER_COMPOSE=docker-compose
JAR_FILE=target/Warehouse-0.0.1-SNAPSHOT.jar

build:
	docker build -t $(APP_NAME) .

up:
	$(DOCKER_COMPOSE) up -d --build

down:
	$(DOCKER_COMPOSE) down

restart:
	$(DOCKER_COMPOSE) down && $(DOCKER_COMPOSE) up -d --build

logs:
	$(DOCKER_COMPOSE) logs -f

clean:
	mvn clean
	rm -rf target

package:
	mvn clean package -DskipTests

test:
	mvn test

ps:
	$(DOCKER_COMPOSE) ps

help:
	@echo "Makefile Commands:"
	@echo "  build       - Build the Docker image"
	@echo "  up          - Start the application with Docker Compose"
	@echo "  down        - Stop and remove containers"
	@echo "  restart     - Restart the application"
	@echo "  logs        - Show container logs"
	@echo "  clean       - Remove build artifacts"
	@echo "  package     - Build the JAR file with Maven"
	@echo "  test         - Run the tests with Maven"
	@echo "  ps          - Show running containers"
