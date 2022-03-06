# Sample Docker file
Name: Dockerfile (exactly)
```Dockerfile
FROM openjdk:8-jdk-alpine
LABEL Harsh Chiki <harshchiki@gmail.com>
RUN apk add --no-cache curl tar bash procps
CMD [""]
```

# Build the image
> docker image build -t <some name> .
If everything went well, you should see the following line in your terminal:
`Successfully tagged <somename>:latest`

# Verify that your image has been built
Access image list by
> docker image ls

# Run a container based on the created image
> docker container run -d -r <the chosen name> bash
The container should appear in the list of containers displayed when running the following:
> docker container ps -a 
Output
```
CONTAINER ID   IMAGE                    COMMAND                  CREATED          STATUS                      PORTS                NAMES
09782b01eb60   test-jdk8-maven          "bash"                   19 seconds ago   Up 18 seconds                                    condescending_brown
```
