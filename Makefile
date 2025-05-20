.PHONY: run-local reset-local run-staging reset-staging

# Local environment
run-local:
	cp .env.local .env
	docker-compose -f docker-compose.yml -f docker-compose.local.yml up --build

reset-local:
	docker-compose -f docker-compose.yml -f docker-compose.local.yml down -v

# Staging environment
run-staging:
	cp .env.staging .env
	docker-compose -f docker-compose.yml up --build

reset-staging:
	docker-compose -f docker-compose.yml down -v
