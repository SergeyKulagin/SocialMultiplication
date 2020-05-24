# multiarch build

### nfs provisioner
``DOCKER_BUILD_KIT=1 DOCKER_CLI_EXPERIMENTAL=enabled docker buildx build --platform linux/arm64 --tag sergeykulagin/nfs-provisioner-v2.3.0 --push .``

### prerequisite
docker > 19.03
```shell script
docker run --privileged multiarch/qemu-user-static --reset -p yes
docker buildx rm builder
docker buildx create --name builder --driver docker-container --use
docker buildx inspect --bootstrap
```