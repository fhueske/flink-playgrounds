# PyFlink Playground

The Flink operations playground let's you explore and play with [Apache Flink](https://flink.apache.org)'s Python Table API.

**NOTE** The Python Table API is still an experimental feature. 

The playground is based on a [docker-compose](https://docs.docker.com/compose/) environment and super easy to setup.

## Setup

The operations playground requires a custom Docker image in addition to public images for Kafka and ZooKeeper. 

The `docker-compose.yaml` file of the operations playground is located in the `operations-playground` directory. Assuming you are at the root directory of the [`flink-playgrounds`](https://github.com/apache/flink-playgrounds) repository, change to the `pyflink-playground` folder by running

```bash
cd pyflink-playground
```

### Building the custom Docker image

Build the Docker image by running

```bash
docker-compose build
```

### Starting the Playground

Once you built the Docker image, run the following command to start the playground

```bash
docker-compose up -d
```

You can check if the playground was successfully started, if you can access the Jupyter Notebook at [http://localhost:xxxx](http://localhost:xxxx).

### Stopping the Playground

To stop the playground, run the following command

```bash
docker-compose down
```

## Further instructions

The playground setup and more detailed instructions are presented in the
["Getting Started" guide](https://ci.apache.org/projects/flink/flink-docs-release-1.9/getting-started/docker-playgrounds/pyflink-playground.html) of Flink's documentation.