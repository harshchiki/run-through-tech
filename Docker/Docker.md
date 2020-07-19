Tutorial - https://docker-curriculum.com/

# Hello-world
> docker run hello-world
1. The Docker client contacted the **Docker daemon**.
2. The Docker daemon pulled the "hello-world" image **from the Docker Hub**. (amd64)
3. The Docker daemon **created a new container** from that image which runs the executable that produces the output you are currently reading.
4. The Docker daemon **streamed that output to the Docker client**, which sent it to your terminal.


# To trigger ubuntu bash
> docker run -it ubuntu bash

# Fetch image from Docker Registry and save on local system - `pull` command
> docker pull busybox

# Note
* Running the run command with the -it flags attaches us to an interactive tty in the container. Now we can run as many commands in the container as we want.




# References
* Docker courses <https://dzone.com/articles/50-useful-docker-tutorials-for-it-professionals-fr>