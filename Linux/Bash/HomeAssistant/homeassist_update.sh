#!/bin/bash
#Update home assistant

#Update the container
sudo docker pull ghcr.io/home-assistant/home-assistant:stable

#Stop the container
sudo docker stop homeassistant

#Remove the container
sudo docker rm homeassistant

#Start the container
sudo docker run -d   --name homeassistant   --privileged   --restart=unless-stopped   -e TZ=America/Chicago   -v /root:/config   --network=host   ghcr.io/home-assistant/home-assistant:stable