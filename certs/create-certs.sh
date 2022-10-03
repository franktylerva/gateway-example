#! /bin/bash

cfssl gencert -initca ca-csr.json | cfssljson -bare ca

cfssl gencert -ca=ca.pem -ca-key=ca-key.pem -config=ca-config.json \
    -profile=kubernetes bob-csr.json | cfssljson -bare bob

cfssl gencert -ca=ca.pem -ca-key=ca-key.pem -config=ca-config.json \
  -profile=kubernetes auth-server-csr.json | cfssljson -bare auth-server

cfssl gencert -ca=ca.pem -ca-key=ca-key.pem -config=ca-config.json \
  -profile=kubernetes api-gateway-csr.json | cfssljson -bare api-gateway

cfssl gencert -ca=ca.pem -ca-key=ca-key.pem -config=ca-config.json \
  -profile=kubernetes resource-server-csr.json | cfssljson -bare resource-server