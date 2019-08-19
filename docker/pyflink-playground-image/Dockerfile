###############################################################################
#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
# limitations under the License.
###############################################################################

###############################################################################
# Build Pyflink Playground Image
###############################################################################

# TODO: switch to Zeppelin
# TODO: Update to 1.9.1 for proper process cancellation

FROM openjdk:8-jre

# install Python 2.7, pip, and Jupyter
RUN apt-get update; \
    apt-get -y install python-dev python-setuptools python-pip python-wheel; \
    rm -rf /var/lib/apt/lists/*; \
    python2 -m pip install jupyter

# Get Flink sources and binary distribution and assemble them to a build environment for PyFlink
ENV FLINK_VERSION=1.9.0
ENV FLINK_SRC_FILE_PATH=flink/flink-${FLINK_VERSION}/flink-${FLINK_VERSION}-src.tgz
ENV FLINK_SRC_URL=https://www.apache.org/dyn/closer.cgi?action=download&filename=${FLINK_SRC_FILE_PATH}
ENV FLINK_BIN_FILE_PATH=flink/flink-${FLINK_VERSION}/flink-${FLINK_VERSION}-bin-scala_2.11.tgz
ENV FLINK_BIN_URL=https://www.apache.org/dyn/closer.cgi?action=download&filename=${FLINK_BIN_FILE_PATH}
RUN cd /opt; \
    wget -nv -O flink-src.tgz "$FLINK_SRC_URL"; \
    tar xfz flink-src.tgz; \
    rm flink-src.tgz; \
    mv flink-1.9.0 flink-src; \
    wget -nv -O flink-bin.tgz "$FLINK_BIN_URL"; \
    tar xfz flink-bin.tgz; \
    rm flink-bin.tgz; \
    mv flink-1.9.0 flink-src/build-target

# Install PyFlink and kafka-python
RUN cd /opt/flink-src/flink-python; \ 
    python setup.py sdist bdist_wheel; \
    pip install dist/*.tar.gz; \
    pip install kafka-python; \
    cd /opt; \
    rm -r /opt/flink-src

# Copy Flink connector JARs to PyFlink
COPY jars/* /usr/local/lib/python2.7/dist-packages/pyflink/lib/

RUN mkdir /home/pyflink
WORKDIR /home/pyflink

# Copy Jupyter notebooks
COPY notebooks/* ./

EXPOSE 8888
