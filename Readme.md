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

### Socker Swarm
* Create VM machines with `docker-machine` command (have a Virtual Box app running) 
docker-machine create --driver virtualbox <vm-name>
* Get the IP for the node of the cluster that will be the lead manager:
`docker-machine ip <vm-name>`
* Connect to your designed lead manager node with:
`docker-machine ssh <vm-name>`
* Init the Docker Swarm cluster with the following command:
`docker swarm init --advertise-addr <ip-vm-swarm>`
* See the nodes on your cluster (from a manager) with:
`docker node ls`
* Init a registry on your cluster 
`docker service create --name registry --publish published=5000,target=5000 registry:2`
* Run docker commands from your local on the swarm cluster with:
 `eval $(docker-machine env <vm-swarm>)`
* Push your definition of compose to the cluster with:
`docker-compose push`
* Deploy your services from Docker Compose file with Stack command:
 `docker stack deploy --compose-file docker-compose.yml <stack-name>`
* If needed, create the default folder for the DB you are using (preferly use a volume):
 `mkdir -p ~/docker/volumes/mongodb`

## Contact 📩
* carlos.salazar@codesolt.com
* @chuucksc
* https://carlos-salazar.com/
* https://codesolt.com/

Thanks for reading 👍🏾