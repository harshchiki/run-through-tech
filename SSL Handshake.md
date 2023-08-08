Step 1: The SSL/TLS client will send the server a “ClientHello” message that details the client’s configuration settings, including the SSL/TLS version, the cipher suites it supports, the data compression technique it employs, and a string of random data referred to as “client random.”
Step 2: The SSL/TLS server sends back a “ServerHello” message containing its own public key, digital certificate, session identifier, the cryptographic algorithm agreement (selected by the server from the client-supplied list of algorithms), and the “server random”.
Step 3: The client performs authentication by contacting the server’s certificate authority (CA) to validate the web server’s digital certificate. This confirms the authenticity of the web server, thus, establishing trust.
Step 4: During the ClientKeyExchange step, the client extracts the public key from the verified certificate and generates a new random sequence called the premaster secret. The premaster secret is then encrypted using the extracted public key and is sent to the server.
Step 5: The SSL/TLS server decrypts the premaster secret using its private key.
Step 6: Both the client and the server now use the premaster secret to configure a shared secret key.
Step 7: Next, the client sends an encrypted “finished” message using the shared secret key. This message says that the client’s part of the handshake is complete.
Step 8: Finally, an encrypted “finished” message is sent back to the client from the server using the previously agreed shared secret key, which indicates the end of the server’s side of the handshake.
Step 9: Once the SSL/TLS handshake and negotiation is done, the server and the client communication continues, i.e., they begin to share files and messages using the session keys (symmetric encryption).
