container:
	sudo docker ps -a
image:
	sudo docker images
build:
	sudo docker build -t webservice-rest .
run:
	sudo docker run -v $(pwd):/src -v /src/node_modules -d --name webservice-rest-app webservice-rest
