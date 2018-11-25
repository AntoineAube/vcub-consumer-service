# VCub consumer service

A simple webservice which queries the VCub API and returns the nearest VLS stations.

# Build & run

You need a vCub API key to make the service work.

* Build the service and the associated Docker image with ```./build.sh```.
* Run the Docker image : ```docker run -e API_KEY=[your API key] -p [your port]:8080 antoineaube/vcs:latest```

# Usage

One route to get the nearest stations:

```GET /bicycles/stations?latitude=[...]&longitude=[...]```

Example: ```GET http://localhost:8080/vcs/bicycles/stations?latitude=40.0&longitude=-0.4```

## References

* [WFS 1.1.0 specification](http://portal.opengeospatial.org/files/?artifact_id=8339)
* [WFS on geoserver.org](https://docs.geoserver.org/latest/en/user/services/wfs/reference.html)
