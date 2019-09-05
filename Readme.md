# Coffe Service ☕️
## Spring Boot with Mongo DB and support for Docker compose and K8s 🤓

### Run locally:
* Select the embbeded Mongo DB at build.gradle
* Comment Mongo DB connection at application.yml
* Run from idea or from gradle: `./gradlew bootRun`
* See swagger UI at: `localhost:8080`

### Build image from Dockerfile
* Build the image: `docker image build -t codesolt/boot-mongo:0.0.1 .`
* Ensure the image by running it as: 
`docker container run --name mongoapp -p 8080:8080 -d codesolt/boot-mongo:0.0.1`
* Kill the image: `docker rm -f <container-id>` 

### Run with Docker compose
* Same Mongo config as locally
* Pull Mongo official image: `docker pull mongo`
* Create Docker image with: `./gradlew jibDockerBuild`
* Execute with `docker-compose up --build`
* Ensure delete resources after test with `docker-compose down`

## Export Docker compose with Kompose
* At Docker folder, execute: `kompose convert -f docker-compose.yml`
* Deploy created resources with `kubectl create -f <definition-to-deploy>`
* Ensure delete resources after test with `kubecyl delete -f <definition-to-delete>`

## Deploy created definitions to K8s
* At k8s folder, execute: `kubectl create -f mongo.yml`
* Then: `kubectl create -f app.yml`
* Remove resources as previews step

## Dev deploy with Skaffold
* At K8s folder, execute: `skaffold dev`
* Press `Cmmnd + C` to finish dev delploy

## Remote debugging with K8s
* Create a new debug profile with `remote` option
* Attach it to port 5005 with default idea configs
* Add some brakepoints and start debugging

### Useful commands for Docker Swarm:
* docker-machine create --driver virtualbox swarm-vm1
* docker-machine ssh swarm-vm1
* docker-machine ip swarm-vm1
* docker swarm init --advertise-addr 192.168.99.102
* docker service create --name registry --publish published=5000,target=5000 registry:2
* eval $(docker-machine env swarm-vm1)
* docker stack deploy --compose-file docker-compose.yml spring-mongo-app
* mkdir -p ~/docker/volumes/mongodb

## Contact 📩
* carlos.salazar@codesolt.com
* @chuucksc
* https://carlos-salazar.com/
* https://codesolt.com/

Thanks for reading 👍🏾