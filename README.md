# MultithreadedWebServer
Implementação em Java de um servidor web multithread para a disciplina Programação Paralela e Distribuída da Universidade Federal de Lavras.

É recomendável a execução da aplicação em ambiente GNU / Linux, pois todos os testes foram realizados neste ambiente.

Baixe o arquivo "MultithreadedWebServer-master.zip" e o descompacte em uma pasta de sua preferência

1) Execute o terminal do sistema operacional na pasta do projeto.

2) Acesse a pasta dist:
>>> cd dist

3) Execute o executável jar
>>> java -jar multithreaded-web-server.jar

Por padrão a aplicação será executada na porta 8008 com um pool de threads com 3 threads e uma fila de tarefas com a capacidade máxima de 10 tarefas, porém os parâmetros podem ser ajustados. Os parâmetros abaixo podem ajustar a execução do código, caso seja utilizado apenas um parâmetros ficam por padrão:

-p  ----  Ajusta a porta de execução
-s  ----  Define o número de Threads
-c  ----  Capacidade máxima da fila de threads. 
-h  ----  Lista os comandos

Exemplos:

java -jar multithreaded-web-server.jar -p 9005 ->> A aplicação é executada na porta 9005 com um pool de 3 threads e uma fila de tarefas com a capacidade máxima de 10 tarefas. 

java -jar multithreaded-web-server.jar -p 9005 -s 8 - c 20 ->>   A aplicação é executada na porta 9005 com um pool de 8 threads e uma fila de tarefas com a capacidade máxima de 20 tarefas. 


A aplicação estará disponível na porta especificada, basta acessar no seu navegador de preferência:

http://127.0.0.1:<porta>/, substituindo <porta> pela porta específicada
