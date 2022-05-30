# mini-autorizador
Projeto responsável por autorizar transações

# Docker

subir o container docker
- sudo docker-compose up

Identificando o ip do docker para acessar o banco
- Descobrindo id do container
    - sudo docker-compose up 
- Com id do container em mãos execute o proximo comando
  - sudo docker inspect id_do_container | grep "IPAddress"
- O comando acima ira mostrar o ip do container docker que ira ajudar a confiugrar o banco de dados Mysql