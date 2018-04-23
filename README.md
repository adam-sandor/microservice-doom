# Kube-DOOM 

Finally the original DOOM has been reimplemented using a modern microservice architecture running on the Kubernetes
container platform...

... well that might be a bit of an overstatement. The purpose of this application made up of 3 microservice is to
demonstrate building a Java application and running it on Kubernetes. The DOOM part is just for kicks. Go ahead
and shoot some demons, but don't expect the thrills of the original until I put at least a 10000 more hours into it.

## How does it work?

This is not one of those demos where every demon represents a Pod, so you can demonstrate fault tolerance by shooting
them. I tried to go for a more realistic architecture with one service holding the state of the game world,
another acting as the game engine and a third one being the client facing the player.

* doom-state: Holds the state of the world, basically health of each demon and the player.
* doom-engine: Makes changes to the game world, like player shooting demons or demons attacking the player.
* doom-client: The client displaying the game to the player and allowing actions to be taken by the player.

## TODOs

* Add database to hold the game state
* Engine should be updating demons in the background, so they attack the player
* UI should be refreshing using ajax calls, or websockets.