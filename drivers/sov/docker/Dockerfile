# Dockerfile for universalregistrar/driver-did-sov

FROM universalregistrar/base-ubuntu
MAINTAINER Markus Sabadello <markus@danubetech.com>

# build dependencies

RUN apt-get -y update && \
    apt-get install -y --no-install-recommends software-properties-common gnupg && \
    apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 68DB5E88 && \
    add-apt-repository "deb https://repo.sovrin.org/sdk/deb xenial stable" && \
    apt-get install -y libindy=1.11.1 && \
    apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

RUN cd /opt/ && \
    git clone https://github.com/hyperledger/indy-sdk.git && \
    cd indy-sdk && \
    git checkout v1.11.1 && \
    cd /opt/indy-sdk/wrappers/java/ && mvn clean install -DskipTests

# build driver-did-sov

ADD . /opt/driver-did-sov
RUN cd /opt/driver-did-sov && mvn clean install package -N -DskipTests

# variables

ENV uniregistrar_driver_did_sov_libIndyPath=
ENV uniregistrar_driver_did_sov_poolConfigs=_;./sovrin/mainnet.txn;staging;./sovrin/stagingnet.txn;builder;./sovrin/buildernet.txn;danube;./sovrin/danube.txn
ENV uniregistrar_driver_did_sov_poolVersions=_;2;staging;2;builder;2;danube;2
ENV uniregistrar_driver_did_sov_walletName=default
ENV uniregistrar_driver_did_sov_trustAnchorSeed=

# done

EXPOSE 8080

RUN chmod a+rx /opt/driver-did-sov/docker/run-driver-did-sov.sh
CMD "/opt/driver-did-sov/docker/run-driver-did-sov.sh"
