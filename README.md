```
openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout certs/rootCA.key -out certs/rootCA.crt

openssl req -new -newkey rsa:4096 -keyout certs/tfrank-a01.vmware.com.key -out certs/tfrank-a01.vmware.com.csr

cat << EOF > certs/tfrank-a01.vmware.com.ext
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
subjectAltName = @alt_names
[alt_names]
DNS.1 = localhost
EOF

openssl x509 -req -CA certs/rootCA.crt -CAkey certs/rootCA.key -in certs/tfrank-a01.vmware.com.csr -out certs/tfrank-a01.vmware.com.crt -days 365 -CAcreateserial -extfile certs/web-tyler-com.ext

openssl x509 -in certs/localhost.crt -text

openssl pkcs12 -export -out certs/tfrank-a01.vmware.com.p12 -name "localhost" -inkey certs/tfrank-a01.vmware.com.key -in certs/tfrank-a01.vmware.com.crt

keytool -importkeystore -srckeystore certs/web-tyler-com.p12 -srcstoretype PKCS12 -destkeystore certs/web-tyler-com.jks -deststoretype JKS

keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks

keytool -import -trustcacerts -noprompt -alias ca -ext san=dns:localhost,ip:127.0.0.1 -file rootCA.crt -keystore truststore.jks

openssl req -new -newkey rsa:4096 -nodes -keyout certs/clientBob.key -out certs/clientBob.csr
openssl x509 -req -CA certs/rootCA.crt -CAkey certs/rootCA.key -in certs/clientBob.csr -out certs/clientBob.crt -days 365 -CAcreateserial
openssl pkcs12 -export -out certs/clientBob.p12 -name "clientBob" -inkey certs/clientBob.key -in certs/clientBob.crt

```